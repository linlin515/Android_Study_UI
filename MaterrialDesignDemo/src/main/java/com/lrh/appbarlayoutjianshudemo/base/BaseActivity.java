package com.lrh.appbarlayoutjianshudemo.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author lrh
 */
public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (hideActionBar()) {
            hideToolBar();
        }
    }

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
    private void hideToolBar() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
    }

    protected boolean hideActionBar() {
        return false;
    }

}
