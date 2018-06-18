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
import android.widget.ImageButton;
import android.widget.ImageView;


public class KesfetCat extends Fragment {

    public KesfetCat() {
        // Required empty public constructor
    }

    GridView simpleGrid;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.categories_kesfet, container, false);

        TypedArray kesfet_category_thumbnails = getResources().obtainTypedArray(R.array.kesfet_category_thumbnails);

        //Toast.makeText(getActivity(),kesfetCategoryArray[3],Toast.LENGTH_LONG).show();


        ImageButton button = view.findViewById(R.id.image_kesfet);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                KesfetFragment ldf = new KesfetFragment ();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.mainLayout, ldf)  // content_main.xml deki frame layout id'si
                        .addToBackStack(null)
                        .commit();
            }
        });


        simpleGrid =  view.findViewById(R.id.simpleGridViewKesfet); // init GridView

        // Create an object of CatAdapter and set Adapter to GridView
        final CatAdapter catAdapter = new CatAdapter(this.getActivity().getApplicationContext(), kesfet_category_thumbnails);
        simpleGrid.setAdapter(catAdapter);

        // implement setOnItemClickListener event on GridView
        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ///// BURAYA MUZE, TARIH VS. FRAGMENTLARI KONULACAKTIR.

            }
        });


        return view;
    }






    public class CatAdapter extends BaseAdapter {

        //song list and layout
        private final Context context;
        private final TypedArray kesfetCategoryThumbnail;


        //constructor
        public CatAdapter(Context c, TypedArray kesfetCategoryThumbnail){
            this.kesfetCategoryThumbnail = kesfetCategoryThumbnail;
            this.context = c;

        }

        @Override
        public int getCount() {
            return 4;}

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
                view = layoutInflater.inflate(R.layout.activity_gridview_kesfet, null);

                final ImageView kesfetCategoryThumbnail= view.findViewById(R.id.kesfet_category_thumbnail);

                final KesfetCat.CatAdapter.ViewHolder viewHolder = new KesfetCat.CatAdapter.ViewHolder(kesfetCategoryThumbnail);
                view.setTag(viewHolder);
            }

            final KesfetCat.CatAdapter.ViewHolder viewHolder = (KesfetCat.CatAdapter.ViewHolder)view.getTag();

            viewHolder.kesfetCategoryThumbnail.setImageResource(kesfetCategoryThumbnail.getResourceId(position, -1));

            return view;
        }

        private class ViewHolder {
            private final ImageView kesfetCategoryThumbnail;


            public ViewHolder(ImageView kesfetCategoryThumbnail) {
                this.kesfetCategoryThumbnail = kesfetCategoryThumbnail;
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Keşfet");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
    }

}
