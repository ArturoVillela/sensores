package com.basicos.charlie.sensores;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.basicos.charlie.brujulacharlie.R;


public class Acelerometro extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor acelerometro;
    private TextView tv_sensor;

    private RadioGroup radioGroup;
    private RadioButton rbX, rbY, rbZ;

    private int rb_checked = 0; //x

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acelerometro);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //type uno es Acelerometer
        sensorManager.registerListener(this,acelerometro,SensorManager.SENSOR_DELAY_NORMAL);

        tv_sensor = (TextView) findViewById(R.id.tv_sa_resultado);

        radioGroup = (RadioGroup) findViewById(R.id.rg_variaciones_acelerometro);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int radioButtonId) {

                switch (radioButtonId){
                    case R.id.radioX:
                                    rb_checked = 0;
                                    msg("x");
                                    break;
                    case R.id.radioY:
                                    rb_checked = 1;
                                    msg("y");
                                    break;
                    case R.id.radioZ:
                                    rb_checked = 2;
                                    msg("z");
                                    break;
                }
            }
        });


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            String cad = "";

            switch (rb_checked){
                case 0://x
                       cad = "Variacion: "+x;break;
                case 1://x
                    cad = "Variacion: "+y;break;
                case 2://x
                    cad = "Variacion: "+z;break;
            }
            tv_sensor.setText(cad);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void msg(String cad){
        Log.d("charlie", cad);
    }

    //region Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.acelerometro, menu);
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
