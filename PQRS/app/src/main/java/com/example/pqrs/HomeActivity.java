package com.example.pqrs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.pqrs.Adapters.PqrsAdapter;
import com.example.pqrs.Models.PqrsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private ListView lv_lista_PQRS;
    private Button btn_home_nuevo_pqrs;
    private EditText et_home_buscar;
    private ArrayList<PqrsModel> list;
    private PqrsModel model;

    private final  String text_reference="PQRS";
    private FirebaseDatabase database=FirebaseDatabase.getInstance();
    private DatabaseReference reference=database.getReference(text_reference);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()){
                    model = child.getValue(PqrsModel.class);
                    list.add(model);
                }
                lv_lista_PQRS.setAdapter(new PqrsAdapter(HomeActivity.this, list));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HomeActivity.this, "Error con firebase", Toast.LENGTH_SHORT).show();

            }
        });

        btn_home_nuevo_pqrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevopqrs();
            }
        });
    }

    public void init(){
        lv_lista_PQRS = findViewById(R.id.lv_lista_PQRS);
        list = new ArrayList<>();
        model = model;
        btn_home_nuevo_pqrs = findViewById(R.id.btn_home_nuevo_pqrs);
    }

    public void nuevopqrs(){
        Intent nuevo = new Intent(this, CrearPqrsActivity.class);
        startActivity(nuevo);
    }
}
