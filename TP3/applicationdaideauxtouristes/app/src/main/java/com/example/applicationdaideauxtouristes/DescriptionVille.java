package com.example.applicationdaideauxtouristes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DescriptionVille extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_ville);

        Intent intent = getIntent();
        Button mainPageBtn = findViewById(R.id.mainPageBtn);

        if (intent != null) {
            String ville = intent.getStringExtra("ville");
            afficherDescription(ville);
            afficherLienDeLaVille(ville);
        }
        mainPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retourPagePrincipale();
            }
        });
    }
        private void afficherDescription(String ville) {
            TextView descriptionTextView = findViewById(R.id.descriptionTextView);
            String description = "";
            if ("Montréal".equals(ville)) {
                description = getString(R.string.description_montreal);
            } else if ("Granby".equals(ville)) {
                description = getString(R.string.description_granby);
            }
            descriptionTextView.setText(description);
        }

        private void afficherLienDeLaVille(String ville){
            TextView urlTextView = findViewById(R.id.urlTextView);
            String url = "";
            if ("Montréal".equals(ville)) {
                url = getString(R.string.site_web_montreal);
            } else if ("Granby".equals(ville)) {
                url = getString(R.string.site_web_granby);
            }
            urlTextView.setText(url);
        }
        private void retourPagePrincipale() {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
}