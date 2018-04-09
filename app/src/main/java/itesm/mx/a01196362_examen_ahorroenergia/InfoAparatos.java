package itesm.mx.a01196362_examen_ahorroenergia;

import android.app.Application;
import java.util.ArrayList;


public class InfoAparatos extends Application {

    private ArrayList<Electrodomestico> listaElectrodomesticos;
    public void setListaElectrodomesticos() { listaElectrodomesticos = getListaElectrodomesticos(); }
    public Electrodomestico getElectrodomestico(int index) { return listaElectrodomesticos.get(index); }

    public ArrayList<Electrodomestico> getListaElectrodomesticos(){
        Electrodomestico electrodomestico;
        listaElectrodomesticos = new ArrayList<Electrodomestico>();


        electrodomestico = new Electrodomestico("Licuadora", 125, R.drawable.licuadora);
        listaElectrodomesticos.add(electrodomestico);

        electrodomestico = new Electrodomestico("Estéreo musical", 100, R.drawable.estereo);
        listaElectrodomesticos.add(electrodomestico);

        electrodomestico = new Electrodomestico("Cafetera", 895, R.drawable.cafetera);
        listaElectrodomesticos.add(electrodomestico);

        electrodomestico = new Electrodomestico("Radiograbadora", 70, R.drawable.radiograbadora);
        listaElectrodomesticos.add(electrodomestico);

        electrodomestico = new Electrodomestico("Lavadora ropa (automática)", 510, R.drawable.lavadora);
        listaElectrodomesticos.add(electrodomestico);

        electrodomestico = new Electrodomestico("Horno de microondas", 1450, R.drawable.microondas);
        listaElectrodomesticos.add(electrodomestico);

        electrodomestico = new Electrodomestico("Plancha", 1000, R.drawable.plancha);
        listaElectrodomesticos.add(electrodomestico);

        electrodomestico = new Electrodomestico("Ventilador", 85, R.drawable.ventilador);
        listaElectrodomesticos.add(electrodomestico);


        electrodomestico = new Electrodomestico("Televisor color", 400, R.drawable.televisor);
        listaElectrodomesticos.add(electrodomestico);


        electrodomestico = new Electrodomestico("Refrigerador", 610, R.drawable.refrigerador);
        listaElectrodomesticos.add(electrodomestico);

        return listaElectrodomesticos;
    }
}
