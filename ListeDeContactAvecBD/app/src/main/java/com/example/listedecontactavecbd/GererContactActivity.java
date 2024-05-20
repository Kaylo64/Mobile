package com.example.listedecontactavecbd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GererContactActivity extends Activity {
    private EditText editNom, editPrenom, editEmail, editTelephone;
    private Button okayButton, cancelButton;
    private ContactsDao contactsDao;
    private long contactId = -1;
    private String action;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gerer_contact);

        contactsDao = new ContactsDao(this);

        editNom = (EditText) findViewById(R.id.nomEditText);
        editPrenom = (EditText) findViewById(R.id.prenomEditText);
        editEmail = (EditText) findViewById(R.id.courrielEditText);
        editTelephone = (EditText) findViewById(R.id.telEditText);

        Intent intent = getIntent();
        action = intent.getStringExtra("action");
        if ("edit".equals(action)) {
            contactId = intent.getLongExtra("contactId", -1);
            if (contactId != -1) {
                Contact contact = contactsDao.getContactById(contactId);
                if (contact != null) {
                    editNom.setText(contact.getNom());
                    editPrenom.setText(contact.getPrenom());
                    editEmail.setText(contact.getCourriel());
                    editTelephone.setText(contact.getTelephone());
                }
            }
        }

        Button btnSave = findViewById(R.id.okayButton);
        btnSave.setOnClickListener(v -> saveContact());

        Button btnCancel = findViewById(R.id.cancelButton);
        btnCancel.setOnClickListener(v -> finish());
    }

    private void saveContact() {
        String nom = editNom.getText().toString();
        String prenom = editPrenom.getText().toString();
        String email = editEmail.getText().toString();
        String telephone = editTelephone.getText().toString();

        Contact contact = new Contact(contactId, nom, prenom, email, telephone);
        if ("add".equals(action)) {
            contactsDao.ajouterContact(contact);
        } else if ("edit".equals(action)) {
            contactsDao.modifierContact(contact);
        }

        finish();
    }
}
