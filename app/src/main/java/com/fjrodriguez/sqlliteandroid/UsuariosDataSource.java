package com.fjrodriguez.sqlliteandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fjrodriguez on 25/06/14.
 */
public class UsuariosDataSource {

    // Campos de la base de datos.
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMNA_ID,
            MySQLiteHelper.COLUMNA_NOMBRE,
            MySQLiteHelper.COLUMNA_CORREO,
            MySQLiteHelper.COLUMNA_CP,
            MySQLiteHelper.COLUMNA_EDAD,
            MySQLiteHelper.COLUMNA_FECHANAC
    };

    public UsuariosDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Usuario crearUsuario(String nombre, String correo, String cp, int edad, String fechaNac) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMNA_NOMBRE, nombre);
        values.put(MySQLiteHelper.COLUMNA_CORREO, correo);
        values.put(MySQLiteHelper.COLUMNA_CP, cp);
        values.put(MySQLiteHelper.COLUMNA_EDAD, edad);
        values.put(MySQLiteHelper.COLUMNA_FECHANAC, fechaNac);

        long insertId = database.insert(MySQLiteHelper.TABLA_USUARIOS, null, values);

        Cursor cursor = database.query(MySQLiteHelper.TABLA_USUARIOS,
                allColumns, MySQLiteHelper.COLUMNA_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Usuario nuevoUsuario = cursorToUsuario(cursor);
        cursor.close();

        return nuevoUsuario;
    }

    public void borrarUsuario (Usuario usuario) {
        long id = usuario.getId();
        Log.d("test", "Usuario borrado con id: " + id);
        database.delete(MySQLiteHelper.TABLA_USUARIOS, MySQLiteHelper.COLUMNA_ID
            + " = " + id, null);
    }

    public List<Usuario> getAllUsuarios() {
        List<Usuario> usuarios = new ArrayList<Usuario>();

        Cursor cursor = database.query(MySQLiteHelper.TABLA_USUARIOS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Usuario usuario = cursorToUsuario(cursor);
            usuarios.add(usuario);
            cursor.moveToNext();
        }

        // asegurarse de que se cierra el cursor.
        cursor.close();
        return usuarios;
    }

    private Usuario cursorToUsuario (Cursor cursor) {
        Usuario usuario = new Usuario();
        usuario.setId(cursor.getLong(0));
        usuario.setNombre(cursor.getString(1));
        usuario.setCorreo(cursor.getString(2));
        usuario.setCodigoPostal(cursor.getString(3));
        usuario.setEdad(cursor.getInt(4));
        usuario.setFechaNacimiento(cursor.getString(5));

        return usuario;
    }
}
