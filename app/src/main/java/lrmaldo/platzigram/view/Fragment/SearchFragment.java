package lrmaldo.platzigram.view.Fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import lrmaldo.platzigram.R;
import lrmaldo.platzigram.adapter.SearchAdapterRecyclerview;
import lrmaldo.platzigram.model.Picture;
import lrmaldo.platzigram.model.picture_search;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment  implements  SearchView.OnQueryTextListener {
    RecyclerView recyclerView;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclersearch);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayout.VERTICAL, false);

        SearchAdapterRecyclerview searchAdapterRecyclerview = new SearchAdapterRecyclerview(buildPictures(), R.layout.cardview_pinture_search, getActivity());

        SearchView searchView = (SearchView) view.findViewById(R.id.search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {

                } catch (Exception e) {

                }


                return false;
            }
        });

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(searchAdapterRecyclerview);
        return view;
    }

    public ArrayList<picture_search> buildPictures() {
        ArrayList<picture_search> pictures = new ArrayList<>();
        pictures.add(new picture_search("http://oi67.tinypic.com/whiaae.jpg", "Leonardo Lopez"));
        pictures.add(new picture_search("http://oi64.tinypic.com/15pj3bo.jpg", "Leonardo Lopez"));
        pictures.add(new picture_search("http://oi68.tinypic.com/15pjs3q.jpg", "Leonardo Lopez"));
        pictures.add(new picture_search("http://i.ytimg.com/vi/CSdmjTxEz7Y/hqdefault.jpg", "Leonardo Lopez"));
        pictures.add(new picture_search("https://i.ytimg.com/vi/iulzHgBowDc/hqdefault.jpg", "Leonardo Lopez"));
        pictures.add(new picture_search("https://s-media-cache-ak0.pinimg.com/564x/4e/ca/ac/4ecaac01a753d6ef7d61fe2a15deb2a1.jpg", "Leonardo Lopez"));
        return pictures;
    }


    public void cargar() {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        try {


        } catch (Exception e) {

        }
        return false;
    }



}
