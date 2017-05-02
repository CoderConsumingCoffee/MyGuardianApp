package com.aphart.myguardian.fragmentClasses.initialSignIn;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.aphart.myguardian.R;
import com.aphart.myguardian.interfaces.DataAccessObject;
import com.aphart.myguardian.interfaces.UpdateUIOnDAOComplete;

import myguardianDB.DBContract;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PersonalInfoTwoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PersonalInfoTwoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalInfoTwoFragment extends Fragment implements UpdateUIOnDAOComplete{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static Bundle userInfoBundle;
    // TODO: Rename and change types of parameters

    private OnFragmentInteractionListener mListener;
    private CheckBox pitMentalHealth;
    private CheckBox pitPhysicalHealth;
    private CheckBox pitPregnant;
    private CheckBox pitAddiction;


    public PersonalInfoTwoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param userInfoBundle Parameter 1.
     * @return A new instance of fragment PersonalInfoTwoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalInfoTwoFragment newInstance(Bundle userInfoBundle) {
        PersonalInfoTwoFragment fragment = new PersonalInfoTwoFragment();
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
        return inflater.inflate(R.layout.fragment_personal_info_two, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        Activity activity = getActivity();
        pitPregnant = (CheckBox)activity.findViewById(R.id.pit_pregnantCheckbox);
        pitAddiction = (CheckBox)activity.findViewById(R.id.pit_addictionCheckbox);
        pitMentalHealth = (CheckBox)activity.findViewById(R.id.pit_mentalHealthCheckbox);
        pitPhysicalHealth = (CheckBox)activity.findViewById(R.id.pit_physicalHealthCheckbox);

        pitPregnant.setChecked(userInfoBundle.getBoolean(DBContract.UserInfo.PREGNANT));
        pitPhysicalHealth.setChecked(userInfoBundle.getBoolean(DBContract.UserInfo.PHYSICAL_HEALTH_ISSUES));
        pitMentalHealth.setChecked(userInfoBundle.getBoolean(DBContract.UserInfo.MENTAL_HEALTH_ISSUES));
        pitAddiction.setChecked(userInfoBundle.getBoolean(DBContract.UserInfo.ADDICTION_STATUS));
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
