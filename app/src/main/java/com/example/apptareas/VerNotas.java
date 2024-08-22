package com.example.apptareas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
public class VerNotas extends AppCompatActivity {
    private TextView txtMensaje;
    private Button btnRegresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_notas);
        txtMensaje = findViewById(R.id.txtMensaje);
        btnRegresar = findViewById(R.id.btnRegresar);

        // Obtener el mensaje enviado desde la actividad anterior
        String tarea = getIntent().getStringExtra("tarea");

        // Mostrar el mensaje en el TextView
        txtMensaje.setText(tarea);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresarAlMenu();
            }
        });
    }
    private void regresarAlMenu() {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
        finish(); // Si deseas cerrar esta actividad al regresar al menú
    }
}