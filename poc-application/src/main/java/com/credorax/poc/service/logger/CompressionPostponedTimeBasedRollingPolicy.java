package com.credorax.poc.service.logger;

import ch.qos.logback.core.rolling.RolloverFailure;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.rolling.helper.ArchiveRemover;
import ch.qos.logback.core.rolling.helper.CompressionMode;
import ch.qos.logback.core.rolling.helper.Compressor;
import ch.qos.logback.core.rolling.helper.RenameUtil;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import ch.qos.logback.core.rolling.RolloverFailure;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.rolling.helper.ArchiveRemover;
import ch.qos.logback.core.rolling.helper.CompressionMode;
import ch.qos.logback.core.rolling.helper.Compressor;
import ch.qos.logback.core.rolling.helper.RenameUtil;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * This implementation is fully synchronous.
 * Clean and Compress operations are synchronous.
 * <p>
 * The logic is next:
 * 1. During the startup, it verifies for old log files and tries to archive them.
 * 2. Renaming current log file into the name determined by the policy, but do not compress it.
 * 3. Next step is to compress the file that was created 2 iterations before current activity.
 * <p>
 * When the application stops carefully, this RollingPolicy awaits for latest jobs to be finished.
 */
public class CompressionPostponedTimeBasedRollingPolicy extends TimeBasedRollingPolicy {

    private static Field compressionFuture;
    private static Field cleanUpFuture;
    private static SimpleDateFormat sdf;
    private static final String patternSuffix = ":mm";
    private static final String internalPrefix = "#D";
    private static final String internalPostfix = "D#";

    static {
        Field[] fields = TimeBasedRollingPolicy.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("compressionFuture")) {
                compressionFuture = field;
            } else if (field.getName().equals("cleanUpFuture")) {
                cleanUpFuture = field;
            }
        }
        ReflectionUtils.makeAccessible(compressionFuture);
        ReflectionUtils.makeAccessible(cleanUpFuture);
    }

    private String activeFileName = null;
    private String lastActiveFileName = null;
    private String toBeArchivedFileName = null;
    private RenameUtil renameUtil = new RenameUtil();
    private Compressor compressor;

    @Override
    public void start() {
        super.start();

        renameUtil.setContext(context);

        compressor = new Compressor(getCompressionMode());
        compressor.setContext(context);

        //Extracting the real filePatter from logback.xml
        extractFilePattern();

        //Workaround for new functionality to make old log files to be deleted after the application restarts.
        cleanUpOldLogFiles();
    }

    private void extractFilePattern() {
        String filePattern = getFileNamePattern();
        String pattern = filePattern.substring(filePattern.indexOf("#D%d{") + 5, filePattern.indexOf("}D#"));
        sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    /**
     * Method is used by the logger context during the startup. It finds all unarchived files and archives them.
     */
    private void cleanUpOldLogFiles() {
        String fileName = getActiveFileName();
        File activeFile = new File(fileName);

        if (activeFile.exists()) {
            File absolute = activeFile.getAbsoluteFile();
            File parentDirectory = absolute.getParentFile();
            if (parentDirectory.isDirectory()) {
                for (File file : parentDirectory.listFiles()) {
                    if (verifyFileName(file)) {
                        compressor.compress(
                                file.getAbsoluteFile().getAbsolutePath(),
                                generateArchiveName(file.getAbsolutePath(), file),
                                file.getAbsoluteFile().getAbsolutePath());
                    }
                }
            }
        }
    }

    private boolean verifyFileName(File file) {
        String pattern = getFileNamePattern();
        String fileName = file.getAbsolutePath();
        String subPattern = pattern.substring(0, pattern.indexOf(internalPrefix));
        return file.getName().endsWith(".log") &&
                fileName.contains(subPattern) &&
                !fileName.endsWith(getActiveFileName());
    }

    @Override
    public void stop() {
        CompressionMode mode = getCompressionMode();
        if (lastActiveFileName != null && (CompressionMode.ZIP.equals(mode) || CompressionMode.GZ.equals(mode))) {
            compressor.compress(lastActiveFileName, lastActiveFileName, lastActiveFileName);
        }
        super.stop();
    }

    @Override
    public void rollover() throws RolloverFailure {
        activeFileName = getActiveFileName();
        CompressionMode mode = getCompressionMode();
        ArchiveRemover archiveRemover = getTimeBasedFileNamingAndTriggeringPolicy().getArchiveRemover();
        toBeArchivedFileName = lastActiveFileName;
        lastActiveFileName = getTimeBasedFileNamingAndTriggeringPolicy().getElapsedPeriodsFileName();

        renameUtil.rename(activeFileName, lastActiveFileName);

        if (compressionFuture != null && toBeArchivedFileName != null && (CompressionMode.ZIP.equals(mode) || CompressionMode.GZ.equals(mode))) {
            ReflectionUtils.setField(compressionFuture, this,
                    compressor.asyncCompress(
                            toBeArchivedFileName,
                            generateArchiveName(toBeArchivedFileName, null),
                            toBeArchivedFileName));
        }

        if (cleanUpFuture != null && archiveRemover != null) {
            Date now = new Date(getTimeBasedFileNamingAndTriggeringPolicy().getCurrentTime());
            ReflectionUtils.setField(cleanUpFuture, this, archiveRemover.cleanAsynchronously(now));
        }
    }

    /**
     * Method generates right name based on the FilePattern from logback.xml and the date when log file was modified last time.
     * It uses additional pattern directives to find the pattern using (#D as beginning and D# as end of the pattern)
     * <p>
     * 1. It generates the archive file name
     * 2. Verifies that file does not exist in the file system (location)
     * 3. In case it does (can be happened when the files are created within the same period of rolling period, less than 1 minute for example)
     * 4. Appends additional minutes to the default file name pattern.
     *
     * @param name
     * @param optionalFile
     * @return
     */
    private String generateArchiveName(String name, File optionalFile) {
        String archiveFileName = generateArchiveName(name, optionalFile, null);

        if (Files.exists(Paths.get(archiveFileName))) {
            archiveFileName = generateArchiveName(name, optionalFile, sdf.toPattern() + patternSuffix);
        }

        return archiveFileName;
    }

    private String generateArchiveName(String name, File optionalFile, String alternativePattern) {
        if (name.contains("#D")) {
            File file = new File(name);
            if (optionalFile != null) {
                file = optionalFile;
            }
            long modifiedTime = file.lastModified();
            try {
                BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                modifiedTime = attr.creationTime().toMillis();
            } catch (IOException e) {
            }

            Date modifiedDate = new Date(modifiedTime);
            String modifiedDateText = sdf.format(modifiedDate);

            //In case file already exists, we need to use alternative pattern for GZ files. Default is appending the ":mm" minutes to the end of real pattern.
            if (alternativePattern != null) {
                SimpleDateFormat sdfLocal = new SimpleDateFormat(alternativePattern);
                modifiedDateText = sdfLocal.format(modifiedDate);
            }

            StringBuilder newName = new StringBuilder();
            newName.append(name.substring(0, name.indexOf(internalPrefix)));
            newName.append(modifiedDateText);
            newName.append(name.substring(name.indexOf(internalPostfix) + 2));
            return newName.toString();
        } else {
            return name;
        }
    }
}
