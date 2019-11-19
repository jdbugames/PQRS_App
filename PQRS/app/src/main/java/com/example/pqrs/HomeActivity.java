package com.example.pqrs;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class HomeActivity extends AppCompatActivity {
    private ListView lv_lista_PQRS;
    private Button btn_buscar,btn_home_nuevo_pqrs;
    private EditText et_home_buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();

        btn_home_nuevo_pqrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             nuevopqrs();
            }
        });
    }

    public void init(){
        lv_lista_PQRS = findViewById(R.id.lv_lista_PQRS);
        btn_buscar = findViewById(R.id.btn_buscar);
        btn_home_nuevo_pqrs = findViewById(R.id.btn_home_nuevo_pqrs);
        et_home_buscar = findViewById(R.id.et_home_buscar);
    }

    public void nuevopqrs(){
        Intent nuevo = new Intent(this, CrearPqrsActivity.class);
        startActivity(nuevo);
    }
}
