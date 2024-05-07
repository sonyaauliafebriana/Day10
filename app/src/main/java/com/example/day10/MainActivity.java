package com.example.day10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioButton rbPajero, rbAlphard, rbInnova, rbBrio, rbInsideCity, rbOutsideCity;
    private Button btnRent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbPajero = findViewById(R.id.rbPajero);
        rbAlphard = findViewById(R.id.rbAlphard);
        rbInnova = findViewById(R.id.rbInnova);
        rbBrio = findViewById(R.id.rbBrio);
        rbInsideCity = findViewById(R.id.rbInsideCity);
        rbOutsideCity = findViewById(R.id.rbOutsideCity);

        //tombol "Rent"
        btnRent = findViewById(R.id.btnRent);

        btnRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCarSelected() && isLocationSelected()) {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("carType", getSelectedCarType());
                    intent.putExtra("locationType", getSelectedLocationType());
                    intent.putExtra("dayOrRent", "Day"); // Misalnya, default "Day"
                    startActivity(intent);
                } else {
                    // Tampilkan pesan jika mobil atau lokasi belum dipilih
                    Toast.makeText(MainActivity.this, "Please select car and location", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isCarSelected() {
        return rbPajero.isChecked() || rbAlphard.isChecked() || rbInnova.isChecked() || rbBrio.isChecked();
    }

    private boolean isLocationSelected() {
        return rbInsideCity.isChecked() || rbOutsideCity.isChecked();
    }

    // mobil yang dipilih
    private String getSelectedCarType() {
        if (rbPajero.isChecked()) {
            return "Pajero";
        } else if (rbAlphard.isChecked()) {
            return "Alphard";
        } else if (rbInnova.isChecked()) {
            return "Innova";
        } else if (rbBrio.isChecked()) {
            return "Brio";
        }
        return "";
    }

    // lokasi yang dipilih
    private String getSelectedLocationType() {
        if (rbInsideCity.isChecked()) {
            return "Inside city";
        } else if (rbOutsideCity.isChecked()) {
            return "Outside city";
        }
        return "";
    }
}
