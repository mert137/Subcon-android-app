package com.example.fragment.subcontechs;



import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Arrays;


public class HorizontalListViewFragment extends Fragment {

    public HorizontalListViewFragment() {
        // Required empty public constructor
    }

    String[] movieThumbnailUriArray;
    String[] movieNameArray;
    String[] movieDurationArray;
    String[] movieCategoryArray;
    String[] movieUriArray;
    String[] movieTrailerUriArray;
    String[] movieCastingArray;
    String[] movieExplanationArray;

    String ipaddr;
    String pathtofilm;
    String pathtotrailer;
    String pathtothumbnail;
    String movieNumber;



    Context c;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.categories_film, container, false);

        c = getActivity().getApplicationContext();

        int movieNumberHere;

        CommonMovieClass obj = new CommonMovieClass();
        movieNumber = obj.getMovieNumber();
        movieNumberHere = Integer.parseInt(movieNumber);


        movieNameArray = new String[movieNumberHere];
        movieDurationArray = new String[movieNumberHere];
        movieCastingArray = new String[movieNumberHere];
        movieCategoryArray = new String[movieNumberHere];
        movieExplanationArray = new String[movieNumberHere];
        movieTrailerUriArray = new String[movieNumberHere];
        movieThumbnailUriArray = new String[movieNumberHere];
        movieUriArray = new String[movieNumberHere];

        movieNameArray = obj.getMovieNameArray();
        movieDurationArray = obj.getMovieDurationArray();
        movieCastingArray = obj.getMovieCastingArray();
        movieExplanationArray = obj.getMovieExplanationArray();
        movieCategoryArray = obj.getMovieCategoryArray();


        ipaddr = obj.getUrl();
        pathtofilm = getResources().getString(R.string.pathtofilm);
        pathtotrailer = getResources().getString(R.string.pathtotrailer);
        pathtothumbnail = getResources().getString(R.string.pathtothumbnail);



        for (int i = 0; i < movieNumberHere; i++) {
            movieTrailerUriArray[i] = ipaddr + pathtotrailer + Integer.toString(i+1) + "/t.m3u8";
            movieThumbnailUriArray[i] = ipaddr + pathtothumbnail + Integer.toString(i+1) + ".jpg";
            movieUriArray[i] = ipaddr + pathtofilm + Integer.toString(i+1) + "/f.m3u8";
        }


        String[] movie_categories = getResources().getStringArray(R.array.movie_categories);
        int[][] catarray = new int[movie_categories.length][];

        int count[] = new int[movie_categories.length];
        Arrays.fill(count,0);

        for(int j=0; j<movie_categories.length; j++){

            for(int i=0;i<movieNumberHere; i++){
                if(movieCategoryArray[i].equals(movie_categories[j])){
                    count[j]++;
                }
            }

            catarray[j] = new int[count[j]];
            Arrays.fill(catarray[j],0);
        }

        Arrays.fill(count,0);

        for(int j=0; j<movie_categories.length; j++){

            for(int i=0;i<movieNumberHere; i++){
                if(movieCategoryArray[i].equals(movie_categories[j])){
                    catarray[j][count[j]] = i;
                    count[j]++;
                }
            }
        }




        My_Recycle(view,R.id.cardView,catarray[1]);
        My_Recycle(view,R.id.cardView2,catarray[0]);
       // My_Recycle(view,R.id.cardView3,catarray[2]);

        See_All(view,R.id.see_all_aksiyon,catarray[1]);
        See_All(view,R.id.see_all_komedi,catarray[0]);
       // See_All(view,R.id.see_all_gerilim,catarray[2]);


        return view;
    }



    private class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private ImageView coverImageView;
        CommonMovieClass obj;

        private MyViewHolder(View v) {
            super(v);
            obj = new CommonMovieClass();
            titleTextView = (TextView) v.findViewById(R.id.movie_name);
            coverImageView = (ImageView) v.findViewById(R.id.movie_thumbnail);
            coverImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  //  filmPage((int) v.getTag());
                    Intent intent = new Intent(getActivity(), VideoViewActivity.class);
                    intent.putExtra("mp4uri",obj.getUrl() + "/videos/1/f.m3u8" );
                    startActivity(intent); // start Intent
                }
            });

        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {


        int catarray2_view[];

        private MyAdapter(int catarray4[]) {

            catarray2_view = catarray4;
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_gridview_film, parent, false);
            MyViewHolder holder = new MyViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {

            holder.titleTextView.setText(movieNameArray[catarray2_view[position]]);
            Picasso.with(c).load(movieThumbnailUriArray[catarray2_view[position]]).into(holder.coverImageView);
            holder.coverImageView.setTag(catarray2_view[position]);
        }

        @Override
        public int getItemCount() {
            return catarray2_view.length;
        }

    }

    public void My_Recycle(View view, int resource2, int[] catarray4)
    {

        RecyclerView MyRecyclerView;

        MyRecyclerView =  view.findViewById(resource2);
        MyRecyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        if (MyRecyclerView != null) {
            MyRecyclerView.setAdapter(new MyAdapter(catarray4));
        }
        MyRecyclerView.setLayoutManager(MyLayoutManager);
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

    public void See_All(View view, int resource, final int catarray_grid[])
    {
        Button see_all;

        see_all =  view.findViewById(resource);
        see_all.setOnClickListener(new View.OnClickListener() {    //nullpointerexception
            @Override
            public void onClick(View v) {
                SecondFragment ldf = new SecondFragment ();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                Bundle args = new Bundle();
                args.putIntArray("movieCatArray", catarray_grid);
                ldf.setArguments(args);
                manager.beginTransaction()
                        .replace(R.id.mainLayout, ldf)  // content_main.xml deki frame layout id'si
                        .addToBackStack(null)
                        .commit();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("İzle");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
    }

}
