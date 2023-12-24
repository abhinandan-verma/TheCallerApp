package com.example.thecallerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.thecallerapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //DataSource
    private  ContactDatabase contactDatabase;
    private ArrayList<Contacts> contactsArrayList = new ArrayList<>();

    //Adapter
    private  MyAdapter myAdapter;

    //Binding
    private ActivityMainBinding mainBinding;
    private  MainActivityClickHandlers clickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        clickHandlers = new MainActivityClickHandlers(this);

        mainBinding.setClickHandler(clickHandlers);

        //RecyclerView
        RecyclerView recyclerView = mainBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //Database
        contactDatabase = ContactDatabase.getInstance(this);

        //ViewModel
        MyViewModel viewModel =
                new ViewModelProvider(this).get(MyViewModel.class);

        //Loading the Data from
        viewModel.getAllContacts().observe(this,
                new Observer<List<Contacts>>() {
                    @Override
                    public void onChanged(List<Contacts> contacts) {

                        contactsArrayList.clear();

                        for(Contacts c: contacts){
                            Log.v("TAGY",c.getName());
                            contactsArrayList.add(c);
                        }

                        myAdapter.notifyDataSetChanged();
                    }
                });
        //Adapter
        myAdapter = new MyAdapter(contactsArrayList);

        //Linking the RecyclerView with the Adapter
        recyclerView.setAdapter(myAdapter);

        //Swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            //If you swipe the item to the Left
                Contacts c = contactsArrayList.get(viewHolder.getAdapterPosition());

                viewModel.deleteContact(c);
            }
        }).attachToRecyclerView(recyclerView);
    }
}