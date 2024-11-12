package com.sr.a6semprojectfinal.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.sr.a6semprojectfinal.APIRequests.RegisterUser;
import com.sr.a6semprojectfinal.R;

public class RegisterScreen extends Fragment {
    View view;
    ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b){
        view = inflater.inflate(R.layout.register_screen,container,false);
        EditText email = view.findViewById(R.id.register_email_input);
        EditText username = view.findViewById(R.id.register_username_input);
        EditText password = view.findViewById(R.id.register_password_input);
        Button button = view.findViewById(R.id.register_btn);
        TextView textView = view.findViewById(R.id.register_result);
        progressBar = view.findViewById(R.id.register_loadingIndicator);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);

                if (!username.getText().toString().isEmpty() && !email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                    Log.d("Register","registration started");

                    String emailString = email.getText().toString();

                    String usernameString = username.getText().toString();
                    String passwordInput = password.getText().toString();

                    email.setText("");
                    username.setText("");
                    password.setText("");

                    RegisterUser.register(emailString, usernameString, passwordInput, getContext(), new RegisterUser.RegisterCompletionListener() {
                        @Override
                        public void onCompletion() {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getContext(),"Register successful",Toast.LENGTH_SHORT).show();

                            textView.setText("Registered");
                        }

                        @Override
                        public void onRegistrationError() {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getContext(),"Register unSuccessful",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError() {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getContext(),"Server Error",Toast.LENGTH_SHORT).show();
                        }
                    });

                    Log.d("Register","registration initiated");
                }
                else {
                    textView.setText("Please Fill All Fields");
                }

            }
        });



        return view;

    }

}
//TODO dialog fragment for tooltip