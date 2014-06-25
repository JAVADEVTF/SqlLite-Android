package com.fjrodriguez.sqlliteandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.fjrodriguez.sqlliteandroid.R;

public class MainActivity extends Activity {

    private UsuariosDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSource = new UsuariosDataSource(this);
        try {
            dataSource.open();
        } catch (Exception e) {
            Log.d("test", "Error al abrir la base de datos.");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            Log.d("test", "Error al abrir la base de datos.");
        }
        super.onResume();

    }

    @Override
    public void onPause() {
        dataSource.close();
        super.onPause();
    }

    public void pulsado_botones (View view) {
        /* TODO: Implementar el código de navegación por los registros de la base de datos */
        switch (view.getId()) {
            case R.id.btn_insertar:
                break;
            case R.id.btn_primero:
                break;
            case R.id.btn_atras:
                break;
            case R.id.btn_avanzar:
                break;
            case R.id.btn_final:
                break;
        }
    }
}
