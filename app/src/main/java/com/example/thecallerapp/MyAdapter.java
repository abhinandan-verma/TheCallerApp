package com.example.thecallerapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thecallerapp.databinding.ContactListItemBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ContactViewHolder> {

    private ArrayList<Contacts> contactsArrayList;

    public MyAdapter(ArrayList<Contacts> contactsArrayList) {
        this.contactsArrayList = contactsArrayList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Creating new ViewHolders for items in recyclerView

        ContactListItemBinding contactListItemBinding =
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.contact_list_item,
                        parent,
                        false
                );
        return new ContactViewHolder(contactListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

       Contacts currentContact = contactsArrayList.get(position);

       holder.contactListItemBinding.setContact(currentContact);

    }

    @Override
    public int getItemCount() {
    //determines the total number of Items in the dataset that will be
        //displayed in the recyclerView

        if (contactsArrayList != null){
            return  contactsArrayList.size();
        }else {
            return 0;
        }

    }

    public void setContactsArrayList(ArrayList<Contacts> contactsArrayList) {
        this.contactsArrayList = contactsArrayList;
        //notify  the recyclerView that the underlying
        //dataset has changed
        notifyDataSetChanged();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder{

        private ContactListItemBinding contactListItemBinding;

        public ContactViewHolder(@NonNull ContactListItemBinding contactListItemBinding) {
            super(contactListItemBinding.getRoot());
            this.contactListItemBinding = contactListItemBinding;
        }
    }
}
