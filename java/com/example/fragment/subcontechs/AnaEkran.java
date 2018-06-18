package com.example.fragment.subcontechs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class AnaEkran extends Fragment {


    public AnaEkran() {
        // Required empty public constructor
    }

    Button discover_button;
    Button film_button;
    Button music_button;
    Button oyun_button;
    Button futbol_button;
    Button oku_button;
    Button ogren_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ana_ekran, container, false);

        //Bu yorumlu kodlar çeşitli denemeler içindir. Asıl programa dahil değildir.
        //CommonMovieClass obj = new CommonMovieClass();
        //String musicCategoryArray = obj.getGameNumber();
        //Toast.makeText(getContext(),musicCategoryArray,Toast.LENGTH_LONG).show();


        discover_button = (Button) view.findViewById(R.id.discover_button);
        discover_button.setOnClickListener(new View.OnClickListener() {    //nullpointerexception
            @Override
            public void onClick(View v) {
                KesfetCat ldf = new KesfetCat();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.mainLayout, ldf)  // content_main.xml deki frame layout id'si
                        .addToBackStack(null)
                        .commit();
            }
        });

        film_button = (Button) view.findViewById(R.id.film_button);
        film_button.setOnClickListener(new View.OnClickListener() {    //nullpointerexception
            @Override
            public void onClick(View v) {
                HorizontalListViewFragment ldf = new HorizontalListViewFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.mainLayout, ldf)  // content_main.xml deki frame layout id'si
                        .addToBackStack(null)
                        .commit();
            }
        });


        music_button = (Button) view.findViewById(R.id.music_button);
        music_button.setOnClickListener(new View.OnClickListener() {    //nullpointerexception
            @Override
            public void onClick(View v) {
                MusicCategories ldf = new MusicCategories();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.mainLayout, ldf)  // content_main.xml deki frame layout id'si
                        .addToBackStack(null)
                        .commit();
            }
        });

        oyun_button = (Button) view.findViewById(R.id.oyun_button);
        oyun_button.setOnClickListener(new View.OnClickListener() {    //nullpointerexception
            @Override
            public void onClick(View v) {
                GameCat ldf = new GameCat();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.mainLayout, ldf)  // content_main.xml deki frame layout id'si
                        .addToBackStack(null)
                        .commit();
            }
        });

        futbol_button = (Button) view.findViewById(R.id.futbol_button);
        futbol_button.setOnClickListener(new View.OnClickListener() {    //nullpointerexception
            @Override
            public void onClick(View v) {
                FutbolCat ldf = new FutbolCat();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.mainLayout, ldf)  // content_main.xml deki frame layout id'si
                        .addToBackStack(null)
                        .commit();
            }
        });
        oku_button = (Button) view.findViewById(R.id.oku_button);
        oku_button.setOnClickListener(new View.OnClickListener() {    //nullpointerexception
            @Override
            public void onClick(View v) {
                OkuCat ldf = new OkuCat();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.mainLayout, ldf)  // content_main.xml deki frame layout id'si
                        .addToBackStack(null)
                        .commit();
            }
        });
        ogren_button = (Button) view.findViewById(R.id.ogren_button);
        ogren_button.setOnClickListener(new View.OnClickListener() {    //nullpointerexception
            @Override
            public void onClick(View v) {
                OgrenCat ldf = new OgrenCat();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.mainLayout, ldf)  // content_main.xml deki frame layout id'si
                        .addToBackStack(null)
                        .commit();
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.logo, null));

    }

}


