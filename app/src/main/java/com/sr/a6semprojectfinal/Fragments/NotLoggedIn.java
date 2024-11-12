package com.sr.a6semprojectfinal.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sr.a6semprojectfinal.R;

public class NotLoggedIn extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b){
        View view = inflater.inflate(R.layout.not_logged_in,container,false);
        Button button = view.findViewById(R.id.logIN);
        Button regBtn = view.findViewById(R.id.register);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginScreen screen = new LoginScreen();

                FragmentManager manager = getParentFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container,screen);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterScreen screen = new RegisterScreen();

                FragmentManager manager = getParentFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container,screen);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;

    }

}
