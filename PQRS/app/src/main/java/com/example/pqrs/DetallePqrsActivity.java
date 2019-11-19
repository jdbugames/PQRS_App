package com.example.pqrs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetallePqrsActivity extends AppCompatActivity {

    private TextView tv_detalle_pqrs_tipo,
            tv_detalle_pqrs_titulo,
            tv_detalle_pqrs_clase,
            tv_detalle_pqrs_profesor,
            tv_detalle_prs_autor,
            tv_detalle_pqrs_fecha,
            tv_detalle_pqrs_contenido ;

    private Button btn_detalle_pqrs_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pqrs);
        init();
        btn_detalle_pqrs_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irhome();
            }
        });
    }
    public  void init (){
        tv_detalle_pqrs_tipo=findViewById(R.id.tv_detalle_pqrs_tipo);
        tv_detalle_pqrs_titulo=findViewById(R.id.tv_detalle_pqrs_titulo);
        tv_detalle_pqrs_clase=findViewById(R.id.tv_detalle_pqrs_clase);
        tv_detalle_pqrs_profesor=findViewById(R.id.tv_detalle_pqrs_profesor);
        tv_detalle_prs_autor=findViewById(R.id.tv_detalle_prs_autor);
        tv_detalle_pqrs_fecha=findViewById(R.id.tv_detalle_pqrs_fecha);
        tv_detalle_pqrs_contenido=findViewById(R.id.tv_detalle_pqrs_contenido);
        btn_detalle_pqrs_home = findViewById(R.id.btn_detalle_pqrs_home);
    }
    public void irhome(){
        Intent home =new Intent(this,HomeActivity.class );
        startActivity(home);

    }

}
