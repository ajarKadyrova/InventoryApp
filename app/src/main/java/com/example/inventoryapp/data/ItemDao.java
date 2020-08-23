package com.example.inventoryapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.inventoryapp.Item;

import java.util.List;

@Dao
public interface ItemDao {
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    void insert(Item item);

    @Update (onConflict = OnConflictStrategy.REPLACE)
    void update(Item item);

    @Query("SELECT * FROM items_table")
    List<Item> getAllNotes();

    @Query("DELETE FROM items_table")
    void deleteAllNotes();

    @Query("DELETE FROM items_table WHERE mId = :id")
    void deleteItem(int id);
}
