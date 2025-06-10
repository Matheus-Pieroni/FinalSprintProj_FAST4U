package com.example.finalsprintproj_fast4u;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PurchaseActivity extends AppCompatActivity {
    ImageView img;
    TextView userName;
    Button send;
    EditText cardName, cardNum, cardVal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        FirebaseApp.initializeApp(this);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        //Você já sabe né, não preciso nem dizer o que é isso...
        img = findViewById(R.id.userPhoto);
        userName = findViewById(R.id.userName);
        cardName = findViewById(R.id.editTextNAMCARD);
        cardNum = findViewById(R.id.editTextNUMCARD);
        cardVal = findViewById(R.id.editTextDATECARD);
        send = findViewById(R.id.sendBtn);

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            userName.setText(currentUser.getDisplayName());
            Uri photoUri = currentUser.getPhotoUrl();
            if (photoUri != null) {
                Glide.with(this).load(photoUri).into(img);
            }
        } else {
            Toast.makeText(this, "Usuário não logado!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish(); // <- impede continuar na tela de prefs sem login
        }

        //Funções para limpar os campos em que o usuário clicar.
        boolean cardNameClear = false;
        boolean cardValClear = false;
        boolean cardNumClear = false;
        if (cardName.isEnabled() && !cardNameClear) {
            cardName.setText("");
            cardNameClear = true;
        }
        if (cardVal.isEnabled() && !cardValClear) {
            cardVal.setText("");
            cardValClear = true;
        }
        if (cardNum.isEnabled() && !cardNumClear) {
            cardNum.setText("");
            cardNumClear = true;
        }

        //Código remontado pelo gepeto.     Obrigado gepeto!    :J
        //Aqui é onde ocorre a verificação, ou seja mágica né, porque só pode.
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] assocBank = {"Bradesco", "Mastercard", "Itau", "Santander"};
                String userBank = cardName.getText().toString().trim();
                String cardDate = cardVal.getText().toString().trim();  // formato esperado: ddMMyyyy
                String cardNumUser = cardNum.getText().toString().trim();  // precisa de .getText()
                int cardNumVerif = 527; //Statico para testes e devops

                // Verificar banco
                boolean bancoValido = false;
                for (String banco : assocBank) {
                    if (banco.equalsIgnoreCase(userBank)) {
                        bancoValido = true;
                        break;
                    }
                }
                if (!bancoValido) {
                    Toast.makeText(PurchaseActivity.this, "ERRO: BANCO NÃO ENCONTRADO", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Verificar tamanho da data
                if (cardDate.length() != 8) {
                    Toast.makeText(PurchaseActivity.this, "ERRO: Data inválida. Use DDMMAAAA", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int dia = Integer.parseInt(cardDate.substring(0, 2));
                    int mes = Integer.parseInt(cardDate.substring(2, 4));
                    int ano = Integer.parseInt(cardDate.substring(4, 8));

                    if (dia < 1 || dia > 31) {
                        Toast.makeText(PurchaseActivity.this, "ERRO: Dia inválido", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (mes < 1 || mes > 12) {
                        Toast.makeText(PurchaseActivity.this, "ERRO: Mês inválido", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (ano < 2025) {
                        Toast.makeText(PurchaseActivity.this, "ERRO: Ano inválido", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Validar número do cartão
                    if (Double.parseDouble(cardNumUser) != cardNumVerif) {
                        Toast.makeText(PurchaseActivity.this, "ERRO: Número do cartão incorreto", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Se chegou até aqui, tudo está OK
                    Toast.makeText(PurchaseActivity.this, "Compra realizada com sucesso!", Toast.LENGTH_LONG).show();
                    compraRealizada();

                } catch (NumberFormatException e) {
                    Toast.makeText(PurchaseActivity.this, "ERRO: Data ou número inválido", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void compraRealizada() {
        Intent compraFin = new Intent(this, ActivityPrchsDone.class);
        startActivity(compraFin);
        finish();
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
