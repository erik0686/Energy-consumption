package itesm.mx.a01196362_examen_ahorroenergia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ElectrodomesticoAdapter extends ArrayAdapter<Electrodomestico> {

    TextView tvNombre;
    TextView tvWatts;
    ImageView ivLibro;


    public ElectrodomesticoAdapter(Context context, ArrayList<Electrodomestico> electrodomesticos){
        super(context, 0, electrodomesticos);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Electrodomestico electrodomestico = getItem(position);
        RecyclerView.ViewHolder holder;

        View view = convertView;

        if (view == null){

            view = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        }
        tvNombre = view.findViewById(R.id.text_nombre);
        tvWatts = view.findViewById(R.id.text_watts);
        ivLibro = view.findViewById(R.id.image_electrodomestico);
        tvNombre.setText(electrodomestico.getNombre());
        tvWatts.setText("Consumo: " + Double.toString(electrodomestico.getWatts()) + " watts/hora");
        ivLibro.setImageResource(electrodomestico.getIdImagen());

        return view;
    }


}
