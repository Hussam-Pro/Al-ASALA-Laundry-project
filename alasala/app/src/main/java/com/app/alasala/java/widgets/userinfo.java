package com.app.alasala.java.widgets;

import static com.app.alasala.java.auth.Login.userEmail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.alasala.R;
import com.app.alasala.java.home.Home;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class userinfo extends AppCompatActivity {

    CheckBox checkBoxVica, checkBoxCash;
    ImageView gobButton;
    TextInputEditText nameinfo, phoneinfo, cityinfo, homeinfo;
    private FirebaseFirestore db;
    String serviceName;
    double  basePrice, totalPrice;
    int quantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo);

        checkBoxVica = findViewById(R.id.vica);
        checkBoxCash = findViewById(R.id.cash);
        db = FirebaseFirestore.getInstance();

        quantity = Objects.requireNonNull(getIntent().getExtras()).getInt("quantity");
        serviceName = Objects.requireNonNull(getIntent().getExtras()).getString("serviceName");
        basePrice = Objects.requireNonNull(getIntent().getExtras()).getDouble("basePrice");
        totalPrice = Objects.requireNonNull(getIntent().getExtras()).getDouble("totalPrice");

        Log.e("serviceName", serviceName);
        Log.e("quantity", String.valueOf(quantity));
        Log.e("basePrice", String.valueOf(basePrice));
        Log.e("totalPrice", String.valueOf(totalPrice));
        // Ensure only one checkbox can be selected at a time
        checkBoxVica.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkBoxCash.setChecked(false);
            }
        });

        checkBoxCash.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkBoxVica.setChecked(false);
            }
        });
        nameinfo = findViewById(R.id.nameinfo);
        phoneinfo = findViewById(R.id.phoneinfo);
        cityinfo = findViewById(R.id.cityinfo);
        homeinfo = findViewById(R.id.homeinfo);

        gobButton = findViewById(R.id.userdetailGoback);
        gobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void openMapActivity(View view) {
        // Check which payment method is selected
        String selectedPaymentMethod = "";
        if (checkBoxVica.isChecked()) {
            selectedPaymentMethod = "visa";
        } else if (checkBoxCash.isChecked()) {
            selectedPaymentMethod = "Cash";
        } else {
            Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show();
            return; // Do not proceed if no payment method is selected
        }
        // Pass the selected payment method to the map activity
        Intent intent = new Intent(userinfo.this, map.class);
        intent.putExtra("paymentMethod", selectedPaymentMethod);
        startActivity(intent);
    }

    public void sendRequest(View view) {
        // Display a toast message when the "Request Now" button is pressed

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("serviceName", serviceName);
        requestMap.put("quantity", quantity);
        requestMap.put("basePrice", basePrice);
        requestMap.put("totalPrice", totalPrice);
        if(checkBoxCash.isChecked()) {
            requestMap.put("paymentMethod", "cash");
        }
        else  if(checkBoxVica.isChecked()) {
            requestMap.put("paymentMethod", "visa");
        }

        requestMap.put("name", nameinfo.getText().toString());
        requestMap.put("email", userEmail);
        requestMap.put("city", cityinfo.getText().toString());
        requestMap.put("phone", phoneinfo.getText().toString());
        requestMap.put("home", homeinfo.getText().toString());


        db.collection("requests").document()
                .set(requestMap)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Request sent to laundry system successfully", Toast.LENGTH_SHORT).show();
                    nameinfo.setText("");
                    phoneinfo.setText("");
                    cityinfo.setText("");
                    homeinfo.setText("");
                    Intent intent1 = new Intent(userinfo.this, Home.class);
                    startActivity(intent1);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Request failed", Toast.LENGTH_SHORT).show();
                });


    }
}
