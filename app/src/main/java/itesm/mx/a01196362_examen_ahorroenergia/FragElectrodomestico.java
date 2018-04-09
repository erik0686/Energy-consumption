package itesm.mx.a01196362_examen_ahorroenergia;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class FragElectrodomestico extends ListFragment {

    ArrayList<Electrodomestico> listaElectrodomesticos;
    ElectrodomesticoAdapter adapterElectrodomesticos;
    OnElectrodomesticoSelectedListener electrodomesticoListener;


    public interface OnElectrodomesticoSelectedListener {

        void onElectrodomesticoSelected(int position);
    }


    public FragElectrodomestico() {
    }



    public static FragElectrodomestico newInstance(){
        return new FragElectrodomestico();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        final InfoAparatos listaElectrodomesticos2 = new InfoAparatos();
        listaElectrodomesticos2.setListaElectrodomesticos();
        listaElectrodomesticos = listaElectrodomesticos2.getListaElectrodomesticos();

        adapterElectrodomesticos = new ElectrodomesticoAdapter(getContext(), listaElectrodomesticos);
        setListAdapter(adapterElectrodomesticos);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof OnElectrodomesticoSelectedListener) {
            electrodomesticoListener = (OnElectrodomesticoSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        electrodomesticoListener.onElectrodomesticoSelected(position);
    }


}
