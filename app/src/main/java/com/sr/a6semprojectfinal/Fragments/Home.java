package com.sr.a6semprojectfinal.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.sr.a6semprojectfinal.R;

public class Home extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b){

        view  = inflater.inflate(R.layout.home_fragment,container,false);




        return view;

    }

    public void handler(){

    }
}