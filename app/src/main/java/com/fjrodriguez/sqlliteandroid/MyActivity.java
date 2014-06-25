package com.fjrodriguez.sqlliteandroid;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MyActivity extends Activity {

    SQLiteDatabase DBW;
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        info = (TextView) findViewById(R.id.textViewInfo);
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

    public void recuperar(View view) {
        if (DBW != null) {
            String SQLSELECCION = "SELECT NOMBRE, CORREO FROM usuarios;";
            String[] argumentos = null;
            Cursor C = DBW.rawQuery(SQLSELECCION, argumentos);
            boolean continuar = C.moveToFirst();
            while (continuar) {
                info.append("Nombre: "+C.getString(0)+"\n");
                info.append("Correo: "+C.getString(1)+"\n");
                continuar = C.moveToNext();
            }
            info.append("Total hallados: "+String.valueOf(C.getCount())+"\n");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        database DB = new database(this, "Miagenda3.db", null, 1);
        DBW = DB.getWritableDatabase();
    }

    @Override
    public void onPause() {
        super.onPause();
        DBW.close();
    }
}
