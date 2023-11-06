package com.thecurseds.gamequest;


import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class VerPerfil extends AppCompatActivity {

    TextView UserName;
    TextView PhoneNumber;
    TextView Mail;
    TextView Address;
    ImageView Profile;
    FirebaseUser user;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_perfil);
        UserName = findViewById(R.id.txt_nombreUsuario);
        PhoneNumber = findViewById(R.id.txt_telefono);
        Mail = findViewById(R.id.txt_Ciudad);
        Address = findViewById(R.id.txt_valoracion);
        Profile = findViewById(R.id.img_Profile);
        UserData();
        cargarDatos();
    }

    public void GoMain(View v) {
        finish();
    }

    public void UserData() {
        UserName.setText("Nombre de Usuario");
        PhoneNumber.setText("Número de teléfono");
        Mail.setText("correo@mail.com");
        Address.setText("Ciudad, Región");

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            UserName.setText(user.getDisplayName());
            Mail.setText(user.getEmail());

            //userPhoto = user.getPhotoUrl();
            //Profile.setImageURI(userPhoto);

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();


        }else {
            finish();
        }


    }
    private void cargarDatos(){

        //datos
        db.collection("usuarios")
                .document(user.getUid()).get().addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()){
                        PhoneNumber.setText(documentSnapshot.getString("Phone"));
                        Address.setText(documentSnapshot.getString("City"));
                        UserName.setText(documentSnapshot.getString("Name"));
                        Mail.setText(documentSnapshot.getString("eMail"));
                    }
                });
        //imagen
        StorageReference imageref = storage.getReference().child(user.getUid()+".jpg");
        imageref.getBytes(1024*1024)
                .addOnSuccessListener(bytes -> {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0, bytes.length);
                    Profile.setImageBitmap(bitmap);
                });
    }


}