package com.example.login;

import androidx.annotation.RequiresPermission;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyDao {

    @Insert
    public void adduser(User user);


}
