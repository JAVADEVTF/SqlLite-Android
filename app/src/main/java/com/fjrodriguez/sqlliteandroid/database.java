package com.fjrodriguez.sqlliteandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Alumno on 25/06/2014.
 */
public class database extends SQLiteOpenHelper {

    public database (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQLCREATION = "CREATE TABLE usuarios (" +
                "CODIGO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "NOMBRE CHAR(25) NOT NULL, " +
                "CORREO CHAR(40), " +
                "CODIGOPOSTAL CHAR(10), " +
                "EDAD INTEGER, " +
                "FECHANAC DATE);";

        try {
            sqLiteDatabase.execSQL(SQLCREATION);
        } catch (Exception e) {
            Log.d("test", "Error al crear la base de datos.");

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        // Aquí agregamos todos los drop tables, los alter tables,

    }
}
