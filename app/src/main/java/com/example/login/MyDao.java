package com.example.login;

import androidx.annotation.RequiresPermission;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void adduser(User user);

    @Query("SELECT * FROM users WHERE matriculas=(:matricula) and password=(:password)")
    User login(int matricula, String password);

    @Query("SELECT * FROM users WHERE matriculas=(:matricula)")
    User check(int matricula);

    @Query("SELECT * FROM users WHERE password=(:password)")
    User passwd(String password);

    @Update
    void updateuser(User user);

    


}
