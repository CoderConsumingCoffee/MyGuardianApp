package com.aphart.myguardian.utilities;

import android.database.Cursor;
import android.os.AsyncTask;

/**
 * Created by aphart on 4/19/2017.
 */

public abstract class SQLiteAsyncTaskDataLoader extends AsyncTask<Object , Integer, Cursor> {

    @Override
    protected Cursor doInBackground(Object... dao) {



        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {

    }

    @Override
    protected void onPostExecute(Cursor result) {

    }
}
