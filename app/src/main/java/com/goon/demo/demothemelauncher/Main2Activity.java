package com.goon.demo.demothemelauncher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.goon.demo.demothemelauncher.KioskModeClasses.KioskActivity;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        KioskActivity.startKioskActivity(Main2Activity.this,ApplicationMainActivity.class);
    }
}
