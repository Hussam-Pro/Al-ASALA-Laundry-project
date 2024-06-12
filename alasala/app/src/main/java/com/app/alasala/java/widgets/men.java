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

public class men extends AppCompatActivity {

    TextView dishdashaname, dishdashaprice, dishdashatime, tshirtname, tshirtprice, tshirttime;
    ImageView MimageView, dishdashaImage, tshirtImage;
    CardView dishdasha, tshirt;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.men);

        MimageView = findViewById(R.id.menGoback);
        MimageView.setOnClickListener(v -> finish());

        dishdashaname = findViewById(R.id.dishdashaname);
        dishdashaprice = findViewById(R.id.dishdashaPrice);
        dishdashatime = findViewById(R.id.dishdashaTime);
        dishdashaImage = findViewById(R.id.dishdashaImage);
        dishdasha = findViewById(R.id.dishdasha);

        dishdasha.setOnClickListener(v -> {
            Intent dishintent = new Intent(men.this, Cart.class);
            dishintent.putExtra("name", dishdashaname.getText().toString());
            dishintent.putExtra("price", dishdashaprice.getText().toString());
            dishintent.putExtra("time", dishdashatime.getText().toString());
            dishintent.putExtra("image", R.drawable.dishdasha);
            startActivity(dishintent);
        });

        tshirtname = findViewById(R.id.tshirtname);
        tshirtprice = findViewById(R.id.tshirtprice);
        tshirttime = findViewById(R.id.tshirtTime);
        tshirtImage = findViewById(R.id.tshirtImage);
        tshirt = findViewById(R.id.tshirt);

        tshirt.setOnClickListener(v -> {
            Intent Tintent = new Intent(men.this, Cart.class);
            Tintent.putExtra("name", tshirtname.getText().toString());
            Tintent.putExtra("price", tshirtprice.getText().toString());
            Tintent.putExtra("time", tshirttime.getText().toString());
            Tintent.putExtra("image", R.drawable.shirt);
            startActivity(Tintent);
        });
    }
}
