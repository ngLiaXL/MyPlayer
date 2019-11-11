package com.ngliaxl.myplayer.persistence.datasource;

import android.content.Context;

import com.ngliaxl.myplayer.MainApp;
import com.ngliaxl.myplayer.persistence.Database;
import com.ngliaxl.myplayer.persistence.exception.RoomException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


public class DataSourceFactory {

    private static Map<String, DataSource> holder = new HashMap<>();


    public static <T extends DataSource> T provide(Class<T> clazz) {
        return provide(clazz, MainApp.getContext());
    }


    @SuppressWarnings("unchecked")
    public static <T extends DataSource> T provide(Class<T> clazz, Context context) {
        if (holder.containsKey(clazz.getName())) {
            return (T) holder.get(clazz.getName());
        } else {
            T t = invoke(clazz, context);
            holder.put(clazz.getName(), t);
            return t;
        }
    }

    private static <T extends DataSource> T invoke(Class<T> clazz, Context
            context) {
        T source = null;
        try {
            Constructor<T> constructor = clazz.getConstructor();
            source = constructor.newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException |
                InvocationTargetException e) {
            e.printStackTrace();
        }
        return setDatabases(source, context);
    }

    private static <T extends DataSource> T setDatabases(T dataSource, Context context) {
        if (dataSource == null) {
            throw new RoomException("Data source invoke error");
        }
        dataSource.setDatabase(Database.getInstance(context));
        return dataSource;
    }


}
