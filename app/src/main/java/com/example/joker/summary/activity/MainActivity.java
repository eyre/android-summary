package com.example.joker.summary.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.joker.summary.R;
import com.example.joker.summary.adapter.ExampleExpandableListAdapter;
import com.example.joker.summary.listener.OnExampleItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ExpandableListView elvSummary;
    private List<String> titleList;
    private List<List<String>> itemTitleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleList = new ArrayList<>();
        itemTitleList = new ArrayList<>();
        initContentView();
    }

    private void initContentView(){
        elvSummary = (ExpandableListView)findViewById(R.id.elvSummary);

        titleList.add("Environment");
        titleList.add("Media");
        titleList.add("Util");

        List envList = new ArrayList();
        envList.add("Directory");
        itemTitleList.add(envList);
        List mediaList = new ArrayList();
        mediaList.add("MediaRecorder");
        itemTitleList.add(mediaList);
        List utilList = new ArrayList();
        utilList.add("TcpUtil");
        itemTitleList.add(utilList);

        ExpandableListAdapter summaryListViewAdapter = new ExampleExpandableListAdapter(
                this, titleList, itemTitleList);
        elvSummary.setAdapter(summaryListViewAdapter);
        OnExampleItemClickListener onExampleItemClickListener = new OnExampleItemClickListener(
                this);

        elvSummary.setOnChildClickListener(onExampleItemClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
