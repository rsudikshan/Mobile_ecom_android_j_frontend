package com.sr.a6semprojectfinal.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.sr.a6semprojectfinal.APIRequests.GetExploreData;
import com.sr.a6semprojectfinal.DataHolders.CartData;
import com.sr.a6semprojectfinal.DataHolders.SessionReference;
import com.sr.a6semprojectfinal.R;

import java.util.ArrayList;

public class ExploreRecyclerAdapter extends RecyclerView.Adapter<ExploreRecyclerAdapter.CustomRecylcerViewHolder> {
    Context context;
    public ArrayList<String> productNames;
    public ArrayList<String> productPrice;
    public ArrayList<String> imageURL;
    public ArrayList<String> category;

    public ExploreRecyclerAdapter(Context context){
        this.context = context;
        Log.d("Adapter","Initialized");
        productNames = new ArrayList<>();
        productPrice = new ArrayList<>();
        imageURL = new ArrayList<>();


    }


    @Override
    public int getItemCount(){
        return productNames.size();

    }

    @Override
    public CustomRecylcerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.explore_custom_item,parent,false);


        return new CustomRecylcerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(CustomRecylcerViewHolder viewHolder, int position){

        int i = position;

        viewHolder.name.setText(productNames.get(position));
        viewHolder.price.setText("Rs: "+productPrice.get(position));
        Picasso.get().load(imageURL.get(position)).fit().centerInside().into(viewHolder.itemImage);


            viewHolder.order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(SessionReference.isLoggedIn){
                        String cartProductName = productNames.get(i);
                        String cartProductPrice = productPrice.get(i);
                        String cartImageURL = imageURL.get(i);

                        CartData.productNames.add(cartProductName);
                        CartData.productPrice.add(cartProductPrice);
                        CartData.imageURL.add(cartImageURL);

                        Toast.makeText(context, cartProductName + " Added to Cart", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Toast.makeText(context, "Please log in to add to cart", Toast.LENGTH_SHORT).show();
                    }




                }
            });





    }




    public class CustomRecylcerViewHolder extends RecyclerView.ViewHolder{
        ImageView itemImage;
        TextView name;
        TextView price;
        Button order;


        CustomRecylcerViewHolder(View itemView){
            super(itemView);
            itemImage = itemView.findViewById(R.id.explore_custom_item_image);
            name = itemView.findViewById(R.id.explore_custom_item_name);
            price = itemView.findViewById(R.id.explore_custom_item_price);
            order = itemView.findViewById(R.id.explore_custom_item_order);


            Log.d("Adapter","ViewHolder Created");


        }
    }

    public void updateData(ArrayList<String> productNames, ArrayList<String> productPrice, ArrayList<String> imageURL){
            this.productNames.clear();
            this.productPrice.clear();
            this.imageURL.clear();

            this.productNames.addAll(productNames);
            this.productPrice.addAll(productPrice);
            this.imageURL.addAll(imageURL);

            notifyDataSetChanged();
    }

}
