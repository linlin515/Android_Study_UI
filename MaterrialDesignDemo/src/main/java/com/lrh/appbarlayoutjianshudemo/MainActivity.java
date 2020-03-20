package com.lrh.appbarlayoutjianshudemo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.lrh.appbarlayoutjianshudemo.base.BaseActivity;

/**
 * @author lrh
 */
public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("首页");
    }


    //创建菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //菜单选项
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        switch (i) {
            //
            case R.id.jianshu:
                Toast.makeText(this, "简书", Toast.LENGTH_SHORT).show();
                gotoOtherActivity(AppBarJianShuActivity.class);
                break;
            //
            case R.id.two:
                Toast.makeText(this, "two", Toast.LENGTH_SHORT).show();
                break;
            //
            case R.id.three:
                Toast.makeText(this, "three", Toast.LENGTH_SHORT).show();
                break;
            //
            case R.id.four:
                Toast.makeText(this, "four", Toast.LENGTH_SHORT).show();
                break;
            //
            case R.id.five:
                Toast.makeText(this, "five", Toast.LENGTH_SHORT).show();
                break;
            //
            case R.id.six:
                Toast.makeText(this, "six", Toast.LENGTH_SHORT).show();
                break;
            //
            case R.id.seven:
                Toast.makeText(this, "seven", Toast.LENGTH_SHORT).show();
                break;
            //
            case R.id.eight:
                Toast.makeText(this, "eight", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;

        }
        return true;
    }
}
