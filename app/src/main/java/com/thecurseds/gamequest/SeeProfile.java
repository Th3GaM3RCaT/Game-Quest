package com.thecurseds.gamequest;


import androidx.appcompat.app.AppCompatActivity;


import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SeeProfile extends AppCompatActivity {

    TextView UserName;
    TextView PhoneNumber;
    TextView Mail;
    TextView Address;
    FirebaseUser user;
    Uri userPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_perfil);
        UserName = findViewById(R.id.txt_nombreUsuario);
        PhoneNumber = findViewById(R.id.txt_telefono);
        Mail = findViewById(R.id.txt_correo);
        Address = findViewById(R.id.txt_ciudadRegion);
        UserData();
    }

    public void GoMain(View v) {
        finish();
    }

    public void UserData() {
        UserName.setText("Nombre de Usuario");
        PhoneNumber.setText("Número de teléfono");
        Mail.setText("correo@mail.com");
        Address.setText("Ciudad, Región");

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            UserName.setText(user.getDisplayName());
            Mail.setText(user.getEmail());

            userPhoto = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();


        }


    }


}