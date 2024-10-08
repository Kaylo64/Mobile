package com.example.listedecontactavecbd;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<Contact> {
    public ContactAdapter(Context context, ArrayList<Contact> contacts) {
        super(context, 0, contacts);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContactView contactView = (ContactView) convertView;
        if (contactView == null) {
            contactView = new ContactView(getContext());
        }
        Contact contact = getItem(position);
        contactView.setContact(contact);

        return contactView;
    }
}
