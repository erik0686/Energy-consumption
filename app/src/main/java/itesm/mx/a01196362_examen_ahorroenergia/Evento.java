package itesm.mx.a01196362_examen_ahorroenergia;

public class Evento {
    private long id;
    private String consumo;
    private String nombre;
    private String fecha;
    private byte[] imagen;


    public Evento() {
        this.id = 0;
        this.consumo = null;
        this.nombre = null;
        this.fecha = null;
        this.imagen = null;
    }

    public Evento(String consumo, String nombre, String fecha, byte[] imagen) {
        this.id = id;
        this.consumo = consumo;
        this.nombre = nombre;
        this.fecha = fecha;
        this.imagen = imagen;
    }

    public Evento(long id, String consumo, String nombre, String fecha, byte[] imagen) {
        this.id = id;
        this.consumo = consumo;
        this.nombre = nombre;
        this.fecha = fecha;
        this.imagen = imagen;
    }




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConsumo() {
        return consumo;
    }

    public void setConsumo(String consumo) {
        this.consumo = consumo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }


}
