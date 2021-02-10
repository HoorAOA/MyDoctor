package com.mydoctor.customer.utils.database;

import android.content.Context;

import com.mydoctor.customer.utils.Logger;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FileUtils {

    private FileUtils() {
        // empty constructor
    }

    /**
     * Write Data to a file on the specified location
     *
     * @param message
     * @param path
     * @param fileName
     * @return
     */
    public static void writeToFile(String message, String path, String fileName) {

        try {

            File dir = getFile(path);
            dir.mkdirs();
            File file = getFile(dir, fileName);
            try (PrintWriter writer =
                         new PrintWriter(new BufferedWriter(
                                 new FileWriter(file, true), 8 * 1024))) {
                writer.print(message);
                file.getAbsolutePath();
                writer.flush();
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }

    /**
     * Write Data to a file on the specified location
     *
     * @param data
     * @param path
     * @param fileName
     * @return
     */
    public static void writeByteArrayToFile(byte[] data, String path, String fileName) {
        try {
            File dir = getFile(path);
            dir.mkdirs();

            File file = getFile(dir, fileName);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                try (BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                    bos.write(data);
                    file.getAbsolutePath();

                    bos.flush();
                    fos.flush();
                }
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }


    private static String convertStreamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    /**
     * Read Data from the File
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static String readStringFromFile(String filePath) throws IOException {
        File fl = getFile(filePath);
        FileInputStream fin = new FileInputStream(fl);
        String ret = convertStreamToString(fin);
        //Make sure you close all streams.
        fin.close();
        return ret;
    }

    /**
     * Read Data from the File
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static byte[] readByteArrayFromFile(String filePath) {

        File file = getFile(filePath);

        try {

            try (InputStream inputStream = new FileInputStream(file)) {

                // Get the size of the file
                long length = file.length();

                // You cannot create an array using a long type.
                // It needs to be an int type.
                // Before converting to an int type, check
                // to ensure that file is not larger than Integer.MAX_VALUE.
                if (length > Integer.MAX_VALUE) {
                    throw new IOException("Could not completely read FILE " + file.getName()
                            + " as it is too long (" + length + " bytes, max supported " + Integer.MAX_VALUE + ")");
                }

                // Create the byte array to hold the data
                byte[] bytes = new byte[(int) length];

                // Read in the bytes
                int offset = 0;
                int numRead;
                while (offset < bytes.length && (numRead = inputStream.read(bytes, offset, bytes.length - offset)) >= 0) {
                    offset += numRead;
                }

                // Ensure all the bytes have been read in
                if (offset < bytes.length) {
                    throw new IOException("Could not completely read FILE " + file.getName());
                }

                return bytes;
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
        return new byte[]{};
    }

    /**
     * Check if the file exist at the given file path
     *
     * @param path
     * @return
     */
    public static boolean ifFileExists(String path) {
        File file = getFile(path);
        return file.exists();
    }

    /**
     * Provides internal file path for app
     *
     * @param context
     * @return
     */
    public static String getInternalFilePath(Context context) {
        if (context != null) {
            File externalFileDirectory = context.getFilesDir();
            if (externalFileDirectory != null) {
                return context.getFilesDir().getAbsolutePath();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * No sonar added for security hotspot
     *
     * @param path
     * @return
     */
    public static File getFile(String path) {
        return new File(path);//NOSONAR
    }

    /**
     * No sonar added for security hotspot
     *
     * @param path
     * @return
     */
    public static File getFile(File dir, String path) {
        return new File(dir, path);//NOSONAR
    }


    /**
     * No sonar added for security hotspot
     *
     * @param path
     * @return
     */
    public static FileOutputStream getFileOutputStream(String path) {
        try {
            return new FileOutputStream(path);//NOSONAR
        } catch (Exception e) {
            return null;
        }
    }
}
