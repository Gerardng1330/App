package com.example.apptareas;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;

public class Menu extends AppCompatActivity {

    private LinearLayout guardarTareas, verTareas;
    private Button btnLogout;
    private FirebaseAuth mFireAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mFireAuth = FirebaseAuth.getInstance();

        //Asignamos los elementos
        guardarTareas = findViewById(R.id.opcion1);
        verTareas = findViewById(R.id.opcion2);
        btnLogout = findViewById(R.id.btnLogout);

        guardarTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu.this, GuardarTareas.class));
            }
        });

        verTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu.this, VerNotas.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFireAuth.signOut();            //Cierra sesion
                finish();
                startActivity(new Intent(Menu.this, MainActivity.class));
            }
        });
    }
}