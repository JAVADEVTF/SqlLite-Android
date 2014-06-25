package com.fjrodriguez.sqlliteandroid;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MyActivity extends Activity {

    SQLiteDatabase DBW;
    EditText editTextNombre, editTextCorreo, editTextCP, editTextFechaNacimiento;
    Cursor C;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        editTextCorreo = (EditText) findViewById(R.id.editTextCorreo);
        editTextCP = (EditText) findViewById(R.id.editTextCP);
        editTextFechaNacimiento = (EditText) findViewById(R.id.editTextFechaNacimiento);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void guardar(View view) {
        if (DBW != null) {
            String SQLINSERTAR = "INSERT INTO usuarios" +
                "(NOMBRE,CORREO,CODIGOPOSTAL,EDAD,FECHANAC)" +
                "VALUES ('Pedro Pérez', 'pedroperez@notengo.com','38010',30,'1984-10-30'), " +
                    "('Ana Fernández', 'anafer@a.com', '38000', 20, '1994-10-10'); ";
            DBW.execSQL(SQLINSERTAR);
            try {
                DBW.execSQL(SQLINSERTAR);
            } catch (Exception e) {
                Toast.makeText(this, "Error al insertar", Toast.LENGTH_LONG);
            }
        } else
            Toast.makeText(this, "No conectado a la B.D.", Toast.LENGTH_LONG);
    }

    public void cargarDatos() {
        if (DBW != null) {
            String SQLSELECCION = "SELECT NOMBRE, CORREO, CODIGOPOSTAL, EDAD, FECHANAC " +
                    "FROM usuarios;";
            String[] argumentos = null;
            Cursor C = DBW.rawQuery(SQLSELECCION, argumentos);
        }

    }

    private void mostrarDatos() {
        editTextNombre.setText(C.getString(0));
        editTextCorreo.setText(C.getString(1));
        editTextCP.setText(C.getString(2));
        editTextFechaNacimiento.setText(C.getString(3));
    }

    public void mostrarUsuario(View view) {
        switch (view.getId()) {
            case R.id.btn_principio:
                C.moveToFirst();
                break;
            case R.id.btn_atras:
                C.moveToPrevious();
                break;
            case R.id.btn_avanzar:
                C.moveToNext();
                break;
            case R.id.btn_final:
                C.moveToLast();
                break;
        }
        mostrarDatos();
    }

    @Override
    public void onResume() {
        super.onResume();
        database DB = new database(this, "Miagenda3.db", null, 1);
        DBW = DB.getWritableDatabase();
        if (DBW != null) {
            cargarDatos();
            mostrarUsuario(findViewById(R.id.btn_principio));
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        DBW.close();
    }
}
