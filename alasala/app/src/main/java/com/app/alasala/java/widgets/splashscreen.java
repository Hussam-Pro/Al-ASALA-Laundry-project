package com.app.alasala.java.widgets;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.app.alasala.R;
import com.app.alasala.java.auth.Login;


@SuppressLint("CustomSplashScreen")
public class splashscreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splashscreen);

        int SPLASH_SCREEN = 800;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashscreen.this, Login.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}
