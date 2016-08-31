package uk.ac.mmu.webmd.bottomup_version_10;

import android.provider.BaseColumns;

/**
 * Created by Tom on 22/06/2016.
 */
public final class PersonSQLContract {

    public PersonSQLContract(){}

    public static abstract class FitnessDatabase implements BaseColumns {
        public static final String PERSON_TABLE_NAME = "person";
        public static final String PERSON_COLUMN_PASSWORD = "password";
        public static final String PERSON_COLUMN_ID = "_id";
        public static final String PERSON_COLUMN_AGE = "age";
        public static final String PERSON_COLUMN_NAME = "name";
        public static final String PERSON_COLUMN_GENDER = "gender";
        public static final String ACTIVITY_TABLE_NAME = "activity";
        public static final String ACTIVITY_COLUMN_ID = "_id";
        public static final String ACTIVITY_COLUMN_NAME = "name";
        public static final String ACTIVITY_COLUMN_DATE = "date";
        public static final String ACTIVITY_COLUMN_STILL = "still";
        public static final String ACTIVITY_COLUMN_STANDING = "standing";
        public static final String ACTIVITY_COLUMN_WALKING = "walking";
        public static final String ACTIVITY_COLUMN_RUNNING = "running";
        public static final String ACTIVITY_COLUMN_UNKNOWN = "unknown";

    }
}
