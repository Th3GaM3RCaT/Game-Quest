package com.thecurseds.gamequest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button_compra;
    Button button_chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_compra = findViewById(R.id.btn_compra);

        button_compra.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddAmountActivity.class);
            startActivity(intent);
        });
        button_chat = findViewById(R.id.btn_chat);

        button_chat.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Chat.class);
            startActivity(intent);
        });
    }



    public void IrLogin (View v){
        Intent intent = new Intent(this, MainLogin.class);
        startActivity(intent);
    }

}