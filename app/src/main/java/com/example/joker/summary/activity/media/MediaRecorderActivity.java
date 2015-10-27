package com.example.joker.summary.activity.media;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.joker.summary.R;
import com.example.joker.summary.constants.SobrrConstants;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MediaRecorderActivity extends ActionBarActivity implements SurfaceHolder.Callback,View.OnClickListener,
        MediaRecorder.OnInfoListener,MediaRecorder.OnErrorListener{
    private static final String TAG = "MediaRecorderActivity";

    MediaRecorder recorder;
    SurfaceView mCameraView;
    SurfaceHolder mHolder;
    Camera mCamera;
    Button btnControl;
//    Button btnPlay;
    AudioManager audioManager;

    private static final int CAMERA_FACING_BACK = android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK;
    private static final int CAMERA_FACING_FRONT = android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT;
    private int mCurrentCamera;
    private boolean recordState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_recorder);

        initContentView();
        mHolder = mCameraView.getHolder();
        mHolder.addCallback(this);

        mCurrentCamera = CAMERA_FACING_BACK;

        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        recordState = false;
        btnControl.setText("start");
    }

    private void initContentView(){
        mCameraView = (SurfaceView)findViewById(R.id.cameraView);
        btnControl = (Button)findViewById(R.id.btnControl);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mCameraView.getLayoutParams();
        params.height = SobrrConstants.getDeviceWidth()*4/3;
        mCameraView.setLayoutParams(params);

        btnControl.setOnClickListener(this);
    }

    private void initMediaRecorder(){
        String strVideoPath = Environment.getExternalStorageDirectory() + "/test_media_recorder.mp4";
//        String strVideoPath = getFilesDir().getAbsolutePath() + "/test_media_recorder.mp4";
        File fileVideoPath = new File(strVideoPath);
        if(fileVideoPath.exists())
            fileVideoPath.delete();
        try {
            recorder = new MediaRecorder();
            recorder.setOnErrorListener(this);
            recorder.setOnInfoListener(this);

            recorder.setCamera(mCamera);
            recorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
            recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);

            CamcorderProfile profile = CamcorderProfile.get(CamcorderProfile.QUALITY_480P);
            //视频相关设置
            recorder.setVideoSize(mCamera.getParameters().getPreviewSize().width, mCamera.getParameters().getPreviewSize().height);
//            recorder.setVideoSize(640, 480);
            recorder.setVideoEncodingBitRate(profile.videoBitRate / 2);
//            recorder.setVideoEncodingBitRate(2 * 1024 * 1024);
            recorder.setVideoFrameRate(30);
            //音频相关设置
            recorder.setAudioChannels(1);
            recorder.setAudioEncodingBitRate(44100);
            recorder.setAudioSamplingRate(8*1024);

//            recorder.setVideoEncodingBitRate(profile.videoBitRate / 2);
//            recorder.setVideoFrameRate(profile.videoFrameRate);
//            recorder.setVideoSize(profile.videoFrameWidth,
//                    profile.videoFrameHeight);
//
//            recorder.setAudioChannels(profile.audioChannels);
//            recorder.setAudioEncodingBitRate(profile.audioBitRate);
//            recorder.setAudioSamplingRate(profile.audioSampleRate);

            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            recorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
            recorder.setMaxDuration(15000);
            //后摄像头拍摄旋转90度
            recorder.setOrientationHint(90);

            recorder.setPreviewDisplay(mHolder.getSurface());
            recorder.setOutputFile(strVideoPath);

            recorder.prepare();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_media_recorder, menu);
        return true;
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
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            mCamera = getCameraInstance();
            initCamera();
            mCamera.setPreviewDisplay(surfaceHolder);
        } catch (IOException e) {
            e.printStackTrace();
            clearCamera();
        }
        initMediaRecorder();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
//        updateParameter(mParameters);
//
//        mCamera.setParameters(mParameters);
//        mCamera.startPreview();
//        Camera.Parameters parameters = mCamera.getParameters();
//        parameters.setPreviewSize(640,480);
//        mCamera.setParameters(parameters);

        mCamera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mCamera.stopPreview();
        clearRecorder();
        clearCamera();
    }

    private void initCamera() {
        if (mCamera != null) {

            Camera.Parameters parameters = mCamera.getParameters();
            float ratio = 4/3.0f;
            List<Camera.Size> mSupportedPreviewSizes = parameters.getSupportedPreviewSizes();
            Camera.Size ratioSize = null;
            for(Camera.Size size:mSupportedPreviewSizes){
                if(size.width == size.height * ratio){
                    if(ratioSize==null || ratioSize.width < size.width){
                        ratioSize = size;
                        Log.i(TAG, "width:" + size.width + ",height:" + size.height);
                    }
                }
            }
            Log.i(TAG,"ratio width:"+ratioSize.width+",height:"+ratioSize.height);
            parameters.setPreviewSize(640, 480);

            List<String> focusModes = parameters.getSupportedFocusModes();
            if (focusModes.contains("continuous-video")) {
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
            }

            mCamera.setParameters(parameters);
        }
    }

    private Camera getCameraInstance() {
        if (mCamera != null) {
            return mCamera;
        } else {
            try {
                mCamera = Camera.open(mCurrentCamera);
                if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                    mCamera.setDisplayOrientation(90);
                } else {
                    mCamera.setDisplayOrientation(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mCamera;
        }
    }
    private void clearCamera() {
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }
    private void clearRecorder() {
        if (recorder != null) {
            recorder.release();
            recorder = null;
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnControl){
//            int oldStreamVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            if(!recordState) {
//                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,0,AudioManager.FLAG_PLAY_SOUND);

                mCamera.unlock();
                recorder.start();

                btnControl.setText("pause");
            }else{
                recorder.stop();
//                mCamera.reconnect();

//                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,oldStreamVolume, AudioManager.FLAG_PLAY_SOUND);
                btnControl.setText("start");
                Intent intent = new Intent(this,TextureviewActivity.class);
                startActivity(intent);
            }
            recordState = !recordState;
        }
    }

    @Override
    public void onError(MediaRecorder mediaRecorder, int i, int i1) {
        Log.e("mediaRecorder","error type:"+i+",extra:"+i1);
    }

    @Override
    public void onInfo(MediaRecorder mediaRecorder, int i, int i1) {
        Log.e("mediaRecorder","info type:"+i+",extra:"+i1);
    }
}
