package com.aphart.myguardian.fragmentClasses.initialSignIn;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.aphart.myguardian.R;
import com.aphart.myguardian.enums.GenderIdentityEnums;
import com.aphart.myguardian.enums.SignInDataEnum;
import com.aphart.myguardian.interfaces.DataAccessObject;
import com.aphart.myguardian.interfaces.UpdateUIOnDAOComplete;

import myguardianDB.DBContract;

/**
 * A placeholder fragment containing a simple view.
 */
public class InitialSignInActivityBeginFragment extends Fragment implements View.OnClickListener{




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private Cursor cursor = null;
    private static int genderId;
    private static Bundle userInfoBundle;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ContactInfoFragment.OnFragmentInteractionListener mListener;

    public InitialSignInActivityBeginFragment() {

        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ContactInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactInfoFragment newInstance() {
        ContactInfoFragment fragment = new ContactInfoFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        if(savedInstanceState != null){
            genderId = savedInstanceState.getInt(DBContract.UserInfo.GENDER);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_initial_sign_in, container, false);


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
        if (context instanceof ContactInfoFragment.OnFragmentInteractionListener) {
            mListener = (ContactInfoFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        if(genderId>0){
            Button btn = (Button) getActivity().findViewById(genderId);
            btn.setBackgroundColor(getResources().getColor(R.color.millinialPink));
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
        outState.putInt(DBContract.UserInfo.GENDER, genderId);
        userInfoBundle.putInt(DBContract.UserInfo.GENDER, genderId);
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


    /*
    *Override onClick listener to listen for a button, assign the genderId, and then launch new fragment.
    * genderId will be bundled onSavedInstance.
     */
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.isi_amMan:{
                genderId = view.getId();
                launchContactInfoFragment();
            }
                break;
            case R.id.isi_amWoman:{
                genderId = view.getId();
                launchContactInfoFragment();
            }
                break;
            case R.id.isi_amNonGendered:{
                genderId = view.getId();
                launchContactInfoFragment();
            }
                break;
        }
    }
    private void launchContactInfoFragment(){
        userInfoBundle.putInt(DBContract.UserInfo.GENDER, genderId);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.initialSignInActivityFragmentOne, ContactInfoFragment.newInstance(userInfoBundle)); //Pass info
        ft.addToBackStack("iniSignInActivityBeginFragment");
        ft.commit();
    }
}