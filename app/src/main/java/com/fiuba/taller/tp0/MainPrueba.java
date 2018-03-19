package com.fiuba.taller.tp0;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainPrueba extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] nombreSemana;
    private final Integer[] imgM;
    private final Integer[] imgT;
    private final String[] tempM;
    private final String[] tempT;


    public MainPrueba(Activity context, String[] dias, Integer[] imageIdM, Integer[] imageIdT, String[] tempM, String[] tempT) {
        super(context, R.layout.prueba_main, dias);
        this.context = context;
        this.nombreSemana = dias;
        this.imgM = imageIdM;
        this.imgT = imageIdT;
        this.tempM = tempM;
        this.tempT = tempT;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.prueba_main, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        txtTitle.setText(nombreSemana[position]);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);

        imageView.setImageResource(imgM[position]);
        return rowView;
    }
}