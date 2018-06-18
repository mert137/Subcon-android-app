package com.example.fragment.subcontechs;


public class CommonMovieClass {

    public static String url;

    public static String[] movieNameArray;
    public static String[] movieDurationArray;
    public static String[] movieCastingArray;
    public static String[] movieExplanationArray;
    public static String movieNumber;
    public static String[] movieCategoryArray;

    public static String[] musicNameArray;
    public static String[] musicAlbumeArray;
    public static String[] musicArtistArray;
    public static String[] musicCategoryArray;
    public static String musicNumber;
    public static String[] musicThumnailId;

    public static String[] gameNameArray;
    public static String gameNumber;

    public static String[] OkuNameArray;
    public static String OkuNumber;
    public static String[] OkuCategoryArray;

    //////////////////////////////////////////////////////////////////////////////


    public static String getUrl() {
        return url;
    }

    public static String[] getMovieNameArray(){
        return movieNameArray;
    }
    public static String[] getMovieDurationArray(){
        return movieDurationArray;
    }
    public static String[] getMovieCastingArray(){
        return movieCastingArray;
    }
    public static String[] getMovieExplanationArray(){
        return movieExplanationArray;
    }
    public static String getMovieNumber(){
        return movieNumber;
    }
    public static String[] getMovieCategoryArray(){
        return movieCategoryArray;
    }

    public static String[] getMusicNameArray() { return musicNameArray; }
    public static String[] getMusicAlbumeArray() { return musicAlbumeArray; }
    public static String[] getMusicArtistArray() { return musicArtistArray; }
    public static String getMusicNumber(){
        return musicNumber;
    }
    public static String[] getMusicCategoryArray(){
        return musicCategoryArray;
    }
    public static String[] getMusicThumbnailId(){
        return musicThumnailId;
    }

    public static String[] getGameNameArray() { return gameNameArray; }
    public static String getGameNumber(){
        return gameNumber;
    }

    public static String[] getOkuNameArray() { return OkuNameArray; }
    public static String getOkuNumber(){
        return OkuNumber;
    }
    public static String[] getOkuCategoryArray(){
        return OkuCategoryArray;
    }
        //////////////////////////////////////////////////////////////////////////////


    public static void setUrl(String n) {
        url = n;
    }

    public static void setMovieNameArray(String[] n){
        movieNameArray = n;
    }
    public static void setMovieDurationArray(String[] n){
        movieDurationArray = n;
    }
    public static void setMovieCastingArray(String[] n){
        movieCastingArray = n;
    }
    public static void setMovieCategoryArray(String[] n){movieCategoryArray = n; }
    public static void setMovieExplanationArray(String[] n){
        movieExplanationArray= n;
    }
    public static void setMovieNumber(String n){movieNumber = n;}

    public static void setMusicNameArray(String[] n){musicNameArray = n;    }
    public static void setMusicAlbumeArray(String[] n){musicAlbumeArray = n;    }
    public static void setMusicArtistArray(String[] n){musicArtistArray = n;    }
    public static void setMusicCategoryArray(String[] n){musicCategoryArray = n;    }
    public static void setMusicNumber(String n){musicNumber = n;}
    public static void setMusicThumnailId(String[] n){musicThumnailId = n;    }

    public static void setGameNameArray(String[] n){gameNameArray = n;    }
    public static void setGameNumber(String n){gameNumber = n;}

    public static void setOkuNameArray(String[] n){OkuNameArray = n;    }
    public static void setOkuNumber(String n){OkuNumber = n;}
    public static void setOkuCategoryArray(String[] n){OkuCategoryArray = n;    }
}
