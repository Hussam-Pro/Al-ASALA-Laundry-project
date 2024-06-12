package com.app.alasala.java.auth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.alasala.R;
import com.app.alasala.java.home.Home;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Login extends AppCompatActivity {
    private Button lReg, llogin;
    private TextInputEditText lemail, lPassword;
    private FirebaseAuth firebaseAuth;
    public static String userEmail;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);

        lemail = findViewById(R.id.logemail);
        lPassword = findViewById(R.id.logPassword);

        lReg = findViewById(R.id.logReg);
        lReg.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Signup.class);
            startActivity(intent);
            finish();
        });

        firebaseAuth = FirebaseAuth.getInstance();

        llogin = findViewById(R.id.llogin);
        llogin.setOnClickListener(v -> SignInUser());
    }

    private void SignInUser() {
        String usernametxt = Objects.requireNonNull(lemail.getText()).toString();
        String passwordtxt = Objects.requireNonNull(lPassword.getText()).toString();

        if (TextUtils.isEmpty(usernametxt) || TextUtils.isEmpty(passwordtxt)) {
            Toast.makeText(this, "Please Enter Email and Password!", Toast.LENGTH_SHORT).show();
        } else {
            firebaseAuth.signInWithEmailAndPassword(usernametxt, passwordtxt)
                    .addOnCompleteListener(task -> {

                        if (task.isSuccessful()) {
                            userEmail = Objects.requireNonNull(lemail.getText()).toString();

                            Intent intent1 = new Intent(Login.this, Home.class);
                            startActivity(intent1);
                            finish();
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Login.this, "Wrong email or password!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(Login.this, "Login failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        }
    }
}
