package julien.farmproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBManager {

    private DBHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insertTonnage(int userID, int nbPounds, String date_entry, int fieldID) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.TONNAGE_COLUMN_NAME_USAGER_FK, userID);
        contentValue.put(DBHelper.TONNAGE_COLUMN_NAME_DATE_ENTRY, date_entry);
        contentValue.put(DBHelper.TONNAGE_COLUMN_NAME_FIELD_FK, fieldID);
        contentValue.put(DBHelper.TONNAGE_COLUMN_NAME_NUMBER_OF_POUNDS, nbPounds);
        contentValue.put(DBHelper.TONNAGE_COLUMN_NAME_IS_VALID, false);
        database.insert(DBHelper.TONNAGE_TABLE_NAME, null, contentValue);
    }

    public String countTonnagesNotSended() {
        Cursor cursor = database.query(DBHelper.TONNAGE_TABLE_NAME, new String[]{"count(*)"}, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor.getInt(0) + "";
    }



}
//
//    public Cursor fetch() {
//        String[] columns = new String[] { DBHelper._ID, DBHelper.SUBJECT, DBHelper.DESC };
//        Cursor cursor = database.query(DBHelper.TABLE_NAME, columns, null, null, null, null, null);
//        if (cursor != null) {
//            cursor.moveToFirst();
//        }
//        return cursor;
//    }
//
//    public int update(long _id, String name, String desc) {
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(DBHelper.SUBJECT, name);
//        contentValues.put(DBHelper.DESC, desc);
//        int i = database.update(DBHelper.TABLE_NAME, contentValues, DBHelper._ID + " = " + _id, null);
//        return i;
//    }
//
//    public void delete(long _id) {
//        database.delete(DBHelper.TABLE_NAME, DBHelper._ID + "=" + _id, null);
//    }

