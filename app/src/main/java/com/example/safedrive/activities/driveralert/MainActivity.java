package com.example.safedrive.activities.driveralert;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.multidex.MultiDex;

import com.example.safedrive.R;


public class MainActivity extends FragmentActivity {
    FrameLayout frame;
    Button agree,disagree;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        frame = (FrameLayout)findViewById(R.id.frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,new monitor_menu()).commit();

        Toast.makeText(getApplicationContext(),"Drowziness detection start", Toast.LENGTH_SHORT).show();


    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }






}
