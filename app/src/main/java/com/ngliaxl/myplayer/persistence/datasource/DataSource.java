package com.ngliaxl.myplayer.persistence.datasource;


import android.database.SQLException;

import androidx.annotation.NonNull;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ngliaxl.myplayer.persistence.Database;

import java.util.concurrent.Callable;

public class DataSource {


    private Database mDatabase;

    public void setDatabase(@NonNull Database database) {
        this.mDatabase = database;
    }

    public Database database() {
        return mDatabase;
    }

    public void runInTransaction(Runnable body) {
        mDatabase.runInTransaction(body);
    }

    public <V> V runInTransaction(@NonNull Callable<V> body) {
        return mDatabase.runInTransaction(body);
    }

    public void execSQL(String sql, Object[] bindArgs) {
        SupportSQLiteDatabase writableDatabase = mDatabase.getOpenHelper().getWritableDatabase();
        try {
            if (bindArgs == null) {
                writableDatabase.execSQL(sql);
            } else {
                writableDatabase.execSQL(sql, bindArgs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean success(long[] rowIds) {
        if (rowIds == null || rowIds.length == 0) return false;
        for (long rowId : rowIds) {
            if (rowId == -1) return false;
        }
        return true;
    }


}