package com.thecurseds.gamequest;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class AddCardDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card_detail);

        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextCardNumber = findViewById(R.id.editTextCardNumber);
        EditText editTextExpiryDate = findViewById(R.id.editTextExpiryDate);
        EditText editTextCVV = findViewById(R.id.editTextCVV);
        Button buttonPay = findViewById(R.id.buttonPay);
        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    // Procesa el pago
                    Toast.makeText(AddCardDetailsActivity.this, "Pago realizado", Toast.LENGTH_SHORT).show();
                }

            }

            private boolean validateFields() {

                boolean isValid = true;

                if (editTextName.getText().toString().isEmpty()) {
                    editTextName.setError("Este campo es obligatorio");
                    isValid = false;
                }

                if (editTextCardNumber.getText().toString().isEmpty()) {
                    editTextCardNumber.setError("Este campo es obligatorio");
                    isValid = false;
                }

                if (!isValidExpiryDateFormat(editTextExpiryDate.getText().toString())) {
                    editTextExpiryDate.setError("Este campo es obligatorio,ingrese la fecha de vencimiento en formato MM/YY");
                    isValid = false;
                }

                if (editTextCVV.getText().toString().isEmpty()) {
                    editTextCVV.setError("Este campo es obligatorio");
                    isValid = false;
                }

                return isValid;

            }
            private boolean isValidExpiryDateFormat(String expiryDate) {
                // Regular expression for the MM/YY format
                String expiryDateFormatPattern = "^(0[1-9]|1[0-2])/([0-9]{2})$";
                return expiryDate.matches(expiryDateFormatPattern);
            }


        });

        editTextExpiryDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 3 && !s.toString().contains("/")) {
                    editTextExpiryDate.setText(s.toString().substring(0, 2) + "/");
                    editTextExpiryDate.setSelection(3);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 2) {
                    int month = Integer.parseInt(s.toString());
                    if (month < 1 || month > 12) {
                        s.delete(1, 2);
                        Toast.makeText(AddCardDetailsActivity.this,
                                "Mes no válido, por favor ingrese un mes válido (01-12)",
                                Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}