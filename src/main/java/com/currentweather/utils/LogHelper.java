package com.currentweather.utils;

import ch.qos.logback.classic.LoggerContext;
import com.github.sbabcoc.logback.testng.ReporterAppender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogHelper {

    static {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        ReporterAppender reporterAppender = new ReporterAppender();
        reporterAppender.setContext(lc);
        reporterAppender.start();
    }

    public static Logger getLogger() {
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String className = stackTrace[2].getClassName();
        return LoggerFactory.getLogger(className);
    }

    public void error(String var1, Object... var2) {
        getLogger().error(var1, var2);
    }

    public void info(String var1, Object... var2) {
        getLogger().info(var1, var2);
    }

}
