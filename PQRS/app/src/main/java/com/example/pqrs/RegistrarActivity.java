package com.example.pqrs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pqrs.Models.UsuarioModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegistrarActivity extends AppCompatActivity {

    private EditText et_registro_usuario_nombre,et_registro_usuario_cedula,et_registro_usuario_usuario,et_registro_usuario_contrasena;
    private Button btn_registro_usuario_registrar;
    private UsuarioModel usuarioModel;
    private ArrayList<UsuarioModel>list;
    private final  String text_reference="Usuario";
    private FirebaseDatabase database=FirebaseDatabase.getInstance();
    private DatabaseReference reference=database.getReference(text_reference);

    private String nombre="";
    private String cedula="";
    private String usuario="";
    private String contrasena="";

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        list=new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();

        init();

        btn_registro_usuario_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {


                 nombre=et_registro_usuario_nombre.getText().toString();
                 cedula=et_registro_usuario_cedula.getText().toString();
                 usuario=et_registro_usuario_usuario.getText().toString();
                 contrasena=et_registro_usuario_contrasena.getText().toString();
                if(!nombre.isEmpty() && !cedula.isEmpty() && !usuario.isEmpty() && !contrasena.isEmpty()){
                    if (contrasena.length()>=6){
                        registrarUser();


                    }else {
                        Toast.makeText(RegistrarActivity.this, "La contraseña debe tener al menos 6 caracteres.", Toast.LENGTH_SHORT).show();
                    }

                } else{

                    Toast.makeText(RegistrarActivity.this, "Por favor complete todos los datos.", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }
    private void registrarUser(){
        mAuth.createUserWithEmailAndPassword(usuario, contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                   Map<String, Object> map = new HashMap<>();
                   map.put("nombre", nombre);
                   map.put("cedula", cedula);
                   map.put("usuario", usuario);
                   map.put("contrasena", contrasena);

                   String id = mAuth.getCurrentUser().getUid();

                    reference.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                startActivity(new Intent(RegistrarActivity.this, LoginActivity.class));
                                finish();
                            }else{
                                Toast.makeText(RegistrarActivity.this, "No se pudieron crear los datos correctamente.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(RegistrarActivity.this, "No se pudo registrar este usuario.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void init(){

        et_registro_usuario_nombre = findViewById(R.id.et_registro_usuario_nombre);
        et_registro_usuario_cedula = findViewById(R.id.et_registro_usuario_cedula);
        et_registro_usuario_usuario = findViewById(R.id.et_registro_usuario_usuario);
        et_registro_usuario_contrasena = findViewById(R.id.et_registro_usuario_contrasena);
        btn_registro_usuario_registrar = findViewById(R.id.btn_registro_usuario_registrar);
    }
    public void irinicio(){
        Intent inicio = new Intent(this,MainActivity.class);
        startActivity(inicio);

    }

}
