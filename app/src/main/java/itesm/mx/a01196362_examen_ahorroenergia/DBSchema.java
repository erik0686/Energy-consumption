package itesm.mx.a01196362_examen_ahorroenergia;

import android.provider.BaseColumns;

public class DBSchema {
    private DBSchema() {}

    public static class EventoTable implements BaseColumns {
        public static final String TABLE_NAME = "Evento";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_CONSUMO = "consumo";
        public static final String COLUMN_NAME_IMAGEN = "imagen";
        public static final String COLUMN_NAME_FECHA = "fecha";
    }
}

