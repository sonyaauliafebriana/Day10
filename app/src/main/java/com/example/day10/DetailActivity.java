package com.example.day10;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    TextView tvCarType, tvLocationType, tvDayOrRent, tvTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Temukan TextViews
        tvCarType = findViewById(R.id.tvCarType);
        tvLocationType = findViewById(R.id.tvLocationType);
        tvDayOrRent = findViewById(R.id.tvDayOrRent);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);

        // Memperbarui total harga pada pembuatan aktivitas
        updateTotalPrice();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Memperbarui total harga saat aktivitas dilanjutkan
        updateTotalPrice();
    }

    // Metode untuk memperbarui total harga
    private void updateTotalPrice() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String carType = extras.getString("carType");
            String locationType = extras.getString("locationType");
            String dayOrRent = extras.getString("dayOrRent");

            tvCarType.setText("Car Type: " + carType);
            tvLocationType.setText("Location: " + locationType);
            tvDayOrRent.setText("Day Or Rent: " + dayOrRent);

            double price = calculatePrice(carType, locationType, dayOrRent);
            tvTotalPrice.setText("Total Price: Rp " + String.valueOf(price));
        }
    }

    // Menghitung total harga
    private double calculatePrice(String carType, String locationType, String dayOrRent) {
        double carPrice = 0;
        double locationPrice = 0;

        // Mengatur harga berdasarkan mobil
        switch (carType) {
            case "Pajero":
                carPrice = 2400000;
                break;
            case "Alphard":
                carPrice = 1550000;
                break;
            case "Innova":
                carPrice = 850000;
                break;
            case "Brio":
                carPrice = 550000;
                break;
        }

        // Mengatur harga berdasarkan lokasi
        switch (locationType) {
            case "Inside city":
                locationPrice = 135000;
                break;
            case "Outside city":
                locationPrice = 250000;
                break;
        }

        // Menghitung harga total
        double totalPrice = 0;
        if (dayOrRent.equals("Day")) {
            totalPrice = carPrice + locationPrice;
        } else if (dayOrRent.equals("Rent")) {
            totalPrice = carPrice * 30 + locationPrice;
        }

        // Mengatur diskon jika total harga lebih besar dari 10 juta
        if (totalPrice > 10000000) {
            totalPrice *= 0.93; // 7% diskon
        } else if (totalPrice > 5000000) {
            totalPrice *= 0.95; // 5% diskon
        }

        return totalPrice;
    }
}
