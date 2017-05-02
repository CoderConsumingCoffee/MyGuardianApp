package com.aphart.myguardian.fragmentClasses.initialSignIn;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.aphart.myguardian.R;

import myguardianDB.DBContract;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PersonalInfoOneFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PersonalInfoOneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalInfoOneFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static Bundle userInfoBundle;
    private RadioGroup pioRadioGroup;
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


        if (getArguments() != null) {

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
        Activity parentActivity = getActivity();
        pioRadioGroup = (RadioGroup) parentActivity.findViewById(R.id.pio_radioGroup);
        pioDateOfBirth = (EditText) parentActivity.findViewById(R.id.pio_birthDate);
        pioJobStatus = (Spinner) parentActivity.findViewById(R.id.pio_employmentStatusSpinner);
        pioeducationLevel = (Spinner) parentActivity.findViewById(R.id.pio_educationLevelSpinner);
        pioNumberOfChildren = (Spinner) parentActivity.findViewById(R.id.pio_numberOfChildrenSpinner);

        pioRadioGroup.check();
        pioDateOfBirth.setText(userInfoBundle.getString(DBContract.UserInfo.BIRTH_DATE));

        String[] jobArray = getResources().getStringArray(R.array.job_status);
        for (int i = 0; i < jobArray.length; i++) {
            if (jobArray[i].equalsIgnoreCase(userInfoBundle.getString(DBContract.UserInfo.JOB_STATUS))){
                pioJobStatus.setSelection(i);
            }
        }

        String[] educationArray = getResources().getStringArray(R.array.education_status);
        for (int i = 0; i < educationArray.length; i++) {
            if (educationArray[i].equalsIgnoreCase(userInfoBundle.getString(DBContract.UserInfo.EDUCATION_LEVEL))){
                pioeducationLevel.setSelection(i);
            }
        }

        String[] childArray = getResources().getStringArray(R.array.number_of_children);
        for (int i = 0; i < childArray.length; i++) {
            if (childArray[i].equalsIgnoreCase(userInfoBundle.getString(DBContract.UserInfo.NUMBER_OF_CHILDREN))){
                pioNumberOfChildren.setSelection(i);
            }
        }


    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
}
