package com.thecurseds.gamequest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registrar extends AppCompatActivity {



    private EditText emailB;
    private EditText password;
    private Button registrarseButton;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        emailB =findViewById(R.id.emailB);
        password =findViewById(R.id.password);
        registrarseButton =findViewById(R.id.registrarseButton);
        auth = FirebaseAuth.getInstance();


        registrarseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String txt_email = emailB.getText().toString();
               String txt_password = password.getText().toString();


               if   (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                   Toast.makeText(Registrar.this, "Sin Nada mano", Toast.LENGTH_SHORT).show();
               } else if (txt_password.length()< 6) {
                   Toast.makeText(Registrar.this, "Demaciado Facil Papito", Toast.LENGTH_SHORT).show();
               }else {
                   registerUser(txt_password , txt_email);
               }


            }

        });




    }

    private void registerUser(String emailB, String password) {

        auth.createUserWithEmailAndPassword(emailB, password).addOnCompleteListener(Registrar.this , new  OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Toast.makeText(Registrar.this, "Registrado manito", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Registrar.this, "Fallo manito todo de nuevo nomas causa :v ", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}