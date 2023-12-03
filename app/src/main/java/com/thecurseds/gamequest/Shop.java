package com.thecurseds.gamequest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;

import java.util.ArrayList;

public class Shop extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseFirestore db;
    Button BtnAdd;

    // ArrayList para almacenar los datos del RecyclerView
    ArrayList<game_md> gameList;
    game_ad gameAdapter;
    Mqtt3AsyncClient client = MqttClient.builder()

            .useMqttVersion3()
            .identifier("fdb426aa6db546d487e07d0bf966aca2.s2.eu.hivemq.cloud")
            .serverPort(8883)
            .useSslWithDefaultConfig()
            .buildAsync();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        this.setTitle("Aca Va la tienda");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BtnAdd = findViewById(R.id.BtnAdd);
        BtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Shop.this, AddGame.class));
            }
        });

        // Configuración del RecyclerView
        recyclerView = findViewById(R.id.recyV);
        db = FirebaseFirestore.getInstance();

        // Inicialización del ArrayList y del adaptador
        gameList = new ArrayList<>();
        gameAdapter = new game_ad(gameList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(gameAdapter);

        // Recuperar datos de Firebase y actualizar el adaptador
        db.collection("juego")
                .orderBy("nombre", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.e("Firestore Error", error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()) {
                            switch (dc.getType()) {
                                case ADDED:
                                    // Se añadió un nuevo juego
                                    game_md game = dc.getDocument().toObject(game_md.class);

                                    // Verifica que los campos no sean nulos antes de agregar a la lista
                                    if (game != null && game.getNombre() != null && game.getDescripcion() != null && game.getPrecio() != null) {
                                        gameList.add(game);
                                    }
                                    break;
                                // Agrega casos para MODIFIED y REMOVED según sea necesario
                            }
                        }

                        // Actualizar el adaptador después de agregar los juegos
                        gameAdapter.notifyDataSetChanged();
                    }
                });

    }
        @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}

