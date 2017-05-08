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
import android.widget.Toast;

import com.aphart.myguardian.R;
import com.aphart.myguardian.interfaces.DataAccessObject;
import com.aphart.myguardian.interfaces.OnFragmentInteractionListener;
import com.aphart.myguardian.interfaces.UpdateUIOnDAOComplete;
import com.google.android.gms.internal.zzis;

import myguardianDB.DBContract;


public class ContactInfoFragment extends Fragment{
    private Cursor cursor = null;
    private static String userPhoneNumber;
    private static Bundle userInfoBundle;
    private EditText phoneNumber;
    private EditText emailAddress;
    private Spinner preferedContact;
    private OnFragmentInteractionListener mListener;
    private static boolean isReloaded;

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
    public static ContactInfoFragment newInstance(Bundle userInfoBundle, boolean reloaded) {
        ContactInfoFragment fragment = new ContactInfoFragment();
        fragment.userInfoBundle = userInfoBundle;
        isReloaded = reloaded;
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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





    @Override
    public void onStart(){
    super.onStart();
        //TODO set up permissions for the app
       // TelephonyManager tm = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        //userPhoneNumber = tm.getLine1Number();

        View.OnClickListener onClickListener= new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switch (view.getId()){

                    case R.id.ci_prev:{
                        android.app.FragmentManager fm = getActivity().getFragmentManager();
                        fm.popBackStack("iniSignInActivityBeginFragment",fm.POP_BACK_STACK_INCLUSIVE);
                    }
                    break;
                    case R.id.ci_next:{

                        if(validateEmail(emailAddress.getText()) && validatePhone(phoneNumber.getText())){
                            setUserBundle();
                           proceed();

                        }
                        else {
                            Toast.makeText(getActivity(),"Contact Information is not valid",Toast.LENGTH_LONG).show();
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
        };

        getView().findViewById(R.id.ci_prev).setOnClickListener(onClickListener);
        getView().findViewById(R.id.ci_next).setOnClickListener(onClickListener);
        getView().findViewById(R.id.ci_phone).setOnClickListener(onClickListener);
        getView().findViewById(R.id.ci_email).setOnClickListener(onClickListener);



        phoneNumber = (EditText) getActivity().findViewById(R.id.ci_phone);
        emailAddress = (EditText) getActivity().findViewById(R.id.ci_email);
        preferedContact = (Spinner) getActivity().findViewById(R.id.ci_prefered_contact_spinner);
        phoneNumber.setText(userPhoneNumber);

            if (userInfoBundle.getString(DBContract.UserInfo.EMAIL) != null) {

                emailAddress.setText(userInfoBundle.getString(DBContract.UserInfo.EMAIL));
            }
            if (userInfoBundle.getString(DBContract.UserInfo.PHONE_NUMBER) != null) {

                phoneNumber.setText(userInfoBundle.getString(DBContract.UserInfo.PHONE_NUMBER));
            }
            if (userInfoBundle.getString(DBContract.UserInfo.PREFERRED_CONTACT_METHOD) != null) {
                //Set previously selected job status
                String[] pcArray = getResources().getStringArray(R.array.ci_preferred_contact_method);
                for (int i = 0; i < pcArray.length; i++) {
                    if (pcArray[i].equalsIgnoreCase(userInfoBundle.getString(DBContract.UserInfo.PREFERRED_CONTACT_METHOD))) {
                        preferedContact.setSelection(i);
                    }
                }
            }

    }
    @Override
    public void onPause(){
        super.onPause();
        setUserBundle();
        mListener.primaryFragmentDeath(this, userInfoBundle);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


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
        mListener.primaryFragmentDeath(this, userInfoBundle);

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
        String[] contactArray = getResources().getStringArray(R.array.ci_preferred_contact_method);
        userInfoBundle.putString(DBContract.UserInfo.PREFERRED_CONTACT_METHOD, contactArray[preferedContact.getSelectedItemPosition()]);
    }
    private void proceed(){
        mListener.replaceFragment(this, userInfoBundle);
    }
}
