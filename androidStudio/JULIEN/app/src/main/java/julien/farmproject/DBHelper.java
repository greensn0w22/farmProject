package julien.farmproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "farmProject.db";
    static final int DB_VERSION = 1;

    public static final String FIELD_TABLE_NAME = "field";
    public static final String FIELD_COLUMN_NAME_FIELD_PK = "field_pk";
    public static final String FIELD_COLUMN_NAME_FIELD_NAME = "field_name";
    public static final String FIELD_COLUMN_NAME_FIELD_NUMBER = "field_number";

    public static final String TONNAGE_TABLE_NAME = "tonnage";
    public static final String TONNAGE_COLUMN_NAME_TONNAGE_PK = "tonnage_pk";
    public static final String TONNAGE_COLUMN_NAME_DATE_ENTRY = "date_entry";
    public static final String TONNAGE_COLUMN_NAME_NUMBER_OF_POUNDS = "number_of_pounds";
    public static final String TONNAGE_COLUMN_NAME_IS_VALID = "is_valid";
    public static final String TONNAGE_COLUMN_NAME_USAGER_FK = "usager_fk";
    public static final String TONNAGE_COLUMN_NAME_FIELD_FK = "field_fk";

    public static final String USAGER_TABLE_NAME = "usager";
    public static final String USAGER_COLUMN_NAME_USAGER_PK = " usager_pk";
    public static final String USAGER_COLUMN_NAME_FIRST_NAME = " first_name";
    public static final String USAGER_COLUMN_NAME_LAST_NAME = " last_name";
    public static final String USAGER_COLUMN_NAME_API_KEY = " api_key";

    public static final String CREATE_TABLE_USAGER = "CREATE TABLE usager (usager_pk integer PRIMARY KEY AUTOINCREMENT," +
            "first_name varchar(45) NOT NULL,last_name varchar(45) NOT NULL,api_key varchar(100) NOT NULL);";
    public static final String CREATE_TABLE_TONNAGE = "CREATE TABLE tonnage (tonnage_pk integer PRIMARY KEY AUTOINCREMENT," +
            "date_entry datetime NOT NULL,number_of_pounds integer NOT NULL,is_valid bool NOT NULL,usager_fk integer NOT NULL,field_fk integer NOT NULL);";
    public static final String CREATE_TABLE_FIELD = "CREATE TABLE field (field_pk integer NOT NULL,field_name varchar(45) NOT NULL,field_number integer NOT NULL,PRIMARY KEY (field_pk));";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FIELD);
        db.execSQL(CREATE_TABLE_TONNAGE);
        db.execSQL(CREATE_TABLE_USAGER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TONNAGE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FIELD_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USAGER_TABLE_NAME);
        onCreate(db);
    }
}