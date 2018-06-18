package com.example.fragment.subcontechs;

import android.content.Context;
import android.content.res.TypedArray;
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

import java.util.Arrays;


public class MusicCategories extends Fragment {

    public MusicCategories() {
        // Required empty public constructor
    }

    GridView simpleGrid;
    int[][] catarray;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.categories_music, container, false);

        String[] musicCategoryArray;
        final String[] music_categories = getResources().getStringArray(R.array.music_categories);
        TypedArray music_category_thumbnails = getResources().obtainTypedArray(R.array.music_category_thumbnails);

        CommonMovieClass obj = new CommonMovieClass();
        String musicNumber = obj.getMusicNumber();
        musicCategoryArray = obj.getMusicCategoryArray();

        int musicNumberHere = Integer.parseInt(musicNumber);
        catarray = new int[music_categories.length][];

        //Toast.makeText(getActivity(),musicCategoryArray[3],Toast.LENGTH_LONG).show();

        int count[] = new int[music_categories.length];
        Arrays.fill(count,0);

        for(int j=0; j<music_categories.length; j++){

            for(int i=0;i<musicNumberHere; i++){
                if(musicCategoryArray[i].equals(music_categories[j])){
                    count[j]++;
                }
            }

            catarray[j] = new int[count[j]];
            Arrays.fill(catarray[j],0);
        }

        Arrays.fill(count,0);

        for(int j=0; j<music_categories.length; j++){

            for(int i=0;i<musicNumberHere; i++){
                if(musicCategoryArray[i].equals(music_categories[j])){
                    catarray[j][count[j]] = i;
                    count[j]++;
                }
            }
        }



        simpleGrid =  view.findViewById(R.id.musicGridView); // init GridView

        // Create an object of CatAdapter and set Adapter to GridView
        final CatAdapter catAdapter = new CatAdapter(this.getActivity().getApplicationContext(), music_categories, music_category_thumbnails);
        simpleGrid.setAdapter(catAdapter);

        // implement setOnItemClickListener event on GridView
        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MusicFragment ldf = new MusicFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();

                Bundle args = new Bundle();
                args.putIntArray("musicCatArray", catarray[position] );
                args.putString("cattitle", music_categories[position] );
                ldf.setArguments(args);

                manager.beginTransaction()
                        .replace(R.id.mainLayout, ldf)  // content_main.xml deki frame layout id'si
                        .addToBackStack(null)
                        .commit();
            }
        });


        return view;
    }






    public class CatAdapter extends BaseAdapter {

        //song list and layout
        private final Context context;
        private final String[] musicCategoryArray1;
        private final TypedArray musicCategoryThumbnail;


        //constructor
        public CatAdapter(Context c, String[] musicCategory, TypedArray musicCategoryThumbnail){
            this.musicCategoryArray1 = musicCategory;
            this.musicCategoryThumbnail = musicCategoryThumbnail;
            this.context = c;

        }

        @Override
        public int getCount() {
            return musicCategoryArray1.length;}

        @Override
        public Object getItem(int arg0) {
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }


        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            if (view == null) {
                final LayoutInflater layoutInflater = LayoutInflater.from(this.context);
                view = layoutInflater.inflate(R.layout.activity_gridview_game, null);

                final ImageView musicCategoryThumbnail= view.findViewById(R.id.music_category_thumbnail);

                final MusicCategories.CatAdapter.ViewHolder viewHolder = new MusicCategories.CatAdapter.ViewHolder(musicCategoryThumbnail);
                view.setTag(viewHolder);
            }

            final MusicCategories.CatAdapter.ViewHolder viewHolder = (MusicCategories.CatAdapter.ViewHolder)view.getTag();

            viewHolder.musicCategoryThumbnail.setImageResource(musicCategoryThumbnail.getResourceId(position, -1));

            return view;
        }

        private class ViewHolder {
            private final ImageView musicCategoryThumbnail;


            public ViewHolder(ImageView musicCategoryThumbnail) {
                this.musicCategoryThumbnail = musicCategoryThumbnail;
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Dinle");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
    }

}
