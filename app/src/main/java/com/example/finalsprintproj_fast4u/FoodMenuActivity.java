package com.example.finalsprintproj_fast4u;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class FoodMenuActivity extends AppCompatActivity {
    ImageView img, hamburgbtn, pizzabtn, docesbtn, japabtn, salgadbtn;
    private FirebaseAuth mAuth;
    Button logOutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        FirebaseApp.initializeApp(this);

        //Instanciando os objetos a serem utilizados (ou coisas já nem sei mais)

        mAuth = FirebaseAuth.getInstance();

        img = findViewById(R.id.userPhoto);
        TextView userText = findViewById(R.id.userText);
        hamburgbtn = findViewById(R.id.hamburgbtn);
        salgadbtn = findViewById(R.id.salgadbtn);
        docesbtn = findViewById(R.id.docesbtn);
        japabtn = findViewById(R.id.japabtn);
        pizzabtn = findViewById(R.id.pizzabtn);
        logOutBtn = findViewById(R.id.logOutBtn);


        String defaultUserImage = "https://cdn.pixabay.com/photo/2023/02/18/11/00/icon-7797704_1280.png";

        //Essa intent foi INUTILIZADA, TEM MEIOS MELHORES DE CONSEGUIR ESSES DADOS!!!
        //Intent userData = getIntent();

        //Intent e data para usuários locais.
        Intent newUser = getIntent();
        String nome;
        String email;
        String endereco;

        //Dedicado ao usuário com conta google
        Uri userPhoto = null;
        String userName = "";

        if (newUser != null) {
            nome = newUser.getStringExtra("user-name");
            email = newUser.getStringExtra("user-email");
            endereco = newUser.getStringExtra("user-address");

            userText.setText(nome);
        }

//Loading the image >>
        if (mAuth != null) {
            userPhoto = FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl();
            userName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

            Glide.with(this)
                    .load(userPhoto)
                    .into(img);
        } else {
            Glide.with(this)
                    .load(defaultUserImage)
                    .into(img);
        }

        if (userName != null && !userName.isEmpty()) {
            userText.setText(userName);
        }

        Intent prefFoodIntent = new Intent(FoodMenuActivity.this, PreferenceActivity.class);

        //Aqui começa a desgraça -->>   :D
        hamburgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefFoodIntent.removeExtra("pref_food");
                prefFoodIntent.putExtra("pref_food", "hamb");
                startActivity(prefFoodIntent);
                finish();
            }
        });

        pizzabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefFoodIntent.removeExtra("pref_food");
                prefFoodIntent.putExtra("pref_food", "pizz");
                startActivity(prefFoodIntent);
                finish();
            }
        });

        docesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefFoodIntent.removeExtra("pref_food");
                prefFoodIntent.putExtra("pref_food", "doce");
                startActivity(prefFoodIntent);
                finish();
            }
        });

        salgadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefFoodIntent.removeExtra("pref_food");
                prefFoodIntent.putExtra("pref_food", "salg");
                startActivity(prefFoodIntent);
                finish();
            }
        });

        japabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefFoodIntent.removeExtra("pref_food");
                prefFoodIntent.putExtra("pref_food", "japa");
                startActivity(prefFoodIntent);
                finish();
            }
        });
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent signOut = new Intent(FoodMenuActivity.this, LoginActivity.class);
                startActivity(signOut);
                finish();
            }
        });
    }
    //Só para retornar corretamente à pagina inicial.
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        goBack();
    }

    public void goBack() {
        finishAffinity();
    }
}
