package com.example.sample4_6;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SensorManager mySm;
    private Sensor myS;
    private TextView tvx;
    private TextView tvy;
    private TextView tvz;
//    private TextView tv1;
    private TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySm = (SensorManager) getSystemService(SENSOR_SERVICE);
//        myS = mySm.getDefaultSensor(Sensor.TYPE_LIGHT);
//        我这手机好像不存在温度传感器
//        myS = mySm.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
//        myS = mySm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
//        myS = mySm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        myS = mySm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
//        myS = mySm.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        myS = mySm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
//        tv1 = findViewById(R.id.tv1);
        tvx = findViewById(R.id.tvx);
        tvy = findViewById(R.id.tvy);
        tvz = findViewById(R.id.tvz);
        tv2 = findViewById(R.id.tv2);

        StringBuffer str = new StringBuffer();
        str.append("\n名称：");
        str.append(myS.getName());
        str.append("\n类型编号：");
        str.append(myS.getType());
        str.append("\n耗电量(mA)");
        str.append(myS.getPower());
        str.append("\n测量最大范围：");
        str.append(myS.getMaximumRange());
        str.append("\n版本：");
        str.append(myS.getVersion());
        tv2.setText(str);
        tv2.setTextSize(25);
    }
    private SensorEventListener mySel = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] value = event.values;
//            tv1.setText("\n光照强度是：" + value[0]);
//            tv1.setTextSize(25);
            tvx.setText("x轴方向的位置：" + value[0]);
            tvy.setText("y轴方向的位置：" + value[1]);
            tvz.setText("z轴方向的位置：" + value[2]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mySm.registerListener(mySel,myS,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mySm.unregisterListener(mySel);
    }
}