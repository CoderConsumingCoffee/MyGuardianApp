package com.aphart.myguardian;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.aphart.myguardian.fragmentClasses.initialSignIn.ContactInfoFragment;
import com.aphart.myguardian.fragmentClasses.initialSignIn.InitialSignInActivityBeginFragment;
import com.aphart.myguardian.fragmentClasses.initialSignIn.PersonalInfoOneFragment;
import com.aphart.myguardian.fragmentClasses.initialSignIn.PersonalInfoTwoFragment;

public class InitialSignInActivity extends AppCompatActivity implements
        InitialSignInActivityBeginFragment.OnFragmentInteractionListener,
        ContactInfoFragment.OnFragmentInteractionListener,
        PersonalInfoOneFragment.OnFragmentInteractionListener,
        PersonalInfoTwoFragment.OnFragmentInteractionListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_sign_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//
//        ft.replace(R.id.initialSignInActivityFragmentOne, new InitialSignInActivityBeginFragment());
//        ft.commit();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
