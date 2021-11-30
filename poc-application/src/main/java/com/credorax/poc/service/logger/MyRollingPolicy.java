package com.credorax.poc.service.logger;

import ch.qos.logback.core.rolling.RolloverFailure;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.rolling.helper.ArchiveRemover;
import ch.qos.logback.core.rolling.helper.CompressionMode;
import ch.qos.logback.core.rolling.helper.Compressor;
import ch.qos.logback.core.rolling.helper.RenameUtil;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MyRollingPolicy extends TimeBasedRollingPolicy {

    private static Field compressionFuture;
    private static Field cleanUpFuture;
    private static SimpleDateFormat sdf;

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
                    if (file.getName().endsWith(".log") && !file.getAbsoluteFile().getAbsolutePath().endsWith(getActiveFileName())) {
                        compressor.compress(
                                file.getAbsoluteFile().getAbsolutePath(),
                                file.getAbsoluteFile().getParentFile().getAbsolutePath() + File.separatorChar + generateArchiveName(file.getName(), file) + ".gz",
                                file.getAbsoluteFile().getAbsolutePath());
                    }
                }
            }
        }
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
     *
     * @param name
     * @param optionalFile
     * @return
     */
    private String generateArchiveName(String name, File optionalFile) {
        File file = new File(name);
        if (optionalFile != null) {
            file = optionalFile;
        }
        long modifiedTime = file.lastModified();
        Date modifiedDate = new Date(modifiedTime);
        String modifiedDateText = sdf.format(modifiedDate);

        StringBuilder newName = new StringBuilder();
        newName.append(name.substring(0, name.indexOf("#D")));
        newName.append(modifiedDateText);
        newName.append(name.substring(name.indexOf("D#") + 2));
        return newName.toString();
    }
}
