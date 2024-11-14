package com.sr.a6semprojectfinal.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.sr.a6semprojectfinal.APIRequests.GetTransactionHistory;
import com.sr.a6semprojectfinal.APIRequests.LogoutRequest;
import com.sr.a6semprojectfinal.Adapters.HistoryListCustomAdapter;
import com.sr.a6semprojectfinal.DataHolders.SessionReference;
import com.sr.a6semprojectfinal.R;

public class UserInfo extends Fragment {
    View view;
    TextView username;
    TextView email;
    Button logout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b){
        view = inflater.inflate(R.layout.user_profile,container,false);
        username = view.findViewById(R.id.userName);
        email = view.findViewById(R.id.userEmail);
        logout = view.findViewById(R.id.logout);

        username.setText(SessionReference.sessionTracker.get("Username"));
        email.setText(SessionReference.sessionTracker.get("Email"));

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogoutRequest.logout(getContext(), new LogoutRequest.OnCompleteListener() {
                    @Override
                    public void onCompletion() {
                        SessionReference.isLoggedIn = false;
                        getParentFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    }

                    @Override
                    public void onError() {

                    }
                });

            }
        });

        list_handler();

        return view;



    }

    public void list_handler(){
        ListView listView = view.findViewById(R.id.history_list);
        GetTransactionHistory.getTransactionHistory(getContext(), new GetTransactionHistory.CompletionListener() {
            @Override
            public void onCompletion() {
                HistoryListCustomAdapter customAdapter = new HistoryListCustomAdapter(getContext(),
                        GetTransactionHistory.getProductNames(),
                        GetTransactionHistory.getProductPrice(),
                        GetTransactionHistory.getOrderDate(),
                        GetTransactionHistory.getTransactionKey());
                listView.setAdapter(customAdapter);
            }

            @Override
            public void onError() {

            }

            @Override
            public void onServerError() {

            }
        });
    }
}
//TODO bg loading videos , Better UI For User Profile , History , Logout Sync/Server check, Sending JSON object not key value hashmap