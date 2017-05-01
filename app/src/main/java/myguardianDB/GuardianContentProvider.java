package myguardianDB;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by aphart on 10/29/2016.
 */

public class GuardianContentProvider extends ContentProvider {

    private static final String PROVIDER_NAME = "myguardianDB.GuardianContentProvider";
    private static final String CONTENT = "content://";
    public static final Uri ORGANIZATION = Uri.parse(CONTENT + PROVIDER_NAME + "/" + DBContract.Organization.TABLE_NAME);
    public static final Uri GOVERNMENT = Uri.parse(CONTENT + PROVIDER_NAME + "/" + DBContract.Government.GOVERNMENT_TABLE_NAME);
    public static final Uri NEW_USER = Uri.parse(CONTENT + PROVIDER_NAME + "/" + DBContract.NewUser.NEW_USER_TABLE);

    private static final int ORGANIZATION_SWITCH = 1;
    private static final int GOVERNMENT_SWITCH = 2;
    private static final int NEW_USER_SWITCH = 3;
    private static SQLiteDatabase db; //Sharing across instances allows us to close DB from the static method on the class
    public Context context; //passed during instantiation of the

    static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(PROVIDER_NAME, DBContract.Organization.TABLE_NAME, ORGANIZATION_SWITCH);
        uriMatcher.addURI(PROVIDER_NAME, DBContract.Government.GOVERNMENT_TABLE_NAME, GOVERNMENT_SWITCH);
        uriMatcher.addURI(PROVIDER_NAME, DBContract.NewUser.NEW_USER_TABLE, GOVERNMENT_SWITCH);
//        uriMatcher.addURI(PROVIDER_NAME, "students/#", STUDENT_ID);
    }

    public GuardianContentProvider() {
        super();

    }

    public GuardianContentProvider(Context context) {
        super();
        this.context = context;
        try {
            if (db == null || db.isOpen() == false) {
                db = new DBHelper(context).getWritableDatabase();
                Log.i("NEWDBCONNECTION", "New db connection"); //Delete for production
            }

        } catch (SQLException sqle) {
            Log.e("SQL_EXCEPTION", "Exception in the constructor of GuardianContentProvider");
        }
    }

    public static void closeDBConnection() {
        db.close();

    }

    @Override
    public boolean onCreate() {

        return true;
    }

    @Nullable

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Cursor cursor = null;
        String groupBy = null;
        String having = null;
        switch (uriMatcher.match(uri)) {
            case NEW_USER_SWITCH: {
                cursor = db.query(DBContract.NewUser.NEW_USER_TABLE, projection, selection, selectionArgs, groupBy, having, sortOrder);
            }
            case GOVERNMENT_SWITCH: {
                cursor = db.query(DBContract.Government.GOVERNMENT_TABLE_NAME, projection, selection, selectionArgs, groupBy, having, sortOrder);
            }
            case ORGANIZATION_SWITCH: {
                cursor = db.query(DBContract.Organization.TABLE_NAME, projection, selection, selectionArgs, groupBy, having, sortOrder);
            }
            break;

        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        long rowID = 0;


        switch (uriMatcher.match(uri)) {
            case ORGANIZATION_SWITCH:
                rowID = db.insert(DBContract.Organization.TABLE_NAME, null, contentValues);
                break;

            case GOVERNMENT_SWITCH:
                rowID = db.insert(DBContract.Government.GOVERNMENT_TABLE_NAME, "", contentValues);
                break;

            case NEW_USER_SWITCH:
                rowID = db.insert(DBContract.NewUser.NEW_USER_TABLE, "", contentValues);
                break;

            //                default: throw new IllegalArgumentException("Unknown URI " + uri);

        }
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(ORGANIZATION, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }


        return null;
    }

    @Override
    public int delete(Uri uri, String whereClause, String[] whereArgs) {

        int rowsUpdated = 0;

        switch (uriMatcher.match(uri)) {
            case ORGANIZATION_SWITCH:
                db.delete(DBContract.Organization.TABLE_NAME, whereClause, whereArgs);

                break;

            case GOVERNMENT_SWITCH:
                db.delete(DBContract.Government.GOVERNMENT_TABLE_NAME, whereClause, whereArgs);
                break;

            case NEW_USER_SWITCH:
                db.delete(DBContract.NewUser.NEW_USER_TABLE, whereClause, whereArgs);
                break;


        }


        return rowsUpdated;
    }


    @Override
    public int update(Uri uri, ContentValues contentValues, String whereClause, String[] whereArgs) {
        int rowsUpdated = 0;


        switch (uriMatcher.match(uri)) {
            case ORGANIZATION_SWITCH:
                db.update(DBContract.Organization.TABLE_NAME, contentValues, whereClause, whereArgs);
                break;

            case GOVERNMENT_SWITCH:
                db.update(DBContract.Government.GOVERNMENT_TABLE_NAME, contentValues, whereClause, whereArgs);
                break;

            case NEW_USER_SWITCH:
                db.update(DBContract.NewUser.NEW_USER_TABLE, contentValues, whereClause, whereArgs);
                break;


        }


        return rowsUpdated;
    }
}
