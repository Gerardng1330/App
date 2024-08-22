package com.example.apptareas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class GuardarTareas extends AppCompatActivity {
    private EditText txtTarea;
    private Button btnGuardarTarea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar_tareas);

        txtTarea = findViewById(R.id.txtTarea); // Reemplaza "editTextTarea" con el ID de tu EditText
        btnGuardarTarea = findViewById(R.id.btnGuardarTarea); // Reemplaza "btnGuardarTarea" con el ID de tu botón

        btnGuardarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarTarea();

            }
        });
    }
    private void guardarTarea() {
        String tarea = txtTarea.getText().toString(); // Obtener el texto ingresado por el usuario

        // Aquí puedes guardar la tarea en algún lugar, como en SharedPreferences, base de datos, etc.

        // Mostrar mensaje indicando que se guardó la tarea
        Toast.makeText(this, "Se guardó la tarea", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, VerNotas.class);
        intent.putExtra("tarea",tarea); // Agregar el mensaje como un extra
        startActivity(intent);

        // Regresar a la actividad anterior (suponiendo que "Menu.class" es la actividad del menú)
        /*Intent intent1 = new Intent(this, Menu.class);
        startActivity(intent1)*/;
    }
    private void regresarAlMenu() {
        Intent intent3 = new Intent(this, Menu.class);
        startActivity(intent3);
        finish(); // Si deseas cerrar esta actividad al regresar al menú
    }
}