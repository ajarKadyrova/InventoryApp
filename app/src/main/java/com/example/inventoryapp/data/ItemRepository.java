package com.example.inventoryapp.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.inventoryapp.Item;

import java.util.List;

public class ItemRepository {

    private ItemDao itemDao;
    private LiveData<List<Item>> allNotes;
    public ItemRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        itemDao = database.itemDao();
        allNotes = itemDao.getAllNotes();
    }
    public void insert(Item item) {
        new InsertNoteAsyncTask(itemDao).execute(item);
    }
    public void update(Item item) {
        new UpdateNoteAsyncTask(itemDao).execute(item);
    }

    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(itemDao).execute();
    }

    public void deleteItem(int id){
        new DeleteAsyncTask(itemDao).execute(id);
    }
    public LiveData<List<Item>> getAllNotes() {
        return allNotes;
    }
    private static class InsertNoteAsyncTask extends AsyncTask<Item, Void, Void> {
        private ItemDao itemDao;
        private InsertNoteAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }
        @Override
        protected Void doInBackground(Item... items) {
            itemDao.insert(items[0]);
            return null;
        }
    }
    private static class UpdateNoteAsyncTask extends AsyncTask<Item, Void, Void> {
        private ItemDao itemDao;
        private UpdateNoteAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }
        @Override
        protected Void doInBackground(Item... items) {
            itemDao.update(items[0]);
            return null;
        }
    }


    private static class DeleteAsyncTask extends AsyncTask<Integer, Void, Void>{
        private ItemDao itemDao;
        private DeleteAsyncTask(ItemDao itemDao){
            this.itemDao = itemDao;
        }


        @Override
        protected Void doInBackground(Integer... integers) {
            itemDao.deleteItem(integers[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private ItemDao itemDao;
        private DeleteAllNotesAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            itemDao.deleteAllNotes();
            return null;
        }
    }
}
