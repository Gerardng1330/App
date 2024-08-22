package com.example.apptareas;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin, btnRegis;
    private FirebaseAuth mFireAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFireAuth = FirebaseAuth.getInstance();

        //Asignacion de botones
        btnLogin = findViewById(R.id.btnLogin);
        btnRegis = findViewById(R.id.btnRegistration);

        btnLogin.setOnClickListener(new View.OnClickListener() {    //Envio a LoginView
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MainActivity.this, Login.class);
                startActivity(login);
            }
        });

        btnRegis.setOnClickListener(new View.OnClickListener(){        //Envio a RegistrationView
            @Override
            public void onClick(View v){
                Intent registration = new Intent(MainActivity.this, Registration.class);
                startActivity(registration);
            }
        });
    }

    @Override
    protected void onStart(){               //Revisa que el usuario no haya cerrado sesion para iniciar en este activity
        super.onStart();
        FirebaseUser user = mFireAuth.getCurrentUser();
        if (user!=null){
            startActivity(new Intent(MainActivity.this, Menu.class));
            finish();
        }
    }
}