package com.example.fragment.subcontechs;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MusicFragment extends Fragment implements MediaController.MediaPlayerControl {

    public MusicFragment() {
        // Required empty public constructor
    }

    public static final String Broadcast_PLAY_NEW_AUDIO = "com.example.fragment.fragment.PlayNewAudio";

    private MediaPlayerService player;
    boolean serviceBound = false;
    ArrayList<Audio> audioList;

    String[] musicNameArray;
    String[] musicAlbumeArray;
    String[] musicArtistArray;
    String[] musicUriArray;
    String[] musicThumbnailUriArray;
    String[] musicThumbnailId;

    String ipaddr;
    String pathtomusic;
    String pathtothumbnailmusic;
    String musicNumber;

    //song list variables
    private ListView songView;

    private MusicController controller;
    private boolean playbackPaused=false, paused =false;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.music_main, container, false);

        setController(view);

        loadAudio();
        //Toast.makeText(getActivity(),audioList.get(0).getData(),Toast.LENGTH_LONG).show();
        initRecyclerView(view);

        return view;
    }


    private void initRecyclerView(View view) {
        if (audioList.size() > 0) {
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
            RecyclerView_Adapter adapter = new RecyclerView_Adapter(audioList, this.getContext());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
            recyclerView.addOnItemTouchListener(new CustomTouchListener(this.getContext(), new CustomTouchListener.onItemClickListener() {
                @Override
                public void onClick(View view, int index) {
                    playAudio(index);
                    controller.show(0);
                }
            }));

        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("serviceStatus", serviceBound);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            serviceBound = savedInstanceState.getBoolean("serviceStatus");
        }
    }

    //Binding this Client to the AudioPlayer Service
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MediaPlayerService.LocalBinder binder = (MediaPlayerService.LocalBinder) service;
            player = binder.getService();
            serviceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serviceBound = false;
        }
    };


    private void playAudio(int audioIndex) {
        //Check is service is active
        if (!serviceBound) {
            //Store Serializable audioList to SharedPreferences
            StorageUtil storage = new StorageUtil(getActivity().getApplicationContext());
            storage.storeAudio(audioList);
            storage.storeAudioIndex(audioIndex);

            Intent playerIntent = new Intent(getContext(), MediaPlayerService.class);
            getActivity().startService(playerIntent);
            getActivity().bindService(playerIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        } else {
            //Store the new audioIndex to SharedPreferences
            StorageUtil storage = new StorageUtil(getActivity().getApplicationContext());
            storage.storeAudioIndex(audioIndex);

            //Service is active
            //Send a broadcast to the service -> PLAY_NEW_AUDIO
            Intent broadcastIntent = new Intent(Broadcast_PLAY_NEW_AUDIO);
            getActivity().sendBroadcast(broadcastIntent);
        }
    }


    private void loadAudio() {

        CommonMovieClass obj = new CommonMovieClass();
        musicNumber = obj.getMusicNumber();
        int musicNumberHere = Integer.parseInt(musicNumber);

        musicNameArray = new String[musicNumberHere];
        musicAlbumeArray = new String[musicNumberHere];
        musicArtistArray = new String[musicNumberHere];
        musicUriArray = new String[musicNumberHere];
        musicThumbnailUriArray = new String[musicNumberHere];
        musicThumbnailId = new String[musicNumberHere];

        musicNameArray = obj.getMusicNameArray();
        musicAlbumeArray = obj.getMusicAlbumeArray();
        musicArtistArray = obj.getMusicArtistArray();
        musicThumbnailId = obj.getMusicThumbnailId();

        ipaddr = obj.getUrl();
        pathtomusic = getResources().getString(R.string.pathtomusic);
        pathtothumbnailmusic = getResources().getString(R.string.pathtothumbnailmusic);

        for (int i = 0; i < musicNumberHere; i++) {
            musicThumbnailUriArray[i] = ipaddr + pathtothumbnailmusic + musicThumbnailId[i] + ".jpg";
            musicUriArray[i] = ipaddr + pathtomusic + "1" + "/m.m3u8";  //1 yazan yere Integer.toString(i+1) gelmeli daha sonra
        }

        final int[] musicCatArray = getArguments().getIntArray("musicCatArray");

        audioList = new ArrayList<>();
        for (int i=0 ; i < musicCatArray.length ; i++) {
            String data = musicUriArray[musicCatArray[i]];
            String title = musicNameArray[musicCatArray[i]];
            String album = musicAlbumeArray[musicCatArray[i]];
            String artist = musicArtistArray[musicCatArray[i]];
            String thumbnail = musicThumbnailUriArray[musicCatArray[i]];

            // Save to audioList
            audioList.add(new Audio(data, title, album, artist, thumbnail));
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (serviceBound) {
            getActivity().unbindService(serviceConnection);
            //service is active
            player.stopSelf();
        }
    }







    public class RecyclerView_Adapter extends RecyclerView.Adapter<ViewHolder> {

        List<Audio> list = Collections.emptyList();
        Context context;

        public RecyclerView_Adapter(List<Audio> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //Inflate the layout, initialize the View Holder
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.song, parent, false);
            ViewHolder holder = new ViewHolder(v);
            return holder;

        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
            holder.title.setText(list.get(position).getTitle());
            holder.artist.setText(list.get(position).getArtist());
        }

        @Override
        public int getItemCount() {
            //returns the number of elements the RecyclerView will display
            return list.size();
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView artist;

        ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            artist = (TextView) itemView.findViewById(R.id.artist);
        }
    }

    private void setController(View view){
        controller = new MusicController(getContext());
        controller.setPrevNextListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playNext(v);
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playPrev(v);
            }
        });

        controller.setMediaPlayer(this);
        controller.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.almostTransparent));
        controller.setAnchorView(view.findViewById(R.id.recyclerview));
        controller.setEnabled(true);
    }

    //play next
    private void playNext(View view){
        player.skipToNext();
        if(playbackPaused){
            setController(view);
            playbackPaused=false;
        }
        controller.show(0);
    }


    //play previous
    private void playPrev(View view){
        player.skipToPrevious();
        if(playbackPaused){
            setController(view);
            playbackPaused=false;
        }
        controller.show(0);
    }


    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public int getCurrentPosition() {
        if(player!=null && serviceBound && player.isPng())
            return player.getPosn();
        else return 0;
    }

    @Override
    public int getDuration() {
        if(player!=null && serviceBound && player.isPng())
            return player.getDur();
        else return 0;
    }

    @Override
    public boolean isPlaying() {
        if(player!=null && serviceBound)
            return player.isPng();
        return false;
    }

    @Override
    public void pause() {
        playbackPaused=true;
        player.pausePlayer();
    }

    @Override
    public void seekTo(int pos) {
        player.seek(pos);
    }

    @Override
    public void start() {
        player.go();
    }

    @Override
    public void onPause(){
        super.onPause();
        paused=true;
    }

    @Override
    public void onResume(){
        super.onResume();
        if(paused){
            setController(getView());
            paused=false;
        }

        String cattitle = getArguments().getString("cattitle");
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle(cattitle);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
    }

    @Override
    public void onStop() {
        controller.hide();
        super.onStop();
    }
}