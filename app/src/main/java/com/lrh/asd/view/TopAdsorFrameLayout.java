package com.lrh.asd.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

import com.lrh.asd.R;

/**
 * 自定义ViewGroup_标题栏的悬浮吸顶渐变效果
 * 参考：https://www.jianshu.com/p/3c2ce3c27147
 * @author lrh
 * @date 2020.03.03
 */
public class TopAdsorFrameLayout extends FrameLayout {

    private Context mContext;

    public TopAdsorFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public TopAdsorFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopAdsorFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    private void initView() {

        /**
         * 获取第一个childView转化为NestedScrollView
         */
        NestedScrollView mSestedScrollView = (NestedScrollView) getChildAt(0);
        /**
         * 获取第二个childView,也就是我们的Toolbar部分
         */
        final ViewGroup mViewToolbarRoot = (ViewGroup) getChildAt(1);
        final ViewGroup mViewChildRoot = (ViewGroup) mViewToolbarRoot.getChildAt(0);
        final TextView mTvTitle = mViewChildRoot.findViewById(R.id.tv_title);
        final TextView mTvScan = mViewChildRoot.findViewById(R.id.tv_scan);
        final TextView mTvInvitation = mViewChildRoot.findViewById(R.id.tv_invitation);

        /**
         * 获取整个状态栏的高度，用来控制一些其他变量
         */
        final int mHeaderHeight = mViewToolbarRoot.getMeasuredHeight();

        /**
         * 设置NestedScrollView的paddingTop值防止被mViewToolbarRoot遮盖
         */
        mSestedScrollView.setPadding(getPaddingLeft(), getPaddingTop() + mHeaderHeight, getPaddingRight(), getPaddingBottom());

        final LayoutParams paramsChildRoot = (LayoutParams) mViewChildRoot.getLayoutParams();
        final LinearLayout.LayoutParams paramsChildTv = (LinearLayout.LayoutParams) mTvInvitation.getLayoutParams();

        final int textMaginTop = (int) mContext.getResources().getDimension(R.dimen.text_right_marginTop);//文字距离图标的距离
        final int topMaginHeight = (int) mContext.getResources().getDimension(R.dimen.child_root_marginTop);//标题距离顶部距离
        final float textSize = (int) mContext.getResources().getDimension(R.dimen.text_size_30sp);//标题文字大小

        if (topMaginHeight <= 0) {
            return;
        }

        //梯度值，用来调整相关的速度
        final float offset = 1.7f;

        mSestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY <= topMaginHeight) {

                    float scale = (float) scrollY / topMaginHeight;

                    /**
                     * 控制右边文字的透明度
                     */
                    mTvScan.setAlpha(1 - offset * scale);
                    mTvInvitation.setAlpha(1 - offset * scale);

                    /**
                     * 控制标题文字的大小
                     */
                    scale = (1 - scale) > 0.5f ? (1 - scale) : 0.5f;
                    mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize * scale);

                    /**
                     * 控制整个头布局的高度
                     */
                    paramsChildRoot.topMargin = topMaginHeight - scrollY;
                    mViewChildRoot.setLayoutParams(paramsChildRoot);


                    /**
                     * 控制右边文字的移动
                     */
                    paramsChildTv.topMargin = (int) (-scrollY * offset + textMaginTop);
                    mTvScan.setLayoutParams(paramsChildTv);
                    mTvInvitation.setLayoutParams(paramsChildTv);

                } else {
                    /**
                     * 当滑动距离大于topMaginHeight，状态固定
                     */
                    mTvScan.setAlpha(0);
                    mTvInvitation.setAlpha(0);

                    mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize / 2);

                    paramsChildRoot.topMargin = 0;
                    mViewChildRoot.setLayoutParams(paramsChildRoot);

                    /**
                     * 控制右边文字的移动
                     */
                    paramsChildTv.topMargin = -mHeaderHeight;
                    mTvScan.setLayoutParams(paramsChildTv);
                    mTvInvitation.setLayoutParams(paramsChildTv);

                }
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        /**
         * 在ViewGroup位置摆放完成之后，开始处理
         */
        initView();
    }
}
