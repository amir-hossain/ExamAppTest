package com.example.amir.examtestapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
@Database(entities = {User.class},version = 2)
public abstract class DataBaseHelper extends RoomDatabase {
    public abstract Dao getDao();
}
