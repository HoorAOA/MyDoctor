package com.mydoctor.customer.utils.database;

import android.content.Context;
import android.text.TextUtils;

import com.mydoctor.customer.utils.Logger;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_MIGRATION_FILE_SUFFIX = ".sql";
    static int databaseVersion = 1;
    static String databaseName;
    static String dbMigrationFilePrefix;
    private static DbHelper instance;
    private static SQLiteDatabase sqliteDatabase;
    private static Context mContext;

    private OperationResult migrationResult = new OperationResult(
            OperationResult.OPERATION_SUCCESSFUL);


    private DbHelper(Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    /**
     * Fetch the Writable Database instance by verifying the password
     *
     * @param context
     * @return
     */
    public static synchronized SQLiteDatabase getDatabaseInstance(Context context) {

        mContext = context;
        try {

            if (instance == null) {
                instance = new DbHelper(context);
            }

            if (sqliteDatabase == null || !sqliteDatabase.isOpen()) {

                String strPassword = com.mydoctor.customer.utils.database.DbManager.getInstance().fetchPasswordFromStorage(context);
                if (TextUtils.isEmpty(strPassword)) {
                    Logger.debug("Password: " + strPassword);
                    return null;
                }

                sqliteDatabase = instance.getWritableDatabase(strPassword);
            }

            return sqliteDatabase;

        } catch (Exception e) {
            Logger.debug(e.getMessage());
        }
        return null;
    }

    /**
     * Close the Writable Database
     */
    public static synchronized void closeDatabase() {
        if (sqliteDatabase != null && sqliteDatabase.isOpen()) {
            sqliteDatabase.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        DbMigrationHelper migrationHelper = new DbMigrationHelper(db,
                mContext.getApplicationContext(),
                dbMigrationFilePrefix, DB_MIGRATION_FILE_SUFFIX);
        OperationResult result = migrationHelper.migrateToDbVersion(1);
        migrationResult = result;

        int currentRequiredVersion = databaseVersion;

        if (currentRequiredVersion > 1) {
            result = migrationHelper.upgradeToVersion(1, currentRequiredVersion);
            migrationResult = result;
        }

        checkForDBMigrationResult(currentRequiredVersion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        DbMigrationHelper migrationHelper = new DbMigrationHelper(db,
                mContext.getApplicationContext(),
                dbMigrationFilePrefix, DB_MIGRATION_FILE_SUFFIX);
        migrationResult = migrationHelper.upgradeToVersion(oldVersion, newVersion);

        checkForDBMigrationResult(newVersion);
    }

    private void checkForDBMigrationResult(int version) {
        if (migrationResult != null && (migrationResult.getResult() == OperationResult.OPERATION_SUCCESSFUL)) {
            Logger.debug("DB Creation : onUpgrade : Successfully migrated DB with version : " + version);
        } else {
            Logger.debug("DB Creation : onUpgrade : Failed to migrated DB with version : " + version);
        }
    }
}
