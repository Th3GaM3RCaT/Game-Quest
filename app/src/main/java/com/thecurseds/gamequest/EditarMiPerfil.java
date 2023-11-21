package com.thecurseds.gamequest;

import static android.widget.Toast.LENGTH_SHORT;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class EditarMiPerfil extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

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

        firebaseUser = mAuth.getCurrentUser();

        profile.setOnClickListener(view -> {
            Intent intent;
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
                mGetContent.launch(intent.setType("image/*"));
            } else {
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                mGetContent.launch(intent.setType("image/*"));
            }
        });

        city.setOnClickListener(view -> TerminarEscribir());
        Save.setOnClickListener(View -> GuardarDatos());
        Back.setOnClickListener(View -> finish());
        CargarDatos();

    }
    private void TerminarEscribir(){
        Save.setEnabled(true);
        View view = this.getCurrentFocus();
        if (view != null&&city.didTouchFocusSelect()) {
            InputMethodManager imm =
                    (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        city.clearFocus();
    }

    private void GuardarDatos() {
        CollectionReference usuarios = db.collection("usuarios");
        HashMap<String, String> user = new HashMap<>();
        //a base de datos

        user.put("UserId", firebaseUser.getUid());
        user.put("Name", String.valueOf(userName.getText()));
        user.put("Phone", String.valueOf(numberPhone.getText()));
        user.put("eMail", String.valueOf(eMail.getText()));
        user.put("City", String.valueOf(city.getText()));
        user.put("DateHour", FechaHora());
        usuarios.document(firebaseUser.getUid()).set(user);
        if (uri!=null) {
            SubirFoto(uri);
        }
        ModificarDatos();
    }

    private void CargarDatos() {
        Session();

        userName.setText(firebaseUser.getDisplayName());
        numberPhone.setText(firebaseUser.getPhoneNumber());
        eMail.setText(firebaseUser.getEmail());



        db.collection("usuarios")
                .document(firebaseUser.getUid()).get().addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()){
                        numberPhone.setText(documentSnapshot.getString("Phone"));
                        city.setText(documentSnapshot.getString("City"));
                    }
                });
        StorageReference imageref = storage.getReference().child(firebaseUser.getUid()+".jpg");
        imageref.getBytes(1024*1024)
                .addOnSuccessListener(bytes -> {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0, bytes.length);
                    profile.setImageBitmap(bitmap);
                });
    }

    private void ModificarDatos() {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(String.valueOf(userName.getText()))
                .setPhotoUri(uri)
                .build();
        firebaseUser.updateProfile(profileUpdates)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this,
                                "Datos Actualizados", LENGTH_SHORT).show();
                        finish();

                    }
                });
    }

    private void Session() {
        if (firebaseUser == null) {
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
        date = AddZero(day) + "/" + AddZero(month) + "/" + AddZero(year) + " " +
                AddZero(hour) + ":" + AddZero(minute) + ":" + AddZero(second);
        return date;

    }
    private String AddZero(int number){
        String numbzero = String.valueOf(number);
        if (number<10){numbzero = "0"+number;}
        return numbzero;
    }

    private void SubirFoto(Uri uri) {
        StorageReference spaceRef = storageRef.child(firebaseUser.getUid() + ".jpg");
        UploadTask uploadTask = spaceRef.putFile(uri);

        uploadTask.addOnFailureListener(exception -> Toast.makeText(this,
                "Error al subir foto", LENGTH_SHORT).show()
        ).addOnSuccessListener(taskSnapshot -> {});

    }
}