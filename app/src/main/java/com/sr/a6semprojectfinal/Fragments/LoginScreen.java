package com.sr.a6semprojectfinal.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sr.a6semprojectfinal.APIRequests.UserLogin;
import com.sr.a6semprojectfinal.DataHolders.SessionReference;
import com.sr.a6semprojectfinal.R;

public class LoginScreen extends Fragment {
    Button button;
    EditText usernameInput;
    EditText passwordInput;
    ProgressBar progressBar;
    TextView clickable;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b){
        View view = inflater.inflate(R.layout.login_panel,container,false);
        button = view.findViewById(R.id.login);
        usernameInput = view.findViewById(R.id.login_username_input);
        passwordInput = view.findViewById(R.id.login_password_input);
        progressBar = view.findViewById(R.id.loadingIndicator);
        clickable = view.findViewById(R.id.register_login_link);

        clickable.setOnClickListener((v)->{
            RegisterScreen registerScreen = new RegisterScreen();
            FragmentManager manager = getParentFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_container,registerScreen);
            transaction.commit();
            transaction.addToBackStack(null);

        });


        handler();
        return view;

    }

    protected void handler(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();
                usernameInput.setText("");
                passwordInput.setText("");
                UserLogin.Login(username, password, getContext(), new UserLogin.onLoggedIn() {
                    @Override
                    public void onLogin() {
                        progressBar.setVisibility(View.INVISIBLE);
                        UserInfo fragment = new UserInfo();

                        FragmentManager manager = getParentFragmentManager();
                        manager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);

                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.fragment_container,fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();

                    }

                    @Override
                    public void onCredentialError(){
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError() {
                        progressBar.setVisibility(View.INVISIBLE);

                    }
                });
            }
        });
    }

}
