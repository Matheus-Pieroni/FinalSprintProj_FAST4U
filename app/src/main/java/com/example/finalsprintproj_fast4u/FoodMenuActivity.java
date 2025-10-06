package com.example.finalsprintproj_fast4u;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class FoodMenuActivity extends AppCompatActivity {

    private static final String TAG = "FoodMenuActivity";

    // Declare UI elements and Firebase services
    private ImageView userProfileImage, hamburgBtn, pizzaBtn, docesBtn, japaBtn, salgadBtn;
    private TextView userNameText;
    private Button logOutBtn;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        // 1. Initialize Firebase services
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        currentUser = mAuth.getCurrentUser(); // Get the current user once

        // 2. Initialize all your UI components
        initializeViews();

        // 3. Check if a user is actually logged in
        if (currentUser == null) {
            // If no user is logged in, something is wrong. Go back to Login screen.
            Toast.makeText(this, "Nenhum usuário logado. Redirecionando...", Toast.LENGTH_SHORT).show();
            goToLoginActivity();
            return; // Stop executing the rest of onCreate
        }

        // 4. Populate the UI with the logged-in user's data
        populateUserData();

        // 5. Set up all the click listeners for your buttons
        setupClickListeners();
    }

    /**
     * Finds and assigns all the views from the layout file.
     * This keeps the onCreate method clean.
     */
    private void initializeViews() {
        userNameText = findViewById(R.id.userText);
        userProfileImage = findViewById(R.id.userPhoto);
        hamburgBtn = findViewById(R.id.hamburgbtn);
        salgadBtn = findViewById(R.id.salgadbtn);
        docesBtn = findViewById(R.id.docesbtn);
        japaBtn = findViewById(R.id.japabtn);
        pizzaBtn = findViewById(R.id.pizzabtn);
        logOutBtn = findViewById(R.id.logOutBtn);
    }

    /**
     * Gets the current user's name and photo and displays them.
     */
    private void populateUserData() {
        userNameText.setText(currentUser.getDisplayName());
        Uri photoUrl = currentUser.getPhotoUrl();

        // Use Glide to load the user's profile picture, with a fallback default image
        Glide.with(this)
                .load(photoUrl)
                .placeholder(R.drawable.ic_default_user) // A good practice: a placeholder image
                .error(R.drawable.ic_default_user)       // An image to show if loading fails
                .into(userProfileImage);
    }

    /**
     * Sets up the onClick behavior for all the interactive buttons.
     */
    private void setupClickListeners() {
        // --- Food Preference Buttons ---
        // Each button calls the same method but passes a different food preference
        hamburgBtn.setOnClickListener(v -> onFoodPreferenceSelected("Hamburger"));
        pizzaBtn.setOnClickListener(v -> onFoodPreferenceSelected("Pizza"));
        docesBtn.setOnClickListener(v -> onFoodPreferenceSelected("Doce"));
        salgadBtn.setOnClickListener(v -> onFoodPreferenceSelected("Salgado"));
        japaBtn.setOnClickListener(v -> onFoodPreferenceSelected("Japonesa"));

        // --- Log Out Button ---
        logOutBtn.setOnClickListener(v -> {
            mAuth.signOut();
            Toast.makeText(this, "Você saiu.", Toast.LENGTH_SHORT).show();
            goToLoginActivity();
        });
    }

    /**
     * This single method handles the logic for any food preference button click.
     * @param foodPreference The string representing the user's choice (e.g., "Hamburger").
     */
    private void onFoodPreferenceSelected(String foodPreference) {
        // Save the chosen preference to Firestore
        saveFoodPreference(foodPreference);

        // Start the next activity (PreferenceActivity) and pass the choice
        Intent intent = new Intent(FoodMenuActivity.this, PreferenceActivity.class);
        intent.putExtra("pref_food", foodPreference);
        startActivity(intent);
        finish(); // Finish this activity
    }

    /**
     * Saves the user's food preference to their document in Firestore.
     * @param foodPreference The food preference to save.
     */
    private void saveFoodPreference(String foodPreference) {
        String userEmail = currentUser.getEmail();
        if (userEmail == null || userEmail.isEmpty()) {
            Log.e(TAG, "Cannot save preference, user email is null or empty.");
            return;
        }

        Map<String, Object> userPreferenceMap = new HashMap<>();
        userPreferenceMap.put("food_preference", foodPreference); // A clear key for the preference

        // Using SetOptions.merge() is excellent. It updates the field without overwriting the whole document.
        db.collection("usuarios").document(userEmail)
                .set(userPreferenceMap, SetOptions.merge())
                .addOnSuccessListener(aVoid -> Log.d(TAG, "User preference '" + foodPreference + "' was saved."))
                .addOnFailureListener(e -> Log.e(TAG, "Error saving user preference", e));
    }

    /**
     * Navigates the user back to the LoginActivity and clears the activity stack.
     */
    private void goToLoginActivity() {
        Intent intent = new Intent(FoodMenuActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    /**
     * Handles the back button press to exit the app cleanly.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity(); // Closes the app
    }
}
