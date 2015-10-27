package com.example.joker.summary.util;

/**
 * Created by joker on 15/10/27.
 */
public class CommonUtil {

    //获取函数名
    public static String getMethodName(int stackIndex) {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[stackIndex];
        String methodName = e.getMethodName();
        return methodName;
    }

}
