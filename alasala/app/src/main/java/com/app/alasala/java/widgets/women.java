package com.app.alasala.java.widgets;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.app.alasala.R;
import com.app.alasala.java.cart.Cart;

public class women extends AppCompatActivity {

    TextView abayaName, abayaPrice, abayaTime, dressName, dressPrice, dressTime;
    ImageView women, abayaImage, dressImage;
    CardView abaya, dress;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.women);

        // Back button
        women = findViewById(R.id.womenGoback);
        women.setOnClickListener(v -> finish());

        // Abaya components
        abayaName = findViewById(R.id.abayaName);
        abayaPrice = findViewById(R.id.abayaPrice);
        abayaTime = findViewById(R.id.abayaTime);
        abayaImage = findViewById(R.id.abayaImage);
        abaya = findViewById(R.id.abaya);

        // Set abaya click listener
        abaya.setOnClickListener(v -> {
            Intent abayaIntent = new Intent(women.this, Cart.class);
            abayaIntent.putExtra("name", abayaName.getText().toString());
            abayaIntent.putExtra("price", abayaPrice.getText().toString());
            abayaIntent.putExtra("time", abayaTime.getText().toString());
            abayaIntent.putExtra("image", R.drawable.abaya);  // replace with the actual image resource
            startActivity(abayaIntent);
        });

        // Dress components
        dressName = findViewById(R.id.dressName);
        dressPrice = findViewById(R.id.dressPrice);
        dressTime = findViewById(R.id.dressTime);
        dressImage = findViewById(R.id.dressImage);
        dress = findViewById(R.id.dress);

        // Set dress click listener
        dress.setOnClickListener(v -> {
            Intent dressIntent = new Intent(women.this, Cart.class);
            dressIntent.putExtra("name", dressName.getText().toString());
            dressIntent.putExtra("price", dressPrice.getText().toString());
            dressIntent.putExtra("time", dressTime.getText().toString());
            dressIntent.putExtra("image", R.drawable.dress);  // replace with the actual image resource
            startActivity(dressIntent);
        });
    }
}
