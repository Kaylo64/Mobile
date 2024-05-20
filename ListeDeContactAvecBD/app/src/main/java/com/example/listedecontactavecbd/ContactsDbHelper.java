package com.example.listedecontactavecbd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactsDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "contacts_db";
    private static final int DATABASE_VERSION = 1;
    public ContactsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + Contact.ContactEntry.TABLE_NAME + " (" +
                        Contact.ContactEntry._ID + " INTEGER PRIMARY KEY," +
                        Contact.ContactEntry.COLUMN_NAME + " TEXT," +
                        Contact.ContactEntry.COLUMN_FIRSTNAME + " TEXT," +
                        Contact.ContactEntry.COLUMN_EMAIL + " TEXT," +
                        Contact.ContactEntry.COLUMN_PHONE_NUMBER + " TEXT)";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
}
