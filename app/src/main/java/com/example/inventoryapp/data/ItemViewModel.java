package com.example.inventoryapp.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.inventoryapp.Item;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {

    private ItemRepository repository;
    private LiveData<List<Item>> allNotes;
    public ItemViewModel(@NonNull Application application) {
        super(application);
        repository = new ItemRepository(application);
        allNotes = repository.getAllNotes();
    }
    public void insert(Item item) {
        repository.insert(item);
    }
    public void update(Item item) {
        repository.update(item);
    }
    public void delete(Item item) {
        repository.delete(item);
    }
    public void deleteAllItems() {
        repository.deleteAllNotes();
    }
    public LiveData<List<Item>> getAllItems() {
        return allNotes;
    }
}
