package uk.ac.mmu.webmd.bottomup_version_10;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Tom on 22/08/2016.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText mPassword;
    private EditText mLogin;
    private DAO dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPassword = (EditText) findViewById(R.id.passwordView);
        mLogin = (EditText) findViewById(R.id.loginView);

        dao = new DAO(this);

        Person p = new Person("Tom", 25, "male", "tomwjt");
        dao.insertPerson(p);


    }

    public void accountLogin(View v){

        String accountName = mLogin.getText().toString();
        String passwordEntered = mPassword.getText().toString();
        String passwordCorrect;
        Cursor c;

        if(dao.checkAccounts() == true) {

            try {

                c = dao.getPerson(accountName);
                c.moveToFirst();
                passwordCorrect = c.getString(c.getColumnIndex("password"));
                if (passwordCorrect.equals(passwordEntered) == true) {
                    Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(myIntent);
               }

            } catch (Exception e) {
                Toast errorMessage = Toast.makeText(this, "Invalid Account Name or Password", Toast.LENGTH_LONG);
                errorMessage.show();
            }

        } else{
            Toast errorMessage = Toast.makeText(this, "No Active Accounts", Toast.LENGTH_LONG);
            errorMessage.show();
        }


    }

    public void createAccount(View v){
        Intent myIntent = new Intent(LoginActivity.this, AccountCreationActivity.class);
        startActivity(myIntent);

    }
}
