package com.lyl.wanandroid.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.lyl.wanandroid.R;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by xcfor on 2018/7/11.
 */

public class BannerView extends FrameLayout implements ViewPager.OnPageChangeListener {

    public final int INDICATOR_LOCATION_CENTER_BUTTON = 0x1;//指示器居中
    public final int INDICATOR_LOCATION_LEFT_BUTTON = 0x2;//指示器居左
    public final int INDICATOR_LOCATION_RIGHT_BUTTON = 0x3;//指示器居右
    public final int BANNER_DELAY_TIME = 1000; //轮播图延迟时间
    public final int BANNER_DEF_TIIME = 5000;//轮播图默认切换时间

    /**
     * 存放圆点指示器
     */
    private LinearLayout mIndecatorLayout;
    private PagerAdapter mAdapter;
    private List<String> mUrls; //图片路径list
    private Context mContext;
    private ViewPager mViewPager;


    private boolean mIsScrollingByUser;// 用户手动滑动广告中
    private int mIndicatorDotWidth;//轮播切换小圆点宽度默认宽度
    private int mCurrentPos = 0;//当前页pos
    private int mPrePos = 0; //历史页pos
    private int mCount;//轮播图总数
    private int mIndicatorLocation = 1;//默认值为1：居中  2：左  3：右
    private boolean mChangeIndecatorStyle = false;//是否自定义指示器的样式
    private float mScale;//显示度量的密度

    /**
     * 定时滚动定时器
     */
    private Timer mTimer;
    private TimerTask mTask;


    /**
     * 接收定时器信息的handler
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch (what) {
                case 0:
                    if (mViewPager != null) {
                        if (!mIsScrollingByUser) {
                            if (mCurrentPos == mUrls.size()) {
                                mCurrentPos = 0;
                            } else {
                                mCurrentPos++;
                            }
                        }
                        mViewPager.setCurrentItem(mCurrentPos);
                    }
                    break;
            }
        }
    };

    public BannerView(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void init() {

        if (mUrls.size() != 1) {
            mViewPager.addOnPageChangeListener(this);
        }
        //切换画面
        mViewPager.setPageTransformer(true, new MyPageTransformer());
        mIndecatorLayout.removeAllViews();
        //向线性布局中添加小圆点指示器
        if (mUrls.size()>1){
            View dot;
            LinearLayout.LayoutParams params;
            WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            for (int i = 0; i < mCount; i++) {
                dot = new View(mContext);
                params = new LinearLayout.LayoutParams(mIndicatorDotWidth, mIndicatorDotWidth);
                params.setMargins(mIndicatorDotWidth, 0, 0, dip2px(10));
                dot.setLayoutParams(params);
                dot.setBackgroundResource(R.drawable.basiclib_dot_bg_selector);
                dot.setEnabled(false);//默认设为非选中
                mIndecatorLayout.addView(dot);
            }
            //指示器图标的位置
            switch (mIndicatorLocation){
                case INDICATOR_LOCATION_CENTER_BUTTON:
                    if (mCount % 2 == 0) {
                        mIndecatorLayout.setPadding(width / 2 - (mCount * mIndicatorDotWidth), 0, 0, 8);
                    } else {
                        mIndecatorLayout.setPadding(width / 2 - (mCount * mIndicatorDotWidth), 0, 0, 8);
                    }
                    break;
                case INDICATOR_LOCATION_LEFT_BUTTON:
                    mIndecatorLayout.setPadding(0, 0, 0, 8);
                    break;
                case INDICATOR_LOCATION_RIGHT_BUTTON:
                    mIndecatorLayout.setPadding(width - ((mCount * 2+1) * mIndicatorDotWidth), 0, 0, 8);
                    break;
            }
            int midPos = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % mCount;
            mCurrentPos = midPos;
            mIndecatorLayout.getChildAt(0).setEnabled(true);
            mViewPager.setCurrentItem(midPos);
        }else {
            mIndecatorLayout.setVisibility(GONE);
        }

    }

    /**
     * 设置指示器的位置
     *
     * @param location
     */
    public void relayoutIndicator(int location) {
        mIndicatorLocation = location;
    }


