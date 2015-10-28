package com.example.joker.summary.util;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by joker on 15/10/27.
 */
public class CommonUtil {
    private static final String TAG = "CommonUtil";

    //获取函数名
    public static String getMethodName(int stackIndex) {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[stackIndex];
        String methodName = e.getMethodName();
        return methodName;
    }

    //合并两个录音文件
    public static void mergeMediaFile(String dstPath, String srcPath){
        File dstFile = new File(dstPath);
        File srcFile = new File(srcPath);
        Log.i(TAG,"dstFile len:" + dstFile.length() + ",srcFile len:" + srcFile.length());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(dstFile);
            FileInputStream fileInputStream = new FileInputStream(srcFile);
            byte[] inputBytes = new byte[fileInputStream.available()];
            int inputLength = inputBytes.length;
            while(fileInputStream.read(inputBytes)!=-1){
                fileOutputStream.write(inputBytes, 6, inputLength-6);
            }
            fileOutputStream.flush();
            fileInputStream.close();
            fileOutputStream.close();
            Log.i(TAG, "merged dstFile len:" + dstFile.length());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
