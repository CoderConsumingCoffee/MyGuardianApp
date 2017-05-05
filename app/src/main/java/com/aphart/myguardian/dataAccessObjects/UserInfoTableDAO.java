package com.aphart.myguardian.dataAccessObjects;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.aphart.myguardian.Exceptions.CallingClassNotCompatable;
import com.aphart.myguardian.InitialSignInActivity;
import com.aphart.myguardian.enums.OperationEnum;
import com.aphart.myguardian.interfaces.DataAccessObject;
import com.aphart.myguardian.interfaces.UpdateUIOnDAOComplete;

import myguardianDB.DBContract;
import myguardianDB.GuardianContentProvider;

/**
 * Created by aphart on 4/20/2017.
 */

public class UserInfoTableDAO implements DataAccessObject{
    private static final UserInfoTableDAO nutDAO = new UserInfoTableDAO();
    private UpdateUIOnDAOComplete updateUIOnDAOComplete = null;
    private static Activity activity = null;
    private static OperationEnum operationEnum = null;
    private static Uri uri = null;
    private static int rowsModified;
    private static Bundle userBundle;
    private UserInfoTableDAO(){

    }
    public static UserInfoTableDAO getInstance(){
        return nutDAO;
    }

    private void clearFields(){
        activity = null;
        operationEnum = null;
        uri = null;
        rowsModified = 0;

    }
    public void queryIsNewUser(@NonNull Activity activity) throws CallingClassNotCompatable {
        if (activity instanceof UpdateUIOnDAOComplete) {
            this.updateUIOnDAOComplete = (UpdateUIOnDAOComplete) activity;
            this.activity = activity;
            this.operationEnum = OperationEnum.READ;
            new CRUDOperator().execute(OperationEnum.READ);
        }else{
            throw new CallingClassNotCompatable();
        }
    }

    public void insertUserRegistered(@NonNull Activity activity, Bundle userInfoBundle) throws CallingClassNotCompatable {
        if (activity instanceof UpdateUIOnDAOComplete) {
            this.updateUIOnDAOComplete = (UpdateUIOnDAOComplete) activity;
            this.activity = activity;
            this.operationEnum = OperationEnum.CREATE;
            this.userBundle = userInfoBundle;
            new CRUDOperator().execute(OperationEnum.CREATE);
        }else{
            throw new CallingClassNotCompatable();
        }
    }

    public void unRegisterUser(@NonNull Activity activity) throws CallingClassNotCompatable{
        if (activity instanceof UpdateUIOnDAOComplete) {
            this.updateUIOnDAOComplete = (UpdateUIOnDAOComplete) activity;
            this.activity = activity;
            this.operationEnum = OperationEnum.DELETE;
            new CRUDOperator().execute(OperationEnum.DELETE);
        }else{
            throw new CallingClassNotCompatable();
        }
    }

    public void updateUser(@NonNull Activity activity, Bundle userInfoBundle) throws  CallingClassNotCompatable{
        if (activity instanceof UpdateUIOnDAOComplete) {
            this.updateUIOnDAOComplete = (UpdateUIOnDAOComplete) activity;
            this.activity = activity;
            this.operationEnum = OperationEnum.UPDATE;
            this.userBundle = userInfoBundle;
            new CRUDOperator().execute(OperationEnum.UPDATE);
        }else{
            throw new CallingClassNotCompatable();
        }
    }


    private class CRUDOperator extends AsyncTask<OperationEnum, Integer, Cursor>{
        @Override
        protected Cursor doInBackground(OperationEnum... operationEnum) {
            Cursor cursor = null;
            switch (operationEnum[0]) {
                case DELETE: {
                    GuardianContentProvider gp = new GuardianContentProvider();
                    //Delete Old User information, executes raw sql so no other params save URI
                    //Deletes all rows. There is only one user per app, per device.
                    gp.delete(GuardianContentProvider.USER_INFO, null, null);

                }
                break;
                case CREATE: {

                    if(activity instanceof InitialSignInActivity) {
                        GuardianContentProvider gp = new GuardianContentProvider(activity);

                       //Should never be more than one row
                        uri = gp.insert(GuardianContentProvider.USER_INFO, DBContract.UserInfo.createNewUserInfo(
                                userBundle.getString(DBContract.UserInfo.GENDER),
                                userBundle.getString(DBContract.UserInfo.PHONE_NUMBER),
                                userBundle.getString(DBContract.UserInfo.EMAIL),
                                userBundle.getString(DBContract.UserInfo.PREFERRED_CONTACT_METHOD),
                                userBundle.getBoolean(DBContract.UserInfo.ADDICTION_STATUS),
                                userBundle.getInt(DBContract.UserInfo.NUMBER_OF_CHILDREN),
                                userBundle.getInt(DBContract.UserInfo.AGE), //Make sure this is calculated in pio
                                userBundle.getString(DBContract.UserInfo.BIRTH_DATE),
                                userBundle.getString(DBContract.UserInfo.HOUSING_STATUS),
                                userBundle.getString(DBContract.UserInfo.EMPLOYMENT_STATUS),
                                userBundle.getString(DBContract.UserInfo.EDUCATION_LEVEL),
                                userBundle.getBoolean(DBContract.UserInfo.MENTAL_HEALTH_ISSUES),
                                userBundle.getBoolean(DBContract.UserInfo.PHYSICAL_HEALTH_ISSUES),
                                userBundle.getBoolean(DBContract.UserInfo.PREGNANT),
                                false //user no longer new
                        ));


                    }
                }
                break;
                case READ: {


                    cursor = new GuardianContentProvider(activity).query(GuardianContentProvider.USER_INFO, null, null, null, null, null);
                }
                break;



                case UPDATE:{

                }
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
                    case CREATE:
                        updateUIOnDAOComplete.performUIInsertAction(nutDAO, uri);
                        break;
                    case UPDATE:
                        updateUIOnDAOComplete.performUIUpdateAction(nutDAO, rowsModified);
                        break;
                    case DELETE:
                        updateUIOnDAOComplete.performUIDeleteAction(nutDAO, rowsModified);
                        break;

                }
            }

//


            clearFields();
        }
    }

}
