package com.mydoctor.customer.utils;

import android.os.Environment;
import android.util.Log;

import com.mydoctor.customer.utils.database.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;

public class Logger {

    public static final String LOG_DIR = "/" + Config.APPLICATION_ID;
    public static final String LOG_FILE_NAME = "/" + Config.APPLICATION_ID + "_my_doctor_log.txt";
    private static final String APP_ID = "MYDOCTOR";
    private static final boolean WRITE_LOGS_TO_FILE = false;
    private static final int LOG_LEVEL_VERBOSE = 4;
    private static final int LOG_LEVEL_DEBUG = 3;
    private static final int LOG_LEVEL_INFO = 2;
    private static final int LOG_LEVEL_ERROR = 1;
    private static final int LOG_LEVEL_OFF = 0;
    private static final int CURRENT_LOG_LEVEL = LOG_LEVEL_ERROR;

    private Logger() {
        throw new IllegalStateException("Logger class");
    }

    private static void log(String message, int logLevel) {
        if (logLevel > CURRENT_LOG_LEVEL) {
            return;
        }
        Log.v(APP_ID, message);
        if (WRITE_LOGS_TO_FILE) {
            writeToFile(message);
        }
    }


    private static void writeToFile(String message) {
        File sdCard = Environment.getExternalStorageDirectory();
        File dir = FileUtils.getFile(sdCard.getAbsolutePath() + LOG_DIR);
        dir.mkdir();
        File file = FileUtils.getFile(dir, LOG_FILE_NAME);

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(
                new FileWriter(file, true), 8 * 1024))) {
            writer.println(APP_ID + " " + new Date().toString() + " : "
                    + message);
        } catch (Exception e) {
            Logger.debug(e.getMessage());
        }
    }

    public static void logOff(String message) {
        log(message, LOG_LEVEL_OFF);
    }

    public static void verbose(String message) {
        log(message, LOG_LEVEL_VERBOSE);
    }

    public static void debug(String message) {
        log(message, LOG_LEVEL_DEBUG);
    }

    public static void error(String message) {
        log(message, LOG_LEVEL_ERROR);
    }

    public static void info(String message) {
        log(message, LOG_LEVEL_INFO);
    }

    public static void write(String message) {
        logWriteToFile(message);
    }

    private static void logWriteToFile(String message) {

        writeToFile(message);


    }


}
