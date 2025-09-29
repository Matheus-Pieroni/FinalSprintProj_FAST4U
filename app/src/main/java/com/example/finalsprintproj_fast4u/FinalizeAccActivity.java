package com.example.finalsprintproj_fast4u;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.io.Serializable;
import java.util.HashMap;

public class FinalizeAccActivity extends AppCompatActivity implements Serializable {

    //Persistentes ->>
    private static final String TAG = "GoogleSignIn";

    //Instancia dos objetos
    RadioButton btnPizza;
    RadioButton btnJap;
    RadioButton btnSalg;
    RadioButton btnDoce;
    RadioButton btnHamb;
    RadioGroup foodPrefGr;

    Button finalizeAccBtn;

    TextView userAddress;

    //DB AND ACCOUNT RELATED THINGO
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizeacc);

        mAuth = FirebaseAuth.getInstance();


        btnPizza = findViewById(R.id.btnPizza);
        btnJap = findViewById(R.id.btnJap);
        btnSalg = findViewById(R.id.btnSalg);
        btnHamb = findViewById(R.id.btnHamb);
        btnDoce = findViewById(R.id.btnDoce);

        foodPrefGr = findViewById(R.id.foodPrefGr);

        finalizeAccBtn = findViewById(R.id.createAccBtn); // Botão para terminar de associar as informações.

        userAddress = findViewById(R.id.userAddress);

        // FALTA: Verificar as informações e subir valores correspondentes ao nosso banco de dados.


        //EXTRA - BD USEING FIREARSE FIREER

        if (mAuth.getCurrentUser() != null) {
            //Definingo todes es veriéveis ague
            String UserName = mAuth.getCurrentUser().getDisplayName();
            String UserEmail = mAuth.getCurrentUser().getEmail();
            String UserAddress = userAddress.toString();
            String UserFoodPref;

            //Ninho maligno e absurdo. I:U
            if (btnPizza.isChecked()) { UserFoodPref = "PIZ"; }
            else if (btnPizza.isChecked()) { UserFoodPref = "JAP"; }
            else if (btnPizza.isChecked()) { UserFoodPref = "HAM"; }
            else if (btnPizza.isChecked()) { UserFoodPref = "SAL"; }
            else { UserFoodPref = "DOC"; }

            //Pelo visto os dados serão dispostos em uma organização diferente.

            HashMap<String, String> userMap = new HashMap<String, String>();
            //userMap.put("Nome", UserName); Aqui eu não preciso dessa informação.
            userMap.put("Email", UserEmail);
            userMap.put("Address", UserAddress);
            userMap.put("comidPref", UserFoodPref);

            //userMap.put("Address", Address); -- Esse vai em outro lugar
            db.collection("usuarios").document(UserEmail)
                    .set(userMap, SetOptions.merge())
                    .addOnCompleteListener(task -> {
                        Log.d(TAG, "HOLY (:");
                    })
                    .addOnFailureListener(task -> {
                        Log.d(TAG, "HOLY :(");
                    });
        }

    }
}