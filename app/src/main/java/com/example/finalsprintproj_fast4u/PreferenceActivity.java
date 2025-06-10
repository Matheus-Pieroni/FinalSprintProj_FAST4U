package com.example.finalsprintproj_fast4u;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class PreferenceActivity extends AppCompatActivity {

    ImageView img;
    ImageView foodOpt1;
    ImageView foodOpt2;
    TextView foodOptTxt1;
    TextView foodOptTxt2;
    TextView userText;
    String userName;
    Button subsBtn1;
    Button subsBtn2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pref);
        FirebaseApp.initializeApp(this); //Iniciando o serviço de autenticação.

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        img = findViewById(R.id.userPhoto);
        userText = findViewById(R.id.userText);
        foodOpt1 = findViewById(R.id.foodOpt1);
        foodOpt2 = findViewById(R.id.foodOpt2);
        foodOptTxt1 = findViewById(R.id.foodOptTxt1);
        foodOptTxt2 = findViewById(R.id.foodOptTxt2);
        subsBtn1 = findViewById(R.id.subsBtn1);
        subsBtn2 = findViewById(R.id.subsBtn2);

        //Definindo os links para as imagens em variaveis ->>
        String hamb1 = "https://yt3.googleusercontent.com/m2N3G5IuofYk3Lld7t1jKxUMrJZzpYLul6P0nE-MGKmLKN7qvC6Ocf8YMKw6UEXmaO8wVa2Dvck=s900-c-k-c0x00ffffff-no-rj";
        String hamb2 = "https://yt3.googleusercontent.com/6YddgxsH8xcnDIASLYyN8xOrKU3OrlGvKsP8Gv7kvOsiGqUkolpIanOr0Ku0dWUnhO4wHw-TFg=s900-c-k-c0x00ffffff-no-rj";
        String salg1 = "data:image/jpeg;base64,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";
        String salg2 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRv8GFfFzWk-SFABLUO3v8x2P9eHXp5l0Yqlg&s";
        String doce1 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQno5Dt0PYxth20VEpdV48XR6s47lZef0sHSQ&s";
        String doce2 = "https://img.deliverydireto.com.br/unsafe/origxorig/https://duisktnou8b89.cloudfront.net/img/stores/64c7b59de71e3.png";
        String pizz1 = "https://iguatemi.com.br/saojosedoriopreto/sites/saojosedoriopreto/files/logo-1360205848996.png";
        String pizz2 = "https://play-lh.googleusercontent.com/KBcXULt2lL9nGtn6WZzzJ-7uwiqMaVj0D_SOObMqS99X4hiLHrfyMFazsbpxu0KNddo";
        String japa1 = "https://static.ifood-static.com.br/image/upload/t_high/logosgde/68d77b2b-cb02-4d3a-abc1-7e4b2bfa9787/202503201644_4nIG_l.jpg";
        String japa2 = "https://play-lh.googleusercontent.com/mHUaPq-EwnR74PaP7if9N5lEZzzNxvnXk1FFBaU5hE2mwgOzg7qkYGh4UjoOxBiQzg";
        String erro1 = "https://cdn0.iconfinder.com/data/icons/shift-free/32/Error-512.png";

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            userText.setText(currentUser.getDisplayName());
            Uri photoUri = currentUser.getPhotoUrl();
            if (photoUri != null) {
                Glide.with(this).load(photoUri).into(img);
            }
        } else {
            Toast.makeText(this, "Usuário não logado!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish(); // <- impede continuar na tela de prefs sem login
            return;
        }

    /* Obsoleto
       Loading the user image >>
        if (userPhoto != null) {
            Glide.with(this)
                    .load(userPhoto)
                    .into(img);
        }*/

        if (userName != null && !userName.isEmpty()) {
            userText.setText(userName);
        }

        Intent prefIntent = new Intent();
        prefIntent = getIntent();

        String userPref = prefIntent.getStringExtra("pref_food");

        //Aqui vem o EITA --> ;-;
        //Isso deve se tornar um switch em uma versão mais nova
        /*

        Pego o valor da intent e recebo ele como uma variavel, depois troco a fonte da imagem base
        na string repassada da Intent, no valor "userPref".
        Aqui são cinco -->
        "hamb"
        "salg"
        "doce"
        "japa"
        "pizz"

        depois só arrumar os diagramas baseados nisso.

         */
        switch (Objects.requireNonNull(userPref)) {
            case "hamb":
                Glide.with(this)
                        .load(hamb1)
                        .into(foodOpt1);
                foodOptTxt1.setText("MacDonald's");
                //Falta add a outra imagem ->>
                Glide.with(this)
                        .load(hamb2)
                        .into(foodOpt2);
                foodOptTxt2.setText("BurgerKing");
                break;
            case "salg":
                Glide.with(this)
                        .load(salg1)
                        .into(foodOpt1);
                foodOptTxt1.setText("Hagazo");
                //Falta add a outra imagem ->>
                Glide.with(this)
                        .load(salg2)
                        .into(foodOpt2);
                foodOptTxt2.setText("Julipe");
                break;
            case "doce":
                Glide.with(this)
                        .load(doce1)
                        .into(foodOpt1);
                foodOptTxt1.setText("Cacau Brasil");
                //Falta add a outra imagem ->>
                Glide.with(this)
                        .load(doce2)
                        .into(foodOpt2);
                foodOptTxt2.setText("Cacau Show");
                break;
            case "japa":
                Glide.with(this)
                        .load(japa1)
                        .into(foodOpt1);
                foodOptTxt1.setText("Gendai");
                //Falta add a outra imagem ->>
                Glide.with(this)
                        .load(japa2)
                        .into(foodOpt2);
                foodOptTxt2.setText("China In Box");
                break;
            case "pizz":
                //Aqui é (teóricamente) obrigatoriamente ->> "pizz" <<-
                Glide.with(this)
                        .load(pizz1)
                        .into(foodOpt1);
                foodOptTxt1.setText("Patroni");
                //Falta add a outra imagem ->>
                Glide.with(this)
                        .load(pizz2)
                        .into(foodOpt2);
                foodOptTxt2.setText("Pizza Hut");
                break;
            default:
                //Aqui com certeza total absoluta e 100% crente que é um erro
                Glide.with(this)
                        .load(erro1)
                        .into(foodOpt1);
                foodOptTxt1.setText("ERRO");
                //Falta add a outra imagem ->>
                Glide.with(this)
                        .load(erro1)
                        .into(foodOpt2);
                foodOptTxt2.setText("ERRO");
        }
        subsBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPurchase = new Intent(PreferenceActivity.this, PurchaseActivity.class);
                startActivity(intentPurchase);
                finish();
            }
        });

        subsBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPurchase = new Intent(PreferenceActivity.this, PurchaseActivity.class);
                startActivity(intentPurchase);
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
        Intent backIntent = new Intent(this, FoodMenuActivity.class);
        startActivity(backIntent);
        finish();
    }
}
