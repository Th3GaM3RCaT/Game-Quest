package com.thecurseds.gamequest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class PublicProfile extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();


    ArrayList<Resegna> arrayList;

    TextView txt_nombreUsuario;
    TextView txt_telefono;
    TextView txt_ciudad;
    RatingBar rtb_promedio;
    ImageView img_Profile;
    Button btn_irAResegnar;

    String userid;  //temporal

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_profile);

        userid = "KmeTNkKgB0dTGvas8XNlMRlP2QC3";    //temporal

        arrayList = new ArrayList<>();
        arrayList.add(new Resegna("hola","reseña 1",5));
        arrayList.add(new Resegna("hola","reseña 2",4));
        arrayList.add(new Resegna("hola","reseña 3",3));

        txt_nombreUsuario = findViewById(R.id.txt_nombreUsuario);
        txt_telefono = findViewById(R.id.txt_telefono);
        txt_ciudad = findViewById(R.id.txt_Ciudad);
        rtb_promedio = findViewById(R.id.rtb_promedio);
        img_Profile = findViewById(R.id.img_Profile);
        btn_irAResegnar = findViewById(R.id.btn_irAResegnar);

        btn_irAResegnar.setOnClickListener(view -> {
            Intent intent = new Intent(this, EscribirResegna.class);
            startActivity(intent);
        });
        cargarDatos();
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