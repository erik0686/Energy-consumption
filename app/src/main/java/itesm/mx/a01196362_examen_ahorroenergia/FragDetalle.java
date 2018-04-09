package itesm.mx.a01196362_examen_ahorroenergia;



import android.content.Context;
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

public class FragDetalle extends Fragment implements  View.OnTouchListener  {

    final static String ELECTRODOMESTICO_INDEX = "electrodomestico_index";
    TextView tvNombre;
    TextView tvWatts;
    ImageView ivElectrodomestico;
    int index_electrodomestico = 0;
    GestureDetectorCompat gestureDetector;

    public FragDetalle() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_detalle, container, false);

        tvNombre = view.findViewById(R.id.text_nombre);
        tvWatts = view.findViewById(R.id.text_watts);
        ivElectrodomestico = view.findViewById(R.id.image_electrodomestico);
        ivElectrodomestico.setOnTouchListener(this);
        MyGestureListener myGestureListener = new MyGestureListener(getContext());
        gestureDetector = new GestureDetectorCompat(getContext(), myGestureListener);
        return view;
    }

    public static FragDetalle newInstance(int indexElectrodomestico){
        FragDetalle detalleFragment = new FragDetalle();
        Bundle bundle = new Bundle();
        bundle.putInt(ELECTRODOMESTICO_INDEX, indexElectrodomestico);
        detalleFragment.setArguments(bundle);

        return detalleFragment;
    }

    @Override
    public void onStart(){
        super.onStart();

        if(getArguments() != null){
            index_electrodomestico = getArguments().getInt(ELECTRODOMESTICO_INDEX);
        }
        setElectrodomesticoView(index_electrodomestico);
    }

    public void setElectrodomesticoView(int index){
        final InfoAparatos ListaElectrodomesticos = new InfoAparatos();
        ListaElectrodomesticos.setListaElectrodomesticos();
        Electrodomestico electrodomestico = ListaElectrodomesticos.getElectrodomestico(index);

        tvNombre.setText(electrodomestico.getNombre());
        tvWatts.setText(Double.toString(electrodomestico.getWatts()));
        ivElectrodomestico.setImageResource(electrodomestico.getIdImagen());
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
        public void onShowPress(MotionEvent e) {
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
        public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
            if (event1.getX() > event2.getX()) {
                index_electrodomestico = index_electrodomestico - 1;
                if (index_electrodomestico < 0) {
                    index_electrodomestico = 9;
                }
            }else if (event1.getX() < event2.getX()){
                index_electrodomestico = index_electrodomestico + 1;
                if (index_electrodomestico > 9) {
                    index_electrodomestico = 0;
                }
            }

            setElectrodomesticoView(index_electrodomestico);
            return true;
        }
    }
}
