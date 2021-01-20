package com.example.safedrive.activities.driveralert;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.safedrive.R;

public class contactus extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root;
        root = inflater.inflate(R.layout.activity_contactus,container,false);
        return root;
    }
}
