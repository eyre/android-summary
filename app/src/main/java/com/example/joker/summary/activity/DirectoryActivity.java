package com.example.joker.summary.activity;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.joker.summary.R;

public class DirectoryActivity extends ActionBarActivity {
    private TextView tvDirectory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);

        initContentView();
    }

    public void initContentView() {
        tvDirectory = (TextView) findViewById(R.id.tvDirectory);

        String dataDirectory = Environment.getDataDirectory().getAbsolutePath();
        String downloadCacheDirectory = Environment.getDownloadCacheDirectory().getAbsolutePath();
        String externalStorageDirectory = Environment.getExternalStorageDirectory().getAbsolutePath();
        String externalStoragePublicPicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        String externalStoragePublicMoviesDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getAbsolutePath();
        String externalStorageState = Environment.getExternalStorageState();
        String rootDirectory = Environment.getRootDirectory().getAbsolutePath();
        String fileDir = getFilesDir().getAbsolutePath();
        String cacheDir = getCacheDir().getAbsolutePath();
        String externalFilesDir;
        try {
             externalFilesDir = getExternalFilesDir(null).getAbsolutePath();
        }catch (Exception e){
             externalFilesDir = "not exists";
        }

        String text = "dataDirectory:" + dataDirectory + "\n"
                + "downloadCacheDirectory:" + downloadCacheDirectory + "\n"
                + "externalStorageDirectory:" + externalStorageDirectory + "\n"
                + "externalStoragePublicPicDirectory:" + externalStoragePublicPicDirectory + "\n"
                + "externalStoragePublicMoviesDirectory:" + externalStoragePublicMoviesDirectory + "\n"
                + "externalStorageState:" + externalStorageState + "\n"
                + "rootDirectory:" + rootDirectory + "\n"
                + "fileDir:" + fileDir + "\n"
                + "cacheDir:" + cacheDir + "\n"
                + "externalFilesDir:" + externalFilesDir + "\n";
        tvDirectory.setText(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_directory, menu);
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
}
