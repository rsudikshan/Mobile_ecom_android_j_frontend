package com.sr.a6semprojectfinal.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sr.a6semprojectfinal.APIRequests.GetExploreData;
import com.sr.a6semprojectfinal.APIRequests.SearchRequest;
import com.sr.a6semprojectfinal.Adapters.ExploreRecyclerAdapter;
import com.sr.a6semprojectfinal.R;

public class ExploreFragment extends Fragment {
    View view;
    ExploreRecyclerAdapter adapter;
    RecyclerView recyclerView;
    ProgressBar bar;
    EditText searchText;
    ImageView searchImage;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b){
        view = inflater.inflate(R.layout.explore_fragment,container,false);
        recyclerView = view.findViewById(R.id.explore_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        adapter = new ExploreRecyclerAdapter(getContext());

        bar = view.findViewById(R.id.explore_progressBar);
        searchImage = view.findViewById(R.id.search_bar_image);
        searchText = view.findViewById(R.id.search_bar_text);
        recyclerView.setHasFixedSize(true);//smooth
        recyclerView.setItemViewCacheSize(20);//smooth
        recyclerView.setAdapter(adapter);

        handler();
        searchHandler();



        return view;

    }

    public void handler() {
        Spinner spinner = view.findViewById(R.id.category_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemSelected = adapterView.getItemAtPosition(i).toString();
                bar.setVisibility(View.VISIBLE);

                if (i == 0) {
                    GetExploreData.getItems(getContext(), new GetExploreData.OnLoadingComplete() {
                        @Override
                        public void onResults() {
                            adapter.updateData(
                                    GetExploreData.getProductNames(),
                                    GetExploreData.getProductPrice(),
                                    GetExploreData.getImageURL(),
                                    GetExploreData.getCategory());
                            bar.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onCategoryError() {
                            bar.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onServerError() {
                            bar.setVisibility(View.INVISIBLE);
                        }
                    });
                } else {
                    GetExploreData.getCategorizedData(getContext(), itemSelected, new GetExploreData.onCategoryLoadingComplete() {
                        @Override
                        public void onCategoryResults() {
                            adapter.updateData(
                                    GetExploreData.getCategorizedProductNames(),
                                    GetExploreData.getCategorizedProductPrice(),
                                    GetExploreData.getCategorizedImageURL(),
                                    GetExploreData.getCategorizedProductCategory()

                            );
                            bar.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onCategoryError() {
                            Toast.makeText(getContext(), "Error loading data", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    public void searchHandler() {
        searchImage.setOnClickListener((view) -> {
            String text = searchText.getText().toString();
            SearchRequest.search(text, getContext(), new SearchRequest.SearchCompletionListener() {
                @Override
                public void onComplete() {
                    adapter.updateData(SearchRequest.getProduct_name(), SearchRequest.getProduct_price(), SearchRequest.getImage_url(), SearchRequest.getCategory());
                    Log.d("Search", "Complete");
                }

                @Override
                public void onError() {}
            });
        });
    }


}
