package lrmaldo.platzigram.view.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import lrmaldo.platzigram.R;
import lrmaldo.platzigram.adapter.PictureAdapterRecyclerview;
import lrmaldo.platzigram.model.Picture;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ShowToolbar("",false,view);



        RecyclerView pictureRecycler = (RecyclerView) view.findViewById(R.id.pictureRecyclerProfile);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        pictureRecycler.setLayoutManager(linearLayoutManager);
        PictureAdapterRecyclerview pictureAdapterRecyclerview = new PictureAdapterRecyclerview(buildPictures(),R.layout.cardview_pinture,getActivity());


        pictureRecycler.setAdapter(pictureAdapterRecyclerview);
        return view;
    }

    public ArrayList<Picture> buildPictures(){
        ArrayList<Picture> pictures= new ArrayList<>();
        pictures.add(new Picture("http://oi67.tinypic.com/whiaae.jpg","Leonardo Lopez","4 días","10"));
        pictures.add(new Picture("http://oi64.tinypic.com/15pj3bo.jpg","Leonardo Lopez","4 días","10"));
        pictures.add(new Picture("http://oi68.tinypic.com/15pjs3q.jpg","Leonardo Lopez","4 días","10"));
        pictures.add(new Picture("http://i.ytimg.com/vi/CSdmjTxEz7Y/hqdefault.jpg","Leonardo Lopez","4 días","10"));
        pictures.add(new Picture("https://i.ytimg.com/vi/iulzHgBowDc/hqdefault.jpg","Leonardo Lopez","4 días","10"));
        pictures.add(new Picture("https://s-media-cache-ak0.pinimg.com/564x/4e/ca/ac/4ecaac01a753d6ef7d61fe2a15deb2a1.jpg","Leonardo Lopez","4 días","10"));
        return pictures;
    }

    public  void ShowToolbar(String title, boolean ac_boton , View view){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(ac_boton);

    }

}
