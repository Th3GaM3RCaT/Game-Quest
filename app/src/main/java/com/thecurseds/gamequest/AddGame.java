package com.thecurseds.gamequest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;

import java.util.HashMap;
import java.util.Map;

public class AddGame extends AppCompatActivity {

    Button AddGame;
    EditText EtName , EtPrecio , EtDescripcion;
    private  FirebaseFirestore mfirestore;

    Mqtt3AsyncClient client = MqttClient.builder()

            .useMqttVersion3()
            .identifier("fdb426aa6db546d487e07d0bf966aca2.s2.eu.hivemq.cloud")
            .serverPort(8883)
            .useSslWithDefaultConfig()
            .buildAsync();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);


        this.setTitle("Aca Se Agregan juegos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mfirestore = FirebaseFirestore.getInstance();



        EtPrecio = findViewById(R.id.EtPrecio);
        EtDescripcion = findViewById(R.id.EtDescripcion);
        EtName = findViewById(R.id.EtName);
        AddGame = findViewById(R.id.AddGame);

        AddGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreG = EtName.getText().toString().trim();
                String precioG = EtPrecio.getText().toString().trim();
                String descripcionG = EtDescripcion.getText().toString().trim();


                if (nombreG.isEmpty() && precioG.isEmpty() && descripcionG.isEmpty() ){
                    Toast.makeText(AddGame.this, "Ingresar los datos", Toast.LENGTH_SHORT).show();
                }else {
                    postGame (nombreG,precioG,descripcionG);
                }
            }
        });



    }

    private void postGame(String nombreG, String precioG, String descripcionG) {

        Map<String, Object> map = new HashMap<>();
        map.put("nombre", nombreG);
        map.put("precio",precioG);
        map.put("descripcion",descripcionG);
        mfirestore.collection("juego").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "creado exitosamente", Toast.LENGTH_SHORT).show();
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al ingresar datos", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }


}
