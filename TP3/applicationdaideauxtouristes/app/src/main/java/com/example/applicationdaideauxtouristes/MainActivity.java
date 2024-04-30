package com.example.applicationdaideauxtouristes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mltBtn = findViewById(R.id.mltBtn);
        Button granbyBtn = findViewById(R.id.granbyBtn);
        mltBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ouvrirDescriptionVille("Montréal");
            }
        });
        granbyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ouvrirDescriptionVille("Granby");
            }
        });
    }

    private void ouvrirDescriptionVille(String ville) {
        Intent intent = new Intent(this, DescriptionVille.class);
        intent.putExtra("ville", ville);
        startActivity(intent);
    }
}