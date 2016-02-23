package com.example.administrator.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;

public class MyHorizetalView extends HorizontalScrollView {

    private LinearLayout parentView;
    private View content;
    private final int screenWidth;
    private View menu;
    private final int menuWith;
    private final int screenHeight;

    public MyHorizetalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager wm= (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();
        screenHeight = wm.getDefaultDisplay().getHeight();
        menuWith = Utils.dip2px(getContext(), 300f);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        parentView = (LinearLayout) getChildAt(0);
        menu = parentView.getChildAt(0);
        content =parentView.getChildAt(1);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) content.getLayoutParams();
        layoutParams.width=screenWidth;
        content.setLayoutParams(layoutParams);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        scrollTo(menuWith, 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(ev.getAction()==MotionEvent.ACTION_UP){
            int scrollX = getScrollX();
            if(scrollX<=menuWith/2){
                closeMenu();
            }else{
                openMenu();
            }
            return  true;
        }
        return super.onTouchEvent(ev);
    }

    private void openMenu() {
        smoothScrollTo(menuWith,0);
    }

    private void closeMenu() {
        smoothScrollTo(0 ,0);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.i("===", menuWith+"onScrollChanged==="+l);
        startAni(l);
    }

    private void startAni(int l) {
//        float alpha=1-l*1.0f/menuWith;
//        ViewHelper.setAlpha(menu,alpha);
//        //设置缩放
//        float scale=1.0f-l*1.0f/(4*menuWith);
//        ViewHelper.setPivotX(menu, menuWith);
//        ViewHelper.setPivotY(menu, screenHeight / 2);
//        ViewHelper.setScaleX(menu, scale);
//        ViewHelper.setScaleY(menu, scale);
//
//        float ss=0.8f+l*1.0f/(menuWith*5);
//       ViewHelper.setPivotX(content, 0);
//       ViewHelper.setPivotY(content, screenHeight/2);
//      ViewHelper.setScaleX(content, ss);
//       ViewHelper.setScaleY(content, ss);
        float scale = l * 1.0f / menuWith;
        int scrollX = getScrollX();
        ViewHelper.setTranslationX(menu,l);

    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }
}
