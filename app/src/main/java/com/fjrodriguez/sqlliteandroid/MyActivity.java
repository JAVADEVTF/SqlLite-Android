package com.fjrodriguez.sqlliteandroid;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;


public class MyActivity extends ListActivity {

    private UsuariosDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        dataSource = new UsuariosDataSource(this);
        try {
            dataSource.open();
        } catch (Exception e) {
            Log.d("test", "Error al abrir la base de datos");
        }

        List<Usuario> usuarios = dataSource.getAllUsuarios();

        // Usar el SimpleCursorAdapter para mostrar los elementos en un ListView.
        ArrayAdapter<Usuario> adapter = new ArrayAdapter<Usuario>(this,
                android.R.layout.simple_list_item_1, usuarios);
        setListAdapter(adapter);
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

    @Override
    public void onResume() {
        try {
            dataSource.open();
        } catch (Exception e) {
            Log.d("test", "Error al abrir la base de datos");
        }
        super.onResume();

    }

    @Override
    public void onPause() {
        dataSource.close();
        super.onPause();
    }
}
