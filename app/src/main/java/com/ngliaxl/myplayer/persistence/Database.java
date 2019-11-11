package com.ngliaxl.myplayer.persistence;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.ngliaxl.myplayer.persistence.converter.DateConverter;
import com.ngliaxl.myplayer.persistence.dao.MediaDao;
import com.ngliaxl.myplayer.persistence.tables.MediaT;


@androidx.room.Database(
        entities = {
                MediaT.class,

        },
        version = 1)
@TypeConverters(DateConverter.class)
public abstract class Database extends RoomDatabase {

    private static final String DB_NAME = "player.db";
    private static volatile Database INSTANCE;


    public abstract MediaDao mediaDao();


    //    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(SupportSQLiteDatabase mDatabase) {
//            mDatabase.execSQL("ALTER TABLE DIS_SHOP_REMOVE_GOODS_T "
//                    + " ADD COLUMN OPER_GOODS_TYPE VARCHAR");
//        }
//    };

    public static Database getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Database.class, DB_NAME)
                            .allowMainThreadQueries()
                            .build();
//                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Database.class, "retail.db")
//                            .addMigrations(MIGRATION_1_2).build();
                }
            }
        }
        return INSTANCE;
    }
}
