package com.app.alasala.java.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alasala.R;
import com.app.alasala.java.RequestAdapter;
import com.app.alasala.java.RequestModel;
import com.app.alasala.java.cart.Cart;
import com.app.alasala.java.home.Home;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderHistory extends AppCompatActivity {
    Button rqt, add;
    CheckBox cb1, cb2, cb3;
    Button plusButton, minusButton;
    TextView quantityTextView, priceTextView;
    int quantity = 0;
    double basePrice = 0.200;
    double additionalPrice = 0.000;
    double totalPrice = 0.000;
    DecimalFormat df = new DecimalFormat("0.000");

    RecyclerView recyclerView;
    RequestAdapter adapter;
    List<RequestModel> myOrders = new ArrayList<>();;
    private FirebaseFirestore db;
    private List<DocumentSnapshot> orderList = new ArrayList<>();

    @SuppressLint({"MissingInflatedId", "NotifyDataSetChanged"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fragment_order_history);
        db = FirebaseFirestore.getInstance();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.order_history);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                startActivity(new Intent(getApplicationContext(), Home.class));
                finish();
            } else if (item.getItemId() == R.id.profile) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
                finish();
            }
            else if (item.getItemId() == R.id.cart) {
                startActivity(new Intent(getApplicationContext(), Cart.class));
                finish();
            }
            return false;
        });

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        fetchOrders();
        adapter = new RequestAdapter(this, myOrders);
//        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }


    private void fetchOrders() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            db.collection("requests")
                    .whereEqualTo("email", user.getEmail())
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                orderList.add(document);
                                RequestModel requestModel = new RequestModel();
                                requestModel.setName(document.get("name").toString());
                                requestModel.setPhone(document.get("phone").toString());
                                requestModel.setCity(document.get("city").toString());
                                requestModel.setPaymentMethod(document.get("paymentMethod").toString());
                                requestModel.setBasePrice(Double.parseDouble(document.get("basePrice").toString()));
                                requestModel.setTotalPrice(Double.parseDouble(document.get("totalPrice").toString()));
                                requestModel.setServiceName(document.get("serviceName").toString());
                                requestModel.setQuantity(Integer.parseInt((document.get("quantity").toString())));
                                requestModel.setHome(document.get("home").toString());

                                myOrders.add(requestModel);
                                Log.e("Document Name", document.get("name").toString());
                            }
                            adapter.notifyDataSetChanged();
                            if (myOrders.isEmpty()) {
                                Toast.makeText(this, "No orders found", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "Failed to load orders", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No user logged in", Toast.LENGTH_SHORT).show();
        }
    }
}


