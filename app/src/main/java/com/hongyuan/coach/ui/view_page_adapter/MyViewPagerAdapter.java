package com.hongyuan.coach.ui.view_page_adapter;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.coach.base.CustomFragment;
import com.hongyuan.coach.ui.fragment.HomeCourseFragment;
import com.hongyuan.coach.ui.fragment.HomeFragment;
import com.hongyuan.coach.ui.fragment.PersonFragment;

import java.util.ArrayList;
import java.util.List;

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;

    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
        setData();
    }

    @Override
    public CustomFragment getItem(int position) {
        return fragments.get(position);
    }


    @Override
    public int getCount() {
        return fragments.size();
    }

    /*
     * 初始化数据
     * */
    public void setData() {
        if (fragments == null) {
            fragments = new ArrayList<>();
        }

        fragments.clear();
        fragments.add(new HomeFragment());
        fragments.add(new HomeCourseFragment());
        fragments.add(new PersonFragment());

        notifyDataSetChanged();
    }
}
