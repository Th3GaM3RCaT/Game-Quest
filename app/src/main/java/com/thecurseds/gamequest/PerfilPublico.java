package com.thecurseds.gamequest;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PerfilPublico extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Resegna> arrayList;

    private TextView txt_nombreUsuario;
    private TextView txt_telefono;
    private TextView txt_ciudad;
    private RatingBar rtb_promedio;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arrayList = new ArrayList<>();
        arrayList.add(new Resegna());

        txt_nombreUsuario = findViewById(R.id.txt_nombreUsuario);
        txt_telefono = findViewById(R.id.txt_telefono);
        txt_ciudad = findViewById(R.id.txt_Ciudad);
        rtb_promedio = findViewById(R.id.rtb_promedio);

        recyclerView = findViewById(R.id.rview_resegna);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(recyclerAdapter);


    }

}