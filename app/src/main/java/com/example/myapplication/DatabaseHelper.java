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
        ContentValues values = new ContentValues();
        values.put("nombre", jugador);
        values.put("puntuacion", puntuacion);
        db.insert("Ganadores", null, values);
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
    public String getPrimero() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT nombre FROM Ganadores ORDER BY puntuacion DESC LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);
        String nombreJugador = "";

        // Verificamos si el cursor tiene resultados
        if (cursor.moveToFirst() && !cursor.isNull(0)) {
            // Obtenemos el valor de la columna 'nombre'
            nombreJugador = cursor.getString(0); // Índice 0 porque solo seleccionamos 'nombre'
        }

        // Cerramos el cursor y la base de datos
        cursor.close();
        db.close();

        return nombreJugador; // Retornamos el nombre del jugador con mayor puntuación
    }
    public String getSegundo() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT nombre FROM Ganadores ORDER BY puntuacion DESC LIMIT 1 OFFSET 1";
        Cursor cursor = db.rawQuery(query, null);
        String nombreJugador = "";

        // Verificamos si el cursor tiene resultados
        if (cursor.moveToFirst()) {
            // Obtenemos el valor de la columna 'nombre'
            nombreJugador = cursor.getString(0); // Índice 0 porque solo seleccionamos 'nombre'
        }

        // Cerramos el cursor y la base de datos
        cursor.close();
        db.close();

        return nombreJugador; // Retornamos el nombre del jugador con la segunda mayor puntuación
    }
    public String getTercero() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT nombre FROM Ganadores ORDER BY puntuacion DESC LIMIT 1 OFFSET 2";
        Cursor cursor = db.rawQuery(query, null);
        String nombreJugador = "";

        // Verificamos si el cursor tiene resultados
        if (cursor.moveToFirst()) {
            // Obtenemos el valor de la columna 'nombre'
            nombreJugador = cursor.getString(0); // Índice 0 porque solo seleccionamos 'nombre'
        }

        // Cerramos el cursor y la base de datos
        cursor.close();
        db.close();

        return nombreJugador; // Retornamos el nombre del jugador con la tercera mayor puntuación
    }




}

