package com.thecurseds.gamequest;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainLogin extends AppCompatActivity {


    FirebaseAuth auth;
    Button button;
    TextView textView;
    FirebaseUser user;
    Button historial;
    //Button otroPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logout);
        historial = findViewById(R.id.btn_historial);
        //otroPerfil = findViewById(R.id.btn_otro_perfil);
        textView = findViewById(R.id.user_details);
        user = auth.getCurrentUser();

        if (user == null){
            Intent intent = new Intent(getApplicationContext(),Loggin.class);
            startActivity(intent);
            finish();
        } else {

            textView.setText(user.getEmail());
        }

        button.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(),Loggin.class);
            startActivity(intent);
            finish();
        });

        historial.setOnClickListener(view -> {
            Intent intent = new Intent(this, historial.class);
            startActivity(intent);
        });
        /*otroPerfil.setOnClickListener(view -> {
            Intent intent = new Intent(this,PublicProfile.class);
            startActivity(intent);
        });*/



    }


    public void GoEditProfile (View v){
        Intent intento = new Intent(this, EditarMiPerfil.class);
        startActivity(intento);
    }
    public void GoSeeProfile (View v){
        Intent intento = new Intent(this, SeeProfile.class);
        startActivity(intento);
    }
    public void Volver (View v){
        Intent intent = new Intent(this, Shop.class);
        startActivity(intent);
    }
}