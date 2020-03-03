package com.lrh.asd.base;

import android.content.Intent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {


    /**
     * activity跳转
     *
     * @param otherActivity
     */
    protected void gotoOtherActivity(Class otherActivity) {
        Intent intent = new Intent(this, otherActivity);
        startActivity(intent);
    }

    /**
     * 隐藏toolBar
     */
    protected void hideToolBar() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
    }
}
