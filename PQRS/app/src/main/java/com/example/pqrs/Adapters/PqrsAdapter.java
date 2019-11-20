package com.example.pqrs.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pqrs.Models.PqrsModel;
import com.example.pqrs.R;


import java.util.ArrayList;

public class PqrsAdapter extends BaseAdapter {

    private final Context context;
    private PqrsModel model;
    private ArrayList<PqrsModel> list;

    public PqrsAdapter(Context context, ArrayList<PqrsModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View  view, ViewGroup viewGroup) {
        View itemView = view;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.item_pqrs, viewGroup, false);

        }
        TextView item_pqrs_tipo = itemView.findViewById(R.id.item_pqrs_tipo);
        model = list.get(i);
        item_pqrs_tipo.setText(model.getTipo());

        TextView item_pqrs_titulo = itemView.findViewById(R.id.item_pqrs_titulo);
        model = list.get(i);
        item_pqrs_titulo.setText(model.getTitulo());

        TextView item_pqrs_clase = itemView.findViewById(R.id.item_pqrs_clase);
        model = list.get(i);
        item_pqrs_clase.setText(model.getClase());

        TextView item_pqrs_profesor = itemView.findViewById(R.id.item_pqrs_profesor);
        model = list.get(i);
        item_pqrs_profesor.setText(model.getProfesor());

        TextView item_pqrs_contenido = itemView.findViewById(R.id.item_pqrs_contenido);
        model = list.get(i);
        item_pqrs_contenido.setText(model.getContenido());


        return itemView;
    }
}
