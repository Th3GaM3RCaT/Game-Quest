package com.thecurseds.gamequest;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;

public class EditarMiPerfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);
    }
    public void IrInicio (View v){
        finish();
    }
    public void CambiarFoto (){
        Image i;
        i= null;
    }

}