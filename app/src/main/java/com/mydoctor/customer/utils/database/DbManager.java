package com.mydoctor.customer.utils.database;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;

import androidx.annotation.RequiresApi;

import com.mydoctor.customer.utils.Logger;
import com.mydoctor.customer.utils.database.crypto.CryptoConstants;
import com.mydoctor.customer.utils.database.crypto.EncryptionUtil;
import com.mydoctor.customer.utils.database.crypto.ExtendedEncryptionUtil;

import net.sqlcipher.database.SQLiteDatabase;

public class DbManager {

    private static com.mydoctor.customer.utils.database.DbManager instance;

    private ExtendedEncryptionUtil extendedEncryptionUtil;

    private DbManager() {
        Logger.debug("default constructor");
    }

    /**
     * Init DbManager
     */

    public static synchronized com.mydoctor.customer.utils.database.DbManager getInstance() {

        if (instance == null) {
            instance = new com.mydoctor.customer.utils.database.DbManager();
        }
        return instance;
    }

    /**
     * Init extendedEncryptionUtil
     */
    private void getEncryptorInstance() {
        if (extendedEncryptionUtil == null) {
            extendedEncryptionUtil = new ExtendedEncryptionUtil();
        }
    }

    /**
     * Initialize the Database libraries &
     * Create a password and use the same for getting access to database.
     */
    public void initializeDatabase(Context mContext, int dbVersion,
                                   String dbName, String dbFileNamePrefix) {

        DbHelper.databaseVersion = dbVersion;
        DbHelper.databaseName = dbName;
        DbHelper.dbMigrationFilePrefix = dbFileNamePrefix;

        SQLiteDatabase.loadLibs(mContext);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            saveEncryptedDataExtended(mContext);
        } else {
            saveEncryptedData(mContext);
        }
    }

    private void saveEncryptedData(Context mContext) {

        try {

            if (!FileUtils.ifFileExists(FileUtils.getInternalFilePath(mContext) + "/" + CryptoConstants.KEY_FILE_NAME)) {

                String password = Base64.encodeToString(EncryptionUtil.generateRandomIV(), Base64.DEFAULT).trim();

                String encryptedPassword = EncryptionUtil.getEncryptedValueFrom(password).trim();

                if (!TextUtils.isEmpty(encryptedPassword)) {

                    FileUtils.writeToFile(encryptedPassword,
                            FileUtils.getInternalFilePath(mContext), CryptoConstants.KEY_FILE_NAME);
                }
            }
        } catch (Exception e) {
            Logger.debug(e.getMessage());
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private void saveEncryptedDataExtended(Context mContext) {
        try {

            if (!FileUtils.ifFileExists(FileUtils.getInternalFilePath(mContext) + "/" + CryptoConstants.KEY_FILE_NAME)) {

                getEncryptorInstance();

                String dbEncryptionKey = KeyGenerationUtil.getRandomKey();

                byte[] dbEncryptionKeyArray = extendedEncryptionUtil.encryptText(dbEncryptionKey);

                if (dbEncryptionKeyArray != null && dbEncryptionKeyArray.length > 0) {

                    FileUtils.writeByteArrayToFile(dbEncryptionKeyArray, FileUtils.getInternalFilePath(mContext),
                            CryptoConstants.KEY_FILE_NAME);
                }

                if (!FileUtils.ifFileExists(FileUtils.getInternalFilePath(mContext) + "/" + CryptoConstants.IV_FILE_NAME)) {

                    byte[] dbEncryptionIvArray = extendedEncryptionUtil.getIv();

                    if (dbEncryptionIvArray != null && dbEncryptionIvArray.length > 0) {

                        FileUtils.writeByteArrayToFile(dbEncryptionIvArray,
                                FileUtils.getInternalFilePath(mContext), CryptoConstants.IV_FILE_NAME);
                    }
                }
            }
        } catch (Exception e) {
            Logger.debug(e.getMessage());
        }
    }

    String fetchPasswordFromStorage(Context context) {

        String strPassword = null;

        try {
            getEncryptorInstance();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                byte[] encryptedInfo = FileUtils.readByteArrayFromFile(
                        FileUtils.getInternalFilePath(context) + "/" + CryptoConstants.KEY_FILE_NAME);
                byte[] iv = FileUtils.readByteArrayFromFile(
                        FileUtils.getInternalFilePath(context) + "/" + CryptoConstants.IV_FILE_NAME);

                if (encryptedInfo != null && encryptedInfo.length > 0 && iv != null && iv.length > 0) {
                    strPassword = extendedEncryptionUtil.decryptData(encryptedInfo, iv);
                }
            } else {
                String encryptedValueFromFile = FileUtils.readStringFromFile(
                        FileUtils.getInternalFilePath(context) + "/" + CryptoConstants.KEY_FILE_NAME);
                strPassword = EncryptionUtil.getDecryptedValueFrom(encryptedValueFromFile);
            }

        } catch (Exception ex) {
            Logger.error(ex.getMessage());
        }
        return strPassword;
    }

}
