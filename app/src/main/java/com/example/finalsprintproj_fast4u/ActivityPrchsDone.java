package com.example.finalsprintproj_fast4u;

import static android.widget.Toast.LENGTH_LONG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityPrchsDone extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prchsdone);
        FirebaseApp.initializeApp(this);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        //Aqui o código man :D
        //Nao que tenha muita coisa por aqui. :3

        ImageView fin;
        ImageView img;
        TextView userName;

        //Instanciando... Instanciando...
        Uri finUrl = Uri.parse("https://rafaelglavam.com.br/wp-content/uploads/2020/03/correct-icon-png-8.png");

        img = findViewById(R.id.userPhoto);
        userName = findViewById(R.id.userName);
        fin = findViewById(R.id.completedTransactionImg);

        FirebaseUser currentUser = mAuth.getCurrentUser();


        if (currentUser != null) {
            userName.setText(currentUser.getDisplayName());
            Uri photoUri = currentUser.getPhotoUrl();
            if (photoUri != null) {
                Glide.with(this).load(photoUri).into(img);
            }
        } else {
            Toast.makeText(this, "Usuário não logado!", LENGTH_LONG);
            startActivity(new Intent(this, LoginActivity.class));
            finish(); // <- impede continuar na tela de prefs sem login
        }

        Glide.with(this)
                .load(finUrl)
                .into(fin);
    }


    //Só para retornar corretamente à pagina inicial.
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        goBack();
    }

    public void goBack() {
        Intent backIntent = new Intent(this, FoodMenuActivity.class);
        startActivity(backIntent);
        finish();
    }
}
