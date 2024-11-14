package com.sr.a6semprojectfinal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sr.a6semprojectfinal.R;

import java.util.ArrayList;

public class HistoryListCustomAdapter extends BaseAdapter {

    public ArrayList<String> productNames ;
    public ArrayList<String> productPrice;
    public ArrayList<String> date ;
    public  ArrayList<String> transactionKey ;

    Context context;

    public HistoryListCustomAdapter(Context context,ArrayList<String> productNames,
                                    ArrayList<String> productPrice,
                                    ArrayList<String> date,
                                    ArrayList<String> transactionKey
                                    )
    {
        this.context =  context;
        this.productNames = productNames;
        this.productPrice = productPrice;
        this.date = date;
        this.transactionKey = transactionKey;

    }

    @Override
    public int getCount() {
        return transactionKey.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item =  inflater.inflate(R.layout.user_profile_transaction_list_custom_item,viewGroup,false);

        TextView txt_pNames = item.findViewById(R.id.transaction_history_product_name);
        TextView txt_pPrice = item.findViewById(R.id.transaction_history_price);
        TextView txt_pDate = item.findViewById(R.id.transaction_history_date);
        TextView txt_pKey = item.findViewById(R.id.transaction_id);

        txt_pNames.setText(productNames.get(i));
        txt_pDate.setText(date.get(i));
        txt_pPrice.setText(productPrice.get(i));
        txt_pKey.setText(transactionKey.get(i));





        return item;
    }
}
