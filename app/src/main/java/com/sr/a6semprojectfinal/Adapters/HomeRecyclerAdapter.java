package com.sr.a6semprojectfinal.Adapters;

import static com.sr.a6semprojectfinal.APIRequests.GetExploreData.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.sr.a6semprojectfinal.DataHolders.CartData;
import com.sr.a6semprojectfinal.DataHolders.SessionReference;
import com.sr.a6semprojectfinal.R;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder> {

    Context context;
    public HomeRecyclerAdapter(Context context){
        this.context = context;

    }


    @NonNull
    @Override
    public HomeRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.home_custom_item,parent,false);
        return new HomeRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
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

    @Override
    public int getItemCount() {
        return productNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;
        TextView name;
        TextView price;
        Button order;


        ViewHolder(View item){
            super(item);
            itemImage = itemView.findViewById(R.id.home_custom_item_image);
            name = itemView.findViewById(R.id.home_custom_item_name);
            price = itemView.findViewById(R.id.home_custom_item_price);
            order = itemView.findViewById(R.id.home_custom_item_order);
        }

    }
}
