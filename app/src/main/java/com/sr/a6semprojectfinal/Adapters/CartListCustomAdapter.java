package com.sr.a6semprojectfinal.Adapters;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.sr.a6semprojectfinal.DataHolders.CartData;
import com.sr.a6semprojectfinal.R;

public class CartListCustomAdapter extends BaseAdapter {

    Context context;
    public CartListCustomAdapter(Context context){
        this.context = context;
        CartData.setDefaultProductCount();
    }

    @Override
    public int getCount(){
        return CartData.imageURL.size();
    }
    @Override
    public Object getItem(int i){
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View item = LayoutInflater.from(context).inflate(R.layout.cart_fragment_custom_item,viewGroup,false);
        ImageView itemImage = item.findViewById(R.id.cart_data_custom_item_image);
        TextView itemName = item.findViewById(R.id.cart_data_custom_item_name);
        TextView itemPrice = item.findViewById(R.id.cart_data_custom_item_total_price);
        Button remove = item.findViewById(R.id.cart_data_custom_item_remove);

        ImageView increase = item.findViewById(R.id.cart_data_custom_item_add_quantity);
        ImageView decrease = item.findViewById(R.id.cart_data_custom_item_remove_quantity);
        TextView count = item.findViewById(R.id.cart_data_custom_item_quantity);


        //Log.d("Size",String.valueOf(CartData.sameProductCount.size()));

        count.setText(String.valueOf(CartData.sameProductCount.get(i)));



        Picasso.get().load(CartData.imageURL.get(i)).into(itemImage);
        itemName.setText(CartData.productNames.get(i));
        itemPrice.setText(CartData.productPrice.get(i));



        remove.setOnClickListener((a)->{
            CartData.imageURL.remove(i);
            CartData.productNames.remove(i);
            CartData.productPrice.remove(i);
            notifyDataSetChanged();
        });



        return item;
    }







}