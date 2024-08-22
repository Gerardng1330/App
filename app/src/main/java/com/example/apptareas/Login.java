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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import org.jetbrains.annotations.NotNull;

public class Login extends AppCompatActivity {
    private EditText email, password;
    private Button btnLogin;
    private FirebaseAuth mFireAuth;         //Elemento de Firebase para autenticacion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Se inicializa el elemento de Firebase
        mFireAuth = FirebaseAuth.getInstance();

        //Asignamos componentes
        email = findViewById(R.id.etxtEmailLogin);
        password = findViewById(R.id.etxtPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin2);

        //Se crea el evento para que al presionar el boton de iniciar sesion se realize la autenticacion
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se alamacena la informacion de los usuarios sin espacios
                String emailUser = email.getText().toString().trim();
                String passwordUser = password.getText().toString().trim();

                //Se revisa que todos los campos esten llenos
                if (emailUser.isEmpty() && passwordUser.isEmpty()){
                    //Se envia un mensaje al usuario
                    Toast.makeText(Login.this, "Debe colocar su informacion", Toast.LENGTH_SHORT).show();
                } else{
                    //Se pasan los datos al metodo de inicio de seion
                    loginUser(emailUser, passwordUser);
                }
            }
        });
    }

    private void loginUser(String emailUser, String passwordUser) {
        //Llamada al metodo de inicio de sesion de Firebase
        mFireAuth.signInWithEmailAndPassword(emailUser, passwordUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {       //Si todo sale bien
                if (task.isSuccessful()){
                    finish();                       //Finaliza la activity
                    startActivity(new Intent(Login.this, Menu.class));      //Pasa a otra activity
                    Toast.makeText(Login.this, "Acceso concedido bienvenid@", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {           //Si falla
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(Login.this, "Ocurrio un error al iniciar sesion", Toast.LENGTH_SHORT).show();
            }
        });
    }
}