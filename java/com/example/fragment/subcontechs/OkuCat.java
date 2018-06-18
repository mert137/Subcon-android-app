package com.example.fragment.subcontechs;


import android.content.Context;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Arrays;


public class OkuCat extends Fragment {

    public OkuCat() {
        // Required empty public constructor
    }

    String[] okuThumbnailUriArray;
    String[] okuNameArray;
    String[] okuCategoryArray;

    String ipaddr;
    String pathtokuthumbnail;
    String okuNumber;

    Context c;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.categories_oku, container, false);

        c = getActivity().getApplicationContext();

        int okuNumberHere;

        CommonMovieClass obj = new CommonMovieClass();
        okuNumber = obj.getOkuNumber();
        okuNumberHere = Integer.parseInt(okuNumber);

        okuNameArray = new String[okuNumberHere];
        okuCategoryArray = new String[okuNumberHere];
        okuThumbnailUriArray = new String[okuNumberHere];

        okuNameArray = obj.getOkuNameArray();
        okuCategoryArray = obj.getOkuCategoryArray();


        ipaddr = getResources().getString(R.string.ipaddr2);
        pathtokuthumbnail = getResources().getString(R.string.pathtokuthumbnail);

        for (int i = 0; i < okuNumberHere; i++) {
            okuThumbnailUriArray[i] = ipaddr + pathtokuthumbnail + Integer.toString(i+1) + ".jpg";
        }

        String[] oku_categories = getResources().getStringArray(R.array.oku_categories);
        int[][] catarray = new int[oku_categories.length][];

        int count[] = new int[oku_categories.length];
        Arrays.fill(count,0);

        for(int j=0; j<oku_categories.length; j++){

            for(int i=0;i<okuNumberHere; i++){
                if(okuCategoryArray[i].equals(oku_categories[j])){
                    count[j]++;
                }
            }

            catarray[j] = new int[count[j]];
            Arrays.fill(catarray[j],0);
        }

        Arrays.fill(count,0);

        for(int j=0; j<oku_categories.length; j++){

            for(int i=0;i<okuNumberHere; i++){
                if(okuCategoryArray[i].equals(oku_categories[j])){
                    catarray[j][count[j]] = i;
                    count[j]++;
                }
            }
        }


        My_Recycle(view,R.id.cardView4,catarray[0]);
        My_Recycle(view,R.id.cardView5,catarray[1]);
        My_Recycle(view,R.id.cardView6,catarray[2]);

        return view;
    }



    private class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private ImageView coverImageView;

        private MyViewHolder(View v) {
            super(v);
            titleTextView = (TextView) v.findViewById(R.id.oku_category_name);
            coverImageView = (ImageView) v.findViewById(R.id.oku_category_thumbnail);
            coverImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    okuPage((int) v.getTag());
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
                    .inflate(R.layout.activity_gridview_oku, parent, false);
            MyViewHolder holder = new MyViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {

            holder.titleTextView.setText(okuNameArray[catarray2_view[position]]);
            Picasso.with(c).load(okuThumbnailUriArray[catarray2_view[position]]).into(holder.coverImageView);
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

    public void okuPage(int position) {

        //Put the value
        OkuFragment ldf = new OkuFragment ();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.mainLayout, ldf)  // content_main.xml deki frame layout id'si
                .addToBackStack(null)
                .commit();

    }


    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Oku");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
    }

}
