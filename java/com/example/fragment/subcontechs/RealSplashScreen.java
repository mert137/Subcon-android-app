package com.example.fragment.subcontechs;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;


public class RealSplashScreen extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //////////Buradaki if-else ile full screen yaptık activity'i ancak manifest dosyasında full screen olmayan bir tema kullanılmalı.
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
        //////////////////////////////////////////////////////

        //Sets the layout of welcome_screen.xml
        setContentView(R.layout.splashscreen);
        Thread timer = new Thread() {
            public void run() {
                try {
                    //Display for 3 seconds
                    sleep(1700);
                } catch (InterruptedException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                } finally {
                    //Goes to Activity  StartingPoint.java(STARTINGPOINT)
                    Intent openstartingpoint = new Intent(getApplicationContext(),SplashScreen.class);
                    startActivity(openstartingpoint);
                }
            }
        };
        timer.start();
    }


    //Destroy Welcome_screen.java after it goes to next activity
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();

    }

}