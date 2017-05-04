package myguardianDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by aphart on 10/30/2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 3;
    public static final String DATABASE_NAME = "myguardianreasources";
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBContract.Organization.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBContract.Housing.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBContract.Food.FOOD_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBContract.JobTraining.JOB_TRAINING_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBContract.PhysicalHealth.PHYSICAL_HEALTH_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBContract.ResourceAbuseViolence.ABUSE_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBContract.MentalHealth.MENTAL_HEALTH_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBContract.UtilityRentEviction.UTILITY_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBContract.Government.GOVERNMENT_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBContract.UserInfo.USER_INFO_TABLE);

        sqLiteDatabase.execSQL(DBContract.CREATE_ORGANIZATION_TABLE);
        sqLiteDatabase.execSQL(DBContract.CREATE_HOUSING_TABLE);
        sqLiteDatabase.execSQL(DBContract.CREATE_FOOD_TABLE);
        sqLiteDatabase.execSQL(DBContract.CREATE_JOB_TRAINING_TABLE);
        sqLiteDatabase.execSQL(DBContract.CREATE_PHYSICAL_HEALTH_TABLE);
        sqLiteDatabase.execSQL(DBContract.CREATE_ABUSE_TABLE);
        sqLiteDatabase.execSQL(DBContract.CREATE_MENTAL_HEALTH_TABLE);
        sqLiteDatabase.execSQL(DBContract.CREATE_UTILITY_TABLE);
        sqLiteDatabase.execSQL(DBContract.CREATE_GOVERNMENT_TABLE);
        sqLiteDatabase.execSQL(DBContract.CREATE_USER_INFO_TABLE);


        Log.e("DB CREATION", "Database was created successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        onCreate(sqLiteDatabase);
    }

}
