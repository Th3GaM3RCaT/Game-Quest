package com.thecurseds.gamequest;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registrar extends AppCompatActivity {

    TextInputEditText editTextEmail , editTextPassword;
    Button buttonReg;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        editTextEmail = findViewById(R.id.email);
        mAuth = FirebaseAuth.getInstance();
        editTextPassword = findViewById(R.id.password);
        buttonReg = findViewById(R.id.registerBtn);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.loginNow);
        textView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Loggin.class);
            startActivity(intent);
            finish();
        });


        buttonReg.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            String email, password;
            email = String.valueOf(editTextEmail.getText());
            password = String.valueOf(editTextPassword.getText()) ;

            if (TextUtils.isEmpty(email)){

                Toast.makeText(Registrar.this, "Coloca correo mano", Toast.LENGTH_SHORT).show();
                return;

            }
            if (TextUtils.isEmpty(password)){

                Toast.makeText(Registrar.this, "Coloca contraseña mano", Toast.LENGTH_SHORT).show();
                return;

            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {

                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {

                            Toast.makeText(Registrar.this, "Cuenta creada mano.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),Shop.class);
                            startActivity(intent);
                            finish();
                        }

                        else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Registrar.this, "este correo ya tiene una cuenta.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    });


        });


    }
}



