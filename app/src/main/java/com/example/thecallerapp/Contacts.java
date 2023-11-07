package com.example.thecallerapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts_table")
public class Contacts {
    @PrimaryKey(autoGenerate = true)
    private String name;
    private String email;
    private int mob_number;


    public Contacts(String name, String email, int mobNumber) {
        this.name = name;
        this.email = email;
        this.mob_number = mobNumber;
    }

    public Contacts() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMob_number() {
        return mob_number;
    }

    public void setMob_number(int mob_number) {
        this.mob_number = mob_number;
    }
}
