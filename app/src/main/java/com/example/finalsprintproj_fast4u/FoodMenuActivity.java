package com.example.finalsprintproj_fast4u;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class FoodMenuActivity extends AppCompatActivity {
    ImageView img;
    ImageView hamburgbtn;
    ImageView pizzabtn;
    ImageView docesbtn;
    ImageView japabtn;
    ImageView salgadbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        //Instanciando os objetos a serem utilizados (ou coisas já nem sei mais)
        img = findViewById(R.id.userPhoto);
        TextView userText = findViewById(R.id.userText);
        hamburgbtn = findViewById(R.id.hamburgbtn);
        salgadbtn = findViewById(R.id.salgadbtn);
        docesbtn = findViewById(R.id.docesbtn);
        japabtn = findViewById(R.id.japabtn);
        pizzabtn = findViewById(R.id.pizzabtn);

        //recebendo as coisinhas
        Intent userData = getIntent();
        Uri userPhoto = userData.getParcelableExtra("user-app-photo");
        String userName = userData.getStringExtra("user-app-name");

//Loading the image >>
        if (userPhoto != null) {
            Glide.with(this)
                    .load(userPhoto)
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
    }
}
