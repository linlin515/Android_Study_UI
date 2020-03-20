package com.lrh.appbarlayoutjianshudemo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.adapter.CBPageAdapter;
import com.bigkoo.convenientbanner.adapter.CBViewHolderCreator;
import com.google.android.material.appbar.AppBarLayout;
import com.lrh.appbarlayoutjianshudemo.base.BaseActivity;
import com.lrh.appbarlayoutjianshudemo.bean.JsEntry;
import com.lrh.appbarlayoutjianshudemo.utils.StatusBarUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 模仿简书首页的demo
 *
 * @author lrh
 */
public class AppBarJianShuActivity extends BaseActivity {
    private static final String TAG = "AppBarJianShuActivity";

    @BindView(R.id.banner)
    ConvenientBanner banner;
    @BindView(R.id.label_one)
    TextView labelOne;
    @BindView(R.id.label_two)
    TextView labelTwo;
    @BindView(R.id.label_three)
    TextView labelThree;
    @BindView(R.id.label_four)
    TextView labelFour;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;

    private static final String DRAWABLE_HEAD = "drawable://";

    private String[] imagesNet = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
    };

    private String[] imagesID = {DRAWABLE_HEAD + R.drawable.meizhi, DRAWABLE_HEAD + R.drawable.md, DRAWABLE_HEAD + R.drawable.meizhi, DRAWABLE_HEAD + R.drawable.md};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar_jianshu);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        MyAdapter myAdapter = new MyAdapter();
        recycleView.setAdapter(myAdapter);
        myAdapter.setData(mockData());
        myAdapter.notifyDataSetChanged();

        banner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, Arrays.asList(imagesID));

    }

    @Override
    protected void onResume() {
        super.onResume();
        banner.startTurning(2000);// 2s 换一张
    }

    @Override
    protected void onPause() {
        super.onPause();
        banner.stopTurning();
    }

    /**
     * 模拟首页数据
     *
     * @return
     */
    private List<JsEntry> mockData() {
        List<JsEntry> data = new ArrayList<>();
        JsEntry jsEntry = new JsEntry();
        jsEntry.comment = 50;
        jsEntry.award = 3;
        jsEntry.like = 460;
        jsEntry.seek = 12504;
        jsEntry.time = "15小时前";
        jsEntry.title = "这些情商的技巧，你是不是都掌握了？";
        jsEntry.authorName = "JayChou";
        jsEntry.label = "心理";
        jsEntry.cover = "http://upload-images.jianshu.io/upload_images/2785318-5306a632b46a8c27.jpg?imageMogr2/auto-orient/strip|imageView2/2/w/1020/q/80";
        JsEntry jsEntry2 = new JsEntry();
        jsEntry2.comment = 150;
        jsEntry2.award = 33;
        jsEntry2.like = 1460;
        jsEntry2.seek = 170444;
        jsEntry2.time = "10小时前";
        jsEntry2.title = "除了阴谋，《锦绣未央》里还有哪些温情？";
        jsEntry2.authorName = "菇凉似梦";
        jsEntry2.label = "文化.艺术";
        jsEntry2.cover = "http://upload-images.jianshu.io/upload_images/2881988-b217e714eb05f88e.jpg?imageMogr2/auto-orient/strip|imageView2/2/w/1020/q/80";
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                data.add(jsEntry);
            } else {
                data.add(jsEntry2);
            }
        }
        return data;
    }

    @Override
    protected boolean hideActionBar() {
        return true;
    }


    public static class MyAdapter extends RecyclerView.Adapter {
        private List<JsEntry> mData;

        public void setData(List<JsEntry> data) {
            mData = data;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.jianshu_label_item, null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder viewHolder = (MyViewHolder) holder;
            JsEntry jsEntry = mData.get(position);
            viewHolder.title.setText(jsEntry.title);
            viewHolder.name.setText(jsEntry.authorName);
            viewHolder.label.setText(jsEntry.label);
            viewHolder.time.setText(jsEntry.time);
            ImageLoader.getInstance().displayImage(jsEntry.cover, viewHolder.cover);
            viewHolder.comment.setText(String.format(viewHolder.comment.getContext().getResources().getString(R.string.js_comment), jsEntry.seek, jsEntry.comment, jsEntry.like, jsEntry.award));
        }

        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.size();
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView time;
        private TextView comment;
        private TextView label;
        private TextView name;
        private ImageView cover;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_content);
            time = (TextView) itemView.findViewById(R.id.publish_time);
            comment = (TextView) itemView.findViewById(R.id.js_comment);
            label = (TextView) itemView.findViewById(R.id.js_label);
            name = (TextView) itemView.findViewById(R.id.author_name);
            cover = (ImageView) itemView.findViewById(R.id.cover);
        }
    }

    public static class NetworkImageHolderView implements CBPageAdapter.Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String uri) {
            Log.d(TAG, "UpdateUI: uri = " + uri);
            ImageLoader.getInstance().displayImage(uri, imageView);
        }


    }
}
