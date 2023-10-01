package com.thecurseds.gamequest;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;


public class MainActivity extends AppCompatActivity {



    private Button registroButton;
    private Button logearseButton;


    public void IrEditarMiPerfil (View v){
        Intent intento = new Intent(this, EditarMiPerfil.class);
        startActivity(intento);
    }


    ///lo arregle ctm

}