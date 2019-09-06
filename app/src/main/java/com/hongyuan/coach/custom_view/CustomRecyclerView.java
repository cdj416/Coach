package com.hongyuan.coach.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/*
* 解决横向滑动冲突
* */
public class CustomRecyclerView extends RecyclerView {
    private int mTouchSlop;
    public CustomRecyclerView(Context context) {
        super(context);
        final ViewConfiguration vc = ViewConfiguration.get(context);
        mTouchSlop = vc.getScaledTouchSlop();
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        final ViewConfiguration vc = ViewConfiguration.get(context);
        mTouchSlop = vc.getScaledTouchSlop();
    }


    int move_x,move_y;
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()){

            case MotionEvent.ACTION_DOWN:
                move_x = (int) e.getX();
                move_y = (int) e.getY();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int y = (int) e.getY();
                int x = (int) e.getX();
                if(Math.abs(y-move_y)>mTouchSlop&& Math.abs(x-move_x)<mTouchSlop*2){
                    getParent().requestDisallowInterceptTouchEvent(false);
                }else {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(e);
    }
}
