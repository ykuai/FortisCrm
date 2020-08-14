package com.fortis.crm.android.data;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.fortis.crm.android.MyApplicationContext;
import com.fortis.crm.android.data.dao.CustomerDao;
import com.fortis.crm.android.data.entity.Customer;

@Database(entities = {Customer.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase db;

    public static AppDatabase getDatabase() {
        if (db == null) {
            db = Room.databaseBuilder(MyApplicationContext.getContext(), AppDatabase.class, "database-name").build();
        }
        return db;
    }

    public abstract CustomerDao customerDao();
}
