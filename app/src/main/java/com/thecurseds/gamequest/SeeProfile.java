package com.thecurseds.gamequest;


import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.auth.data.model.PhoneNumber;


public class SeeProfile extends AppCompatActivity {

    TextView UserName;
    TextView PhoneNumber;
    TextView Mail;
    TextView Address;

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
    public void GoMain(View v){
        finish();
    }

    public void UserData(){
        UserName.setText("Rodrigo");
        PhoneNumber.setText("+56 9 1234 5678");
        Mail.setText("correo@mail.com");
        Address.setText("Ciudad, Región");
        

    }


}