package com.sr.a6semprojectfinal.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sr.a6semprojectfinal.APIRequests.SetTransaction;
import com.sr.a6semprojectfinal.Adapters.CartListCustomAdapter;
import com.sr.a6semprojectfinal.DataHolders.CartData;
import com.sr.a6semprojectfinal.R;

public class Cart extends Fragment {
    View view;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b){
        super.onCreateView(inflater,container,b);
        view = inflater.inflate(R.layout.cart_fragment,container,false);

        handler();


        return view;



    }

    public void handler(){
        ListView listView = view.findViewById(R.id.cart_list_view);
        CartListCustomAdapter adapter = new CartListCustomAdapter(getContext());
        listView.setAdapter(adapter);

        button = view.findViewById(R.id.checkout_button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetTransaction.setTransaction(getContext(), new SetTransaction.CompletionListener() {
                    @Override
                    public void onComplete() {
                        CartData.imageURL.clear();
                        CartData.productNames.clear();
                        CartData.productPrice.clear();
                        CartData.category.clear();

                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError() {

                    }
                });
            }
        });


    }
}
