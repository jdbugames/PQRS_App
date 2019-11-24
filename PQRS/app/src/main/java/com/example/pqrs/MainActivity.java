package com.example.pqrs;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private Button btn_main_crear_pqrs, btn_main_ver_pqrs;
    private UsuarioModel model;
    private SharedPreferences preferences;
    private FirebaseAuth mAuth;
    private ArrayList<UsuarioModel>list;
    private FirebaseDatabase database=FirebaseDatabase.getInstance();
    private DatabaseReference reference=database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        model = new UsuarioModel();

        init();
        irlogin();

        btn_main_ver_pqrs.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent ver_pqrs = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(ver_pqrs);
            }
        });

        btn_main_crear_pqrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crearpqrs = new Intent(MainActivity.this, CrearPqrsActivity.class);
                startActivity(crearpqrs);
            }
        });


    }

    public void init(){

        btn_main_crear_pqrs= findViewById(R.id.btn_main_crear_pqrs);
        btn_main_ver_pqrs = findViewById(R.id.btn_main_ver_pqrs);
        reference= database.getReference();
        preferences = getSharedPreferences("preferences", MODE_PRIVATE);

    }

    public   void  irlogin(){
        Intent irlogin= new Intent(this,LoginActivity.class);
        startActivity(irlogin);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser()!=null){
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
            finish();
        }
    }
}
