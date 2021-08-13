package com.example.resolveconflict.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class VPWrapLayout extends LinearLayout {
    public VPWrapLayout(Context context) {
        super(context);
    }

    public VPWrapLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VPWrapLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        requestDisallowInterceptTouchEvent(true);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("dispatchTouchEvent # 孩子  down");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("dispatchTouchEvent # 孩子  move");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("dispatchTouchEvent # 孩子  up");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
