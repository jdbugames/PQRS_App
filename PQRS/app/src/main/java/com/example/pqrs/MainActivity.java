package com.example.pqrs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pqrs.Models.UsuarioModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private EditText et_main_usuario, et_main_contrasena;
    private Button btn_main_ingresar, btn_main_registrarse;
    private UsuarioModel usuarioModel;
    private SharedPreferences preferences;
    private ArrayList<UsuarioModel>list;
    private FirebaseDatabase database=FirebaseDatabase.getInstance();
    private DatabaseReference reference=database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuarioModel = new UsuarioModel();

        init();
        validarsesion();


        btn_main_registrarse.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                irregistrar();
            }
        });

        btn_main_ingresar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final String usuario = et_main_usuario.getText().toString();
                final String contrasena = et_main_contrasena.getText().toString();
                boolean validacionInterfaz = validarCampos(usuario, contrasena);


                if (validacionInterfaz)
                {

                 reference.addValueEventListener(new ValueEventListener()
                   {

                        @Override
                        public void onDataChange( DataSnapshot dataSnapshot)
                        {
                            list=new ArrayList<>();
                            Log.d("", "Value is: " + usuario);
                           for(DataSnapshot child : dataSnapshot.getChildren())
                           {
                            usuarioModel = child.getValue(UsuarioModel.class);
                                list.add(usuarioModel);
                           }
                        }
                        @Override
                        public void onCancelled(DatabaseError error)
                        {
                            // Failed to read value
                            Log.w("", "falló la lectura", error.toException());
                        }
                        /*if(usuarioModel != null)
                               {
                                   String id=reference.push().getKey();
                                   usuarioModel=new UsuarioModel(id);
                                   reference.child(id).setValue(usuarioModel);
                                   irhome();
                               }*/
                   });

                   if(usuarioModel != null)
                   {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("usuario", usuarioModel.getUsuario());
                        editor.putString("contrasena", usuarioModel.getContrasena());
                        usuarioModel = new UsuarioModel();
                        editor.commit();
                        validarsesion();
                        Toast.makeText(MainActivity.this,"usuario encontrado, iniciando sesión...", Toast.LENGTH_LONG).show();
                   }
                   else
                   {
                        Toast.makeText(MainActivity.this,"Usuario no encontrado revise su información...", Toast.LENGTH_LONG).show();

                   }
                }


            }


        });

        btn_main_registrarse.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent registro = new Intent(MainActivity.this, RegistrarActivity.class );
                startActivity(registro);
            }
        });
    }

    public void init(){
        et_main_usuario = findViewById(R.id.et_main_usuario);
        et_main_contrasena = findViewById(R.id.et_main_contrasena);
        btn_main_ingresar = findViewById(R.id.btn_main_ingresar);
        btn_main_registrarse = findViewById(R.id.btn_main_registrarse);
        reference= database.getReference();


        preferences = getSharedPreferences("preferences", MODE_PRIVATE);


    }

    private void validarsesion() {
        int usuario_id = preferences.getInt("usuario_id", 0);
        String usuario_nombre = preferences.getString("usuario_nombre", null);

        if (usuario_id > 0 && usuario_nombre != null) {
            irregistrar();
        }
    }

    private void irregistrar () {
        Intent registrar = new Intent(this, RegistrarActivity.class);

        startActivity(registrar);
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

    public void irhome(){
        Intent home =new Intent(this,HomeActivity.class );
        startActivity(home);

    }

    public void irinicio(){
        Intent inicio =new Intent(this,MainActivity.class );
        startActivity(inicio);

    }
}
