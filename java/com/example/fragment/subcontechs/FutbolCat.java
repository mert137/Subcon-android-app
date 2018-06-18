package com.example.fragment.subcontechs;

import android.support.v4.app.Fragment;
import android.view.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


public class FutbolCat extends Fragment {

    public FutbolCat() {
        // Required empty public constructor
    }

    GridView simpleGrid;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.categories_futbol, container, false);

        final String[] futbol_categories = getResources().getStringArray(R.array.futbol_categories);
        TypedArray futbol_category_thumbnails = getResources().obtainTypedArray(R.array.futbol_category_thumbnails);

        //Toast.makeText(getActivity(),futbolCategoryArray[3],Toast.LENGTH_LONG).show();


        simpleGrid =  view.findViewById(R.id.simpleGridViewFutbol); // init GridView

        // Create an object of CatAdapter and set Adapter to GridView
        final CatAdapter catAdapter = new CatAdapter(this.getActivity().getApplicationContext(), futbol_categories, futbol_category_thumbnails);
        simpleGrid.setAdapter(catAdapter);

        // implement setOnItemClickListener event on GridView
        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FutbolFragment ldf = new FutbolFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
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
        private final String[] futbolCategoryArray1;
        private final TypedArray futbolCategoryThumbnail;


        //constructor
        public CatAdapter(Context c, String[] futbolCategory, TypedArray futbolCategoryThumbnail){
            this.futbolCategoryArray1 = futbolCategory;
            this.futbolCategoryThumbnail = futbolCategoryThumbnail;
            this.context = c;

        }

        @Override
        public int getCount() {
            return futbolCategoryArray1.length;}

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
                view = layoutInflater.inflate(R.layout.activity_gridview_futbol, null);

                final TextView futbolCategory = view.findViewById(R.id.futbol_category_name);
                final ImageView futbolCategoryThumbnail= view.findViewById(R.id.futbol_category_thumbnail);

                final FutbolCat.CatAdapter.ViewHolder viewHolder = new FutbolCat.CatAdapter.ViewHolder(futbolCategory,futbolCategoryThumbnail);
                view.setTag(viewHolder);
            }

            final FutbolCat.CatAdapter.ViewHolder viewHolder = (FutbolCat.CatAdapter.ViewHolder)view.getTag();

            viewHolder.futbolCategory.setText(futbolCategoryArray1[position]);
            viewHolder.futbolCategoryThumbnail.setImageResource(futbolCategoryThumbnail.getResourceId(position, -1));

            return view;
        }

        private class ViewHolder {
            private final TextView futbolCategory;
            private final ImageView futbolCategoryThumbnail;


            public ViewHolder(TextView futbolCategory, ImageView futbolCategoryThumbnail) {
                this.futbolCategory = futbolCategory;
                this.futbolCategoryThumbnail = futbolCategoryThumbnail;
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Futbol");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
    }

}
