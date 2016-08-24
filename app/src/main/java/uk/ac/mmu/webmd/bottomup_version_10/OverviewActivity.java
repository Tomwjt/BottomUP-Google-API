package uk.ac.mmu.webmd.bottomup_version_10;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class OverviewActivity extends AppCompatActivity {

    TextView customWelcome;
    DAO dao;
    Button goalButton;
    Button progressButton;
    Button socialButton;
    Button profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        customWelcome = (TextView) findViewById(R.id.customWelcome);
        goalButton = (Button) findViewById(R.id.goalsButton);
        progressButton = (Button) findViewById(R.id.progressButton);
        socialButton = (Button) findViewById(R.id.socialButton);
        profileButton = (Button) findViewById(R.id.profileButton);

        dao = new DAO(this);


//        createMessage();

//        goalButton.setMaxHeight(15);
//        goalButton.setMaxWidth(15);


    }

    public void goalButton(View v){
        Intent goalActivity = new Intent(this, GoalsActivity.class);
        startActivity(goalActivity);
    }

    public void progressButton(View v){
        Intent progressActivity = new Intent(this, ProgressActivity.class);
        startActivity(progressActivity);

    }


    //Null Pointer Error Needs to be resolved here

//    public void createMessage() {
//        String message = "Hello!";
//        String time = getTime();
//        String name;
//        try {
//            Cursor person = dao.getPerson(1);
//            person.moveToFirst();
//            name = person.getString(person.getColumnIndex("name"));
//
//        if (name.isEmpty() == false && time.isEmpty()) {
//            message = "Hello, " + name + "!";
//        } else if (name.isEmpty() == false && time.isEmpty() == false) {
//            message = time + name + "!";
//        }
//        if (!person.isClosed()){
//            person.close();
//        }
//
//        customWelcome.setText(message);
//        } catch (NullPointerException error){
//            Toast errorMessage = Toast.makeText(this, "Account Not Registered", Toast.LENGTH_LONG);
//            errorMessage.show();
//        }
//    }
//
//    public String getTime() {
//
//        String time = "";
//        Date date = new Date();
//        Calendar cal = Calendar.getInstance();
//        int hours = cal.get(Calendar.HOUR_OF_DAY);
//
//        if (hours >= 1 && hours <= 12) {
//            time = "Wow! You're up early, ";
//        }
//        if (hours >= 12 && hours <= 16) {
//            time = "Good afternoon, ";
//        }
//        if (hours >= 16 && hours <= 21) {
//            time = "Good evening, ";
//        }
//        if (hours >= 21 && hours <= 24) {
//            time = "Get to sleep, ";
//        }
//
//        return time;
//    }


}
