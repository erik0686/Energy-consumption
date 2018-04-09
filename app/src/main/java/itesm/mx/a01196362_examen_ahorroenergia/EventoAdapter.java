package itesm.mx.a01196362_examen_ahorroenergia;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EventoAdapter extends ArrayAdapter<Evento> {
    private Context context;

    public EventoAdapter(Context context, ArrayList<Evento> eventos) {
        super(context, 0, eventos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_evento, parent, false);
        }

        TextView tvNombre = (TextView) convertView.findViewById(R.id.text_nombre);
        TextView tvConsumo = (TextView) convertView.findViewById(R.id.text_consumo);
        TextView tvFecha = (TextView) convertView.findViewById(R.id.text_fecha);
        ImageView pictureIV = (ImageView) convertView.findViewById(R.id.image_electrodomestico);

        Evento evento = getItem(position);
        tvNombre.setText(evento.getNombre());
        tvConsumo.setText("Consumo: " + evento.getConsumo() + " watts");
        tvFecha.setText("Fecha de uso: " + evento.getFecha());
        byte[] imagen = evento.getImagen();

        if (imagen != null) {
            Bitmap BTimage = BitmapFactory.decodeByteArray(imagen, 0, imagen.length);
            pictureIV.setImageBitmap(BTimage);
        }
        return convertView;
    }
}