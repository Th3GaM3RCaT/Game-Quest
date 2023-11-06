package com.thecurseds.gamequest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class PerfilPublico extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();

    private RecyclerView recyclerView;
    private ArrayList<Resegna> arrayList;

    private TextView txt_nombreUsuario;
    private TextView txt_telefono;
    private TextView txt_ciudad;
    private RatingBar rtb_promedio;
    private ImageView img_Profile;
    private Button btn_irAResegnar;

    private String userid;  //temporal



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userid = "KmeTNkKgB0dTGvas8XNlMRlP2QC3";    //temporal

        arrayList = new ArrayList<>();
        arrayList.add(new Resegna());

        txt_nombreUsuario = findViewById(R.id.txt_nombreUsuario);
        txt_telefono = findViewById(R.id.txt_telefono);
        txt_ciudad = findViewById(R.id.txt_Ciudad);
        rtb_promedio = findViewById(R.id.rtb_promedio);
        img_Profile = findViewById(R.id.img_Profile);
        btn_irAResegnar = findViewById(R.id.btn_irAResegnar);

        recyclerView = findViewById(R.id.rview_resegna);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(arrayList);
        //recyclerView.setAdapter(recyclerAdapter);

        cargarDatos();
        /*btn_irAResegnar.setOnClickListener(view -> {
            Intent intento = new Intent(this, EscribirResegna.class);
            startActivity(intento);
        });*/
    }

    private void cargarDatos(){

        db.collection("usuarios")
                .document(userid).get().addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()){
                        txt_telefono.setText(documentSnapshot.getString("Phone"));
                        txt_telefono.setText(documentSnapshot.getString("City"));
                        txt_nombreUsuario.setText(documentSnapshot.getString("Name"));
                    }
                });
        StorageReference imageref = storage.getReference().child(userid+".jpg");
        imageref.getBytes(1024*1024)
                .addOnSuccessListener(bytes -> {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0, bytes.length);
                    img_Profile.setImageBitmap(bitmap);
                });
    }
}