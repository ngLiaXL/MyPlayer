package com.ngliaxl.myplayer.persistence.dao;


import androidx.room.RawQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

public interface BaseDao {

    @RawQuery
    String rawQueryString(SupportSQLiteQuery query);

    @RawQuery
    int rawQueryInt(SupportSQLiteQuery query);

}
