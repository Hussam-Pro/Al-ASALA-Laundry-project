package com.app.alasala.java.cart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.alasala.R;
import com.app.alasala.java.home.Home;
import com.app.alasala.java.profile.OrderHistory;
import com.app.alasala.java.profile.Profile;
import com.app.alasala.java.widgets.userinfo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;

public class Cart extends AppCompatActivity {
    Button rqt, add;
    CheckBox cb1, cb2, cb3;
    Button plusButton, minusButton;
    TextView quantityTextView, priceTextView;
    int quantity = 0;
    double basePrice = 0.200;
    double additionalPrice = 0.000;
    double totalPrice = 0.000;
    DecimalFormat df = new DecimalFormat("0.000");

    @SuppressLint({"MissingInflatedId", "NotifyDataSetChanged"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fragment_cart);

        // Retrieve data from Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String time = intent.getStringExtra("time");
        int imageResId = intent.getIntExtra("image", 0);

        // Update UI with the received data
        TextView nameTextView = findViewById(R.id.servicename);
        priceTextView = findViewById(R.id.serviceprice);
        TextView timeTextView = findViewById(R.id.servicetime);
        ImageView imageView = findViewById(R.id.cart_image);

        nameTextView.setText(name);
        priceTextView.setText(price);
        timeTextView.setText(time);
        imageView.setImageResource(imageResId);

        add = findViewById(R.id.add);
        add.setOnClickListener(v -> {
            Toast.makeText(Cart.this, "Closed", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(Cart.this, Home.class);
            startActivity(intent1);
        });

        // Initialize buttons and quantity TextView
        plusButton = findViewById(R.id.plus_button);
        minusButton = findViewById(R.id.minus_button);
        quantityTextView = findViewById(R.id.quantity);

        rqt = findViewById(R.id.Request);
        rqt.setOnClickListener(v -> {
            Intent intent1 = new Intent(Cart.this, userinfo.class);
            intent1.putExtra("quantity", quantity);
            intent1.putExtra("totalPrice", totalPrice);
            intent1.putExtra("basePrice", basePrice);
            intent1.putExtra("serviceName", nameTextView.getText());
            startActivity(intent1);
        });

        cb1 = findViewById(R.id.checkbox_option1);
        cb2 = findViewById(R.id.checkbox_option2);
        cb3 = findViewById(R.id.checkbox_option3);

        cb1.setOnClickListener(v -> {
            if (cb1.isChecked()) {
                cb2.setChecked(false);
                cb3.setChecked(false);
                additionalPrice = 0.000;
                Toast.makeText(Cart.this, "Iron selected", Toast.LENGTH_SHORT).show();
            } else {
                additionalPrice = 0.000;
                Toast.makeText(Cart.this, "Iron unselected", Toast.LENGTH_SHORT).show();
            }
            updateTotalPrice();
        });

        cb2.setOnClickListener(v -> {
            if (cb2.isChecked()) {
                cb1.setChecked(false);
                cb3.setChecked(false);
                additionalPrice = 0.000;
                Toast.makeText(Cart.this, "Wash selected", Toast.LENGTH_SHORT).show();
            } else {
                additionalPrice = 0.000;
                Toast.makeText(Cart.this, "Wash unselected", Toast.LENGTH_SHORT).show();
            }
            updateTotalPrice();
        });

        cb3.setOnClickListener(v -> {
            if (cb3.isChecked()) {
                cb1.setChecked(false);
                cb2.setChecked(false);
                additionalPrice = 0.200;
                Toast.makeText(Cart.this, "Wash and Iron selected", Toast.LENGTH_SHORT).show();
            } else {
                additionalPrice = 0.000;
                Toast.makeText(Cart.this, "Wash and Iron unselected", Toast.LENGTH_SHORT).show();
            }
            updateTotalPrice();
        });

        plusButton.setOnClickListener(v -> {
            if (quantity < 6) {
                quantity++;
                updateQuantity();
                updateTotalPrice();
            }
        });

        minusButton.setOnClickListener(v -> {
            if (quantity > 0) {
                quantity--;
                updateQuantity();
                updateTotalPrice();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.cart);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                startActivity(new Intent(getApplicationContext(), Home.class));
                finish();
            } else if (item.getItemId() == R.id.profile) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
                finish();
            }
            else if (item.getItemId() == R.id.order_history) {
                startActivity(new Intent(getApplicationContext(), OrderHistory.class));
                finish();
            }
            return false;
        });
    }

    private void updateQuantity() {
        quantityTextView.setText(String.valueOf(quantity));
    }

    private void updateTotalPrice() {
        totalPrice = (basePrice + additionalPrice) * quantity;
        priceTextView.setText(quantity + "x" + df.format(totalPrice));
    }
}
