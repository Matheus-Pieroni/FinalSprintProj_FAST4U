package com.example.finalsprintproj_fast4u;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PurchaseActivity extends AppCompatActivity {
    ImageView img;
    TextView userName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        FirebaseApp.initializeApp(this);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        img = findViewById(R.id.userPhoto);
        userName = findViewById(R.id.userName);

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            userName.setText(currentUser.getDisplayName());
            Uri photoUri = currentUser.getPhotoUrl();
            if (photoUri != null) {
                Glide.with(this).load(photoUri).into(img);
            }
        } else {
            Toast.makeText(this, "Usuário não logado!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish(); // <- impede continuar na tela de prefs sem login
        }
    }
}
