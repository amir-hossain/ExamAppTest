package com.example.amir.examtestapp;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

@android.arch.persistence.room.Dao
public interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);
    @Query("select count(*) from user")
    int total();
}
