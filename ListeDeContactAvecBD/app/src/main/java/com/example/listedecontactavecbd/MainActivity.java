package com.example.listedecontactavecbd;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ContactsDao contactsDao;
    private ContactCursorAdapter adapter;
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
        } else if (item.getItemId() == R.id.modifyContact) {
            modifierContact();
            return true;
        } else if (item.getItemId() == R.id.removeContact) {
            supprimerContact();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_layout);
        listView = findViewById(R.id.listView);
        contactsDao = new ContactsDao(this);

        if (isDatabaseEmpty()) {
            insertDefaultContacts();
        }

        loadContacts();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItemPosition = position;

                for (int i = 0; i < parent.getChildCount(); i++) {
                    parent.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.black));
                }
                view.setBackgroundColor(getResources().getColor(R.color.lightBlue));
            }
        });

        listView.post(() -> {
            View view = listView.getChildAt(selectedItemPosition);
            if (view != null) {
                view.setBackgroundColor(getResources().getColor(R.color.lightBlue));
                view.setSelected(true);
            }
        });
    }

    private void loadContacts() {
        Cursor cursor = contactsDao.getAllContactsCursor();
        adapter = new ContactCursorAdapter(this, cursor, 0);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadContacts();
    }

    private boolean isDatabaseEmpty() {
        Cursor cursor = contactsDao.getAllContactsCursor();
        boolean isEmpty = cursor.getCount() == 0;
        cursor.close();
        return isEmpty;
    }

    private void insertDefaultContacts() {
        contactsDao.ajouterContact(new Contact(0, "Elmidaoui", "Sara", "selmidaoui@cegepgranby.qc.ca", "111-2222"));
        contactsDao.ajouterContact(new Contact(0, "Yang","Jie", "jyang@cegepgranby.qc.ca", "333-4444"));
        contactsDao.ajouterContact(new Contact(0, "Des Aulniers", "Maryse", "mdesaulniers@cegepgranby.qc.ca", "555-6666"));
        contactsDao.ajouterContact(new Contact(0, "Delafosse", "Maurice Jacques-André", "mdelafosse@cegepgranby.qc.ca", "999-0000"));
        contactsDao.ajouterContact(new Contact(0, "Beauregard", "Laurent", "lbeauregard@cegepgranby.qc.ca", "123-4567"));
    }

    private void ajouterContact() {
        Intent intent = new Intent(MainActivity.this, GererContactActivity.class);
        intent.putExtra("action", "add");
        intent.putExtra("position", selectedItemPosition);
        startActivity(intent);
    }

    private void modifierContact() {
        long contactId = adapter.getItemId(selectedItemPosition);
        Intent intent = new Intent(MainActivity.this, GererContactActivity.class);
        intent.putExtra("action", "edit");
        intent.putExtra("contactId", contactId);
        startActivity(intent);
    }

    private void supprimerContact() {
        long contactId = adapter.getItemId(selectedItemPosition);
        contactsDao.supprimerContact(contactId);
        loadContacts();
    }
}

