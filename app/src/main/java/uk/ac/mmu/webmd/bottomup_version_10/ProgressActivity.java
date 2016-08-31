package uk.ac.mmu.webmd.bottomup_version_10;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class ProgressActivity extends AppCompatActivity {

    private EditText walking, still;
    private DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        still = (EditText) findViewById(R.id.sedentaryEditText);
        walking = (EditText) findViewById(R.id.weightEditText);


        dao = new DAO(this);
        Cursor c = dao.getActivityLog("Tom");
        c.moveToFirst();
//        String name = c.getString(c.getColumnIndex("name"));
//        if(!name.isEmpty()){
//            Toast message = Toast.makeText(this, "Welcome, " + name + "!", Toast.LENGTH_LONG);
//            message.show();
//        }

//        String stillText = c.getString(c.getColumnIndex("still"));
//        String walkingText = c.getString(c.getColumnIndex("walking"));
//        still.setText(stillText);
//        walking.setText(walkingText);

    }


}
