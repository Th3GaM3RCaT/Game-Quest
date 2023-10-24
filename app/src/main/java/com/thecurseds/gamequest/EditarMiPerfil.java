package com.thecurseds.gamequest;

import static android.widget.Toast.LENGTH_SHORT;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

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
                    uri = result.getData().getData();
                    profile.setImageURI(uri);
                } catch (Exception e) {
                    Toast.makeText(EditarMiPerfil.this, "Intente de nuevo", LENGTH_SHORT).show();
                }
            }
        }
    );




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

            try {
                Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
                mGetContent.launch(intent.setType("image/*"));
                //.launch("image/*");
            } catch (Exception e) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                mGetContent.launch(intent.setType("image/*"));
            }
        });

        city.setOnClickListener(view -> Save.setEnabled(true));

        Save.setOnClickListener(View -> GuardarDatos());

        Back.setOnClickListener(View -> finish());

        CargarDatos();
        
    }
    public void GuardarDatos(){
        Toast.makeText(this, "Falta subir los datos", LENGTH_SHORT).show();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();
        //a base de datos
        user.put("UserId", User.getUid());
        user.put("Name",  String.valueOf(userName.getText()));
        user.put("Phone", String.valueOf(numberPhone.getText()));
        user.put("eMail", String.valueOf(eMail.getText()));
        user.put("City", String.valueOf(city.getText()));
        //user.put("profileUrl", String.valueOf());

        //a usuario
        ModificarDatos();


// Add a new document with a generated ID
        db.collection("usuarios").add(user);
    }
    public void CargarDatos(){
        Session();
        userName.setText(User.getDisplayName());
        numberPhone.setText(User.getPhoneNumber());
        eMail.setText(User.getEmail());
        //city.setText(User.getCity());
        //User.getPhotoUrl();
    }
    public void ModificarDatos(){
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(String.valueOf(userName.getText()))
                .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                .build();
        User.updateProfile(profileUpdates)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "User profile updated.");
                    }
                });
    }
    private void Session(){if (User == null){finish();}};
}