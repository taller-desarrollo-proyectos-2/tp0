package com.fiuba.taller.tp0;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

        TextView nomDia = (TextView) rowView.findViewById(R.id.txt);
        nomDia.setText(nombreSemana[position]);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);

        imageView.setImageResource(imgM[position]);

        ImageView imageView1 = (ImageView) rowView.findViewById(R.id.img2);
        imageView1.setImageResource(imgT[position]);

        TextView tempDia = (TextView) rowView.findViewById(R.id.txt2);
        tempDia.setText(tempM[position]);

        TextView tempNoc = (TextView) rowView.findViewById(R.id.txt3);
        tempNoc.setText(tempT[position]);

        return rowView;
    }
}