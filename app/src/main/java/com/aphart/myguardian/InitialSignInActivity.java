package com.aphart.myguardian;

import android.app.Activity;
import android.content.Intent;
import android.content.UriMatcher;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aphart.myguardian.fragmentClasses.initialSignIn.ContactInfoFragment;
import com.aphart.myguardian.fragmentClasses.initialSignIn.InitialSignInActivityBeginFragment;
import com.aphart.myguardian.fragmentClasses.initialSignIn.PersonalInfoOneFragment;
import com.aphart.myguardian.fragmentClasses.initialSignIn.PersonalInfoTwoFragment;
import com.aphart.myguardian.interfaces.OnFragmentInteractionListener;

import myguardianDB.DBContract;

public class InitialSignInActivity extends AppCompatActivity implements
        OnFragmentInteractionListener {
        private String fragId;
        private static Bundle userInfoBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_sign_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            //   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.initialSignInActivityFragmentOne, InitialSignInActivityBeginFragment.newInstance(userInfoBundle, false), "iniSignInActivityBeginFragment");
            ft.commit();
        } else {

            fragId = savedInstanceState.getString("oldfrag");
            userInfoBundle = savedInstanceState.getBundle("userInfoBundle");
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Fragment fragment = fm.findFragmentByTag(fragId);
//            if (fragment != null) {
//
//                switch (fragId) {
//                    case "iniSignInActivityBeginFragment": {
//                        InitialSignInActivityBeginFragment frag = (InitialSignInActivityBeginFragment) fm.findFragmentByTag(fragId);
//                        ft.show(frag);
//                    }
//                    break;
//                    case "contactInfoFragment": {
//                        ContactInfoFragment frag = (ContactInfoFragment) fm.findFragmentByTag(fragId);
//                        ft.show(frag);
//                    }
//                    break;
//                    case "personalInfoOneFragment": {
//                        PersonalInfoOneFragment frag = (PersonalInfoOneFragment) fm.findFragmentByTag(fragId);
//                        ft.show(frag);
//                    }
//                    break;
//                    case "personalInfoTwoFragment": {
//                        PersonalInfoTwoFragment frag = (PersonalInfoTwoFragment) fm.findFragmentByTag(fragId);
//                        ft.show(frag);
//                    }
//                    break;
//
//
//                }
//                ft.commit();
//            } else {

                switch (fragId) {
                    case "iniSignInActivityBeginFragment": {

                        ft.replace(R.id.initialSignInActivityFragmentOne, InitialSignInActivityBeginFragment.newInstance(userInfoBundle, true), fragId);
                    }
                    break;
                    case "contactInfoFragment": {
                        ft.replace(R.id.initialSignInActivityFragmentOne, ContactInfoFragment.newInstance(userInfoBundle, true), fragId);
                    }
                    break;
                    case "personalInfoOneFragment": {

                        ft.replace(R.id.initialSignInActivityFragmentOne, PersonalInfoOneFragment.newInstance(userInfoBundle, true), fragId);
                    }
                    break;
                    case "personalInfoTwoFragment": {

                        ft.replace(R.id.initialSignInActivityFragmentOne, PersonalInfoTwoFragment.newInstance(userInfoBundle, true), fragId);
                    }
                    break;
                }
                ft.commit();
//            }
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
    }


    @Override
    public void replaceFragment(Fragment fragment, Bundle userBundle) {
        if (fragment instanceof InitialSignInActivityBeginFragment) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.initialSignInActivityFragmentOne, ContactInfoFragment.newInstance(userBundle, false), "contactInfoFragment"); //Pass info
            ft.addToBackStack("iniSignInActivityBeginFragment");
            ft.commit();

            Toast.makeText(this, "replacing", Toast.LENGTH_LONG).show();

        } else if (fragment instanceof ContactInfoFragment) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.initialSignInActivityFragmentOne, PersonalInfoOneFragment.newInstance(userBundle, false), "personalInfoOneFragment"); //Pass info
            ft.addToBackStack("contactInfoFragment");
            ft.commit();
        } else if (fragment instanceof PersonalInfoOneFragment) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.initialSignInActivityFragmentOne, PersonalInfoTwoFragment.newInstance(userBundle, false),"personalInfoTwoFragment"); //Pass info
            ft.addToBackStack("personalInfoOneFragment");
            ft.commit();

        } else if (fragment instanceof PersonalInfoTwoFragment) {
//Launch main signed in activity

            Intent intent = new Intent(this, UserHomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);//Clear back stack
            startActivity(intent);
            finish(); // call this to finish the current activity
        }
    }

    @Override
    public void primaryFragmentDeath(Fragment fragment, Bundle bundle) {
        fragId = fragment.getTag();
        userInfoBundle = bundle;

    }
    @Override
    public void onSaveInstanceState(Bundle onBundle){
        onBundle.putString("oldfrag", fragId);
        onBundle.putBundle("userInfoBundle", userInfoBundle);
    }
}
