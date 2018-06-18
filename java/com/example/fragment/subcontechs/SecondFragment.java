package com.example.fragment.subcontechs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class SecondFragment extends Fragment {
    public SecondFragment() {
        // Required empty public constructor
    }

    GridView simpleGrid;
    String[] movieThumbnailUriArray;
    String[] movieNameArray;
    String[] movieDurationArray;
    String[] movieUriArray;
    String[] movieTrailerUriArray;
    String[] movieCastingArray;
    String[] movieExplanationArray;

    String ipaddr;
    String pathtofilm;
    String pathtotrailer;
    String pathtothumbnail;
    String movieNumber;

    CommonMovieClass obj;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        obj = new CommonMovieClass();
        movieNumber = obj.getMovieNumber();
        int movieNumberHere = Integer.parseInt(movieNumber);

        movieNameArray = new String[movieNumberHere];
        movieDurationArray = new String[movieNumberHere];
        movieCastingArray = new String[movieNumberHere];
        movieExplanationArray = new String[movieNumberHere];
        movieTrailerUriArray = new String[movieNumberHere];
        movieThumbnailUriArray = new String[movieNumberHere];
        movieUriArray = new String[movieNumberHere];

        movieNameArray = obj.getMovieNameArray();
        movieDurationArray = obj.getMovieDurationArray();
        movieCastingArray = obj.getMovieCastingArray();
        movieExplanationArray = obj.getMovieExplanationArray();

        ipaddr = obj.getUrl();
        pathtofilm = getResources().getString(R.string.pathtofilm);
        pathtotrailer = getResources().getString(R.string.pathtotrailer);
        pathtothumbnail = getResources().getString(R.string.pathtothumbnail);

        for (int i = 0; i < movieNumberHere; i++) {
            movieTrailerUriArray[i] = ipaddr + pathtotrailer + Integer.toString(i+1) + "/t.m3u8";
            movieThumbnailUriArray[i] = ipaddr + pathtothumbnail + Integer.toString(i+1) + ".jpg";
            movieUriArray[i] = ipaddr + pathtofilm + Integer.toString(i+1) + "/f.m3u8";
        }

        simpleGrid =  view.findViewById(R.id.simpleGridView); // init GridView


        final int[] movieCatArray = getArguments().getIntArray("movieCatArray");


        // Create an object of CustomAdapter and set Adapter to GridView
        final CustomAdapter customAdapter = new CustomAdapter(this.getActivity().getApplicationContext(), movieThumbnailUriArray, movieNameArray, movieDurationArray, movieCatArray);
        simpleGrid.setAdapter(customAdapter);

        // implement setOnItemClickListener event on GridView
        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //filmPage(movieCatArray[position]);
                obj = new CommonMovieClass();
                Intent intent = new Intent(getActivity(), VideoViewActivity.class);
                intent.putExtra("mp4uri",obj.getUrl() + "/videos/4/f.m3u8");
                startActivity(intent); // start Intent
            }
        });

        return view;
    }




    public class CustomAdapter extends BaseAdapter {
        private final Context context;
        private final String[] movieNameArray;
        private final String[] movieDurationArray;
        private final String[] movieThumbnailUriArray;
        private final int[] movieCatArray;

        public CustomAdapter(Context applicationContext, String[] movieThumbnailUriArray, String[] movieNameArray, String[] movieDurationArray, int[] movieCatArray) {
            this.context = applicationContext;
            this.movieThumbnailUriArray = movieThumbnailUriArray;
            this.movieNameArray = movieNameArray;
            this.movieDurationArray = movieDurationArray;
            this.movieCatArray = movieCatArray;
        }

        @Override
        public int getCount() {
            return movieCatArray.length;
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
        public View getView(int position, View view, ViewGroup viewGroup) {


            if (view == null) {
                final LayoutInflater layoutInflater = LayoutInflater.from(this.context);
                view = layoutInflater.inflate(R.layout.activity_gridview_second, null);

                final ImageView movieThumbnail = view.findViewById(R.id.movie_thumbnail);
                final TextView movieName = view.findViewById(R.id.movie_name);
                //final TextView movieDuration = view.findViewById(R.id.movie_duration);

                //final ViewHolder viewHolder = new ViewHolder(movieThumbnail, movieName, movieDuration);
                final com.example.fragment.subcontechs.SecondFragment.ViewHolder viewHolder = new com.example.fragment.subcontechs.SecondFragment.ViewHolder(movieThumbnail, movieName);
                view.setTag(viewHolder);
            }

            final com.example.fragment.subcontechs.SecondFragment.ViewHolder viewHolder = (com.example.fragment.subcontechs.SecondFragment.ViewHolder) view.getTag();

            Picasso.with(context).load(movieThumbnailUriArray[movieCatArray[position]]).into(viewHolder.movieThumbnail);
            viewHolder.movieName.setText(movieNameArray[movieCatArray[position]]);
            // viewHolder.movieDuration.setText(movieDurationArray[movieCatArray[position]]);

            return view;
        }
    }

    private class ViewHolder {
        private final ImageView movieThumbnail;
        private final TextView movieName;
        //private final TextView movieDuration;


        public ViewHolder(ImageView movieThumbnail, TextView movieName) {
            this.movieThumbnail = movieThumbnail;
            this.movieName = movieName;
            //    this.movieDuration = movieDuration;
        }
    }






    @Override
    public void onResume() {     // Burası sayesinde başlığı ayarlayabilirsin fragmentlarda
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();

        actionBar.setTitle("Filmler");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

    }

    public void filmPage(int position) {

        //Put the value
        FilmPageFragment ldf = new FilmPageFragment ();
        FragmentManager manager = getActivity().getSupportFragmentManager();

        Bundle args = new Bundle();
        args.putString("movieNameKey", movieNameArray[position]);
        args.putString("movieDurationKey", movieDurationArray[position]);
        args.putString("movieUriKey", movieUriArray[position]);
        args.putString("movieTrailerKey", movieTrailerUriArray[position]);
        args.putString("movieCastingKey", movieCastingArray[position]);
        args.putString("movieExplanationKey", movieExplanationArray[position]);
        args.putString("movieThumbnailKey", movieThumbnailUriArray[position]);
        ldf.setArguments(args);

        manager.beginTransaction()
                .replace(R.id.mainLayout, ldf)  // content_main.xml deki frame layout id'si
                .addToBackStack(null)
                .commit();

    }

}
