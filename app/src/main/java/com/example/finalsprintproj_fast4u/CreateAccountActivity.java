package com.example.finalsprintproj_fast4u;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CreateAccountActivity extends AppCompatActivity implements Serializable{

    Button createAccBtn;
    EditText userNameTxt, userEmailTxt, userLocalTxt;
    Spinner userTypeSpinner;
    UserInfo userInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createacc);

        createAccBtn = findViewById(R.id.createAccBtn);
        userEmailTxt = findViewById(R.id.editTextUserEmail);
        userLocalTxt = findViewById(R.id.editTextUserLocal);
        userNameTxt = findViewById(R.id.editTextUserName);
        userTypeSpinner = findViewById(R.id.userTypeSpinner);
        createAccBtn = findViewById(R.id.createAccBtn);

        final String[] userPrefDef = {null};


        UserInfo user1 = new UserInfo();
        //Começa aqui a verificação para criação de usuário, que deverá ser passado atráves de uma intent com a tag "Serializable"


        createAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Crio um novo usuário local.

                if (!userNameTxt.getText().toString().isEmpty() || userNameTxt.getText() != null) {
                    user1.setNome(userNameTxt.getText().toString());

                    if (!userEmailTxt.getText().toString().isEmpty() || userEmailTxt.getText() != null) {
                        user1.setEmail(userEmailTxt.getText().toString());

                        if (!userLocalTxt.getText().toString().isEmpty() || userLocalTxt.getText() != null) {
                            user1.setUserAddress(userLocalTxt.getText().toString());

                            if (spinnerCheck(userPrefDef[0])) { //So termina se o spinnerCheck() retornar "true";
                                Toast.makeText(CreateAccountActivity.this, "Cadastro Realizado com Sucesso!", Toast.LENGTH_LONG).show();
                                creationCompleted(user1);

                            } else {
                                Toast.makeText(CreateAccountActivity.this, "", Toast.LENGTH_SHORT).show(); }

                        } else {
                            Toast.makeText(CreateAccountActivity.this, "", Toast.LENGTH_LONG).show(); }

                    } else {
                        Toast.makeText(CreateAccountActivity.this, "Email Inválido", Toast.LENGTH_LONG).show(); }

                } else {
                    Toast.makeText(CreateAccountActivity.this, "Nome de usuário Invalido", Toast.LENGTH_LONG).show(); }
            }
        });

// (opcional) capturar seleção {Totalmente necessário}
        userTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userPrefDef[0] = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Desnecessário.
            }
        });
    }
    public boolean spinnerCheck(String owo) {
        if (owo.isEmpty()) {
            Toast.makeText(this, "Dia Inválido ou Não Selecionado!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    //Só para retornar corretamente à pagina inicial.
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        goBack();
    }

    public void goBack() {
        Intent backIntent = new Intent(this, LoginActivity.class);
        startActivity(backIntent);
        finish();
    }

    public void creationCompleted(UserInfo userinfo) {
        if (userinfo.getNome() != null ) {
            Intent userInfo = new Intent(CreateAccountActivity.this, FoodMenuActivity.class);
            userInfo.putExtra("user-name", userNameTxt.getText().toString());
            userInfo.putExtra("user-address", userLocalTxt.getText().toString());
            userInfo.putExtra("user-email", userEmailTxt.getText().toString());
            startActivity(userInfo);
            finish();
        } else {
            Toast.makeText(CreateAccountActivity.this, "Usuário criado!", Toast.LENGTH_LONG).show();
            return;
        }
    }
}
