package com.aphart.myguardian.fragmentClasses.initialSignIn;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.aphart.myguardian.R;
import com.aphart.myguardian.interfaces.DataAccessObject;
import com.aphart.myguardian.interfaces.UpdateUIOnDAOComplete;

import myguardianDB.DBContract;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactInfoFragment extends Fragment implements UpdateUIOnDAOComplete, View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Cursor cursor = null;
    private static String userPhoneNumber;
    // TODO: Rename and change types of parameters

    private static Bundle userInfoBundle;

    private EditText phoneNumber;
    private EditText emailAddress;
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
            userInfoBundle.
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
        outState.
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

    @Override
    public void onClick(View view){
        switch (view.getId()){

            case R.id.ci_prev:{
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("iniSignInActivityBeginFragment",fm.POP_BACK_STACK_INCLUSIVE);
            }
                break;
            case R.id.ci_next:{

                Editable editable = phoneNumber.getText();
                editable.
                if(emailValidated && phoneValidated){
                    userInfoBundle.putString();
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
    private Button btn = (Button)getActivity().findViewById(R.id.ci_prev);

}
