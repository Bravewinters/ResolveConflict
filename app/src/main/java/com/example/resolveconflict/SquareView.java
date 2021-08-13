package com.example.resolveconflict;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class SquareView extends View {
    int background = Color.parseColor("#F6F6F6");

    public SquareView(Context context) {
        this(context, null);
    }

    public SquareView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        System.out.println("measure: " + parentWidth + " h: " + parentHeight);
        setMeasuredDimension(widthMeasureSpec, parentHeight / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(background);
    }

    boolean flag;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
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

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("孩子 onTouchEvent# 孩子  down");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("孩子 onTouchEvent# 孩子  move");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("孩子 onTouchEvent# 孩子  up");
                break;
        }
        return true;
    }
}
