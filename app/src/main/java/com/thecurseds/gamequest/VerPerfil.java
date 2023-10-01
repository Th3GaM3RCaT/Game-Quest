package com.thecurseds.gamequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class VerPerfil extends AppCompatActivity {

    TextView nombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_perfil);
        nombreUsuario = findViewById(R.id.txt_nombreUsuario);
        DatosUsuario();
    }
    public void IrInicio (View v){
        finish();
    }

    public void DatosUsuario (){
        nombreUsuario.setText("Rodrigo");

    }
}