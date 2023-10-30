package com.thecurseds.gamequest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.thecurseds.gamequest.adapter.GameAdapter;
import com.thecurseds.gamequest.model.Game;

import java.util.ArrayList;

public class Shop extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList <Game> gameArrayList;
    GameAdapter gameAdapter;
    FirebaseFirestore db;


    Button BtnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        this.setTitle("Aca Va la tienda");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BtnAdd = findViewById(R.id.BtnAdd);
        recyclerView = findViewById(R.id.RVSingle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        gameArrayList = new ArrayList<Game>();
        gameAdapter = new  GameAdapter(Shop.this,gameArrayList);


        EventChangeListener();



        BtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( Shop.this , AddGame.class));
            }
        });
    }

    private void EventChangeListener() {
        db.collection("juego").orderBy("nombre", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    if (error != null){

                        Log.e("firestore error",error.getMessage());
                        return;
                    }
                    for (DocumentChange dc : value.getDocumentChanges()){

                        if (dc.getType() == DocumentChange.Type.ADDED){
                            gameArrayList.add(dc.getDocument().toObject(Game.class));
                        }

                        gameAdapter.notifyDataSetChanged();
                    }

                    }
                });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}