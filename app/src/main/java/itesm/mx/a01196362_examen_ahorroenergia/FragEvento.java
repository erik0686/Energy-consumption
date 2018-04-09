package itesm.mx.a01196362_examen_ahorroenergia;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class FragEvento extends Fragment implements View.OnClickListener {

    View view;
    EventoOperators eventoOperators;
    EventoAdapter adapter;
    ArrayList<Evento> lvListEventos;
    ListView lvList;
    TextView tvExiste;


    public FragEvento() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_evento, container, false);

        eventoOperators = new EventoOperators(getContext());
        eventoOperators.open();

        tvExiste = (TextView) view.findViewById(R.id.text_existen);
        lvList = (ListView) view.findViewById(R.id.lista_electrodomesticos);
        lvListEventos = showEventos();
        if(lvListEventos.size() == 0){
            tvExiste.setText("Aun no hay registros. Agrega uno!");
        }
        adapter = new EventoAdapter(getContext(), lvListEventos);
        lvList.setAdapter(adapter);
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm = getFragmentManager();
                FragDetalleEvento eventoDetalleFragment = FragDetalleEvento.newInstance(position);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, eventoDetalleFragment, "Detalle evento")
                        .addToBackStack(null)
                        .commit();
            }
        });
        Button btnAgregar = view.findViewById(R.id.btn_agregar);
        btnAgregar.setOnClickListener(this);
        return view;
    }

    public ArrayList<Evento> showEventos() {
        ArrayList<Evento> eventosList = eventoOperators.getAllEventos();
        if (eventosList != null) {
            Collections.reverse(eventosList);
            return eventosList;
        }else {
            return null;
        }
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();
        FragAgregarEvento agregarFragEvento = new FragAgregarEvento();
        fm.beginTransaction()
                .replace(R.id.fragment_container, agregarFragEvento, "Agregar un evento")
                .addToBackStack(null)
                .commit();
    }


}
