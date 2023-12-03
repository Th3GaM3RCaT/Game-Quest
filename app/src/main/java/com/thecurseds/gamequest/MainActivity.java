package com.thecurseds.gamequest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonCompra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonCompra = findViewById(R.id.btn_compra);
    }

    public void IrLogin (View v){
        Intent intent = new Intent(this, MainLogin.class);
        startActivity(intent);
    }




}