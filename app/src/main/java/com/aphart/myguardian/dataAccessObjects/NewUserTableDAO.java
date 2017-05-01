package com.aphart.myguardian.dataAccessObjects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.aphart.myguardian.Exceptions.CallingClassNotCompatable;
import com.aphart.myguardian.enums.OperationEnum;
import com.aphart.myguardian.interfaces.DataAccessObject;
import com.aphart.myguardian.interfaces.UpdateUIOnDAOComplete;

import myguardianDB.DBContract;
import myguardianDB.GuardianContentProvider;

/**
 * Created by aphart on 4/20/2017.
 */

public class NewUserTableDAO implements DataAccessObject{
    private static final NewUserTableDAO nutDAO = new NewUserTableDAO();
    private UpdateUIOnDAOComplete updateUIOnDAOComplete = null;
    private static Context context = null;
    private static OperationEnum operationEnum = null;
    private static ContentValues contentValues = null;
    private static Uri uri = null;
    private static int rowsModified;
    private NewUserTableDAO(){

    }
    public static NewUserTableDAO getInstance(){
        return nutDAO;
    }

    private void clearFields(){
        context = null;
        operationEnum = null;
        contentValues = null;
        uri = null;
        rowsModified = 0;

    }
    public void queryIsNewUser(@NonNull Context context ) throws CallingClassNotCompatable {
        if (context instanceof UpdateUIOnDAOComplete) {
            this.updateUIOnDAOComplete = (UpdateUIOnDAOComplete) context;
            this.context = context.getApplicationContext();
            this.operationEnum = OperationEnum.READ;
            new CRUDOperator().execute(OperationEnum.READ);
        }else{
            throw new CallingClassNotCompatable();
        }
    }

    public void insertUserRegistered(@NonNull Context context) throws CallingClassNotCompatable {
        if (context instanceof UpdateUIOnDAOComplete) {
            this.updateUIOnDAOComplete = (UpdateUIOnDAOComplete) context;
            this.context = context.getApplicationContext();
            this.operationEnum = OperationEnum.INSERT;
            new CRUDOperator().execute(OperationEnum.INSERT);
        }else{
            throw new CallingClassNotCompatable();
        }
    }

    public void unRegisterUser(@NonNull Context context) throws CallingClassNotCompatable{
        if (context instanceof UpdateUIOnDAOComplete) {
            this.updateUIOnDAOComplete = (UpdateUIOnDAOComplete) context;
            this.context = context.getApplicationContext();
            this.operationEnum = OperationEnum.UPDATE;
            new CRUDOperator().execute(OperationEnum.UPDATE);
        }else{
            throw new CallingClassNotCompatable();
        }
    }


    private class CRUDOperator extends AsyncTask<OperationEnum, Integer, Cursor>{
        @Override
        protected Cursor doInBackground(OperationEnum... operationEnum) {
            Cursor cursor = null;
            switch (operationEnum[0]){
            case CREATE:{

            }
                break;
            case UPDATE:{
                String whereClause = DBContract.NewUser.IS_NEW_USER + " = ?";
                String[] whereArgs = {"false"};
                contentValues = new ContentValues();
                contentValues.put(DBContract.NewUser.IS_NEW_USER, false);
               rowsModified = new GuardianContentProvider(context).update(GuardianContentProvider.NEW_USER, contentValues, whereClause, whereArgs);
            }
                break;
            case INSERT:{

                uri = new GuardianContentProvider(context).insert(GuardianContentProvider.NEW_USER, contentValues);
            }
                break;
            case READ:{

              //
                 cursor = new GuardianContentProvider(context).query(GuardianContentProvider.NEW_USER, null, null, null, null, null);
            }
                break;
            case DELETE:
                break;
        }


            return cursor;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {

        }

        @Override
        protected void onPostExecute(Cursor result) {
            if(updateUIOnDAOComplete != null){
                switch (operationEnum){
                    case READ:
                    updateUIOnDAOComplete.performUIQueryAction(nutDAO, result);
                    break;
                    case INSERT:
                        break;
                    case UPDATE:
                        updateUIOnDAOComplete.performUIUpdateAction(nutDAO, rowsModified);
                        break;
                    case DELETE:
                        break;
                }
            }




            clearFields();
        }
    }

}
