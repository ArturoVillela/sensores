package com.basicos.charlie.brujulacharlie;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.basicos.charlie.sensores.Acelerometro;
import com.basicos.charlie.sensores.MagneticFieldSensor;

import java.util.List;

/*
Three broad categories of sensors.
    Motion Sensors
    Environmental sensors
    Position sensors
 */
public class MainActivity extends Activity {

    private SensorManager sensorManager;

    String[] sensoresEnCelular;
    int[] tipoSensor; //referencia hacia el tipo de sensor.. para usarlo..

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getActionBar().hide();
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> list = sensorManager.getSensorList(Sensor.TYPE_ALL);

        sensoresEnCelular = new String[list.size()];
        tipoSensor = new int[list.size()];
        for (int n=0;n<list.size();n++){
            sensoresEnCelular[n] = list.get(n).getName();
            tipoSensor[n] = list.get(n).getType();
        }

        ListView listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,sensoresEnCelular));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int indice, long l) {
                msg("tipo sensor : "+tipoSensor[indice]);
                startNextActivity(indice);
            }
        });


//        tv.setText(cad);
//
//        Log.d("charlie", cad);

//        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //type uno es Acelerometer
//        sensorManager.registerListener(this,acelerometro,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void startNextActivity(int indiceClicked){
        msg("clicked en indice: "+indiceClicked);
        int tipo = tipoSensor[indiceClicked];
        switch (tipo){
            case 1: //tipo sensor Acelerometro
                    startActivity(new Intent(MainActivity.this, Acelerometro.class));
                    break;
            case 2://tipo magnetic field
                    startActivity(new Intent(MainActivity.this, MagneticFieldSensor.class));
                    break;
        }
    }

    public void msg(String cad){
        Log.d("charlie",cad);
    }




    //region menu
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
    //endregion

}
