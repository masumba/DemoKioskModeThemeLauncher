package com.goon.demo.demothemelauncher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.goon.demo.demothemelauncher.KioskModeClasses.KioskActivity;
import com.goon.demo.demothemelauncher.KioskModeClasses.RootKioskActivity;

public class ApplicationMainActivity extends RootKioskActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_main);
    }

    public void stopKioskLock(View view) {
        Toast.makeText(ApplicationMainActivity.this,"Clicked",Toast.LENGTH_SHORT).show();

        KioskActivity.stopKioskActivity(ApplicationMainActivity.this,ApplicationMainActivity.class);
        Intent intent = new Intent(ApplicationMainActivity.this,MainActivity.class);
        startActivity(intent);

    }

    public void startKioskLock(View view) {
        KioskActivity.startKioskActivity(ApplicationMainActivity.this,ApplicationMainActivity.class);
    }
}
