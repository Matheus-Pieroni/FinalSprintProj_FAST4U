package com.example.finalsprintproj_fast4u;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import androidx.credentials.CredentialManager;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.Credential;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.*;

import com.google.android.libraries.identity.googleid.GetGoogleIdOption;
import com.google.android.libraries.identity.googleid.GoogleIdCredential;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "GoogleSignIn";
    private CredentialManager credentialManager;
    private Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        credentialManager = CredentialManager.create(this);

        signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGoogleCredentialManager();
            }
        });
    }

    private void signInWithGoogleCredentialManager() {
        String webClientId = "509687757481-bhhm7l2uen56t5jocgo6c87jggmmmghl.apps.googleusercontent.com";

        GetGoogleIdOption googleIdOption = new GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(true)
                .setServerClientId(webClientId)
                .build();

        GetCredentialRequest request = new GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build();

        credentialManager.getCredentialAsync(this, request)
                .addOnSuccessListener(result -> handleCredentialResponse(result))
                .addOnFailureListener(e -> handleCredentialFailure(e));
    }

    private void handleCredentialResponse(GetCredentialResponse response) {
        Credential credential = response.getCredential();

        if (credential instanceof GoogleIdCredential) {
            GoogleIdCredential googleId = (GoogleIdCredential) credential;
            String idToken = googleId.getIdToken();
            String email = googleId.getId();
            String displayName = googleId.getDisplayName();

            Log.d(TAG, "Login com sucesso!");
            Log.d(TAG, "Nome: " + displayName);
            Log.d(TAG, "Email: " + email);
            Log.d(TAG, "ID Token: " + idToken);

            updateUI(email, displayName);
        } else {
            Log.w(TAG, "Tipo de credencial inesperado: " + credential.getType());
            Toast.makeText(this, "Erro: Tipo de credencial inesperado.", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("RestrictedApi")
    private void handleCredentialFailure(Exception e) {
        if (e instanceof GetCredentialException) {
            Log.e(TAG, "Erro ao obter credencial: " + ((GetCredentialException) e).getErrorMessage());

            if (e instanceof NoDataFromCredentialProviderException) {
                Toast.makeText(this, "Login cancelado ou nenhuma conta Google.", Toast.LENGTH_SHORT).show();
            } else if (e instanceof NoAvailableCredentialsException) {
                Toast.makeText(this, "Nenhuma credencial Google disponível.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Falha ao obter credencial.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.e(TAG, "Erro inesperado.", e);
            Toast.makeText(this, "Erro inesperado no login.", Toast.LENGTH_LONG).show();
        }
    }

    private void updateUI(String email, String displayName) {
        if (email != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("user_email", email);
            intent.putExtra("user_display_name", displayName);
            startActivity(intent);
            finish();
        }
    }

    // Se você quiser criar credenciais para primeiro login (não obrigatório nesta etapa)
    private ActivityResultLauncher<?> createCredentialLauncher;
}
