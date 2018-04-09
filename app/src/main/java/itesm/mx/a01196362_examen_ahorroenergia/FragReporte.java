package itesm.mx.a01196362_examen_ahorroenergia;


import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class FragReporte extends Fragment {

    View view;
    EditText et_fecha;
    Calendar myCalendar;
    EventoOperators eventOperators;
    ArrayList<Evento> listEventos;

    TextView tvLicuadora;
    TextView tvEstereo;
    TextView tvCafetera;
    TextView tvRadiograbadora;
    TextView tvLavadora;
    TextView tvMicroondas;
    TextView tvPlancha;
    TextView tvVentilador;
    TextView tvTelevisor;
    TextView tvRefrigerador;
    TextView tvMas;
    TextView tvMenos;
    TextView tvTotal;

    public FragReporte() {
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag_total, container, false);
        et_fecha = view.findViewById(R.id.et_fecha);

        tvLicuadora = view.findViewById(R.id.text_licuadora);
        tvEstereo  = view.findViewById(R.id.text_estereo);
        tvCafetera  = view.findViewById(R.id.text_cafetera);
        tvRadiograbadora  = view.findViewById(R.id.text_radiograbadora);
        tvLavadora  = view.findViewById(R.id.text_lavadora);
        tvMicroondas  = view.findViewById(R.id.text_microondas);
        tvPlancha  = view.findViewById(R.id.text_plancha);
        tvVentilador  = view.findViewById(R.id.text_ventilador);
        tvTelevisor  = view.findViewById(R.id.text_televisor);
        tvRefrigerador  = view.findViewById(R.id.text_refrigerador);
        tvMas  = view.findViewById(R.id.text_mas);
        tvMenos  = view.findViewById(R.id.text_menos);
        tvTotal = view.findViewById(R.id.text_total);
        myCalendar = Calendar.getInstance();
        eventOperators = new EventoOperators(getContext());
        eventOperators.open();

        listEventos = showEventos();

        final DatePickerDialog.OnDateSetListener fecha = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                try {
                    updateLabel();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        };

        et_fecha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), fecha, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        return view;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() throws ParseException {
        String myFormat = "yyyy/MM/dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);

        String fecha_con_formato = dateFormat.format(myCalendar.getTime());
        et_fecha.setText(fecha_con_formato);

        double licuadora = 0, estereo = 0, cafetera = 0, radiograbadora = 0, lavadora = 0;
        double microondas = 0, plancha = 0, ventilador = 0, televisor = 0, refrigerador = 0;
        double total = 0, max_consumo = 0, min_consumo = Double.MAX_VALUE;
        String max_evento = "", min_evento = "";

        for(int iC=0; iC < listEventos.size(); iC++){
            String dateString = listEventos.get(iC).getFecha().substring(0,10);
            if(fecha_con_formato.equals(dateString)){
                switch(listEventos.get(iC).getNombre()){
                    case "Licuadora":
                        licuadora += Double.parseDouble(listEventos.get(iC).getConsumo());
                        break;
                    case "Estéreo musical":
                        estereo += Double.parseDouble(listEventos.get(iC).getConsumo());
                        break;
                    case "Cafetera":
                        cafetera += Double.parseDouble(listEventos.get(iC).getConsumo());
                        break;
                    case "Radiograbadora":
                        radiograbadora += Double.parseDouble(listEventos.get(iC).getConsumo());
                        break;
                    case "Lavadora ropa (automática)":
                        lavadora += Double.parseDouble(listEventos.get(iC).getConsumo());
                        break;
                    case "Horno de microondas":
                        microondas += Double.parseDouble(listEventos.get(iC).getConsumo());
                        break;
                    case "Plancha":
                        plancha += Double.parseDouble(listEventos.get(iC).getConsumo());
                        break;
                    case "Ventilador":
                        ventilador += Double.parseDouble(listEventos.get(iC).getConsumo());
                        break;

                    case "Televisor color":
                        televisor += Double.parseDouble(listEventos.get(iC).getConsumo());
                        break;

                    case "Refrigerador":
                        refrigerador += Double.parseDouble(listEventos.get(iC).getConsumo());
                        break;

                }
                if (Double.parseDouble(listEventos.get(iC).getConsumo()) >= max_consumo){
                    max_consumo = Double.parseDouble(listEventos.get(iC).getConsumo());
                    max_evento = listEventos.get(iC).getNombre();
                }

                if (Double.parseDouble(listEventos.get(iC).getConsumo()) <= min_consumo) {
                    min_consumo = Double.parseDouble(listEventos.get(iC).getConsumo());
                    min_evento = listEventos.get(iC).getNombre();
                }

                total += Double.parseDouble(listEventos.get(iC).getConsumo());
            }
        }



        if(!checkIfCero(licuadora)){tvLicuadora.setText("Licuadora = " + String.valueOf(licuadora));} else{tvLicuadora.setText("Licuadora "); }
        if(!checkIfCero(estereo)){tvEstereo.setText("Estéreo musical = " + String.valueOf(estereo));} else{tvEstereo.setText("Estéreo musical "); }
        if(!checkIfCero(cafetera)){tvCafetera.setText("Cafetera = " + String.valueOf(cafetera));} else{tvCafetera.setText("Cafetera "); }
        if(!checkIfCero(radiograbadora)){tvRadiograbadora.setText("Radiograbadora = " + String.valueOf(radiograbadora));} else{tvRadiograbadora.setText("Radiograbadora "); }
        if(!checkIfCero(lavadora)){tvLavadora.setText("Lavadora = " + String.valueOf(lavadora));} else{tvLavadora.setText("Lavadora "); }
        if(!checkIfCero(microondas)){tvMicroondas.setText("Horno de microondas = " + String.valueOf(microondas));} else{tvMicroondas.setText("Horno de microondas "); }
        if(!checkIfCero(plancha)){tvPlancha.setText("Plancha = " + String.valueOf(plancha));} else{tvPlancha.setText("Plancha "); }
        if(!checkIfCero(ventilador)){tvVentilador.setText("Ventilador = " + String.valueOf(ventilador));} else{tvVentilador.setText("Ventilador "); }
        if(!checkIfCero(televisor)){tvTelevisor.setText("Televisor = " + String.valueOf(televisor));} else{tvTelevisor.setText("Televisor "); }
        if(!checkIfCero(refrigerador)){tvRefrigerador.setText("Refrigerador = " + String.valueOf(refrigerador));} else{tvRefrigerador.setText("Refrigerador "); }
        tvMas.setText("Mayor consumo: " + max_evento);
        tvMenos.setText("Menor consumo: " + min_evento);
        tvTotal.setText("Total = " + total);
    }
    public boolean checkIfCero(double consumo){
        return consumo == 0.0;
    }


    public ArrayList<Evento> showEventos() {
        ArrayList<Evento> eventosList = eventOperators.getAllEventos();
        Collections.reverse(eventosList);
        return eventosList;
    }

}
