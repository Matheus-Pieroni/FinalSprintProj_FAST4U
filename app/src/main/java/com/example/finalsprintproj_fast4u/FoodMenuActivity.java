package com.example.finalsprintproj_fast4u;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class FoodMenuActivity extends MainActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        img = findViewById(R.id.userPhoto);
        TextView userText = findViewById(R.id.userText);

        //recebendo as
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
    }
}
