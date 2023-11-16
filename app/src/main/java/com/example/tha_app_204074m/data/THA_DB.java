package com.example.tha_app_204074m.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.tha_app_204074m.data.dao.ItemInfoDao;
import com.example.tha_app_204074m.data.entity.ItemInfo;

@Database(entities = {ItemInfo.class}, version = 1,exportSchema = false)
public abstract class THA_DB extends RoomDatabase {

    private static THA_DB DB;
    public abstract ItemInfoDao Dao();

    public static synchronized THA_DB getInstance(Context context) {
        if (DB == null) {
            DB = Room.databaseBuilder(context.getApplicationContext(),
                            THA_DB.class, "THA_DB")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
        }
        return DB;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(DB).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(THA_DB instance) {
            ItemInfoDao dao = instance.Dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
