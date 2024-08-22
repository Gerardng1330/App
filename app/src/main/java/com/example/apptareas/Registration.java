package com.example.apptareas;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {
    private Button btnRegis;
    private EditText email, user, password;
    //Elementos de Firebase para autenticacion y base de datos
    private FirebaseFirestore mFireStore;
    private FirebaseAuth mFireAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Se inicializan los elementos de Firebase
        mFireStore = FirebaseFirestore.getInstance();
        mFireAuth = FirebaseAuth.getInstance();

        //Asignacion de elementos
        btnRegis = findViewById(R.id.btnRegistration2);
        email = findViewById(R.id.etxtEmailRegis);
        user = findViewById(R.id.etxtUserRegis);
        password = findViewById(R.id.etxtPasswordRegis);

        //Se crea el evento para que al presionar el boton de registro se almacena el usuario
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se alamacena la informacion de los usuarios sin espacios
                String nameUser = user.getText().toString().trim();
                String emailUser = email.getText().toString().trim();
                String passwordUser = password.getText().toString().trim();

                //Se revisa que todos los campos esten llenos
                if (nameUser.isEmpty() && emailUser.isEmpty() && passwordUser.isEmpty()){
                    //Se envia un mensaje al usuario
                    Toast.makeText(Registration.this, "Debe llenar todos los datos", Toast.LENGTH_SHORT).show();
                } else{
                    //Se pasan los datos al metodo de registro
                    registerUser(nameUser, emailUser, passwordUser);
                }
            }
        });
    }

    private void registerUser(String nameUser, String emailUser, String passwordUser) {
        //Se crea el usuario utilizando Firebase
        mFireAuth.createUserWithEmailAndPassword(emailUser, passwordUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {           //Si sale bien
                String id = mFireAuth.getCurrentUser().getUid();
                Map<String, Object> map = new HashMap<>();      //Se mapean los elementos
                map.put("id", id);
                map.put("username", nameUser);
                map.put("email", emailUser);
                map.put("password", passwordUser);

                //Se ingresa el usuario a la base de datos
                mFireStore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                        startActivity(new Intent(Registration.this, MainActivity.class));
                        Toast.makeText(Registration.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(Registration.this, "Error al registrarse", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(Registration.this, "Error al registrar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}