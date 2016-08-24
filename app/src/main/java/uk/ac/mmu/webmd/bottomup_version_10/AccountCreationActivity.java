package uk.ac.mmu.webmd.bottomup_version_10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Tom on 22/08/2016.
 */
public class AccountCreationActivity extends AppCompatActivity {

    private EditText mEmailAddress;
    private EditText mPassword;
    private EditText mPasswordConfirmed;
    private EditText mName;
    private EditText mAge;
    private RadioButton mMale, mFemale;
    private RadioGroup mGender;
    private EditText mWeight;
    private DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);

        mEmailAddress = (EditText) findViewById(R.id.emailAddressEditText);
        mPassword = (EditText) findViewById(R.id.passwordOneEditText);
        mPasswordConfirmed = (EditText) findViewById(R.id.passwordTwoEditText);
        mName = (EditText) findViewById(R.id.nameEditText);
        mMale = (RadioButton) findViewById(R.id.maleRadioButton);
        mFemale = (RadioButton) findViewById(R.id.femaleRadioButton);
        mGender = (RadioGroup) findViewById(R.id.genderRadioGroup);
        mAge = (EditText) findViewById(R.id.ageEditText);
        mWeight = (EditText) findViewById(R.id.weightEditText);

        dao = new DAO(this);

    }

    public void addAccount(View v){

        String emailAddress = mEmailAddress.getText().toString();
        String password = mPassword.getText().toString();
        String passwordConfirmed = mPasswordConfirmed.getText().toString();
        String name = mName.getText().toString();

        int age = Integer.parseInt(mAge.getText().toString());
        double weight = Double.parseDouble(mWeight.getText().toString());

        String gender = "None";
        if(mMale.isChecked() == true){
            gender = "male";
        }else if (mFemale.isChecked() == true){
            gender = "female";
        }

        if(password.equals(passwordConfirmed) == true
                && emailAddress.isEmpty() == false
                && name.isEmpty() == false
                && age > 0
                && weight > 0){

            Person p = new Person(name, age, gender, password);
            dao.insertPerson(p);
            Toast successMessage = Toast.makeText(this, "Account added successfully", Toast.LENGTH_LONG);
            successMessage.show();

            Intent myIntent = new Intent(AccountCreationActivity.this, MainActivity.class);
            startActivity(myIntent);

        }else{
            Toast errorMessage = Toast.makeText(this, "Must complete all details", Toast.LENGTH_LONG);
            errorMessage.show();
        }


    }
}
