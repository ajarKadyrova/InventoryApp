package com.example.inventoryapp.EditorActivity_Package;

import com.example.inventoryapp.Item;

public interface EditorContract {

    interface View{

    }

    interface Presenter{
        void insert(Item item);
        void update(int id, String name, Integer price, Integer quantity, String supplier, String picture);
//        void update(Item item);
        void deleteItem(int id);
    }
}
