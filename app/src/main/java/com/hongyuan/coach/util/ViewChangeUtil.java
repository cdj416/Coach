package com.hongyuan.coach.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

public class ViewChangeUtil {

    /*
     * 改变drawable(左边)
     * */
    public static void changeLeftDrawable(Context mContext, TextView mView,int imgId){
        Drawable drawable = mContext.getResources().getDrawable(imgId);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        //mDownLoad是控件的名称,setCompoundDrawables(left,top,right,bottom)
        mView.setCompoundDrawables(drawable,null,null,null);
    }
    /*
     * 改变drawable(上边)
     * */
    public static void changeTopDrawable(Context mContext, TextView mView,int imgId){
        Drawable drawable = mContext.getResources().getDrawable(imgId);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        //mDownLoad是控件的名称,setCompoundDrawables(left,top,right,bottom)
        mView.setCompoundDrawables(null,drawable,null,null);
    }
    /*
     * 改变drawable(右边)
     * */
    public static void changeRightDrawable(Context mContext, TextView mView,int imgId){
        Drawable drawable = mContext.getResources().getDrawable(imgId);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        //mDownLoad是控件的名称,setCompoundDrawables(left,top,right,bottom)
        mView.setCompoundDrawables(null,null,drawable,null);
    }
    /*
     * 改变drawable(下边)
     * */
    public static void changeBottomDrawable(Context mContext, TextView mView,int imgId){
        Drawable drawable = mContext.getResources().getDrawable(imgId);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        //mDownLoad是控件的名称,setCompoundDrawables(left,top,right,bottom)
        mView.setCompoundDrawables(null,null,null,drawable);
    }
}
