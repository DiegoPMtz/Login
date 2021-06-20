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
    public void adduser(User user);

    @Query("SELECT * FROM users WHERE matriculas=(:matricula) and password=(:password)")
    public User login(int matricula,String password);

    @Query("SELECT * FROM users WHERE matriculas=(:matricula)")
    public User check(int matricula);

    


}
