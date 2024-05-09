package com.example.problmecontacts;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ContactView extends RelativeLayout {
    private TextView contactNameTextView;
    private TextView contactMailTextView;
    private TextView contactPhoneNumberTextView;
    private Contact contact;
    public ContactView(Context context) {
        super(context);
        init(context);
    }
    public ContactView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.contact_view_layout, this, true);
        contactNameTextView = findViewById(R.id.contactName);
        contactMailTextView = findViewById(R.id.contactMail);
        contactPhoneNumberTextView = findViewById(R.id.contactPhoneNumber);
    }

    public void setContact(Contact contact) {
        if (contact != null) {
            contactNameTextView.setText(contact.getNom());
            contactMailTextView.setText(contact.getCourriel());
            contactPhoneNumberTextView.setText(contact.getTelephone());
        }
    }

}