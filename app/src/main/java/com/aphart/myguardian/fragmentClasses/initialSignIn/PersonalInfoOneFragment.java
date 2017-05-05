package com.aphart.myguardian.fragmentClasses.initialSignIn;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.aphart.myguardian.R;
import com.aphart.myguardian.interfaces.OnFragmentInteractionListener;

import java.util.Calendar;

import myguardianDB.DBContract;


public class PersonalInfoOneFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static Bundle userInfoBundle;
    private Spinner pioHousingStatus;
    private EditText pioDateOfBirth;
    private Spinner pioNumberOfChildren;
    private Spinner pioJobStatus;
    private Spinner pioeducationLevel;

    private OnFragmentInteractionListener mListener;

    public PersonalInfoOneFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param userInfoBundle Parameter 1.
     * @return A new instance of fragment PersonalInfoOneFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalInfoOneFragment newInstance(Bundle userInfoBundle) {
        PersonalInfoOneFragment fragment = new PersonalInfoOneFragment();
        fragment.userInfoBundle = userInfoBundle;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null && userInfoBundle == null) {
            userInfoBundle = savedInstanceState.getBundle("INITIAL_SIGN_IN");


        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_personal_info_one, container, false);

    }
    @Override
    public void onStart(){
        super.onStart();
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    //Next Button actions
                    case R.id.pio_next:{

                        if(pioJobStatus.getSelectedItemPosition() > 0 &&
                                pioeducationLevel.getSelectedItemPosition() > 0 &&
                                pioHousingStatus.getSelectedItemPosition() > 0 &&
                                //Not equal
                                !pioDateOfBirth.getText().toString().equalsIgnoreCase("")){
                            setUserBundle();
                            proceed();
                        }
                        //Create string builder for use in AlertDialog message
                        StringBuilder sb = new StringBuilder("Please make a selection for the following: \n");
                        //Highlight areas requiring selection
                        if (pioHousingStatus.getSelectedItemPosition() == 0){
                            TextView tv = (TextView)getActivity().findViewById(R.id.pio_housingStatusHeader);
                            tv.setTextColor(getResources().getColor(R.color.red));
                            sb.append(" Your current Housing Status.\n");
                        }
                        if (pioeducationLevel.getSelectedItemPosition() == 0){
                            TextView tv = (TextView)getActivity().findViewById(R.id.pio_educationLevelHeader);
                            tv.setTextColor(getResources().getColor(R.color.red));
                            sb.append(" Your current Education Level.\n");
                        }
                        if(pioJobStatus.getSelectedItemPosition() == 0){
                            TextView tv = (TextView)getActivity().findViewById(R.id.pio_employmentStatusHeader);
                            tv.setTextColor(getResources().getColor(R.color.red));
                            sb.append(" Your current Job Status.\n");

                        }
                        if(pioDateOfBirth.getText().toString().equalsIgnoreCase("")){
                            TextView tv = (TextView)getActivity().findViewById(R.id.dateOfBirthHeader);
                            tv.setTextColor(getResources().getColor(R.color.red));
                            sb.append(" Your birth date. We want to send you something nice!\n");

                        }
                        //Create alert dialog
                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                        dialogBuilder.setMessage(sb.toString());
                        //Create button and action
                        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        //Show dialog
                        dialogBuilder.create().show();
                    }
                    break;
                    //Previous button actions
                    case R.id.pio_previous: {
                        //Save all user fields
                        setUserBundle();
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        //Clear backstack to named fragment
                        fm.popBackStack("contactInfoFragment",fm.POP_BACK_STACK_INCLUSIVE);
                    }break;
                    case R.id.pio_birthDate:{
                        new DatePickerDialog(getActivity(),new DatePickerDialog.OnDateSetListener(){
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year,month,dayOfMonth);
                                pioDateOfBirth.setText(DateFormat.format("MM.dd.yyyy", calendar).toString());
                            }
                        },1996,0,1).show();
                    }break;
                }
            }
        };
        getView().findViewById(R.id.pio_next).setOnClickListener(onClickListener);
        getView().findViewById(R.id.pio_previous).setOnClickListener(onClickListener);
        getView().findViewById(R.id.pio_birthDate).setOnClickListener(onClickListener);
        Activity parentActivity = getActivity();
        pioHousingStatus = (Spinner) parentActivity.findViewById(R.id.pio_housingStatus);
        pioDateOfBirth = (EditText) parentActivity.findViewById(R.id.pio_birthDate);
        pioJobStatus = (Spinner) parentActivity.findViewById(R.id.pio_employmentStatusSpinner);
        pioeducationLevel = (Spinner) parentActivity.findViewById(R.id.pio_educationLevelSpinner);
        pioNumberOfChildren = (Spinner) parentActivity.findViewById(R.id.pio_numberOfChildrenSpinner);

        //Set Birthday
        if(userInfoBundle.getString(DBContract.UserInfo.BIRTH_DATE)!=null) {
            pioDateOfBirth.setText(userInfoBundle.getString(DBContract.UserInfo.BIRTH_DATE));
        }

        //Set previously selected housing status
        if (userInfoBundle.getString(DBContract.UserInfo.HOUSING_STATUS)!=null){
        String[] housingStatusArray = getResources().getStringArray(R.array.housing_status);
        for (int i = 0; i < housingStatusArray.length; i++) {
            if (housingStatusArray[i].equalsIgnoreCase(userInfoBundle.getString(DBContract.UserInfo.HOUSING_STATUS))){
                pioHousingStatus.setSelection(i);
            }
        }}

        //Set previously selected job status
        if (userInfoBundle.getString(DBContract.UserInfo.JOB_STATUS)!=null){
        String[] jobArray = getResources().getStringArray(R.array.job_status);
        for (int i = 0; i < jobArray.length; i++) {
            if (jobArray[i].equalsIgnoreCase(userInfoBundle.getString(DBContract.UserInfo.JOB_STATUS))){
                pioJobStatus.setSelection(i);
            }
        }}
        //Set previously selected education level
        if (userInfoBundle.getString(DBContract.UserInfo.EDUCATION_LEVEL)!=null){
        String[] educationArray = getResources().getStringArray(R.array.education_status);
        for (int i = 0; i < educationArray.length; i++) {
            if (educationArray[i].equalsIgnoreCase(userInfoBundle.getString(DBContract.UserInfo.EDUCATION_LEVEL))){
                pioeducationLevel.setSelection(i);
            }
        }}
        //Set previously selected number of children
        if (userInfoBundle.getInt(DBContract.UserInfo.NUMBER_OF_CHILDREN)>=0){
        String[] childArray = getResources().getStringArray(R.array.number_of_children);
        for (int i = 0; i < childArray.length; i++) {
            if (childArray[i].equalsIgnoreCase(userInfoBundle.getString(DBContract.UserInfo.NUMBER_OF_CHILDREN))){
                pioNumberOfChildren.setSelection(i);
            }
        }
        }
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
        //Save state of all user fields
        setUserBundle();
        //Pass user bundle to saved state
        outState.putBundle("INITIAL_SIGN_IN", userInfoBundle);
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

    private void setUserBundle() {
        if (this.isVisible()) {
            userInfoBundle.putString(DBContract.UserInfo.BIRTH_DATE, pioDateOfBirth.getText().toString());
            userInfoBundle.putString(DBContract.UserInfo.HOUSING_STATUS, pioHousingStatus.getSelectedItem().toString());
            userInfoBundle.putString(DBContract.UserInfo.JOB_STATUS, pioJobStatus.getSelectedItem().toString());
            userInfoBundle.putString(DBContract.UserInfo.EDUCATION_LEVEL, pioeducationLevel.getSelectedItem().toString());
            userInfoBundle.putInt(DBContract.UserInfo.NUMBER_OF_CHILDREN, Integer.parseInt(pioNumberOfChildren.getSelectedItem().toString()));
        }
    }
    private void proceed(){
        mListener.replaceFragment(this, userInfoBundle);
    }
}
