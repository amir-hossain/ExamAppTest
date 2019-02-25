package com.example.amir.examtestapp;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

public class DataBase {
    private static DataBaseHelper dataBaseHelper;
    private static Migration migration_1_2=new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
        }
    };
    public static Dao getDao(Context context){
        if(dataBaseHelper==null){
            dataBaseHelper= Room.databaseBuilder(context,DataBaseHelper.class,"user_db")
                    .addMigrations(migration_1_2)
                    .build();
        }
        return dataBaseHelper.getDao();
    }
}
