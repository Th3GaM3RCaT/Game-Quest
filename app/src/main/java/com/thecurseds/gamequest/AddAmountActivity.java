package com.thecurseds.gamequest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class AddAmountActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_amount);


        Button buttonContinue = findViewById(R.id.buttonContinue);
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextAmount = findViewById(R.id.editTextAmount);
                String amount = editTextAmount.getText().toString();
                if(!amount.equals("")) {
                    Intent intent = new Intent(AddAmountActivity.this, AddCardDetailsActivity.class);
                    intent.putExtra("amount", amount);
                    startActivity(intent);
                } else {
                    Toast.makeText(AddAmountActivity.this, "Ingrese un monto válido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}