package com.example.joker.summary.listener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.joker.summary.activity.DirectoryActivity;
import com.example.joker.summary.activity.TcpActivity;
import com.example.joker.summary.activity.media.MediaActivity;
import com.example.joker.summary.activity.media.MediaRecorderActivity;

public class OnExampleItemClickListener implements
        ExpandableListView.OnChildClickListener {
    private Activity mainActivity;

    public OnExampleItemClickListener(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public boolean onChildClick(ExpandableListView parent, View v,
                                int groupPosition, int childPosition, long id) {
        Intent intent = null;
        boolean result = false;

        if (groupPosition == 0) {
            //Directory
            if (childPosition == 0) {
                intent = new Intent(this.mainActivity, DirectoryActivity.class);
            } else if (childPosition == 1) {
//                intent = new Intent(this.mainActivity, QuickStartVideoExampleActivity.class);
            }
        }
        else if (groupPosition == 1) {
            //Media
            if (childPosition == 0) {
                intent = new Intent(this.mainActivity,
                        MediaActivity.class);
            }
        }
        else if (groupPosition == 2) {
            //Util
            if (childPosition == 0) {
                intent = new Intent(this.mainActivity,
                        TcpActivity.class);
            }
        }
//        } else if (groupPosition == 3) {
//            // audio video play
//            if (childPosition == 0) {
//                intent = new Intent(this.mainActivity,
//                        AudioVideoPlayUseVideoViewListActivity.class);
//            } else if (childPosition == 1) {
//                intent = new Intent(this.mainActivity,
//                        AudioVideoPlayUsePLDPlayerListActivity.class);
//            }
//        } else if (groupPosition == 4) {
//            //image view
//            if (childPosition == 0) {
//                intent = new Intent(this.mainActivity, SimpleImageViewActivity.class);
//            }
//        } else if (groupPosition == 5) {
//            //system capture
//            if (childPosition == 0) {
//                intent = new Intent(this.mainActivity, CaptureImageActivity.class);
//            } else if (childPosition == 1) {
//                intent = new Intent(this.mainActivity, CaptureVideoActivity.class);
//            }
//        }
        if (intent != null) {
            this.mainActivity.startActivity(intent);
            result = true;
        }
        return result;

    }

}
