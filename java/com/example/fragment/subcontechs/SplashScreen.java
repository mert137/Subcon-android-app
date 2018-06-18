package com.example.fragment.subcontechs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.*;
import android.os.*;
import android.content.Intent;


public class SplashScreen extends Activity
{
    public String url;

    public String[] movieNameArray;
    public String[] movieDurationArray;
    public String[] movieCastingArray;
    public String[] movieExplanationArray;
    public String[] movieCategoryArray;
    public String[] movieCategories;
    public int movieNumber;

    public String[] musicNameArray;
    public String[] musicAlbumeArray;
    public String[] musicArtistArray;
    public String[] musicCategoryArray;
    public String[] musicThumbnailId;
    public int musicNumber;

    public  String[] gameNameArray;
    public int gameNumber;

    public  String[] OkuNameArray;
    public int OkuNumber;
    public String[] OkuCategoryArray;



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        new MyAsync2().execute(); //Asynctask Classı Çağırıyoruz
        finish();
    }



    public class MyAsync2 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {


            CommonMovieClass obj = new CommonMovieClass();
            obj.setUrl(getResources().getString(R.string.ipaddr2));

            String jsonStr;
            HttpHandler sh = new HttpHandler();
            url = obj.getUrl() + "/film.json";

            jsonStr = sh.makeServiceCall(url);

            try {
                JSONObject jsonObj = new JSONObject(jsonStr);


                ///// Burası movies databasei
                JSONArray movies = jsonObj.getJSONArray("film");
                movieNumber = movies.length();
                movieNameArray = new String[movieNumber];
                movieDurationArray = new String[movieNumber];
                movieCastingArray = new String[movieNumber];
                movieCategoryArray = new String[movieNumber];
                movieExplanationArray = new String[movieNumber];
                // looping through All Contacts
                for (int i = 0; i < movieNumber; i++) {
                    JSONObject c = movies.getJSONObject(i);
                    movieNameArray[i] = c.getString("name");
                    movieDurationArray[i] = c.getString("duration");
                    movieCastingArray[i] = c.getString("casting");
                    movieCategoryArray[i] = c.getString("category");
                    movieExplanationArray[i] = c.getString("explanation");

                }





                ///// Burası music database'i
                JSONArray music = jsonObj.getJSONArray("music");
                musicNumber = music.length();
                musicNameArray = new String[musicNumber];
                musicAlbumeArray = new String[musicNumber];
                musicArtistArray = new String[musicNumber];
                musicCategoryArray = new String[musicNumber];
                musicThumbnailId = new String[musicNumber];
                // looping through All Contacts
                for (int i = 0; i < musicNumber; i++) {
                    JSONObject c = music.getJSONObject(i);
                    musicNameArray[i] = c.getString("name");
                    musicAlbumeArray[i] = c.getString("albume");
                    musicArtistArray[i] = c.getString("artist");
                    musicCategoryArray[i] = c.getString("category");
                    musicThumbnailId[i] = c.getString("thumbnails");

                }




                ///// Burası game database'i
                JSONArray game = jsonObj.getJSONArray("game");
                gameNumber = game.length();
                gameNameArray = new String[gameNumber];
                // looping through All Contacts
                for (int i = 0; i < gameNumber; i++) {
                    JSONObject c = game.getJSONObject(i);
                    gameNameArray[i] = c.getString("name");
                }


                ///// Burası oku database'i
                JSONArray oku = jsonObj.getJSONArray("oku");
                OkuNumber = oku.length();
                OkuNameArray = new String[OkuNumber];
                OkuCategoryArray = new String[OkuNumber];
                // looping through All Contacts
                for (int i = 0; i < OkuNumber; i++) {
                    JSONObject c = oku.getJSONObject(i);
                    OkuNameArray[i] = c.getString("name");
                    OkuCategoryArray[i] = c.getString("category");
                }




            }
            catch (final JSONException e) {
            }
            return null;
        }



        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            CommonMovieClass obj = new CommonMovieClass();

            obj.setMovieNameArray(movieNameArray);
            obj.setMovieDurationArray(movieDurationArray);
            obj.setMovieCastingArray(movieCastingArray);
            obj.setMovieExplanationArray(movieExplanationArray);
            obj.setMovieCategoryArray(movieCategoryArray);
            obj.setMovieNumber(Integer.toString(movieNumber));

            obj.setMusicNameArray(musicNameArray);
            obj.setMusicAlbumeArray(musicAlbumeArray);
            obj.setMusicArtistArray(musicArtistArray);
            obj.setMusicCategoryArray(musicCategoryArray);
            obj.setMusicNumber(Integer.toString(musicNumber));
            obj.setMusicThumnailId(musicThumbnailId);

            obj.setGameNameArray(gameNameArray);
            obj.setGameNumber(Integer.toString(gameNumber));

            obj.setOkuNameArray(OkuNameArray);
            obj.setOkuNumber(Integer.toString(OkuNumber));
            obj.setOkuCategoryArray(OkuCategoryArray);


            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            finish();

        }
    }

}