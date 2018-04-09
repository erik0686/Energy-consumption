package itesm.mx.a01196362_examen_ahorroenergia;


import java.io.Serializable;

public class Electrodomestico implements Serializable{

    private String nombre;
    private double watts;
    private int idImagen;

    public Electrodomestico(String nombre, double watts, int idImagen) {
        this.nombre = nombre;
        this.watts = watts;
        this.idImagen = idImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getWatts() {
        return watts;
    }

    public void setWatts(double watts) {
        this.watts = watts;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }



}
