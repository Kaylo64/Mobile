package com.example.listedecontactavecbd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class ContactsDao {
    private ContactsDbHelper dbHelper;
    public ContactsDao(Context context) {
        dbHelper = new ContactsDbHelper(context);
    }
    public long ajouterContact(Contact contact) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contact.ContactEntry.COLUMN_NAME, contact.getNom());
        values.put(Contact.ContactEntry.COLUMN_FIRSTNAME, contact.getPrenom());
        values.put(Contact.ContactEntry.COLUMN_EMAIL, contact.getCourriel());
        values.put(Contact.ContactEntry.COLUMN_PHONE_NUMBER, contact.getTelephone());

        return db.insert(Contact.ContactEntry.TABLE_NAME, null, values);
    }

    //Sélectionner tous les contacts
    public List<Contact> getAllContacts() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                Contact.ContactEntry._ID,
                Contact.ContactEntry.COLUMN_NAME,
                Contact.ContactEntry.COLUMN_FIRSTNAME,
                Contact.ContactEntry.COLUMN_EMAIL,
                Contact.ContactEntry.COLUMN_PHONE_NUMBER
        };

        String sortOrder = Contact.ContactEntry.COLUMN_NAME + " ASC";
        Cursor cursor = db.query(
                Contact.ContactEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List<Contact> contacts = new ArrayList<>();
        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(Contact.ContactEntry._ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(Contact.ContactEntry.COLUMN_NAME));
            String firstName = cursor.getString(cursor.getColumnIndexOrThrow(Contact.ContactEntry.COLUMN_FIRSTNAME));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(Contact.ContactEntry.COLUMN_EMAIL));
            String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(Contact.ContactEntry.COLUMN_PHONE_NUMBER));
            contacts.add(new Contact(id, name, firstName, email, phoneNumber));
        }
        cursor.close();
        return contacts;
    }

    //Sélectionner tous les contacts
    public Cursor getAllContactsCursor() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                Contact.ContactEntry._ID,
                Contact.ContactEntry.COLUMN_NAME,
                Contact.ContactEntry.COLUMN_FIRSTNAME,
                Contact.ContactEntry.COLUMN_EMAIL,
                Contact.ContactEntry.COLUMN_PHONE_NUMBER
        };
        String sortOrder = Contact.ContactEntry.COLUMN_NAME + " ASC";
        return db.query(
                Contact.ContactEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
    }

    //Modifier un contact
    public int modifierContact(Contact contact) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contact.ContactEntry.COLUMN_NAME, contact.getNom());
        values.put(Contact.ContactEntry.COLUMN_FIRSTNAME, contact.getPrenom());
        values.put(Contact.ContactEntry.COLUMN_EMAIL, contact.getCourriel());
        values.put(Contact.ContactEntry.COLUMN_PHONE_NUMBER, contact.getTelephone());
        String selection = Contact.ContactEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(contact.getId()) };

        return db.update(Contact.ContactEntry.TABLE_NAME, values, selection, selectionArgs);
    }

    //Sélectionner un contact par son id
    public Contact getContactById(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                Contact.ContactEntry._ID,
                Contact.ContactEntry.COLUMN_NAME,
                Contact.ContactEntry.COLUMN_FIRSTNAME,
                Contact.ContactEntry.COLUMN_EMAIL,
                Contact.ContactEntry.COLUMN_PHONE_NUMBER
        };
        String selection = Contact.ContactEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };
        Cursor cursor = db.query(
                Contact.ContactEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        Contact contact = null;
        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(Contact.ContactEntry.COLUMN_NAME));
            String firstName = cursor.getString(cursor.getColumnIndexOrThrow(Contact.ContactEntry.COLUMN_FIRSTNAME));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(Contact.ContactEntry.COLUMN_EMAIL));
            String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(Contact.ContactEntry.COLUMN_PHONE_NUMBER));
            contact = new Contact(id, name, firstName, email, phoneNumber);
        }
        cursor.close();
        return contact;
    }

    //Supprimer un contact
    public int supprimerContact(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = Contact.ContactEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };
        return db.delete(Contact.ContactEntry.TABLE_NAME, selection, selectionArgs);
    }

    public int getContactPosition(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                Contact.ContactEntry._ID
        };
        String sortOrder = Contact.ContactEntry.COLUMN_NAME + " ASC";
        Cursor cursor = db.query(
                Contact.ContactEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        int position = 0;
        while (cursor.moveToNext()) {
            long contactId = cursor.getLong(cursor.getColumnIndexOrThrow(Contact.ContactEntry._ID));
            if (contactId == id) {
                break;
            }
            position++;
        }
        cursor.close();
        return position;
    }
}
