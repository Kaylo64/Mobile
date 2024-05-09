package com.example.problmecontacts;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.graphics.Color;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class ContactActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Contact> contacts;
    private int selectedItemPosition = 0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addContact) {
            ajouterContact();
            return true;
        } else if (item.getItemId() == R.id.removeContact) {
            supprimerContact();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void ajouterContact() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialogue_layout, null);
        builder.setView(dialogView);

        EditText nomEditText = dialogView.findViewById(R.id.nomEditText);
        EditText courrielEditText = dialogView.findViewById(R.id.courrielEditText);
        EditText telEditText = dialogView.findViewById(R.id.telEditText);
        Button okButton = dialogView.findViewById(R.id.okayButton);
        Button cancelButton = dialogView.findViewById(R.id.cancelButton);
        AlertDialog dialog = builder.create();
        dialog.show();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = nomEditText.getText().toString();
                String courriel = courrielEditText.getText().toString();
                String tel = telEditText.getText().toString();

                contacts.add(new Contact(nom, courriel, tel));
                ((ContactAdapter) listView.getAdapter()).notifyDataSetChanged();

                dialog.dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    private void supprimerContact() {
        if (selectedItemPosition != -1) {
            contacts.remove(selectedItemPosition);
            selectedItemPosition = -1;
            ((ContactAdapter) listView.getAdapter()).notifyDataSetChanged();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.principal_layout);

        if (contacts == null) {
            contacts = new ArrayList<>();
            contacts.add(new Contact("Sara Elmidaoui", "selmidaoui@cegepgranby.qc.ca", "111-2222"));
            contacts.add(new Contact("Jie Yang", "jyang@cegepgranby.qc.ca", "333-4444"));
            contacts.add(new Contact("Maryse Des Aulniers", "mdesaulniers@cegepgranby.qc.ca", "555-6666"));
            contacts.add(new Contact("Charles Sarrazin Boucher", "cboucher@cegepgranby.qc.ca", "777-8888"));
            contacts.add(new Contact("Maurice Jacques-André Delafosse", "mdelafosse@cegepgranby.qc.ca", "999-0000"));
            contacts.add(new Contact("Laurent Beauregard", "lbeauregard@cegepgranby.qc.ca", "123-4567"));
        }

        listView = findViewById(R.id.listView);
        ContactAdapter adapter = new ContactAdapter(this, contacts);
        listView.setAdapter(adapter);

        listView.post(() -> {
           View view = listView.getChildAt(selectedItemPosition);
           view.setBackgroundColor(getResources().getColor(R.color.lightBlue));
           view.setSelected(true);
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            for (int i = 0; i < parent.getChildCount(); i++) {
                parent.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.black));
            }
            view.setBackgroundColor(getResources().getColor(R.color.lightBlue));
            selectedItemPosition = position;
        });
    }
}