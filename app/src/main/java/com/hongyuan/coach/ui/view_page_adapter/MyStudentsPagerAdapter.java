package com.hongyuan.coach.ui.view_page_adapter;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.coach.base.CustomFragment;
import com.hongyuan.coach.ui.beans.TitleBean;
import com.hongyuan.coach.ui.fragment.MyStudentsFragment;

import java.util.ArrayList;
import java.util.List;

public class MyStudentsPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;

    public MyStudentsPagerAdapter(FragmentManager fm) {
        super(fm);
        setData();
    }

    @Override
    public CustomFragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return beans.get(position).getTitleName();
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
        if(beans == null){
            beans = new ArrayList<>();
        }
        fragments.clear();
        beans.clear();
        beans.add(new TitleBean("当前学员",0));
        beans.add(new TitleBean("历史学员",1));
        fragments.add(new MyStudentsFragment().setArguments("1"));
        fragments.add(new MyStudentsFragment().setArguments("2"));

        notifyDataSetChanged();
    }
}
