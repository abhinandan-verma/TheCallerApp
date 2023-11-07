package com.example.thecallerapp;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Repository {

    private  final ContactDAO contactDAO;
    ExecutorService executorService ;
    Handler handler;

    public Repository(Application application) {
    ContactDatabase contactDatabase = ContactDatabase.getInstance(application);
    this.contactDAO = contactDatabase.getContactsDAO();

        executorService = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());

    }

    //Methods in DAO being executed from Repository
    public void addContact(Contacts contact){



        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.insert(contact);
            }
        });
    }
    public void deleteContact(Contacts contact){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.delete(contact);
            }
        });
    }
    public LiveData<List<Contacts>> getAllContacts(){
        return contactDAO.getAllContacts();
    }
}
