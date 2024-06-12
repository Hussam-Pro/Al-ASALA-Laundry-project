package com.app.alasala.java.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.app.alasala.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Signup extends AppCompatActivity {
    private static final String TAG = "Signup";
    private TextInputEditText user, email, password, conPassword, phone;
    private Button registerbtn, goLogin;
    private FirebaseAuth firebaseAuth;

    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://alasala-55043-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference childref = database.getReference("UsersData");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        user = findViewById(R.id.reguser);
        email = findViewById(R.id.regemail);
        password = findViewById(R.id.regPassword);
        conPassword = findViewById(R.id.regconfirmPass);
        phone = findViewById(R.id.regphone);

        goLogin = findViewById(R.id.goLogin);
        goLogin.setOnClickListener(v -> {
            Intent intent = new Intent(Signup.this, Login.class);
            startActivity(intent);
            finish();
        });


        // Create Firebase instance
        firebaseAuth = FirebaseAuth.getInstance();

        // Set up button click listener
        registerbtn = findViewById(R.id.Signupbtn);
        registerbtn.setOnClickListener(v -> SignUpUser());
    }

    private void SignUpUser() {
        String usertxt = Objects.requireNonNull(user.getText()).toString();
        String emailtxt = Objects.requireNonNull(email.getText()).toString();
        String passwordtxt = Objects.requireNonNull(password.getText()).toString();
        String conpasstxt = Objects.requireNonNull(conPassword.getText()).toString();
        String PhoneNumber = Objects.requireNonNull(phone.getText()).toString();

        if (TextUtils.isEmpty(usertxt) || TextUtils.isEmpty(emailtxt) || TextUtils.isEmpty(PhoneNumber) || TextUtils.isEmpty(passwordtxt) || TextUtils.isEmpty(conpasstxt)) {
            Toast.makeText(this, "Please fill all the fields!", Toast.LENGTH_SHORT).show();
        } else if (!passwordtxt.equals(conpasstxt)) {
            Toast.makeText(this, "Passwords not match", Toast.LENGTH_SHORT).show();
        } else {
            firebaseAuth.createUserWithEmailAndPassword(emailtxt, passwordtxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        if (firebaseAuth.getCurrentUser() != null) {
                            String userId = firebaseAuth.getCurrentUser().getUid();
                            Toast.makeText(Signup.this, "User successfully registered", Toast.LENGTH_SHORT).show();
                            DatabaseReference reference = childref.child(userId);
                            Userdetails model = new Userdetails(usertxt, emailtxt, PhoneNumber);
                            reference.setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "User data stored successfully.");
                                        Intent intent = new Intent(Signup.this, Login.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Log.e(TAG, "Failed to upload user data: " + task.getException());
                                        Toast.makeText(Signup.this, "Failed to upload user data", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e(TAG, "Error uploading user data: " + e.getMessage());
                                    Toast.makeText(Signup.this, "Error uploading user data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Toast.makeText(Signup.this, "User cannot be registered", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e(TAG, "Registration failed: " + task.getException());
                        Toast.makeText(Signup.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "Error: " + e.getMessage());
                    Toast.makeText(Signup.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
