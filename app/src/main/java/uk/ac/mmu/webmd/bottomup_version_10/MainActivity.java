package uk.ac.mmu.webmd.bottomup_version_10;

import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private EditText nameField, ageField;
    private DAO dao;
    public GoogleApiClient mApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao = new DAO(this);

        mApiClient = new GoogleApiClient.Builder(this)
                .addApi(ActivityRecognition.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mApiClient.connect();

    }

    @Override
    public void onConnected(Bundle bundle) {

        Intent myIntent = new Intent(this, ActivityRecognitionService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(mApiClient, 3000, pendingIntent);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    //    public void createAccount(View view){
//        int age = 0;
//        Person person;
//        String name;
//        String gender;
//
//        //Get persons age
//        try {
//            age = Integer.parseInt(ageField.getText().toString());
//        }catch(Exception e){
//            Toast displayAccountErrorMessage = Toast.makeText(this, "Invalid Age", Toast.LENGTH_LONG);
//            displayAccountErrorMessage.show();
//        }
//
//        //Get person name
//        name = nameField.getText().toString();
//        gender = "male";
//        if(age > 0 && age < 120 && name.isEmpty() == false && gender.isEmpty() == false) {
//            person = new Person(name, age, gender);
//            dao.insertPerson(person);
//        }else{
//            Toast displayAccountErrorMessage = Toast.makeText(this, "Invalid Details", Toast.LENGTH_LONG);
//            displayAccountErrorMessage.show();
//        }
//
//    }

//    public void displayAccount(View view){
//
//        int id = 1;
//        //Need to add in an if(ArrayList == Empty) statement
//
//        try {
//            id = Integer.parseInt(accountNum.getText().toString());
//        } catch(Exception e){
//            Toast displayAccountErrorMessage = Toast.makeText(this, "ID must only contain numbers.", Toast.LENGTH_LONG);
//            displayAccountErrorMessage.show();
//        }
//
//        try {
//            Cursor c = dao.getPerson(id);
//
//                c.moveToFirst();
//                int age = c.getInt(c.getColumnIndex("age"));
//                accountNum.setText(age + "");
//
//                if (!c.isClosed()) {
//                    c.close();
//                }
//        }catch(Exception e){
//            Toast displayAccountErrorMessage = Toast.makeText(this, "Invalid ID", Toast.LENGTH_LONG);
//            displayAccountErrorMessage.show();
//        }
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent overviewActivity = new Intent(this, OverviewActivity.class);
            startActivity(overviewActivity);
        }

        if (id == R.id.accelerometer_readings){
            Intent accelActivity = new Intent(this, AccelerometerActivity.class);
            startActivity(accelActivity);
        }

        return super.onOptionsItemSelected(item);
    }


}
