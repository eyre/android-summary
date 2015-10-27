package com.example.joker.summary.activity.media;

import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.TextureView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.joker.summary.R;
import com.example.joker.summary.constants.SobrrConstants;

public class TextureviewActivity extends ActionBarActivity implements TextureView.SurfaceTextureListener{
    private TextureView textureView;
    private MediaPlayer mediaPlayer;
    private ImageView shelterLayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textureview);

        textureView = (TextureView)findViewById(R.id.textureView);
        textureView.setSurfaceTextureListener(this);
        shelterLayer = (ImageView)findViewById(R.id.shelterLayer);
        ViewGroup.LayoutParams layoutParams = textureView.getLayoutParams();
        layoutParams.width = SobrrConstants.getDeviceWidth();
        layoutParams.height = layoutParams.width*4/3;
        textureView.setLayoutParams(layoutParams);

        RelativeLayout.LayoutParams shelterLayerParams = (RelativeLayout.LayoutParams)shelterLayer.getLayoutParams();
        shelterLayerParams.setMargins(0,layoutParams.width,0,0);
        shelterLayer.setLayoutParams(shelterLayerParams);
    }

    private void prepare(Surface surface) {
        try {
            String strVideoPath = Environment.getExternalStorageDirectory() + "/test_media_recorder.mp4";
//            String strVideoPath = getFilesDir().getAbsolutePath() + "/test_media_recorder.mp4";
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.reset();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            // 设置需要播放的视频
            mediaPlayer.setDataSource(strVideoPath);
            // 把视频画面输出到Surface
            mediaPlayer.setSurface(surface);
            mediaPlayer.setLooping(false);
            mediaPlayer.prepare();
            mediaPlayer.seekTo(0);
        } catch (Exception e) {
        }
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        prepare(new Surface(surfaceTexture));
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        mediaPlayer.release();
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }
}
