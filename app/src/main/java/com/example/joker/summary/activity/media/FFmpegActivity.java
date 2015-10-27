package com.example.joker.summary.activity.media;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.widget.Button;

import com.example.joker.summary.R;
import com.example.joker.summary.util.CommonUtil;

import java.io.IOException;
import java.util.List;

public class FFmpegActivity extends ActionBarActivity implements TextureView.SurfaceTextureListener{
    private static final String TAG = "TextureSurfaceFFmpeg";

    private Button btnUseSurfaceView;
    private Button btnUseTextureView;
    private TextureView textureView;

    Camera mCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ffmpeg);

        initContentView();
    }

    private void initContentView(){
        btnUseSurfaceView = (Button)findViewById(R.id.btnUseSurfaceView);
        btnUseTextureView = (Button)findViewById(R.id.btnUseTextureView);
        textureView = (TextureView)findViewById(R.id.textureView);

        textureView.setSurfaceTextureListener(this);
    }

    private void initCamera(float previewRate) {
        if (mCamera != null) {

            Camera.Parameters mParams = mCamera.getParameters();
//            mParams.setPictureFormat(PixelFormat.JPEG);//设置拍照后存储的图片格式
//          CamParaUtil.getInstance().printSupportPictureSize(mParams);
//          CamParaUtil.getInstance().printSupportPreviewSize(mParams);
            //设置PreviewSize和PictureSize
//            Camera.Size pictureSize = CamParaUtil.getInstance().getPropPictureSize(
//                    mParams.getSupportedPictureSizes(), previewRate, 800);
//            mParams.setPictureSize(pictureSize.width, pictureSize.height);
//            Size previewSize = CamParaUtil.getInstance().getPropPreviewSize(
//                    mParams.getSupportedPreviewSizes(), previewRate, 800);
            mParams.setPreviewSize(640, 480);

            mCamera.setDisplayOrientation(90);

//          CamParaUtil.getInstance().printSupportFocusMode(mParams);
            List<String> focusModes = mParams.getSupportedFocusModes();
            if (focusModes.contains("continuous-video")) {
                mParams.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
            }
            mCamera.setParameters(mParams);
//            mCamera.startPreview();//开启预览

//            isPreviewing = true;
//            mPreviwRate = previewRate;

            mParams = mCamera.getParameters(); //重新get一次
            Log.i(TAG, "最终设置:PreviewSize--With = " + mParams.getPreviewSize().width
                    + "Height = " + mParams.getPreviewSize().height);
            Log.i(TAG, "最终设置:PictureSize--With = " + mParams.getPictureSize().width
                    + "Height = " + mParams.getPictureSize().height);
        }
    }

        @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        Log.i(TAG, CommonUtil.getMethodName(3));
        mCamera = Camera.open();
        initCamera(0);
        try {
            mCamera.setPreviewTexture(surfaceTexture);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {
        Log.i(TAG, CommonUtil.getMethodName(3));
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        Log.i(TAG, CommonUtil.getMethodName(3));
        mCamera.stopPreview();
        mCamera.release();
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

        Log.i(TAG, CommonUtil.getMethodName(3));
    }
}
