package com.example.thecallerapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contacts.class},version = 1)
public abstract class ContactDatabase extends RoomDatabase {

    //Singleton Pattern - to provide single Database & optimise Resources
    private  static ContactDatabase dbInstance;

    public  static synchronized ContactDatabase getInstance(Context context){
        if(dbInstance == null){
            dbInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ContactDatabase.class,
                    "contacts_db"
            ).fallbackToDestructiveMigration().build();
        }
        return dbInstance;
    }

    public abstract ContactDAO getContactsDAO();
}
