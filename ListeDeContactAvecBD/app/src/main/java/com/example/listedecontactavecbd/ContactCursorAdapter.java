package com.example.listedecontactavecbd;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ContactCursorAdapter extends CursorAdapter {
    public ContactCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.contact_view_layout, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        TextView contactFirstNameTextView = view.findViewById(R.id.contactFirstName);
        TextView contactNameTextView = view.findViewById(R.id.contactName);
        TextView contactEmailTextView = view.findViewById(R.id.contactMail);
        TextView contactNumberTextView = view.findViewById(R.id.contactPhoneNumber);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(Contact.ContactEntry.COLUMN_NAME));
        String surname = cursor.getString(cursor.getColumnIndexOrThrow(Contact.ContactEntry.COLUMN_FIRSTNAME));
        String email = cursor.getString(cursor.getColumnIndexOrThrow(Contact.ContactEntry.COLUMN_EMAIL));
        String number = cursor.getString(cursor.getColumnIndexOrThrow(Contact.ContactEntry.COLUMN_PHONE_NUMBER));

        contactNameTextView.setText(name);
        contactFirstNameTextView.setText(surname);
        contactEmailTextView.setText(email);
        contactNumberTextView.setText(number);
    }
}
