package com.example.ecommerceshoeapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ShopDao {

    @Insert
    void insertrecord(Shop shops);

    @Query("SELECT EXISTS(SELECT * FROM Shop WHERE uid = :Id)")
    Boolean is_exist(int Id);


    @Query("DELETE FROM Shop WHERE uid = :id")
    void deleterecord(int id);

    @Query("SELECT * FROM Shop")
    List<Shop> getallproducts();
}
