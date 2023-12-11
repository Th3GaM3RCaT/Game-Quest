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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class PublicProfile extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();



    TextView txt_nombreUsuario;
    TextView txt_telefono;
    TextView txt_ciudad;
    RatingBar rtb_promedio;
    ImageView img_Profile;
    Button btn_irAResegnar;

    RecyclerView recyclerView;
    List<Resegna> resegnaList;
    ReviewAdapter reviewAdapter;
    String userid;  //temporal

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_profile);

        userid = "KmeTNkKgB0dTGvas8XNlMRlP2QC3";    //temporal

        recyclerView = findViewById(R.id.recyclerView_resegna);

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
        ConsultaDB();
        generarRecycler();
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
    private void ConsultaDB() {
        db.collection("reseñas").whereEqualTo("id del reseñado", userid).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String quienResegno = document.getString("id de quien dejó la reseña");
                            String resenia = document.getString("reseña");
                            int valoracion = Integer.parseInt(document.getString("valoracion"));
                            StorageReference imageref = storage.getReference().child(quienResegno + ".jpg");
                            imageref.getBytes(1024 * 1024)
                                    .addOnSuccessListener(bytes -> {
                                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                        Resegna resegna = new Resegna(bitmap,resenia,valoracion);
                                        resegnaList.add(resegna);
                                        generarRecycler();
                                    });
                        }
                    }
                });
    }
    private void generarRecycler(){
        reviewAdapter = new ReviewAdapter(this,resegnaList);
        recyclerView.setAdapter(reviewAdapter);
    }
}