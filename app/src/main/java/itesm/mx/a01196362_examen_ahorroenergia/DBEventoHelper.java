package itesm.mx.a01196362_examen_ahorroenergia;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBEventoHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DBapp.db";
    private static final int DATABASE_VERSION = 1;

    public DBEventoHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVENTOS_TABLE = "CREATE TABLE " +
                DBSchema.EventoTable.TABLE_NAME +
                "(" +
                DBSchema.EventoTable._ID + " INTEGER PRIMARY KEY," +
                DBSchema.EventoTable.COLUMN_NAME_NOMBRE + " TEXT," +
                DBSchema.EventoTable.COLUMN_NAME_CONSUMO + " TEXT," +
                DBSchema.EventoTable.COLUMN_NAME_FECHA + " TEXT," +
                DBSchema.EventoTable.COLUMN_NAME_IMAGEN + " BLOB " +
                ")";
        Log.i("onCreate", CREATE_EVENTOS_TABLE);
        db.execSQL(CREATE_EVENTOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DELETE_EVENTOS_TABLE = "DROP TABLE IF EXISTS " +
                DBSchema.EventoTable.TABLE_NAME;
        db.execSQL(DELETE_EVENTOS_TABLE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
