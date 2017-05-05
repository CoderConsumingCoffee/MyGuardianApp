package com.aphart.myguardian.interfaces;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by andrew on 5/4/2017.
 */

public interface OnFragmentInteractionListener {
    void replaceFragment(Fragment fragment, Bundle userBundle);
    void primaryFragmentDeath(Fragment fragment);
}
