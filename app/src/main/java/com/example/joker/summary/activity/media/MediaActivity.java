package com.example.joker.summary.activity.media;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.joker.summary.R;

public class MediaActivity extends ActionBarActivity implements View.OnClickListener{
    private Button btnTakePicture;
    private Button btnTakeVideoByMediaRecord;
    private Button btnTakeVideoByFFmpeg;
    private Button btnPlayVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        initContentView();
    }

    private void initContentView(){
        btnTakePicture = (Button)findViewById(R.id.btnTakePicture);
        btnTakeVideoByFFmpeg = (Button)findViewById(R.id.btnTakeVideoByFFmpeg);
        btnTakeVideoByMediaRecord = (Button)findViewById(R.id.btnTakeVideoByMediaRecord);
        btnPlayVideo = (Button)findViewById(R.id.btnPlayVideo);

        btnTakePicture.setOnClickListener(this);
        btnTakeVideoByFFmpeg.setOnClickListener(this);
        btnTakeVideoByMediaRecord.setOnClickListener(this);
        btnPlayVideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.btnTakePicture:
//                intent = new Intent(this,TakePictureActivity.class);
//                startActivity(intent);
                break;
            case R.id.btnTakeVideoByFFmpeg:
                intent = new Intent(this,FFmpegActivity.class);
                startActivity(intent);
                break;
            case R.id.btnTakeVideoByMediaRecord:
                intent = new Intent(this,MediaRecorderActivity.class);
                startActivity(intent);
                break;
            case R.id.btnPlayVideo:
                break;
            default:
                break;
        }
    }
}
