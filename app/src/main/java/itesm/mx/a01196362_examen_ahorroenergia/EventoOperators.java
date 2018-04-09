package itesm.mx.a01196362_examen_ahorroenergia;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;

public class EventoOperators {

    private SQLiteDatabase db;
    private DBEventoHelper dbHelper;
    private Evento evento;

    public EventoOperators(Context context) { dbHelper = new DBEventoHelper(context); }
    public void open() throws SQLException {
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            Log.e("SQLOPEN", e.toString());
        }

    }

    public void close() { db.close(); }

    public long addEvent(Evento evento) {
        long newRowId = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(DBSchema.EventoTable.COLUMN_NAME_CONSUMO, evento.getConsumo());
            values.put(DBSchema.EventoTable.COLUMN_NAME_NOMBRE, evento.getNombre());
            values.put(DBSchema.EventoTable.COLUMN_NAME_FECHA, evento.getFecha());
            values.put(DBSchema.EventoTable.COLUMN_NAME_IMAGEN, evento.getImagen());

            newRowId = db.insert(DBSchema.EventoTable.TABLE_NAME, null, values);
        } catch (SQLException e) {
            Log.e("Agregar evento", e.toString());
        }
        return newRowId;
    }

    public ArrayList<Evento> getAllEventos() {
        ArrayList<Evento> listaEventos = new ArrayList<Evento>();
        String selectQuery = "SELECT * FROM " + DBSchema.EventoTable.TABLE_NAME;

        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    evento = new Evento(Integer.parseInt(cursor.getString(0)),
                            cursor.getString(2),
                            cursor.getString(1),
                            cursor.getString(3),
                            cursor.getBlob(4));
                    listaEventos.add(evento);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("Get all events", e.toString());
        }
        return listaEventos;
    }
}
