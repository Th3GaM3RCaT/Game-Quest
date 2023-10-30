package com.thecurseds.gamequest;

import static android.widget.Toast.LENGTH_SHORT;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class EditarMiPerfil extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser User;
    ImageButton profile;
    EditText userName;
    EditText numberPhone;
    EditText eMail;
    EditText city;
    Button Save;
    Button Back;
    Uri uri;
    ActivityResultLauncher<Intent>
            mGetContent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    try {
                        assert result.getData() != null;
                        uri = result.getData().getData();
                        profile.setImageURI(uri);
                    } catch (Exception tryAgain) {
                        Toast.makeText(EditarMiPerfil.this, "Intente de nuevo", LENGTH_SHORT).show();
                    }
                }
            }
    );
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();


    @SuppressLint({"IntentReset", "InlinedApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        mAuth = FirebaseAuth.getInstance();


        profile = findViewById(R.id.img_Profile);
        userName = findViewById(R.id.lbl_userName);
        numberPhone = findViewById(R.id.lbl_numberPhone);
        eMail = findViewById(R.id.lbl_mail);
        city = findViewById(R.id.lbl_city);
        Save = findViewById(R.id.btn_guardar);
        Back = findViewById(R.id.btn_volver);

        User = mAuth.getCurrentUser();

        profile.setOnClickListener(view -> {
            // Pass in the mime type you want to let the user select
            // as the input
            Intent intent;
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
                mGetContent.launch(intent.setType("image/*"));
            } else {
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                mGetContent.launch(intent.setType("image/*"));
            }

        });

        city.setOnClickListener(view -> Save.setEnabled(true));

        Save.setOnClickListener(View -> GuardarDatos());

        Back.setOnClickListener(View -> finish());

        CargarDatos();

    }

    private void GuardarDatos() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();
        //a base de datos
        user.put("UserId", User.getUid());
        user.put("Name", String.valueOf(userName.getText()));
        user.put("Phone", String.valueOf(numberPhone.getText()));
        user.put("eMail", String.valueOf(eMail.getText()));
        user.put("City", String.valueOf(city.getText()));
        user.put("DateHour", FechaHora());
        SubirFoto(uri);
        

        //a usuario
        ModificarDatos();

        // Add a new document with a generated ID
        db.collection("usuarios").add(user);
    }

    private void CargarDatos() {
        Session();

        userName.setText(User.getDisplayName());
        numberPhone.setText(User.getPhoneNumber());
        eMail.setText(User.getEmail());

        storageRef.child(User.getUid()+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                profile.setImageURI(uri);
            }
        });



        // EditText userName;
        //    EditText numberPhone;
        //    EditText eMail;
        //    EditText city;


    }

    private void ModificarDatos() {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(String.valueOf(userName.getText()))
                .setPhotoUri(uri)
                .build();
        User.updateProfile(profileUpdates)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Datos Actualizados", LENGTH_SHORT).show();
                    }
                });
    }

    private void Session() {
        if (User == null) {
            finish();
        }
    }

    @NonNull
    private String FechaHora() {
        String date;
        int year = LocalDateTime.now().getYear();
        int month = LocalDateTime.now().getMonthValue();
        int day = LocalDate.now().getDayOfMonth();
        int hour = LocalDateTime.now().getHour();
        int minute = LocalDateTime.now().getMinute();
        int second = LocalDateTime.now().getSecond();
        date = day + "/" + month + "/" + year + " " + hour + ":" + minute + ":" + second;
        return date;
    }

    @NonNull
    private String SubirFoto(Uri uri) {

        StorageReference spaceRef = storageRef.child(User.getUid() + "/profile.jpg");
        UploadTask uploadTask = spaceRef.putFile(uri);

        uploadTask.addOnFailureListener(exception -> Toast.makeText(this, "Error al subir foto", LENGTH_SHORT).show()
        ).addOnSuccessListener(taskSnapshot -> {
            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
        });
        return spaceRef.child(User.getUid() + "/profile.png").getDownloadUrl().toString();
    }
}