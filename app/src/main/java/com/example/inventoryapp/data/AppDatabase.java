package com.example.inventoryapp.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.inventoryapp.Item;

@Database(entities = {Item.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract ItemDao itemDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ItemDao itemDao;

        private PopulateDbAsyncTask(AppDatabase db) {
            itemDao = db.itemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            itemDao.insert(new Item("MacBook Pro", 3000, 1, "Apple Co",
                    "https://www.google.com/search?q=macbook+pictures&tbm=isch&hl=en&safe=strict&chips=q:macbook+pictures,g_1:apple:rRavkOuxKZY%3D&rlz=1C1GGRV_enKG897KG897&safe=strict&hl=en&sa=X&ved=2ahUKEwiB7Pz6j6frAhVV6CoKHYVOAyoQ4lYoBnoECAEQIA&biw=1349&bih=625#imgrc=raYyHHd3omhLpM"));
            itemDao.insert(new Item(" NoteBook 9 Pro", 1200, 2, "Samsung",
                    "https://www.google.com/search?q=samsung+laptop&tbm=isch&ved=2ahUKEwjlh6qAkKfrAhWWwyoKHToZB7AQ2-cCegQIABAA&oq=samsung+&gs_lcp=CgNpbWcQARgBMgQIABBDMgQIABBDMgQIABBDMgQIABBDMgIIADIECAAQQzIECAAQQzICCAAyBAgAEEMyBAgAEEM6BAgjECdQp-oDWP70A2CmjARoAHAAeACAAaMBiAHOCJIBAzAuOJgBAKABAaoBC2d3cy13aXotaW1nwAEB&sclient=img&ei=yAc9X-WDPJaHqwG6spyACw&bih=625&biw=1349&rlz=1C1GGRV_enKG897KG897&safe=strict&hl=en&hl=en#imgrc=PcJLgUA5XoJp-M"));
            itemDao.insert(new Item("T5 Wireless In-Ear Earphones", 75, 3, "Klipsch",
                    "https://www.google.com/search?q=earphones&tbm=isch&ved=2ahUKEwjryZOhkKfrAhUC86YKHcD1AIQQ2-cCegQIABAA&oq=earphones&gs_lcp=CgNpbWcQAzICCAAyBAgAEEMyAggAMgIIADICCAAyAggAMgIIADICCAAyAggAMgIIADoECCMQJ1C-sARYsc0EYM3OBGgAcAB4AIABygGIAYsKkgEFMC44LjGYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=DQg9X6uPMoLmmwXA64OgCA&bih=625&biw=1349&rlz=1C1GGRV_enKG897KG897&safe=strict&hl=en&hl=en#imgrc=9HK4iogfuUB8pM"));
            return null;
        }
    }
}
