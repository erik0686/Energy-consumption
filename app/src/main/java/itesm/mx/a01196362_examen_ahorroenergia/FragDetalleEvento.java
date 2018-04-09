package itesm.mx.a01196362_examen_ahorroenergia;



import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


public class FragDetalleEvento extends Fragment implements  View.OnTouchListener {
    final static String ELECTRODOMESTICO_INDEX = "electrodomestico_index";
    int actualIndexEvento = 0;
    TextView tvNombre;
    TextView tvWatts;
    TextView tvFecha;
    ImageView ivElectrodomestico;
    View view;
    EventoOperators operadoresEvento;
    ArrayList<Evento> listEventos;
    GestureDetectorCompat gestureDetector;

    public FragDetalleEvento() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_evento_detalle, container, false);

        operadoresEvento = new EventoOperators(getContext());
        operadoresEvento.open();
        listEventos = showEventos();
        tvNombre = view.findViewById(R.id.text_nombre);
        tvWatts = view.findViewById(R.id.text_watts);
        tvFecha = view.findViewById(R.id.text_fecha);
        ivElectrodomestico = view.findViewById(R.id.image_electrodomestico);
        FragDetalleEvento.MyGestureListener myGestureListener = new FragDetalleEvento.MyGestureListener(getContext());
        gestureDetector = new GestureDetectorCompat(getContext(), myGestureListener);
        ivElectrodomestico.setOnTouchListener(this);
        return view;
    }


    public static FragDetalleEvento newInstance(int indexElectrodomestico){
        FragDetalleEvento detalleFragment = new FragDetalleEvento();
        Bundle bundle = new Bundle();
        bundle.putInt(ELECTRODOMESTICO_INDEX, indexElectrodomestico);
        detalleFragment.setArguments(bundle);

        return detalleFragment;
    }

    @Override
    public void onStart(){
        super.onStart();

        if(getArguments() != null){
            actualIndexEvento = getArguments().getInt(ELECTRODOMESTICO_INDEX);
        }
        setElectrodomesticoView(actualIndexEvento);
    }

    public void setElectrodomesticoView(int index){
        Evento evento = listEventos.get(index);
        tvNombre.setText(evento.getNombre());
        tvWatts.setText("Consumo: " + evento.getConsumo() + " watts");
        ivElectrodomestico.setImageBitmap(BitmapFactory.decodeByteArray(evento.getImagen(), 0, evento.getImagen().length));
        tvFecha.setText(evento.getFecha());
    }

    public ArrayList<Evento> showEventos() {
        ArrayList<Evento> ListaEventos = operadoresEvento.getAllEventos();
        Collections.reverse(ListaEventos);
        if (ListaEventos != null) {
            return ListaEventos;
        }else {
            return null;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }

    public class MyGestureListener implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

        public MyGestureListener(Context applicationContext) {
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() > e2.getX()) {
                actualIndexEvento = actualIndexEvento - 1;
                if (actualIndexEvento < 0) {
                    actualIndexEvento = listEventos.size()-1;
                }
            }else if (e1.getX() < e2.getX()){
                actualIndexEvento = actualIndexEvento + 1;
                if (actualIndexEvento >= listEventos.size()) {
                    actualIndexEvento = 0;
                }
            }

            setElectrodomesticoView(actualIndexEvento);
            return true;
        }
    }


}
