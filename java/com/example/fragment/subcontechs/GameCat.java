package com.example.fragment.subcontechs;

import android.content.Context;
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

public class GameCat extends Fragment {

    GridView simpleGrid;
    String[] gameThumbnailUriArray;
    String[] gameNameArray;
    String[] gameUriArray;

    String ipaddr;
    String pathtogame;
    String pathtogamethumbnail;
    String gameNumber;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.categories_games, container, false);

        CommonMovieClass obj = new CommonMovieClass();
        gameNumber = obj.getGameNumber();
        int gameNumberHere = Integer.parseInt(gameNumber);

        gameNameArray = new String[gameNumberHere];
        gameNameArray = obj.getGameNameArray();

        gameThumbnailUriArray = new String[gameNumberHere];
        gameUriArray = new String[gameNumberHere];

        ipaddr = obj.getUrl();
        pathtogame = getResources().getString(R.string.pathtogame);
        pathtogamethumbnail = getResources().getString(R.string.pathtogamethumbnail);

       // for (int i = 0; i < gameNumberHere; i++) {
        //    gameThumbnailUriArray[i] = ipaddr + pathtogamethumbnail + Integer.toString(i+1) + ".jpg";
           // gameUriArray[i] = ipaddr + pathtogame + Integer.toString(i+1);
        //}

        gameUriArray[0] = ipaddr + pathtogame + "1";

        gameThumbnailUriArray[0] = ipaddr + pathtogamethumbnail + "1" + ".png";



        simpleGrid =  view.findViewById(R.id.simpleGridViewGame); // init GridView

        // Create an object of GameAdapter and set Adapter to GridView
        final GameAdapter gameAdapter = new GameAdapter(getContext(),gameThumbnailUriArray, gameNameArray);
        simpleGrid.setAdapter(gameAdapter);

        // implement setOnItemClickListener event on GridView
        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GameFragment ldf = new GameFragment ();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                Bundle args = new Bundle();
                args.putString("game", gameUriArray[position]);
                ldf.setArguments(args);
                manager.beginTransaction()
                        .replace(R.id.mainLayout, ldf)  // content_main.xml deki frame layout id'si
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }





    public class ViewHolder {
        private final ImageView gameThumbnail;
        private final TextView gameName;


        public ViewHolder(ImageView gameThumbnail, TextView gameName) {
            this.gameThumbnail = gameThumbnail;
            this.gameName = gameName;
        }
    }


    public class GameAdapter extends BaseAdapter {
        private final Context context;
        private final String[] gameNameArray;
        private final String[] gameThumbnailUriArray;

        public GameAdapter(Context applicationContext, String[] gameThumbnailUriArray, String[] gameNameArray) {
            this.context = applicationContext;
            this.gameThumbnailUriArray = gameThumbnailUriArray;
            this.gameNameArray = gameNameArray;
        }

        @Override
        public int getCount() {
            return gameNameArray.length;
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
                view = layoutInflater.inflate(R.layout.activity_gridview_games, null);

                final ImageView gameThumbnail = view.findViewById(R.id.game_thumbnail);
                final TextView gameName = view.findViewById(R.id.game_name);

                final ViewHolder viewHolder = new ViewHolder(gameThumbnail, gameName);
                view.setTag(viewHolder);
            }

            final ViewHolder viewHolder = (ViewHolder)view.getTag();

            Picasso.with(context).load(gameThumbnailUriArray[position]).into(viewHolder.gameThumbnail);
            viewHolder.gameName.setText(gameNameArray[position]);

            return view;
        }
    }






    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Oyna");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
    }
}
