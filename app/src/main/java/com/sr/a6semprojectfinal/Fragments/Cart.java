package com.sr.a6semprojectfinal.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sr.a6semprojectfinal.APIRequests.SetTransaction;
import com.sr.a6semprojectfinal.Adapters.CartListCustomAdapter;
import com.sr.a6semprojectfinal.DataHolders.CartData;
import com.sr.a6semprojectfinal.R;

public class Cart extends Fragment {
    View view;
    Button button;

    Handler handler = new Handler();
    Runnable runnable ;
    int total_price;

    ProgressBar progressBar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle b){
        super.onCreateView(inflater,container,b);
        view = inflater.inflate(R.layout.cart_fragment,container,false);
        progressBar = view.findViewById(R.id.cart_progress_bar);
        handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                checkDataAndUpdateUI();
                handler.postDelayed(this, 100);
            }
        };

        return view;



    }

    public void handler(){
        ListView listView = view.findViewById(R.id.cart_list_view);
        CartListCustomAdapter adapter = new CartListCustomAdapter(getContext());
        CartData.setDefaultProductCount();
        listView.setAdapter(adapter);

        button = view.findViewById(R.id.checkout_button);





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SetTransaction.setTransaction(getContext(), new SetTransaction.CompletionListener() {
                    @Override
                    public void onStart(){
                        progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onComplete() {
                        CartData.imageURL.clear();
                        CartData.productNames.clear();
                        CartData.productPrice.clear();
                        CartData.defaultPriceHolder.clear();
                        CartData.category.clear();
                        CartData.sameProductCount.clear();

                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError() {

                    }
                });
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        handler.post(runnable);
    }

    @Override
    public void onPause(){
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    public void checkDataAndUpdateUI(){
        if(!CartData.productPrice.isEmpty()){
            total_price = CartData.getTotalPrice();
            button.setText("Checkout : Rs "+String.valueOf(total_price));
        }
    }
}
