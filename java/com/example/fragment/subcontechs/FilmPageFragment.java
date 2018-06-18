package com.example.fragment.subcontechs;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class FilmPageFragment extends Fragment {
    public FilmPageFragment() {
        // Required empty public constructor
    }

    String movieThumbnailUri;
    String movieName;
    String movieDuration;
    String movieUri;
    String movieTrailerUri;
    String movieCasting;
    String movieExplanation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.film_page, container, false);

        movieName = getArguments().getString("movieNameKey");
        movieDuration = getArguments().getString("movieDurationKey");
        movieUri = getArguments().getString("movieUriKey");
        movieTrailerUri = getArguments().getString("movieTrailerKey");
        movieCasting = getArguments().getString("movieCastingKey");
        movieExplanation = getArguments().getString("movieExplanationKey");
        movieThumbnailUri = getArguments().getString("movieThumbnailKey");


        final TextView movieNameText = view.findViewById(R.id.textView1);
        final TextView movieDurationText = view.findViewById(R.id.textView2);
        final TextView movieCastingText = view.findViewById(R.id.textView3);
        final TextView movieExplanationText = view.findViewById(R.id.textView4);
        final ImageView movieThumnail = view.findViewById(R.id.imageView);

        movieNameText.setText(movieName);
        movieDurationText.setText(movieDuration);
        movieCastingText.setText(movieCasting);
        movieExplanationText.setText(movieExplanation);
        Picasso.with(getContext()).load(movieThumbnailUri).into(movieThumnail);

        ImageButton watch_trailer_button = view.findViewById(R.id.watch_trailer);
        watch_trailer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VideoViewActivity.class);
                intent.putExtra("mp4uri", movieTrailerUri);
                startActivity(intent); // start Intent
            }
        });

        ImageButton play_movie_button = view.findViewById(R.id.play_movie);
       // try {
            play_movie_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), VideoViewActivity.class);
                    intent.putExtra("mp4uri", movieUri);
                    startActivity(intent); // start Intent
                }
            });
        //}
        //catch ()
        //{
        //    Toast.makeText(getContext(),R.string.moviecannotopen,Toast.LENGTH_LONG).show();
        //}
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle(movieName);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
    }
}
