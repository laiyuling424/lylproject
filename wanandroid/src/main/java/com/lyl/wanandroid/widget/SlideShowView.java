package com.lyl.wanandroid.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lyl.wanandroid.R;
import com.lyl.wanandroid.util.MyLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Create By: lyl
 * Date: 2019-07-10 09:25
 */

public class SlideShowView extends FrameLayout {

    //自动轮播启用开关
    private final static boolean isAutoPlay = true;
    public int position;
    Timer timer;
    //自定义轮播图的资源ID
    private int[] imagesResIds;
    //放轮播图片的ImageView 的list
    private List<ImageView> imageViewsList;
    //放圆点的View的list
    private List<View> dotViewsList;
    private ViewPager viewPager;
    //当前轮播页
    private int currentItem = 0;
    //定时任务
    private ScheduledExecutorService scheduledExecutorService;
    private List<String> urlList;
    private Context mContext;
    //Handler
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            viewPager.setCurrentItem(currentItem);
        }

    };

    public SlideShowView(Context context) {
        this(context, null);
        // TODO Auto-generated constructor stub
    }

    public SlideShowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }

    public SlideShowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        initData();
        initUI(context);
        mContext = context;
        if (isAutoPlay) {
            startPlay();
        }

    }

    /**
     * 开始轮播图切换
     */
    private void startPlay() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 3, TimeUnit.SECONDS);
    }

    /**
     * 停止轮播图切换
     */
    private void stopPlay() {
        scheduledExecutorService.shutdown();
    }

    /**
     * 初始化相关Data
     */
    private void initData() {
        imagesResIds = new int[]{
                R.drawable.pic1,
                R.drawable.pic2,
                R.drawable.pic3,
                R.drawable.pic4,
                R.drawable.pic5,

        };
        imageViewsList = new ArrayList<ImageView>();
        dotViewsList = new ArrayList<View>();
        urlList = new ArrayList<>();
    }

    /**
     * 初始化Views等UI
     */
    private void initUI(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_slideshow, this, true);
//        for(int imageID : imagesResIds){
//            ImageView view =  new ImageView(context);
//            view.setImageResource(imageID);
//            view.setScaleType(ScaleType.FIT_XY);
//            imageViewsList.add(view);
//        }
        if (urlList.size() == 0) {

        } else {
            for (String url : urlList) {
                ImageView view = new ImageView(context);
                Glide.with(mContext)
                        .applyDefaultRequestOptions(new RequestOptions()
                                .placeholder(R.drawable.place)
                                .error(R.drawable.place))
                        .load(url)
                        .into(view);
                view.setScaleType(ScaleType.FIT_XY);
                imageViewsList.add(view);
            }
            dotViewsList.add(findViewById(R.id.v_dot1));
            dotViewsList.add(findViewById(R.id.v_dot2));
            dotViewsList.add(findViewById(R.id.v_dot3));
            dotViewsList.add(findViewById(R.id.v_dot4));
        }

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setFocusable(true);

        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
    }

    //TODO 没有用 点击banner停止轮播 放开后3秒在轮播
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                MyLog.INSTANCE.Logd("index===");
                stopPlay();
                return true;
            case MotionEvent.ACTION_UP:
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        startPlay();
                    }
                }, 3000);
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 销毁ImageView资源，回收内存
     */
    private void destoryBitmaps() {

        for (int i = 0; i < urlList.size(); i++) {
            ImageView imageView = imageViewsList.get(i);
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                //解除drawable对view的引用
                drawable.setCallback(null);
            }
        }
    }

    public void setUrlList(List<String> list) {
        MyLog.INSTANCE.Logd("list" + list.size());
        urlList = list;
        imageViewsList.clear();
        for (String url : list) {
            ImageView view = new ImageView(mContext);

            Glide.with(mContext)
                    .applyDefaultRequestOptions(new RequestOptions()
                            .placeholder(R.drawable.place)
                            .error(R.drawable.place))
                    .load(url)
                    .into(view);
            view.setScaleType(ScaleType.FIT_XY);
            imageViewsList.add(view);
        }
        dotViewsList.add(findViewById(R.id.v_dot1));
        dotViewsList.add(findViewById(R.id.v_dot2));
        dotViewsList.add(findViewById(R.id.v_dot3));
        dotViewsList.add(findViewById(R.id.v_dot4));

        viewPager.getAdapter().notifyDataSetChanged();
    }

    /**
     * 填充ViewPager的页面适配器
     */
    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public void destroyItem(View container, int position, Object object) {
            // TODO Auto-generated method stub
            //((ViewPag.er)container).removeView((View)object);
            ((ViewPager) container).removeView(imageViewsList.get(position));
        }

        @Override
        public Object instantiateItem(View container, int position) {
            // TODO Auto-generated method stub
            ((ViewPager) container).addView(imageViewsList.get(position));
            return imageViewsList.get(position);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return imageViewsList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

    }

    /**
     * ViewPager的监听器
     * 当ViewPager中页面的状态发生改变时调用
     */
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        boolean isAutoPlay = false;

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
            switch (arg0) {
                case 1:// 手势滑动，空闲中
                    isAutoPlay = false;
                    break;
                case 2:// 界面切换中
                    isAutoPlay = true;
                    break;
                case 0:// 滑动结束，即切换完毕或者加载完毕
                    // 当前为最后一张，此时从右向左滑，则切换到第一张
                    if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                        viewPager.setCurrentItem(0);
                    }
                    // 当前为第一张，此时从左向右滑，则切换到最后一张
                    else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
                        viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
                    }
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int pos) {
            // TODO Auto-generated method stub
            position = pos;
            currentItem = pos;
            for (int i = 0; i < dotViewsList.size(); i++) {
                if (i == pos) {
                    ((View) dotViewsList.get(pos)).setBackgroundResource(R.drawable.dot_black);
                } else {
                    ((View) dotViewsList.get(i)).setBackgroundResource(R.drawable.dot_white);
                }
            }
        }

    }

    /**
     * 执行轮播图切换任务
     */
    private class SlideShowTask implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized (viewPager) {
                currentItem = (currentItem + 1) % imageViewsList.size();
                handler.obtainMessage().sendToTarget();
            }
        }

    }

}