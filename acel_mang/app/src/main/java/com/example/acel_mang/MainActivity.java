package com.example.acel_mang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager sensorManager;
    private TextView message_gravity;
    private TextView message_magnetometro;
    private TextView message;
    private TextView m_x;
    private TextView m_y;
    private TextView m_z;
    private TextView g_x;
    private TextView g_y;
    private TextView g_z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        message = findViewById(R.id.textView_message);
        message_gravity = findViewById(R.id.textView_message_gravity);
        message_magnetometro = findViewById(R.id.textView_message_magnetometro);

        m_x = findViewById(R.id.textView_x);
        m_y = findViewById(R.id.textView_y);
        m_z = findViewById(R.id.textView_z);
        g_x = findViewById(R.id.text_x);
        g_y = findViewById(R.id.text_y);
        g_z = findViewById(R.id.text_z);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Sensor gravity = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (gravity != null) {
            sensorManager.registerListener(this, gravity,SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);
        }
        else
        {
            message_gravity.setText("Sin sensor de Gravedad");
        }
        Sensor magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (magneticField != null) {
            sensorManager.registerListener(this, magneticField, SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);
        }else{
            message_magnetometro.setText("Sin sensor de Magentometro");
        }

    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            m_x.setText(""+x);
            m_y.setText(""+y);
            m_z.setText(""+z);
            if(z<2 && z>-2){
               if(x>-2 && x<2){
                   if( y>-10 && y<-7 ) message.setText("Vertical 2");
                   else if(y<10 && y>7)  message.setText("Vertical 1");
               }
               if(y<1 && y>-2){
                   if( x>-10 && x<-9 ) message.setText("Horizontal 2");
                   else if(x<10 && x>9)  message.setText("Horizontal 1");
               }

            }
            //message.setText("Vertical 1");


            //message.setText("Horizontal 1");
            //message.setText("Horizontal 1");


        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            m_x.setText(""+x);
            m_y.setText(""+y);
            m_z.setText(""+z);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}