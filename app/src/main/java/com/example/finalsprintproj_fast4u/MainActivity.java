package com.example.finalsprintproj_fast4u;

import static android.view.View.*;

import android.content.Intent;
import android.credentials.CredentialManager;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.*;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //O Codigo começa aqui depois do Create() :3

        progressBar = findViewById(R.id.progressBar);

        showProgressBar(); // Mostra o ProgressBar
        // Esconde o ProgressBar após 3 segundos
        new android.os.Handler().postDelayed(
                this::hideProgressBar,
                5000 // 5000 milissegundos = 5 segundos
        );
    }

    // Metodo para mostrar o ProgressBar
    public void showProgressBar() {
        if (progressBar.getVisibility() != VISIBLE) {
            progressBar.setVisibility(VISIBLE);
        }
    }

    /* Metodo para esconder o ProgressBar */
    public void hideProgressBar() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
            Intent intent = new Intent(MainActivity.this.peekAvailableContext(), LoginActivity.class);
            startActivity(intent);
        }
    }
}