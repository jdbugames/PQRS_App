package com.example.pqrs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button btn_login,btn_registrar;
    private EditText password,correo;

    private  String email="";
    private  String contrasena="";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        password=findViewById(R.id.password);
        correo=findViewById(R.id.correo);

        btn_login=findViewById(R.id.btn_login);
        btn_registrar=findViewById(R.id.btn_registrar);
        mAuth=FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=correo.getText().toString();
                contrasena=password.getText().toString();
                if (!email.isEmpty() && !contrasena.isEmpty()){

                    loginuser();


                }else{
                    Toast.makeText(LoginActivity.this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this,RegistrarActivity.class));

            }
        });

    }

    private void loginuser(){
        mAuth.signInWithEmailAndPassword(email,contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "No se pudo inicia secion, compruebe los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
