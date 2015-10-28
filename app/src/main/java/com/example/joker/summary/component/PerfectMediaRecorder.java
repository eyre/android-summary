package com.example.joker.summary.component;

import android.media.MediaRecorder;

/**
 * Created by joker on 15/10/28.
 */
public class PerfectMediaRecorder extends MediaRecorder{

    public void pause(){
        stop();
        release();
    }

}
