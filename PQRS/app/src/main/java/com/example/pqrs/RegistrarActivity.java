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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegistrarActivity extends AppCompatActivity {

    private EditText et_registro_usuario_nombre,et_registro_usuario_cedula,et_registro_usuario_usuario,et_registro_usuario_contrasena;
    private Button btn_registro_usuario_registrar;
    private UsuarioModel usuarioModel;
    private ArrayList<UsuarioModel>list;
    private final  String text_reference="Usuario";
    private FirebaseDatabase database=FirebaseDatabase.getInstance();
    private DatabaseReference reference=database.getReference(text_reference);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        list=new ArrayList<>();

        init();

        btn_registro_usuario_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
               list= new ArrayList<>();
                String nombre=et_registro_usuario_nombre.getText().toString();
                String cedula=et_registro_usuario_cedula.getText().toString();
                String usuario=et_registro_usuario_usuario.getText().toString();
                String contrasena=et_registro_usuario_contrasena.getText().toString();

                validarCampos(usuario,contrasena);
                irinicio();

                if(!nombre.equals("")&&!cedula.equals("")&&!usuario.equals("")&& !contrasena.equals(""))
                {
                    String id=reference.push().getKey();
                    if(id !=null&& !id.equals("")){
                        usuarioModel = new UsuarioModel(id,nombre,cedula,usuario,contrasena);
                        reference.child(id).setValue(usuarioModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Snackbar.make(view,"no se pudo guardar, ravisa la informacion",Snackbar.LENGTH_LONG).show();
                                    }
                                });
                    }else
                    {
                        Snackbar.make(view,"problemas al crear id en base de datos", Snackbar.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(RegistrarActivity.this,"Por favor ingrese todos los datos ",Toast.LENGTH_SHORT).show();
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

    public boolean validarCampos(String usuario, String contrasena){
        if(usuario.isEmpty()|| contrasena.isEmpty()){
            Toast.makeText(this,"Por favor ingrese usuario y conraseña", Toast.LENGTH_LONG).show();
            return false;
        }else if (contrasena.length()<4|| contrasena.length()>4){
            Toast.makeText(this,"Debe ingresar una contraseña numerica de 4 digitos", Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }
    }

}
