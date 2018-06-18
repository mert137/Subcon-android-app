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
import android.widget.TextView;


public class OgrenCat extends Fragment {

    public OgrenCat() {
        // Required empty public constructor
    }

    GridView simpleGrid;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.categories_ogren, container, false);

        final String[] ogren_categories = getResources().getStringArray(R.array.ogren_categories);
        TypedArray ogren_category_thumbnails = getResources().obtainTypedArray(R.array.ogren_category_thumbnails);

        //Toast.makeText(getActivity(),ogrenCategoryArray[3],Toast.LENGTH_LONG).show();


        simpleGrid =  view.findViewById(R.id.simpleGridViewOgren); // init GridView

        // Create an object of CatAdapter and set Adapter to GridView
        final CatAdapter catAdapter = new CatAdapter(this.getActivity().getApplicationContext(), ogren_categories, ogren_category_thumbnails);
        simpleGrid.setAdapter(catAdapter);

        // implement setOnItemClickListener event on GridView
        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OgrenFragment ldf = new OgrenFragment();
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
        private final String[] ogrenCategoryArray1;
        private final TypedArray ogrenCategoryThumbnail;


        //constructor
        public CatAdapter(Context c, String[] ogrenCategory, TypedArray ogrenCategoryThumbnail){
            this.ogrenCategoryArray1 = ogrenCategory;
            this.ogrenCategoryThumbnail = ogrenCategoryThumbnail;
            this.context = c;

        }

        @Override
        public int getCount() {
            return ogrenCategoryArray1.length;}

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
                view = layoutInflater.inflate(R.layout.activity_gridview_ogren, null);

                final TextView ogrenCategory = view.findViewById(R.id.ogren_category_name);
                final ImageView ogrenCategoryThumbnail= view.findViewById(R.id.ogren_category_thumbnail);

                final OgrenCat.CatAdapter.ViewHolder viewHolder = new OgrenCat.CatAdapter.ViewHolder(ogrenCategory,ogrenCategoryThumbnail);
                view.setTag(viewHolder);
            }

            final OgrenCat.CatAdapter.ViewHolder viewHolder = (OgrenCat.CatAdapter.ViewHolder)view.getTag();

            viewHolder.ogrenCategory.setText(ogrenCategoryArray1[position]);
            viewHolder.ogrenCategoryThumbnail.setImageResource(ogrenCategoryThumbnail.getResourceId(position, -1));

            return view;
        }

        private class ViewHolder {
            private final TextView ogrenCategory;
            private final ImageView ogrenCategoryThumbnail;


            public ViewHolder(TextView ogrenCategory, ImageView ogrenCategoryThumbnail) {
                this.ogrenCategory = ogrenCategory;
                this.ogrenCategoryThumbnail = ogrenCategoryThumbnail;
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Öğren");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
    }

}
