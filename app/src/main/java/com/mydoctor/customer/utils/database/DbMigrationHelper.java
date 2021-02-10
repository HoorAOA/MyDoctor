package com.mydoctor.customer.utils.database;

import android.content.Context;

import com.mydoctor.customer.utils.Logger;

import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

class DbMigrationHelper {

    private final String dbMigrationFilePrefix;
    private final String dbMigrationFileSuffix;
    private final Context context;

    private final SQLiteDatabase writableDb;

    public DbMigrationHelper(SQLiteDatabase writableDb, Context context,
                             String dbMigrationFilePrefix, String dbMigrationFileSuffix) {
        this.writableDb = writableDb;
        this.context = context;
        this.dbMigrationFilePrefix = dbMigrationFilePrefix;
        this.dbMigrationFileSuffix = dbMigrationFileSuffix;
    }

    private String fileNameForVersion(int version) {
        return ("" + dbMigrationFilePrefix + version + dbMigrationFileSuffix);
    }


    public OperationResult migrateToDbVersion(int version) {
        try (InputStream inputStream = context.getAssets().open(fileNameForVersion(version))) {
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                inputLine = inputLine.trim();
                if (inputLine.length() == 0) {
                    continue;
                }
                OperationResult operationResult = executeSqlQuery(inputLine);
                if (operationResult != null) {
                    return operationResult;
                }
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return new OperationResult(OperationResult.OPERATION_FAILED,
                    "Unexpected IO Error.");
        }
        return new OperationResult(OperationResult.OPERATION_SUCCESSFUL);
    }


    private OperationResult executeSqlQuery(String inputLine) {
        try {
            writableDb.execSQL(inputLine);
        } catch (SQLException sqlEx) {
            Logger.error(sqlEx.getMessage());
            return new OperationResult(OperationResult.OPERATION_FAILED,
                    "Unexpected Database Error.");
        }
        return null;
    }

    public OperationResult upgradeToVersion(int oldVersion, int newVersion) {
        OperationResult result = null;
        for (int i = oldVersion + 1; i <= newVersion; i++) {
            result = migrateToDbVersion(i);

            // Break the migration even if one operation fails.
            if (result.getResult() != OperationResult.OPERATION_SUCCESSFUL) {
                return result;
            }
        }

        return result;
    }


}
