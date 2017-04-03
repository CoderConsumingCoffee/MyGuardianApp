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

    private static final int ORGANIZATION_SWITCH = 1;
    private static final int GOVERNMENT_SWITCH = 2;
    private SQLiteDatabase db;
    private Context context = getContext();

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(PROVIDER_NAME, DBContract.Organization.TABLE_NAME, ORGANIZATION_SWITCH);
        uriMatcher.addURI(PROVIDER_NAME, DBContract.Government.GOVERNMENT_TABLE_NAME, GOVERNMENT_SWITCH);
//        uriMatcher.addURI(PROVIDER_NAME, "students/#", STUDENT_ID);
    }

    @Override
    public boolean onCreate() {

        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {

        return null;
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
        try(SQLiteDatabase db = new DBHelper(getContext()).getWritableDatabase()){

            switch (uriMatcher.match(uri)) {
                case ORGANIZATION_SWITCH:
                    rowID =  db.insert(DBContract.Organization.TABLE_NAME, null, contentValues);
                    break;

                case GOVERNMENT_SWITCH:
                    rowID = db.insert(DBContract.Government.GOVERNMENT_TABLE_NAME, "", contentValues);
                    break;


    //                default: throw new IllegalArgumentException("Unknown URI " + uri);

            }
            if (rowID > 0)
            {
                Uri _uri = ContentUris.withAppendedId(ORGANIZATION, rowID);
                getContext().getContentResolver().notifyChange(_uri, null);
                return _uri;
            }
            throw new SQLException("Failed to add a record into " + uri);
        }catch (SQLException sqle){
            Log.e("SQL ISSUE", sqle.getMessage());
        }

return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
