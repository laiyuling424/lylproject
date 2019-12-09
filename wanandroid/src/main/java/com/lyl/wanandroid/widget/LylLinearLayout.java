package com.lyl.wanandroid.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Create By: lyl
 * Date: 2019-07-11 10:19
 */
public class LylLinearLayout extends LinearLayout
        implements NestedScrollingParent2 {


    private static int MAX_HEIGHT = -1;
    private View headerView;
    private View childView;


    public LylLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        headerView = getChildAt(0);
        childView = getChildAt(1);
        MAX_HEIGHT = headerView.getHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        ViewGroup.LayoutParams params = childView.getLayoutParams();
//        params.height = getMeasuredHeight();
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {

    }

    /**
     * 返回true代表处理本次事件
     */
    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int nestedScrollAxes, int type) {
        return target instanceof RecyclerView;
    }

    /**
     * 复位初始位置
     */
    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {

    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        // 如果在自定义ViewGroup之上还有父View交给我来处理
        getParent().requestDisallowInterceptTouchEvent(true);
        if (type == ViewCompat.TYPE_TOUCH) {//手指触发的滑动
            // dy>0向下scroll dy<0向上scroll
            layout(getLeft(), getTop() + dy, getRight(), getBottom() + dy);
//            headerView.setTranslationY(-dy);
//            childView.setTranslationY(dy);
            requestLayout();
            invalidate();
            if (dy > 0) {
                // dy>0向下scroll
                if (headerView.getBottom() <= headerView.getHeight() && 0 <= headerView.getBottom()) {
//                    headerView.scrollTo(0, dy);
//                    headerView.layout(headerView.getLeft(),headerView.getTop()+dy,headerView.getRight(),headerView.getBottom()+dy);
//                    headerView.setTranslationY(dy);
//                    this.setTranslationY(dy);
//                    requestLayout();
//                    invalidate();
                }
            }
            {
                // dy<0向上scroll
                if (headerView.getBottom() <= headerView.getHeight() && 0 <= headerView.getBottom()) {
//                    headerView.scrollTo(0, dy);
//                    headerView.layout(headerView.getLeft(),headerView.getTop()+dy,headerView.getRight(),headerView.getBottom()+dy);
//                    headerView.setTranslationY(dy);
//                    this.setTranslationY(dy);
//                    requestLayout();
//                    invalidate();

                }
            }


            // dy>0向下scroll dy<0向上scroll
//            boolean hiddenTop = dy > 0 && getScrollY() < MAX_HEIGHT && !target.canScrollVertically(-1);
//            boolean showTop = dy < 0 && !target.canScrollVertically(-1);
//            boolean hiddenBottom = dy < 0 && getScrollY() > MAX_HEIGHT && !target.canScrollVertically(1);
//            boolean showBottom = dy > 0 && !target.canScrollVertically(1);
//            if (hiddenTop || showTop || hiddenBottom || showBottom) {
//                if (animator.isStarted()) {
//                    animator.pause();
//                }
//                scrollBy(0, damping(dy));
//                if (animator.isPaused()) {//手动cancel 避免内存泄漏
//                    animator.cancel();
//                }
//                consumed[1] = dy;
//            }
//            adjust(dy, target);//调整错位
        }
    }


    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        getParent().requestDisallowInterceptTouchEvent(true);
//        if (type == ViewCompat.TYPE_NON_TOUCH) {//非手指触发的滑动，即Filing
//            //解决冗余fling问题
//            if (((Math.floor(getScrollY()) == 0) || ((Math.ceil(getScrollY()) == 2 * MAX_HEIGHT))) && !isFirstRunAnim) {
//                int startY = 0;
//                if (dyUnconsumed > 0) {
//                    startY = 2 * MAX_HEIGHT;
//                }
//                animator.startOfFloat(target, startY);
//                isFirstRunAnim = true;
//            }
//            if (isFirstRunAnim)
//                return;
//
//            // dy>0向下fling dy<0向上fling
//            boolean showTop = dyUnconsumed < 0 && !target.canScrollVertically(-1);
//            boolean showBottom = dyUnconsumed > 0 && !target.canScrollVertically(1);
//            if (showTop || showBottom) {
//                if (animator.isStarted()) {
//                    animator.pause();
//                }
//                scrollBy(0, damping(dyUnconsumed));
//                if (animator.isPaused()) {//手动cancel 避免内存泄漏
//                    animator.cancel();
//                }
//            }
//            adjust(dyUnconsumed, target);//调整错位
//        }

    }

    /**
     * 衰减可继续scroll或fling的距离
     */
    private int damping(int dy) {
        //计算衰减系数,越大可继续scroll或fling的距离越短
        int i = (int) (Math.abs(MAX_HEIGHT - getScrollY()) * 0.01);
        return i < 2 ? dy : dy / i;
    }

    /**
     * 调整错位问题(强转精度损失造成的错位)
     */
    private void adjust(int condition1, View condition2) {
        if (condition1 > 0 && getScrollY() > MAX_HEIGHT && !condition2.canScrollVertically(-1)) {
            scrollTo(0, MAX_HEIGHT);
        }
        if (condition1 < 0 && getScrollY() < MAX_HEIGHT && !condition2.canScrollVertically(1)) {
            scrollTo(0, MAX_HEIGHT);
        }
    }

    /**
     * 限制滑动 移动y轴不能超出最大范围
     */
    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        } else if (y > MAX_HEIGHT * 2) {
            y = MAX_HEIGHT * 2;
        }
        super.scrollTo(x, y);
    }

    /**
     * 回弹动画
     */
    private class ReboundAnimator extends ValueAnimator {
        private View target;

        private ReboundAnimator() {
            init();
        }

        private void init() {
            this.setInterpolator(new DecelerateInterpolator());//添加减速插值器
            this.setDuration(260);
            //添加值更新监听器
            this.addUpdateListener(new AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float currValue = (float) getAnimatedValue();
                    scrollTo(0, (int) currValue);
                    // 调整错位问题(强转精度损失造成的错位)
                    if (getScrollY() > MAX_HEIGHT && !target.canScrollVertically(-1)) {
                        scrollTo(0, MAX_HEIGHT);
                    }
                    if (getScrollY() < MAX_HEIGHT && !target.canScrollVertically(1)) {
                        scrollTo(0, MAX_HEIGHT);
                    }
                }
            });
        }

        private void startOfFloat(View target, float startY) {
            this.setFloatValues(startY, MAX_HEIGHT);
            this.target = target;
            this.start();
        }
    }
}


