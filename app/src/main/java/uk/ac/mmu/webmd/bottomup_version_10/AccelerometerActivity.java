package uk.ac.mmu.webmd.bottomup_version_10;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ArrayList sensorData;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private float current_x, current_y, current_z;
    private static final int SHAKE_THRESHOLD = 600;
    private float vibrateThreshold = 0;
    private TextView current_x_tv, current_y_tv, current_z_tv, classificationTextView;
    private double gravity[];
    private double linear_acceleration[];

    //Activity Recognition
    private Float X_mean, Y_mean, Z_mean;
    private Float X_new, Y_new, Z_new;
    private Float X_total, Y_total, Z_total;
    private int Break, SABout, PABout;
    private String classification;
    private int readingsNum;
    private float test;


    public Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        initialiseViews();

        //Define a variable to determine if someone is in a break or not
        Break = 0;
        SABout = 0;
        PABout = 0;
        X_mean = Float.valueOf(0);
        Y_mean = Float.valueOf(0);
        Z_mean = Float.valueOf(0);
        X_total = Float.valueOf(0);
        Y_total = Float.valueOf(0);
        Z_total = Float.valueOf(0);
        classification = "N/A";
        readingsNum = 0;

        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        if(mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){

            mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            vibrateThreshold = mAccelerometer.getMaximumRange() / 2;
            sensorData = new ArrayList();
        } else {

        }

        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void initialiseViews() {
        current_x_tv = (TextView) findViewById(R.id.current_x);
        current_y_tv = (TextView) findViewById(R.id.current_y);
        current_z_tv = (TextView) findViewById(R.id.current_z);
        classificationTextView = (TextView) findViewById(R.id.classificationTextView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        readingsNum += 1;
        if(readingsNum <= 100) {

            X_new = Math.abs(event.values[0]);
            Y_new = Math.abs(event.values[1]);
            Z_new = Math.abs(event.values[2]);
            X_mean = (X_total += X_new) / readingsNum;
            Y_mean = (Y_total += Y_new) / readingsNum;
            Z_mean = (Z_total += Z_new) / readingsNum;

            displayCleanValues();
            displayCurrentValues();
        }

        if(Math.abs(Y_mean) > Math.abs(X_mean)) {
            if(Math.abs(Y_mean) > Math.abs(Z_mean)){
                if(Math.signum(Y_mean) ==  -1) {
                    classification = "Active";
                    test = Math.signum(Y_mean);
                }
                else {
                    classification = "Sedentary";
                }
            }else{
                classification = "Sedentary";
            }
        } else {
            classification = "Sedentary";
        }




        Log.e("IMPORTANT", "IMPORTANT");

//        displayCleanValues();
//        displayCurrentValues();

//        final double alpha = 0.8;

//        //isolate the force of gravity with the low-pass filter
//        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
//        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
//        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];
//
//        //remove the gravity contribution with the high-pass filter
//        linear_acceleration[0] = event.values[0] - gravity[0];
//        linear_acceleration[1] = event.values[1] - gravity[1];
//        linear_acceleration[2] = event.values[2] - gravity[2];

//        current_x = Math.abs(last_x - event.values[0]);
//        current_y = Math.abs(last_y - event.values[1]);
//        current_z = Math.abs(last_z - event.values[2]);


    }

    public void displayCleanValues() {
        current_x_tv.setText("0.0");
        current_y_tv.setText("0.0");
        current_z_tv.setText("0.0");
    }

    public void displayCurrentValues(){

        current_x_tv.setText(String.format("%.6f", X_mean));
        current_y_tv.setText(Integer.toString(readingsNum));
        current_z_tv.setText(String.format("%.6f", X_total));
        classificationTextView.setText(classification);

//        current_x_tv.setText(Float.toString(current_x));
//        current_y_tv.setText(Float.toString(current_y));
//        current_z_tv.setText(Float.toString(current_z));

//        current_x_tv.setText(Double.toString(linear_acceleration[0]));
//        current_y_tv.setText(Double.toString(linear_acceleration[1]));
//        current_z_tv.setText(Double.toString(linear_acceleration[2]));
    }
}
