package com.thecurseds.gamequest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class EscribirResegna extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;

    TextView txt_usuario_calificado;

    RatingBar rtb_hacer_resegna;
    EditText txt_escribir_resegna;
    Button guardar;
    private String userid;  //temporal
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escribir_resegna);
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        userid = "KmeTNkKgB0dTGvas8XNlMRlP2QC3";    //temporal

        txt_usuario_calificado = findViewById(R.id.txt_usuario_calificado);

        rtb_hacer_resegna = findViewById(R.id.rtb_hacer_resegna);
        txt_escribir_resegna = findViewById(R.id.txt_escribir_resegna);
        guardar = findViewById(R.id.btn_guardar_resegna);

        txt_escribir_resegna.setOnClickListener(view -> TerminarEscribir());
        guardar.setOnClickListener(view ->GuardarResegna());

    }

    private void GuardarResegna() {
        CollectionReference collectionReference = db.collection("reseñas");
        HashMap<String, String> resegna = new HashMap<>();
        resegna.put("id de quien dejó la reseña", firebaseUser.getUid());
        resegna.put("reseña", String.valueOf(txt_escribir_resegna.getText()));
        resegna.put("id del reseñado", userid);
        resegna.put("valoracion", String.valueOf(rtb_hacer_resegna.getNumStars()));

        collectionReference.document(firebaseUser.getUid()).set(resegna).addOnSuccessListener(unused -> {
            Toast.makeText(EscribirResegna.this, "Reseña subida con éxito", Toast.LENGTH_SHORT).show();
            finish();
        }).addOnFailureListener(e -> Toast.makeText(EscribirResegna.this, "Intente de nuevo", Toast.LENGTH_SHORT).show());
    }

    private void TerminarEscribir(){
        guardar.setEnabled(true);
        View view = this.getCurrentFocus();
        if (view != null&&txt_escribir_resegna.didTouchFocusSelect()) {
            InputMethodManager imm =
                    (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        txt_escribir_resegna.clearFocus();
    }

}