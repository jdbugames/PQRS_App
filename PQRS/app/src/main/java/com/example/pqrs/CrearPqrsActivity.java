package com.example.pqrs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.pqrs.Models.PqrsModel;
import com.example.pqrs.Models.UsuarioModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CrearPqrsActivity extends AppCompatActivity {

    private Spinner sp_crear_pqrs_tipo,sp_crear_pqrs_clase,sp_crear_pqrs_profesor;
    private EditText ed_crear_pqrs_titulo,ed_crear_pqrs_contenido;
    private Button btn_crear_pqrs_guardando;
    private PqrsModel pqrsModel;
    private final  String text_reference="PQRS";
    private FirebaseDatabase database=FirebaseDatabase.getInstance();
    private DatabaseReference reference=database.getReference(text_reference);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_pqrs);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();

        btn_crear_pqrs_guardando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String tipo= sp_crear_pqrs_tipo.getSelectedItem().toString();
                String titulo=ed_crear_pqrs_titulo.getText().toString();
                String clase= sp_crear_pqrs_clase.getSelectedItem().toString();
                String profesor= sp_crear_pqrs_profesor.getSelectedItem().toString();
                String contenido=ed_crear_pqrs_contenido.getText().toString();
                irdetalle();



                boolean validacionInterfaz = validarCampos(tipo, titulo,clase,profesor,contenido);

                if (!tipo.equals("")&&!titulo.equals("")&&!clase.equals("")&&!profesor.equals("")&&!contenido.equals("")){
                    String id=reference.push().getKey();
                    if (id !=null&& !id.equals("")){
                        pqrsModel = new PqrsModel(id, tipo, titulo, clase, profesor, contenido);
                        reference.child(id).setValue(pqrsModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Snackbar.make(view, "No se pudo guardar la información.", Snackbar.LENGTH_LONG).show();
                                    }

                                });
                    }else{
                        Snackbar.make(view, "Problemas al crear el Id en la base de datos.", Snackbar.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(CrearPqrsActivity.this, "Por favor ingrese todos los datos.", Toast.LENGTH_SHORT).show();
                }

                if (validacionInterfaz){

                    if(pqrsModel != null) {
                        Toast.makeText(CrearPqrsActivity.this, "Los datos están completos, guardando la información...", Toast.LENGTH_LONG).show();



                        Intent irahome = new Intent(CrearPqrsActivity.this, HomeActivity.class );
                        startActivity(irahome);
                    }
                }else{
                    Toast.makeText(CrearPqrsActivity.this,"Algunos campos no cumplen con los requisitos, por favor verifique la información...", Toast.LENGTH_LONG).show();
                }



            }

        });

    }

    public  void init(){
        sp_crear_pqrs_tipo=findViewById(R.id.sp_crear_pqrs_tipo);
        sp_crear_pqrs_clase=findViewById(R.id.sp_crear_pqrs_clase);
        sp_crear_pqrs_profesor=findViewById(R.id.sp_crear_pqrs_profesor);
        ed_crear_pqrs_titulo=findViewById(R.id.ed_crear_pqrs_titulo);
        ed_crear_pqrs_contenido=findViewById(R.id.ed_crear_pqrs_contenido);
        btn_crear_pqrs_guardando=findViewById(R.id.btn_crear_pqrs_guardando);
        pqrsModel = new PqrsModel();

        String [] opciones_tipo = {" ","Pregunta", "Queja", "Reclamo", "Sugerencia"};
        ArrayAdapter<String> adapter_tipo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, opciones_tipo);
        sp_crear_pqrs_tipo.setAdapter(adapter_tipo);

        String [] opciones_clase = {" ","Metodologías Ágiles", "Móviles II", "Aplicaciones para la Web II"};
        ArrayAdapter<String> adapter_clase = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, opciones_clase);
        sp_crear_pqrs_clase.setAdapter(adapter_clase);

        String [] opciones_profesor = {" ","Carlos Jaramillo", "Juan Fernando Fernández", "Juan de Dios Carmona"};
        ArrayAdapter<String> adapter_profesor = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, opciones_profesor);
        sp_crear_pqrs_profesor.setAdapter(adapter_profesor);



    }

    public boolean validarCampos(String tipo, String titulo, String clase, String profesor, String contenido ){
        if(tipo.isEmpty()|| titulo.isEmpty() || clase.isEmpty()|| profesor.isEmpty() || contenido.isEmpty()){
            Toast.makeText(this,"Todos los campos son obligatorios", Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }
    }

    public void irdetalle(){
        Intent detalle =new Intent(this,DetallePqrsActivity.class );
        startActivity(detalle);

    }
}
