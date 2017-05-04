package com.aphart.myguardian.fragmentClasses.initialSignIn;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.aphart.myguardian.R;
import com.aphart.myguardian.interfaces.DataAccessObject;
import com.aphart.myguardian.interfaces.UpdateUIOnDAOComplete;
import com.google.android.gms.internal.zzis;

import myguardianDB.DBContract;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactInfoFragment extends Fragment implements UpdateUIOnDAOComplete, View.OnClickListener, View.OnFocusChangeListener{
    private Cursor cursor = null;
    private static String userPhoneNumber;
    private boolean emailValidated;
    private boolean phoneValidated;
    private static Bundle userInfoBundle;
    private EditText phoneNumber;
    private EditText emailAddress;
    private Spinner preferedContact;
    private OnFragmentInteractionListener mListener;

    public ContactInfoFragment() {

        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ContactInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactInfoFragment newInstance(Bundle userInfoBundle) {
        ContactInfoFragment fragment = new ContactInfoFragment();
        fragment.userInfoBundle = userInfoBundle;
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TelephonyManager tm = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        userPhoneNumber = tm.getLine1Number();
        if(savedInstanceState != null){
            userInfoBundle = savedInstanceState.getBundle("INITIAL_SIGN_IN");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_info, container, false);
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onStart(){
    super.onStart();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        phoneNumber = (EditText) getActivity().findViewById(R.id.ci_phone);
        emailAddress = (EditText) getActivity().findViewById(R.id.ci_email);
        phoneNumber.setText(userPhoneNumber);
        if(userInfoBundle != null){
            if (userInfoBundle.getString(DBContract.UserInfo.EMAIL).isEmpty()){

            }else{
                emailAddress.setText(userInfoBundle.getString(DBContract.UserInfo.EMAIL));
            }
            if (userInfoBundle.getString(DBContract.UserInfo.PHONE_NUMBER).isEmpty()){

            }else{
                phoneNumber.setText(userInfoBundle.getString(DBContract.UserInfo.PHONE_NUMBER));
            }
            //Set previously selected job status
            String[] pcArray = getResources().getStringArray(R.array.prefered_contact_method);
            for (int i = 0; i < pcArray.length; i++) {
                if (pcArray[i].equalsIgnoreCase(userInfoBundle.getString(DBContract.UserInfo.PREFERRED_CONTACT_METHOD))){
                    preferedContact.setSelection(i);
                }
            }
        }

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setUserBundle();
        outState.putBundle("INITIAL_SIGN_IN", userInfoBundle);
    }

    @Override
    public void performUIQueryAction(DataAccessObject dao, Cursor cursor) {

    }

    @Override
    public void performUIInsertAction(DataAccessObject dao, Uri uri) {

    }

    @Override
    public void performUIUpdateAction(DataAccessObject dao, int rowsModified) {

    }

    @Override
    public void performUIDeleteAction(DataAccessObject dao, int rowsModified) {

    }

    @Override
    public void onFocusChange(View view, boolean b) {

        switch (view.getId()){

            case R.id.ci_phone:{
                if(!b){
                    Editable phone = phoneNumber.getText();
                    phoneValidated = validatePhone(phone);

                }
            }
            break;
            case R.id.ci_email:{
                if(!b){
                    Editable email= emailAddress.getText();
                    emailValidated = validateEmail(email);
                }
            }
            break;
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){

            case R.id.ci_prev:{
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("iniSignInActivityBeginFragment",fm.POP_BACK_STACK_INCLUSIVE);
            }
                break;
            case R.id.ci_next:{

                if(emailValidated && phoneValidated){
                    userInfoBundle.putString(DBContract.UserInfo.EMAIL, emailAddress.toString());
                    userInfoBundle.putString(DBContract.UserInfo.PHONE_NUMBER, phoneNumber.toString());
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(android.R.id.content, PersonalInfoOneFragment.newInstance(userInfoBundle)); //Pass info
                    ft.addToBackStack("contactInfoFragment");
                    ft.commit();
                }
            }
                break;
            case R.id.ci_phone:{

            }
                break;
            case R.id.ci_email:{

            }
                break;
        }
    }

    private boolean validateEmail(Editable emailAdd){
        boolean isValid = false;
        isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(emailAdd).matches();
        return isValid;
    }
    private boolean validatePhone(Editable phoneNum){
        boolean isValid = false;
        isValid = Patterns.PHONE .matcher(phoneNum).matches();
        return isValid;
    }
    private void setUserBundle(){
        userInfoBundle.putString(DBContract.UserInfo.PHONE_NUMBER,phoneNumber.getText().toString());
        userInfoBundle.putString(DBContract.UserInfo.EMAIL,emailAddress.getText().toString());
        userInfoBundle.putString(DBContract.UserInfo.PREFERRED_CONTACT_METHOD, preferedContact.getSelectedItem().toString());
    }
}
