package uk.ac.mmu.webmd.bottomup_version_10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Tom on 07/06/2016.
 */
public class DAO extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES_PERSON_TABLE =
            "CREATE TABLE " + PersonSQLContract.FitnessDatabase.PERSON_TABLE_NAME + "(" +
                    PersonSQLContract.FitnessDatabase.PERSON_COLUMN_PASSWORD + " TEXT," +
                    PersonSQLContract.FitnessDatabase.PERSON_COLUMN_ID + " INTEGER PRIMARY KEY," +
                    PersonSQLContract.FitnessDatabase.PERSON_COLUMN_NAME + " TEXT," +
                    PersonSQLContract.FitnessDatabase.PERSON_COLUMN_AGE + " INTEGER," +
                    PersonSQLContract.FitnessDatabase.PERSON_COLUMN_GENDER + " TEXT" +
            " )";

//    private static final String SQL_CREATE_ENTRIES_ACTIVITY_TABLE = "CREATE TABLE " + PersonSQLContract.FitnessDatabase.ACTIVITY_TABLE_NAME + "(" +
//            PersonSQLContract.FitnessDatabase.ACTIVITY_COLUMN_NAME + " FOREIGN KEY ("+ PersonSQLContract.FitnessDatabase.PERSON_TABLE_NAME+") " +
//            "REFERENCES "+PersonSQLContract.FitnessDatabase.PERSON_TABLE_NAME+"("+PersonSQLContract.FitnessDatabase.PERSON_COLUMN_NAME+"), " +
//            PersonSQLContract.FitnessDatabase.ACTIVITY_COLUMN_DATE + " TEXT," +
//            PersonSQLContract.FitnessDatabase.ACTIVITY_COLUMN_STILL + " TEXT," +
//            PersonSQLContract.FitnessDatabase.ACTIVITY_COLUMN_STANDING + " TEXT," +
//            PersonSQLContract.FitnessDatabase.ACTIVITY_COLUMN_WALKING + " TEXT," +
//            PersonSQLContract.FitnessDatabase.ACTIVITY_COLUMN_RUNNING + " TEXT," +
//            PersonSQLContract.FitnessDatabase.ACTIVITY_COLUMN_UNKNOWN + " TEXT" +
//            " )";

    private static final String SQL_CREATE_ENTRIES_ACTIVITY_TABLE = "CREATE TABLE " + PersonSQLContract.FitnessDatabase.ACTIVITY_TABLE_NAME + "(" +
            PersonSQLContract.FitnessDatabase.ACTIVITY_COLUMN_ID + " INTEGER PRIMARY KEY, " +
            PersonSQLContract.FitnessDatabase.ACTIVITY_COLUMN_NAME + " TEXT," +
            PersonSQLContract.FitnessDatabase.ACTIVITY_COLUMN_DATE + " TEXT," +
            PersonSQLContract.FitnessDatabase.ACTIVITY_COLUMN_STILL + " TEXT," +
            PersonSQLContract.FitnessDatabase.ACTIVITY_COLUMN_WALKING + " TEXT," +
            " )";

//    private static final String SQL_CREATE_ENTRIES_ACTIVITY_TABLE = "CREATE TABLE " + PersonSQLContract.FitnessDatabase.ACTIVITY_TABLE_NAME + "(" +
//            "_id" + " INTEGER PRIMARY KEY, " +
//            " name " + " TEXT," +
//            " date " + " TEXT," +
//            " still " + " TEXT," +
//            " walking " + " TEXT," +
//            " )";

    public DAO(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_PERSON_TABLE);
        db.execSQL(SQL_CREATE_ENTRIES_ACTIVITY_TABLE);
        Log.e("dbOnCreate", "dbOnCreate");
        Log.e("SQLTest", SQL_CREATE_ENTRIES_ACTIVITY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PersonSQLContract.FitnessDatabase.PERSON_TABLE_NAME);
        onCreate(db);
        Log.e("dbOnUpgrade", "dbOnUpgrade");
    }

    //Person Methods

    public boolean insertPerson(Person p){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PersonSQLContract.FitnessDatabase.PERSON_COLUMN_PASSWORD, p.getPassword());
        contentValues.put(PersonSQLContract.FitnessDatabase.PERSON_COLUMN_NAME, p.getName());
        contentValues.put(PersonSQLContract.FitnessDatabase.PERSON_COLUMN_AGE, p.getAge());
        contentValues.put(PersonSQLContract.FitnessDatabase.PERSON_COLUMN_GENDER, p.getGender());
        db.insert(PersonSQLContract.FitnessDatabase.PERSON_TABLE_NAME, null, contentValues);
        return true;

    }

    public boolean updatePerson(Person p, Integer id){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PersonSQLContract.FitnessDatabase.PERSON_COLUMN_NAME, p.getName());
        contentValues.put(PersonSQLContract.FitnessDatabase.PERSON_COLUMN_AGE, p.getAge());
        contentValues.put(PersonSQLContract.FitnessDatabase.PERSON_COLUMN_AGE, p.getAge());
        contentValues.put(PersonSQLContract.FitnessDatabase.PERSON_COLUMN_GENDER, p.getGender());
        db.update(PersonSQLContract.FitnessDatabase.PERSON_TABLE_NAME, contentValues,
                PersonSQLContract.FitnessDatabase.PERSON_COLUMN_ID + " = ? ", new String[]{Integer.toString(id)});
        return true;
    }


    public Cursor getPerson(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+PersonSQLContract.FitnessDatabase.PERSON_TABLE_NAME+" WHERE "
                + PersonSQLContract.FitnessDatabase.PERSON_COLUMN_NAME + " =? ", new String[] { name });
        return res;
    }

    public Cursor getAllPersons() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + PersonSQLContract.FitnessDatabase.PERSON_TABLE_NAME,
                null );
        return res;
    }

    public Integer deletePerson(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(PersonSQLContract.FitnessDatabase.PERSON_TABLE_NAME,
                PersonSQLContract.FitnessDatabase.PERSON_COLUMN_ID + " = ?",
                new String[] {Integer.toString(id)});
    }

    //Activity Log Methods

    public boolean insertActivityLog(ActivityLog activityLog){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PersonSQLContract.FitnessDatabase.ACTIVITY_COLUMN_NAME, activityLog.getAccountName());
        contentValues.put(PersonSQLContract.FitnessDatabase.ACTIVITY_COLUMN_DATE, activityLog.getDate());
        contentValues.put(PersonSQLContract.FitnessDatabase.ACTIVITY_COLUMN_STILL, activityLog.getTimeStill());
        contentValues.put(PersonSQLContract.FitnessDatabase.ACTIVITY_COLUMN_WALKING, activityLog.getTimeWalking());
        db.insert(PersonSQLContract.FitnessDatabase.ACTIVITY_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getActivityLog(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " +PersonSQLContract.FitnessDatabase.ACTIVITY_TABLE_NAME + " WHERE "
        + PersonSQLContract.FitnessDatabase.ACTIVITY_COLUMN_NAME + " =? ", new String[] { name });
        return res;

    }

    //Utility Methods

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, PersonSQLContract.FitnessDatabase.PERSON_TABLE_NAME);
        return numRows;
    }

    public Boolean checkAccounts(){
        Boolean accountNum;
        int count;
        Cursor c = getAllPersons();
        count = c.getCount();
        if(count == 0){
            accountNum = false;
        }else{
            accountNum = true;
        }
        return accountNum;
    }

}
