package com.thecurseds.gamequest;

import static com.thecurseds.gamequest.R.id.list_users;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;

public class Chat extends AppCompatActivity {

    private String uid;
    private RecyclerView recyclerView;
    private ArrayList<UsuarioChat> usuarioChats;
    private UsuarioAdapter usuarioAdapter;
    private Button btnSendMessage;
    private DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    FirebaseUser User;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mAuth = FirebaseAuth.getInstance();
        uid = "dYrKvcqlZFaNiAVWLdzACytyYkw1";

        User = mAuth.getCurrentUser();

        recyclerView = findViewById(list_users);
        usuarioChats = new ArrayList<>();
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(Chat.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnSendMessage = findViewById(R.id.btn_send_message);



        databaseReference = FirebaseDatabase.getInstance().getReference().child("usuarios");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    //if (snapshot.ge)
                    StorageReference imageref = storage.getReference().child(uid+".jpg");
                    imageref.getBytes(1024*1024)
                            .addOnSuccessListener(bytes -> {
                                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0, bytes.length);
                                UsuarioChat user = new UsuarioChat(
                                        bitmap,
                                        "pepito",
                                        "hola"
                                );
                                usuarioChats.add(user);
                                crearRecycler();
                            });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
        if (false){
            guardarDB(uid);
        }

        crearRecycler();
    }

    private void crearRecycler() {
        usuarioAdapter = new UsuarioAdapter(Chat.this,usuarioChats);
        recyclerView.setAdapter(usuarioAdapter);
    }

    private void guardarDB(String uid){
        db.collection("usuarios")
                .document(uid).get().addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        HashMap map =new HashMap();
                        map.put("nombre",documentSnapshot.getString("Name"));
                        map.put("uid",documentSnapshot.getString("UserId"));
                        map.put("ultimo mensaje","chao xd");
                        databaseReference.child(User.getUid()).child(uid).setValue(map);
                        crearRecycler();
                    }
                });
        crearRecycler();
    }
}