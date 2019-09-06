package com.hongyuan.coach.ui.view_page_adapter;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.coach.base.CustomFragment;
import com.hongyuan.coach.ui.beans.CourseCalendarUseBeans;
import com.hongyuan.coach.ui.fragment.CourseCalendarFragment;

import java.util.ArrayList;
import java.util.List;

public class CourseCalendarPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<CourseCalendarUseBeans> beans;
    private Context mContext;

    public CourseCalendarPagerAdapter(Context mContext, FragmentManager fm) {
        super(fm);
        this.mContext = mContext;
    }

    @Override
    public CustomFragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(beans != null && beans.size() > 0){
            return beans.get(position).getPageNum()+"";
        }
        return "";
    }

    @Override
    public int getCount() {
        if(fragments != null && fragments.size() > 0){
            return fragments.size();
        }
        return 0;
    }

    /*
     * 初始化数据
     * */
    public void setData(List<CourseCalendarUseBeans> beans) {
        if(beans == null){
            return;
        }
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        this.beans = beans;
        this.fragments.clear();
        for (int i = 0 ; i < beans.size() ; i++){
            Bundle bundle = new Bundle();
            bundle.putSerializable("calendarData",beans.get(i));
            fragments.add(new CourseCalendarFragment().setMyArguments(bundle));
        }

        notifyDataSetChanged();
    }
}
