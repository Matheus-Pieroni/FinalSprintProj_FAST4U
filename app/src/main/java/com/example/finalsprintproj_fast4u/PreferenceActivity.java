package com.example.finalsprintproj_fast4u;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PreferenceActivity extends AppCompatActivity {

    private static final String TAG = "PreferenceActivity";

    // --- UI and Firebase Member Variables ---
    private ImageView userProfileImage, foodOptionImage1, foodOptionImage2;
    private TextView userNameText, foodOptionText1, foodOptionText2;
    private Button subscribeButton1, subscribeButton2;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pref);

        // --- Step 1: Initialize Services and Views ---
        initializeServicesAndViews();

        // --- Step 2: Check for a Logged-In User ---
        if (currentUser == null) {
            Toast.makeText(this, "Usuário não encontrado. Redirecionando...", Toast.LENGTH_SHORT).show();
            goToFoodMenuActivity(); // Go back if user is not logged in
            return;
        }

        // --- Step 3: Populate Common UI Elements ---
        populateUserData();

        // --- Step 4: Get Food Preference and Load Specific Content ---
        String foodPreference = getIntent().getStringExtra("pref_food");
        if (foodPreference != null) {
            Log.d(TAG, "Received food preference: " + foodPreference);
            loadFoodOptions(foodPreference);
        } else {
            // Handle case where no preference was passed
            Log.e(TAG, "No food preference was passed from FoodMenuActivity.");
            Toast.makeText(this, "Erro: Nenhuma preferência selecionada.", Toast.LENGTH_LONG).show();
            // Hide the options if there's nothing to show
            foodOptionImage1.setVisibility(View.GONE);
            foodOptionImage2.setVisibility(View.GONE);
            foodOptionText1.setVisibility(View.GONE);
            foodOptionText2.setVisibility(View.GONE);
            subscribeButton1.setVisibility(View.GONE);
            subscribeButton2.setVisibility(View.GONE);
        }

        // --- Step 5: Setup Click Listeners ---
        setupClickListeners();
    }

    /**
     * Initializes Firebase services and assigns all UI views.
     */
    private void initializeServicesAndViews() {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        // Header UI
        userProfileImage = findViewById(R.id.userPhoto);
        userNameText = findViewById(R.id.userText);

        // Food Option 1 UI
        foodOptionImage1 = findViewById(R.id.foodOpt1);
        foodOptionText1 = findViewById(R.id.foodOptTxt1);
        subscribeButton1 = findViewById(R.id.subsBtn1);

        // Food Option 2 UI
        foodOptionImage2 = findViewById(R.id.foodOpt2);
        foodOptionText2 = findViewById(R.id.foodOptTxt2);
        subscribeButton2 = findViewById(R.id.subsBtn2);
    }

    /**
     * Populates the header with the current user's name and profile picture.
     */
    private void populateUserData() {
        userNameText.setText(currentUser.getDisplayName());
        Uri photoUrl = currentUser.getPhotoUrl();

        Glide.with(this)
                .load(photoUrl)
                .placeholder(R.drawable.ic_default_user)
                .error(R.drawable.ic_default_user)
                .into(userProfileImage);
    }

    /**
     * Loads the correct text and images based on the user's food preference.
     * @param preference The food choice passed from the previous activity (e.g., "Hamburger").
     */
    private void loadFoodOptions(String preference) {
        // Here we define the content for each preference.
        // This makes it super easy to change names or image URLs later.
        String title1 = "", imageUrl1 = "", title2 = "", imageUrl2 = "";

        switch (preference) {
            case "Hamburger":
                title1 = "McDonald's";
                imageUrl1 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSq4sIWzdKqCadotiXRKQ0zndmbPGABUHGxJA&s";
                title2 = "Burger King";
                imageUrl2 = "https://raichu-uploads.s3.amazonaws.com/logo_null_spPge9.png";
                break;
            case "Pizza":
                title1 = "Domino's";
                imageUrl1 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQY8C9EEKyH-QuIHhSdpkCBQpM7D_D-HVCz1Q&s";
                title2 = "Pizza Hut";
                imageUrl2 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ5F7IDxxCSN_jDs9pcGZwABLpTY0zMX66qDQ&s";
                break;
            case "Doce":
                title1 = "Cacau Show";
                imageUrl1 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQGObterCpw46VO3cqPcAPJjiHSRXpMZhj-tA&s";
                title2 = "Sodiê Doces";
                imageUrl2 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1RkYCCWpiC7Vp3wsUgv4dJOrRZ96DwoeOFQ&s";
                break;
            case "Salgado": // <<< FIXED: ADDED SALGADO CASE
                title1 = "Habib's";
                imageUrl1 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSM8q-A5_me6xPHIB0zBUN9-QWnAIAn2QF4eQ&s";
                title2 = "Ragazzo";
                imageUrl2 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJ3FZIdWmrRKoZjUA-LsV4zdLDghj1JobCHA&s";
                break;
            case "Japonesa":
                title1 = "Gendai";
                imageUrl1 = "https://gsobmidia.com.br/uploads/lojas/1367/gendai_1610020899.jpg";
                title2 = "China in Box";
                imageUrl2 = "https://play-lh.googleusercontent.com/mHUaPq-EwnR74PaP7if9N5lEZzzNxvnXk1FFBaU5hE2mwgOzg7qkYGh4UjoOxBiQzg";
                break;
            // Add other cases for "Salgado", "Japonesa", etc.
            default:
                // Fallback if the preference is unknown
                Log.w(TAG, "Unknown preference: " + preference);
                Toast.makeText(this, "Opções para '" + preference + "' não encontradas.", Toast.LENGTH_SHORT).show();
                break;
        }

        // Set the text for the options
        foodOptionText1.setText(title1);
        foodOptionText2.setText(title2);

        // Use Glide to load the images for the options
        Glide.with(this).load(imageUrl1).placeholder(R.drawable.ic_default_user).into(foodOptionImage1);
        Glide.with(this).load(imageUrl2).placeholder(R.drawable.ic_default_user).into(foodOptionImage2);
    }

    /**
     * Sets up click listeners for the subscribe buttons.
     */
    private void setupClickListeners() {
        // When a subscribe button is clicked, go to the final confirmation screen.
        View.OnClickListener listener = v -> {
            Intent intent = new Intent(PreferenceActivity.this, ActivityPrchsDone.class);
            startActivity(intent);
            finish();
        };

        subscribeButton1.setOnClickListener(listener);
        subscribeButton2.setOnClickListener(listener);
    }

    /**
     * Handles the physical back button press.
     * Takes the user back to the food menu.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToFoodMenuActivity();
    }

    /**
     * Navigates the user back to the FoodMenuActivity.
     */
    private void goToFoodMenuActivity() {
        Intent intent = new Intent(PreferenceActivity.this, FoodMenuActivity.class);
        startActivity(intent);
        finish(); // Finish this activity
    }
}
