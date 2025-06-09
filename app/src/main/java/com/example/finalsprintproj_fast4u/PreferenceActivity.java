package com.example.finalsprintproj_fast4u;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

public class PreferenceActivity extends AppCompatActivity {

    ImageView img;

    TextView userText;
    Uri userPhoto;

    private FirebaseAuth mAuth;

    String userName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pref);

        userText = findViewById(R.id.userText);

        //Pegando as coisas do usuário pra ele. ;]
        if (mAuth.getCurrentUser() != null) {
            userText.setText(mAuth.getCurrentUser().getDisplayName());
            userPhoto = mAuth.getCurrentUser().getPhotoUrl();
        } else {
            Toast.makeText(this, "HOUVE UM ERRO AO ENCONTRAR O USUÁRIO ATIVO!", Toast.LENGTH_LONG).show();
        }

//Loading the image >>
        if (userPhoto != null) {
            Glide.with(this)
                    .load(userPhoto)
                    .into(img);
        }

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
        Aquelas aulas de OOP não se tornaram verdade depois de muito trabalhar com o android studio.

         */
        if (userPref == "hamb") {

        } else
            if (userPref == "salg") {

            } else
                if (userPref == "doce") {

                } else
                    if (userPref == "japa") {

                } else //Aqui é obrigatoriamente ->> "pizz" <<-
                     {

                    }
    }
}
