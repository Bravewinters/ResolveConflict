package com.example.resolveconflict.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class ContainerLayout extends ViewGroup {
    public ContainerLayout(Context context) {
        this(context, null);
    }

    public ContainerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContainerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View childAt = getChildAt(0);
        childAt.layout(0, 0, childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("dispatchTouchEvent# 父亲  down");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("dispatchTouchEvent# 父亲  move");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("dispatchTouchEvent# 父亲  up");
                break;
        }
        super.dispatchTouchEvent(ev);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("父亲拦截事件# 父亲  down");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("父亲拦截事件# 父亲  move");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("父亲拦截事件# 父亲  up");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("父亲 touch 事件# 父亲  down");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("父亲 touch 事件# 父亲  move");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("父亲 touch 事件# 父亲  up");
                break;
        }
        return false;
    }
}
