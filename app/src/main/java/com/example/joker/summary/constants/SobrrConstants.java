package com.example.joker.summary.constants;


import android.content.res.Resources;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Created by albertzheng on 7/21/14.
 */
public class SobrrConstants {

    public static final String LOCALE_LANGUAGE = "en";

    private static int deviceHeight = 0;
    private static int deviceWidth = 0;
    private static int actionBarHeight = 0;

    public static final long MS_IN_24HR = 86400000;
    public static final long MS_IN_HR = 3600000;
    public static final long S_IN_24HR = 60*60*24;
    public static final long S_IN_HR = 60*60;
    public static final long S_IN_MIN = 60;

    public static final int VIBING_PAGE = 0;
    public static final int CREW_PAGE = 1;
    public static final int CHAT_PAGE = 2;
    public static final int PROFILE_PAGE = 3;

    public static final int COMPOSE_VIBE_TEXT_LIMIT = 140;
    private static final int MAX_USERNAME_LENGTH = 17;

    public static final int MY_QR_CODE_CARD_WIDTH = (int)(SobrrConstants.getDeviceWidth() * 0.85);
    public static final int MY_QR_CODE_CARD_HEIGHT = (int)(SobrrConstants.getDeviceHeight() * 0.6);

    private static NumberFormat locationNumberFormat;

    public static Long getNow() {
        return System.currentTimeMillis()/1000L;
    }

    public static boolean nowIsWithin24HrsAfterDate(long dateLong) {
        Date now = new Date();
        if ((now.getTime()/1000L - dateLong) < S_IN_24HR) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isTimeExpiring(long time) {
        long expirationDateLong = time + S_IN_24HR;

        long interval = expirationDateLong - new Date().getTime() / 1000L;
        return interval <= S_IN_HR;
    }

    public static int getJpegCompression() {
        return 80;
    }

    public static String UUID() {
        return UUID.randomUUID().toString().toUpperCase();
    }

    public static String getDisplayShortForm(int number) {
        if(number < 1000) {
            return String.format("%d", number);
        } else if(number < 1000000) {
            return String.format("%.1fk", number / 1000.0);
        } else {
            return String.format("%.1fM", number / 1000000.0);
        }
    }

    public static int getDeviceWidth() {
        if (deviceWidth == 0) {
            deviceWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        }
        return deviceWidth;
    }

    public static int getDeviceHeight() {
        if (deviceHeight == 0){
            deviceHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        }
        return deviceHeight;
    }

    public static float getPercentageDeviceWidth(float percent) {
        return getDeviceWidth() * percent;
    }

    public static float getPercentageDeviceHeight(float percent) {
        return getDeviceHeight() * percent;
    }

    public static float getDevicePixelDensity() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

    public static float dpToPixels(float dp) {
        return getDevicePixelDensity() * dp;
    }

    public static int getARandomInt(int upperBound) {
        return new Random().nextInt(upperBound);
    }

    public static NumberFormat getLocationNumberFormat() {
        if (locationNumberFormat == null) {
            locationNumberFormat = NumberFormat.getNumberInstance();
            locationNumberFormat.setMaximumFractionDigits(2);
        }
        return locationNumberFormat;
    }

    static int NUMBER_OF_AVATARS = 6;
//    public static String getDefaultAvatarUrl(short gender) {
//        String genderStr;
//        if (gender == Gender.Male) {
//            genderStr = "male";
//        } else {
//            genderStr = "female";
//        }
//        String picName = "avatar-" + genderStr + "-1";
//        return NetworkConstants.QINIU_DEFAULT_RESOURCE_PREFIX + picName + ".png";
//    }

//    private static final String validString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890~_.-!";
    private static final String numberString = "0123456789";

    private static boolean doesStringHaveCorrectCharacter(String username) {
        String regex = "^[a-zA-Z0-9~_.!\\-\\u4e00-\\u9fa5]+$";
        Pattern p = Pattern.compile(regex);
        return p.matcher(username).find();
//        return StringUtils.containsOnly(username, validString);
    }

//    public static void hideKeyBoard(View view) {
//        InputMethodManager imm = (InputMethodManager) SobrrActionUtil.getSobrrAppInstance().getSystemService(SobrrActionUtil.getSobrrAppInstance().INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//    }
//
//    public static void showKeyBoard(View view) {
//        InputMethodManager imm = (InputMethodManager) SobrrActionUtil.getSobrrAppInstance().getSystemService(SobrrActionUtil.getSobrrAppInstance().INPUT_METHOD_SERVICE);
//        imm.showSoftInput(view, 0);
//    }
//
//    public static String getAppVersion() {
//        String packageName = SobrrActionUtil.getSobrrAppInstance().getPackageName();
//        try {
//            return SobrrActionUtil.getSobrrAppInstance().getPackageManager().getPackageInfo(packageName, 0).versionName;
//        } catch (PackageManager.NameNotFoundException e) {
//            return "0.0.0";
//        }
//    }
//
//    public static boolean sdkVersionLowerThanJellyBean(){
//        return DeviceUtil.getSdkVersion() < DeviceUtil.getJellyBeanSdkVersion();
//    }
//
//    public static float getVirtualMenuHeight() {
//        if (ViewConfiguration.get(SobrrActionUtil.getSobrrAppInstance()).hasPermanentMenuKey()) {
//            return 0f;
//        } else {
//            return SobrrActionUtil.getSobrrAppInstance().getResources().getDimensionPixelSize(R.dimen.default_virtual_menu_height);
//        }
//    }
//
//    public static int getActionBarHeight(Activity activity) {
//        if (actionBarHeight == 0) {
//            if (activity != null && activity.getActionBar() != null) {
//                actionBarHeight = activity.getActionBar().getHeight();
//            }
//        }
//        return actionBarHeight;
//    }
//
//    public static int getStatusBarHeight() {
//        int statusBarHeight = (int) dpToPixels(25);
//        try {
//            Class c = Class.forName("com.android.internal.R$dimen");
//            Object obj = c.newInstance();
//            Field field = c.getField("status_bar_height");
//            int x = Integer.parseInt(field.get(obj).toString());
//            statusBarHeight = SobrrActionUtil.getSobrrAppInstance().
//                    getResources().getDimensionPixelSize(x);
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//        return statusBarHeight;
//    }
//
//    public static float getSmartBarHeight() {
//        if (hasSmartBar()) {
//            return SobrrActionUtil.getSobrrAppInstance().getResources().
//                    getDimensionPixelSize(R.dimen.default_virtual_menu_height);
//        } else {
//            return 0f;
//        }
//    }
//
//    /**
//     * This method only used for meizu
//     * @return
//     */
//    private static boolean hasSmartBar() {
//        try {
//            // use Build.hasSmartBar() to judge if it has SmartBar on new meizu phone.
//            Method method = Class.forName("android.os.Build").getMethod("hasSmartBar");
//            return ((Boolean) method.invoke(null)).booleanValue();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        // if can't find Build.hasSmartBar() use Build.DEVICE
//        if (DeviceUtil.isDevice("mx2")) {
//            return true;
//        } else if (DeviceUtil.isDevice("mx") || DeviceUtil.isDevice("m9")) {
//            return false;
//        }
//        return false;
//    }
//
//    public static String getUrlToEncode() {
//        return NetworkConstants.DEFAULT_DOWNLOAD_LINK + "?" + Base64.encodeToString(MemberController.getMe().getRemoteIdString().getBytes(), Base64.DEFAULT);
//    }
}
