package com.app.alasala.java.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.app.alasala.R;
import com.app.alasala.java.cart.Cart;
import com.app.alasala.java.profile.OrderHistory;
import com.app.alasala.java.profile.Profile;
import com.app.alasala.java.widgets.men;
import com.app.alasala.java.widgets.women;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fragment_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.profile) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.cart) {
                startActivity(new Intent(getApplicationContext(), Cart.class));
                finish();
                return true;
            }
            else if (item.getItemId() == R.id.order_history) {
                startActivity(new Intent(getApplicationContext(), OrderHistory.class));
                finish();
                return true;
            }
            return false;
        });
    }

    public void men(View view) {
        startActivity(new Intent(getApplicationContext(), men.class));
    }

    public void women(View view) {
        startActivity(new Intent(getApplicationContext(), women.class));
    }

}
