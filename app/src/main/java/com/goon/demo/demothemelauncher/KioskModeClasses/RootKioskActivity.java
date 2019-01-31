package com.goon.demo.demothemelauncher.KioskModeClasses;

import android.content.ComponentName;
import android.content.pm.PackageManager;

public class RootKioskActivity extends KioskActivity {
    @Override
    protected void onDestroy() {

        ComponentName componentName = new ComponentName(getPackageName(), this.getClass().getName());
        getPackageManager().setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
        super.onDestroy();
    }
}
