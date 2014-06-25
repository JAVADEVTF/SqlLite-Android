package com.fjrodriguez.sqlliteandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Alumno on 25/06/2014.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLA_USUARIOS = "usuarios";
    public static final String COLUMNA_ID = "_id";
    public static final String COLUMNA_NOMBRE = "nombre";
    public static final String COLUMNA_CORREO = "correo";
    public static final String COLUMNA_CP= "cp"; // Código Postal.
    public static final String COLUMNA_EDAD = "edad";
    public static final String COLUMNA_FECHANAC = "fechanac"; // Fecha de nacimiento.

    public static final String DATABASE_NAME = "usuarios.db";
    public static final int DATABASE_VERSION = 1;

    // Sentencia de creación de la base de datos.
    private static final String DATABASE_CREATE = "create table "
            + TABLA_USUARIOS + "(" + COLUMNA_ID
            + " integer primary key autoincrement, " + COLUMNA_NOMBRE
            + " char(25) not null, " + COLUMNA_CORREO
            + " char(40), " + COLUMNA_CP
            + " char(10), " + COLUMNA_EDAD
            + " integer, " + COLUMNA_FECHANAC
            + " date);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIOS);
        onCreate(sqLiteDatabase);

    }
}
