package com.example.fragment.subcontechs;

import android.content.Context;
import android.view.KeyEvent;
import android.widget.MediaController;

public class MusicController extends MediaController {

    public MusicController(Context c){
        super(c);
    }

    public void hide(){}

    //Handle BACK button
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
            super.hide();//Hide mediaController
            //finish();//Close this activity
            return true;//If press Back button, finish here
        }
        //If not Back button, other button (volume) work as usual.
        return super.dispatchKeyEvent(event);
    }

}