package com.example.thecallerapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class AddNewContactClickHandler {

    Contacts  contacts;
    Context context;
    MyViewModel myViewModel;

    public AddNewContactClickHandler(Contacts contacts, Context context,MyViewModel myViewModel) {
        this.contacts = contacts;
        this.context = context;
        this.myViewModel = myViewModel;
    }

    public  void onSubmitBtnClicked(View view){

        if(contacts.getName() == null || contacts.getEmail() == null  ){
            Toast.makeText(context, "This Field can't be Empty", Toast.LENGTH_SHORT).show();
        } else{
            Intent i = new Intent(context, MainActivity.class);
//            i.putExtra("Name",contacts.getName());
//            i.putExtra("Email",contacts.getEmail());
//            i.putExtra("Mobile Number",Integer.toString(contacts.getMob_number()));


            Contacts c = new Contacts(
                    contacts.getName(),contacts.getEmail());

            myViewModel.addNewContact(c);

            context.startActivity(i);
        }
    }
}
