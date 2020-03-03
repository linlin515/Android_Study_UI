package com.lrh.asd.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lrh.asd.base.BaseActivity;
import com.lrh.asd.R;

/**
 * 示例界面
 * 自定义ViewGroup_标题栏的悬浮吸顶渐变效果
 *
 * @author lrh
 * @date 2020.03.03
 */
public class TopAdsorActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_adcor);
        hideToolBar();
    }
}