    /**
     * 自定义指示器入口
     *
     * @param style
     */
    public void customerIndicatorEntry(int style) {
        if (!mChangeIndecatorStyle) {
            return;
        } else {
            changeIndicator(style);
        }
    }

    /**
     * 改变指示器
     * @param style
     */
    public void changeIndicator(int style) {
        for (int i = 0; i < mCount; i++) {
            mIndecatorLayout.getChildAt(i).setBackgroundResource(style);
        }
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //动态创建轮播图最外围布局
        mIndicatorDotWidth = dip2px(4);
        FrameLayout fl = new FrameLayout(mContext);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mViewPager = new ViewPager(mContext);
        mViewPager.setLayoutParams(params);
        fl.addView(mViewPager);

        //动态创建指示器外围布局
        mIndecatorLayout = new LinearLayout(mContext);
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.BOTTOM;
        mIndecatorLayout.setLayoutParams(params);
        mIndecatorLayout.setOrientation(LinearLayout.HORIZONTAL);
        fl.addView(mIndecatorLayout);
        addView(fl);
    }

    /**
     * 获取数据
     */
    public void setData(List<String> urls) {
        if (urls.isEmpty()) {
            return;
        }
        this.mUrls = urls;
        mCount = mUrls.size();
        if (mAdapter == null) {
            mAdapter = new MyAdapter(mUrls);
            mViewPager.setAdapter(mAdapter);
        }else{
            //更新viewpager视图
            //--更新原点指示器
            mAdapter.notifyDataSetChanged();
        }
        init();
    }

    /**
     * 默认时间启动定时器
     */
    public void startBannerScrollTask(){
        if (mUrls.size() != 1){
            startBannerScrollTask(BANNER_DEF_TIIME);
        }
    }

    /**
     * 给外边调用，可以使用外边的适配器
     * @param timer
     */
    public void setScrollTask(Timer timer ,long timeSpace){
        if (mCount == 0){
            return;
        }
        mTask = new TimerTask() {
            @Override
            public void run() {
                if (!mIsScrollingByUser){
                    mHandler.sendEmptyMessage(0);
                }

            }
        };
        timer.schedule(mTask,BANNER_DELAY_TIME, timeSpace);
    }


    //开启轮播图定时器
    public void startBannerScrollTask(long timeSpace) {
        if (mCount == 0) {
            return;
        }
        mTimer = new Timer(true);
        mTask = new TimerTask() {
            @Override
            public void run() {
                if (!mIsScrollingByUser){
                    mHandler.sendEmptyMessage(0);
                }

            }
        };
        mTimer.schedule(mTask, BANNER_DELAY_TIME, timeSpace);//1000ms后按指定时间间隔轮播
    }

    /**
     * 关闭轮播图定时器
     */
    public void stopBannerTask() {
        if (mTask != null) {
            mTask.cancel();
        }
    }



    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     * 改全变量
     */
    public  int dip2px(float dpValue) {
        mScale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * mScale + 0.5f);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mCurrentPos = position;
        mIndecatorLayout.getChildAt(mPrePos % mUrls.size()).setEnabled(false);
        mIndecatorLayout.getChildAt(mCurrentPos % mUrls.size()).setEnabled(true);//设置true放后面，防止初始化时两个pos都为0时。没有默认选中
        mPrePos = mCurrentPos;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 0) {//用户手动滑动广告时，取消自动翻页响应
            mIsScrollingByUser = false;
        } else {
            //用户手动滑动中
            mIsScrollingByUser = true;
        }
    }

    /**
     * 轮播图ViewPager适配器
     */
    class MyAdapter extends PagerAdapter {
        List<String> addUrls;

        public MyAdapter(List<String> addUrls) {
            this.addUrls = addUrls;
        }

        @Override
        public int getCount() {
            if (addUrls.size() == 1){
                return addUrls.size();
            }else {
                return Integer.MAX_VALUE;
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext);
            String currentUrl = addUrls.get(position % addUrls.size());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(mContext).load(currentUrl).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    /**
     * 添加viewPager页面切换效果
     */
    class MyPageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            final float normalizedposition = Math.abs(Math.abs(position) - 1);
            page.setAlpha(normalizedposition);
        }
    }




}
