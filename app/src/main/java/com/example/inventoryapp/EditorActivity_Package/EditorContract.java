package com.example.inventoryapp.EditorActivity_Package;

import com.example.inventoryapp.Item;

public interface EditorContract {

    interface View{

    }

    interface Presenter{
        void insert(Item item);
        void update(Item item);
        void deleteItem(int id);
    }
}
