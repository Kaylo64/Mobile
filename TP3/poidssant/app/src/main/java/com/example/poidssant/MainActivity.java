package com.example.poidssant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText sizeEditText = findViewById(R.id.sizeEditText);
        EditText wristEditText = findViewById(R.id.wristEditText);
        EditText kgEditText = findViewById(R.id.kgEditText);
        EditText poundsEditText = findViewById(R.id.poundsEditText);
        CheckBox kgCheckBox = findViewById(R.id.kgCheckBox);
        CheckBox poundsCheckBox = findViewById(R.id.poundsCheckBox);
        Button calculerBtn = findViewById(R.id.calculerBtn);

        calculerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sizeEditText.getText().toString().isEmpty() || wristEditText.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    Double.parseDouble(sizeEditText.getText().toString());
                    Double.parseDouble(wristEditText.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Les valeurs doivent être numériques.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(kgCheckBox.isChecked()){
                    double poidsIdealKg = Double.parseDouble(calculerPoidsIdeal(sizeEditText, wristEditText));
                    kgEditText.setText(String.format("%.2f", poidsIdealKg));
                }
                if(poundsCheckBox.isChecked()){
                    double poidsIdealLb = Double.parseDouble(calculerPoidsIdeal(sizeEditText, wristEditText)) * 2.2046;
                    poundsEditText.setText(String.format("%.2f", poidsIdealLb));
                }
            }
        });
    }

    private String calculerPoidsIdeal(EditText sizeEditText, EditText wristEditText){
        RadioButton cmRadioBtn = findViewById(R.id.cmRadioBtn);
        RadioButton inchRadioBtn = findViewById(R.id.inchRadioBtn);
        double taille = Double.parseDouble(sizeEditText.getText().toString());
        double poignet = Double.parseDouble(wristEditText.getText().toString());
        double poidsIdeal = 0;

        double weight = 0;

        if (inchRadioBtn.isChecked()) {
            taille *= 2.54;
            poignet *= 2.54;
        }
        poidsIdeal = (taille - 100 + (4 * poignet)) / 2;
        weight = poidsIdeal;
        return weight+"";
    }
}