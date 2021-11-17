package com.credorax.poc.service.logger;

import ch.qos.logback.core.rolling.RolloverFailure;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.rolling.helper.ArchiveRemover;
import ch.qos.logback.core.rolling.helper.CompressionMode;
import ch.qos.logback.core.rolling.helper.Compressor;
import ch.qos.logback.core.rolling.helper.RenameUtil;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Date;

public class MyRollingPolicy extends TimeBasedRollingPolicy {

    private static Field compressionFuture;
    private static Field cleanUpFuture;

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
            ReflectionUtils.setField(compressionFuture, this, compressor.asyncCompress(toBeArchivedFileName, toBeArchivedFileName, toBeArchivedFileName));
        }

        if (cleanUpFuture != null && archiveRemover != null) {
            Date now = new Date(getTimeBasedFileNamingAndTriggeringPolicy().getCurrentTime());
            ReflectionUtils.setField(cleanUpFuture, this, archiveRemover.cleanAsynchronously(now));
        }
    }
}
