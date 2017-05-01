package com.aphart.myguardian.interfaces;

import android.database.Cursor;
import android.net.Uri;

/**
 * Created by aphart on 4/20/2017.
 */

public interface UpdateUIOnDAOComplete {
    void performUIQueryAction(DataAccessObject dao, Cursor cursor);

    void performUIInsertAction(DataAccessObject dao, Uri uri);

    void performUIUpdateAction(DataAccessObject dao, int rowsModified);

    void performUIDeleteAction(DataAccessObject dao, int rowsModified);

}
