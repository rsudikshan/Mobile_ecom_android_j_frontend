package com.sr.a6semprojectfinal.Fragments;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sr.a6semprojectfinal.APIRequests.GetExploreData;
import com.sr.a6semprojectfinal.APIRequests.GetRecommendedProducts;
import com.sr.a6semprojectfinal.Adapters.HomeRecyclerAdapter;
import com.sr.a6semprojectfinal.DataHolders.SessionReference;
import com.sr.a6semprojectfinal.R;

public class Home extends Fragment {
    View view;
    ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b){

        view  = inflater.inflate(R.layout.home_fragment,container,false);
        TextView textView = view.findViewById(R.id.empty_text);
        progressBar = view.findViewById(R.id.home_progress_bar);

        VideoView videoView = view.findViewById(R.id.ad_banner);
        videoView.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.advideoplayback));
        videoView.start();


        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                videoView.start();
            }
        });

        if(SessionReference.isLoggedIn){
            textView.setText("");
        }
        else{
            textView.setText("Please Login To Get Customized Recommendation");
        }

        handler();




        return view;

    }

    public void handler(){

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_recommendations);
        if(SessionReference.isLoggedIn) {
            GetRecommendedProducts.getProducts(getContext(), new GetRecommendedProducts.CompletionListener() {
                @Override
                public void onStart(){
                    progressBar.setVisibility(View.VISIBLE);
                }
                @Override
                public void onComplete() {
                    HomeRecyclerAdapter adapter = new HomeRecyclerAdapter(getContext());
                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    recyclerView.setAdapter(adapter);
                    Log.d("Home", "Adapter Set");
                    Log.d("Home", GetExploreData.productNames.get(0));
                    progressBar.setVisibility(View.INVISIBLE);

                }
            });
        }


    }

}
