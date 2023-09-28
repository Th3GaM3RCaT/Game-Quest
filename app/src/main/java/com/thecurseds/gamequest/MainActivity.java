package com.thecurseds.gamequest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void IrEditarMiPerfil (View v){
        Intent intento = new Intent(this, EditarMiPerfil.class);
        startActivity(intento);
    }

    ///lo arregle ctm

}