package com.example.finalsprintproj_fast4u;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.*;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "GoogleSignIn";

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private Button signInButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        signInButton = findViewById(R.id.signInButton);

        // Configura o cliente Google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)) // vem do google-services.json
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(view -> signIn());
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    firebaseAuthWithGoogle(account.getIdToken(), account);
                } else {
                    Toast.makeText(this, "Conta do Google inválida.", Toast.LENGTH_SHORT).show();
                }

            } catch (ApiException e) {
                Log.w(TAG, "Erro no Google sign in", e);
                Toast.makeText(this, "Falha no login: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Método logo obsoleto, NÃO SE DEVE PASSAR INFORMAÇÕES DE USUÁRIO POR INTENT'S ENTENDEU?!??!??
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent userData = new Intent(this, FoodMenuActivity.class);
            userData.putExtra("user-app-name", user.getDisplayName());
            userData.putExtra("user-app-photo", user.getPhotoUrl());
            startActivity(userData);
            finish();
        }
    }

    private void firebaseAuthWithGoogle(String idToken, GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        updateUI(null);
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            // Envia os dados do usuário para a próxima tela
            Intent userData = new Intent(this, FoodMenuActivity.class);
            userData.putExtra("user-app-name", user.getDisplayName());
            userData.putExtra("user-app-photo", user.getPhotoUrl()); // Uri é parcelable
            startActivity(userData);
            finish(); // Finaliza a tela de login
        } else {
            Toast.makeText(this, "Falha ao autenticar com Firebase", Toast.LENGTH_SHORT).show();
        }
    }

    // NÃO FAÇA logout no onDestroy, ou ele sempre voltará para login
    /*
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAuth.signOut(); // Removido para manter o login
    }
    */
}
