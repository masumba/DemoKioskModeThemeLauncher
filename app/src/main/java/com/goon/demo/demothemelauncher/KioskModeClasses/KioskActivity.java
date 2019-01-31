package com.goon.demo.demothemelauncher.KioskModeClasses;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class KioskActivity extends AppCompatActivity {
    private static final String HANDLE_BACK = "HANDLE_BACK";
    private boolean handleBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleBack = getIntent().getBooleanExtra(HANDLE_BACK,true);
    }

    @Override
    public void onBackPressed() {
        if (!handleBack){
            super.onBackPressed();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        ActivityManager activityManager = (ActivityManager)getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<String> whiteListed = new ArrayList<>();
        whiteListed.add(getPackageName());
        if (!isWhiteListed(activityManager,whiteListed)){
            activityManager.moveTaskToFront(getTaskId(),0);
        }
    }

    /*onkeydown*/

    public static void startKioskActivity(Context context, Class kioskActivityClass){
        runKioskActivity(context,kioskActivityClass,true);
    }

    public static void stopKioskActivity(Context context, Class kioskActivityClass){
        runKioskActivity(context,kioskActivityClass,false);
    }

    public static void runKioskActivity(Context context,Class kioskActivityClass, boolean handleBack){
        ComponentName componentName = new ComponentName(context.getPackageName(),kioskActivityClass.getName());
        context.getPackageManager().setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
        Intent intent = new Intent(context,kioskActivityClass);
        intent.putExtra(HANDLE_BACK,handleBack);
        if (!(context instanceof Activity)){
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    public static boolean isWhiteListed(ActivityManager activityManager,ArrayList<String> whiteList){
        List<ActivityManager.RunningTaskInfo> taskInfos = activityManager.getRunningTasks(1);
        ComponentName componentName = taskInfos.get(0).topActivity;

        for (String item:whiteList){
            if (item.contains(componentName.getPackageName())){
                return true;
            }
        }

        return false;
    }

}
