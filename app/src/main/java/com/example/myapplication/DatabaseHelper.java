package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class DatabaseHelper extends SQLiteOpenHelper {
    private Context contexto;
    private final String SQLCREATE = "CREATE TABLE IF NOT EXISTS Ganadores (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, puntuacion INTEGER)";
    private final String SQLDROP = "DROP TABLE IF EXISTS Ganadores";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DBGanadores.db";

    //Constructor de la bbdd
    public DatabaseHelper(Context contexto) {
        super(contexto, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Metodo obligatorio onCreate (al crear la bbdd, creamos la tabla)
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLCREATE);

    }

    //Metodo obligatorio onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //db.execSQL(SQLDROP);
        // db.execSQL(SQLCREATE);
    }

    public void insertarganador(String jugador, int puntuacion) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Verifico si el jugador ya existe
        String query = "SELECT puntuacion FROM Ganadores WHERE nombre = ?";
        Cursor cursor = db.rawQuery(query, new String[]{jugador});

        if (cursor.moveToFirst()) {
            int puntuacionActual = cursor.getInt(0);

            // si la nueva puntuacion es mejor la cambia
            if (puntuacion > puntuacionActual) {
                ContentValues values = new ContentValues();
                values.put("puntuacion", puntuacion);
                db.update("Ganadores", values, "nombre = ?", new String[]{jugador});
            }
        } else {
            // Si el jugador no existe hace uno nuevo
            ContentValues values = new ContentValues();
            values.put("nombre", jugador);
            values.put("puntuacion", puntuacion);
            db.insert("Ganadores", null, values);
        }

        cursor.close();
        db.close();
    }

    public boolean recordPorJugador(String nombre, int puntuacion) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT MAX(puntuacion) FROM Ganadores WHERE nombre = ?";
        Cursor cursor = db.rawQuery(query, new String[]{nombre});
        int maxPuntuacion = 0;

        if (cursor != null) {
            if (cursor.moveToFirst() && !cursor.isNull(0)) { // Asegúrate de que el cursor tiene datos
                maxPuntuacion = cursor.getInt(0);
            }
            cursor.close();
        }

        db.close();
        return puntuacion > maxPuntuacion; // Devuelve si la puntuación actual es mayor al récord
    }

    public int getRecordPorJugador(String nombre) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT MAX(puntuacion) FROM Ganadores WHERE nombre = ?";
        Cursor cursor = db.rawQuery(query, new String[]{nombre});
        int maxPuntuacion = 0;

        if (cursor != null) {
            if (cursor.moveToFirst() && !cursor.isNull(0)) { // Asegúrate de que el cursor tiene datos
                maxPuntuacion = cursor.getInt(0);
            }
            cursor.close();
        }

        db.close();
        return maxPuntuacion; // Devuelve la puntuación máxima registrada para ese jugador
    }

    public void  reiniciar(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(SQLDROP);
        db.close();
    }
    public void revisar(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(SQLCREATE);
        db.close();

    }
    public List<String> obtenerTop3() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> topJugadores = new ArrayList<>();

        String query = "SELECT nombre, puntuacion FROM Ganadores ORDER BY puntuacion DESC LIMIT 3";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String nombre = cursor.getString(0);
                int puntuacion = cursor.getInt(1);
                topJugadores.add(nombre + " - " + puntuacion);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return topJugadores;
    }




}

