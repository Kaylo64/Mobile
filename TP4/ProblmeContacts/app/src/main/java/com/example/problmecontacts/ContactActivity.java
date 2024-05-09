package com.example.problmecontacts;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ContactActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Contact> contacts;
    private int selectedItemPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.principal_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            contacts = new ArrayList<>();
            contacts.add(new Contact("Sara Elmidaoui", "selmidaoui@cegepgranby.qc.ca", "111-2222"));
            contacts.add(new Contact("Jie Yang", "jyang@cegepgranby.qc.ca", "333-4444"));
            contacts.add(new Contact("Maryse Des Aulniers", "mdesaulniers@cegepgranby.qc.ca", "555-6666"));
            contacts.add(new Contact("Charles Sarrazin Boucher", "cboucher@cegepgranby.qc.ca", "777-8888"));
            contacts.add(new Contact("Maurice Jacques-André Delafosse", "mdelafosse@cegepgranby.qc.ca", "999-0000"));
            contacts.add(new Contact("Laurent Beauregard", "lbeauregard@cegepgranby.qc.ca", "123-4567"));

            listView = findViewById(R.id.listView);
            ContactAdapter adapter = new ContactAdapter(this, contacts);
            listView.setAdapter(adapter);

            return insets;
        });
    }
}