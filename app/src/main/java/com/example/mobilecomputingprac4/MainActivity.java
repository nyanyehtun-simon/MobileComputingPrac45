package com.example.mobilecomputingprac4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SettingFragment.OnSpinnerValueChangedListener {

    MainFragment mainFragment;
    int rotation;
    int currentSelectedSpinnerValue = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            MainFragment mainFragment = new MainFragment();

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mainFragment).commit();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Display display = ((WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        rotation = display.getRotation();

        System.out.println("Rotation: " + rotation);

        if (rotation == 0) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.main_menu, menu);
            return true;
        }

        return false;

    }

    public void activateSettingFragment() {
        SettingFragment settingFragment = new SettingFragment();
        Bundle args = new Bundle();
//        args.putInt(SettingFragment);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, settingFragment);
        transaction.addToBackStack("activateSettingScreen");

        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting:
                // swap with another fragment
                activateSettingFragment();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSpinnerValueChanged(int value) {
        System.out.println("this is in main activity");
        System.out.println(value);

        currentSelectedSpinnerValue = value;

        mainFragment.numOfQuestions = value;
        if (rotation != 0) {
            mainFragment.populateUserChooseableOptions();
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if(fragment instanceof SettingFragment) {
            ((SettingFragment) fragment).setOnSpinnerValueChangedListener(this);
        } else if (fragment instanceof  MainFragment) {
            mainFragment = (MainFragment) fragment;

            System.out.println("Main Fragment gets attached again");

        }
    }

}
