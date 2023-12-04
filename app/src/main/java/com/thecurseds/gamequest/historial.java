package com.thecurseds.gamequest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class historial extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Transaccion> transaccionList;
    TransaccionAdapter transaccionAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        recyclerView = findViewById(R.id.recyclerview_transaccion);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(historial.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        transaccionList = new ArrayList<>();
        ConsultaDB("vendedor");
        ConsultaDB("comprador");
    }
    private void generarRecycler(){
        transaccionAdapter = new TransaccionAdapter(historial.this,transaccionList);
        recyclerView.setAdapter(transaccionAdapter);
    }
    private void ConsultaDB(String usuario) {
        db.collection("transacciones").whereEqualTo(usuario, user.getUid()).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String compradorID = document.getString("comprador");
                            String vendedorID = document.getString("vendedor");
                            String juegoID = document.getString("juego");
                            String tipoTransaccion = document.getString("tipo de transaccion");
                            String fecha = document.getString("fecha");
                            obtenerNombreUsuario(compradorID, nombreComprador -> {
                                obtenerNombreUsuario(vendedorID, nombreVendedor -> {
                                    obtenerDetallesJuego(juegoID, juegoDetalles -> {
                                        Transaccion transaccion = new Transaccion(
                                                nombreComprador, nombreVendedor,
                                                juegoDetalles.get("nombre"),
                                                tipoTransaccion, fecha,
                                                juegoDetalles.get("descripcion"),
                                                juegoDetalles.get("precio"));
                                        transaccionList.add(transaccion);
                                        generarRecycler();
                                    });
                                });
                            });
                        }
                    } else {
                        Toast.makeText(this, "Error getting documents: " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void obtenerNombreUsuario(String userID, Consumer<String> onNombreObtenido) {
        db.collection("usuarios").document(userID).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String nombre = documentSnapshot.getString("Name");
                        onNombreObtenido.accept(nombre);
                    } else onNombreObtenido.accept("Nombre no encontrado");
                })
                .addOnFailureListener(e -> {
                    onNombreObtenido.accept("Error al obtener nombre");
                });
    }
    private void obtenerDetallesJuego(String juegoID, Consumer<Map<String, String>> onDetallesObtenidos) {
        db.collection("juego").document(juegoID).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Map<String, String> detalles = new HashMap<>();
                        detalles.put("nombre", documentSnapshot.getString("nombre"));
                        detalles.put("descripcion", documentSnapshot.getString("descripcion"));
                        detalles.put("precio", documentSnapshot.getString("precio"));
                        onDetallesObtenidos.accept(detalles);
                    } else {
                        Map<String, String> detallesError = new HashMap<>();
                        detallesError.put("nombre", "Juego no encontrado");
                        detallesError.put("descripcion", "Descripción no encontrada");
                        detallesError.put("precio", "Precio no encontrado");
                        onDetallesObtenidos.accept(detallesError);
                    }
                })
                .addOnFailureListener(e -> {
                    Map<String, String> detallesError = new HashMap<>();
                    detallesError.put("nombre", "Error al obtener juego");
                    detallesError.put("descripcion", "Error al obtener descripción");
                    detallesError.put("precio", "Error al obtener precio");
                    onDetallesObtenidos.accept(detallesError);
                });
    }
}