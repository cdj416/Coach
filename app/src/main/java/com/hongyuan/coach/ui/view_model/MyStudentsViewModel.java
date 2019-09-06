package com.hongyuan.coach.ui.view_model;

import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.base.CustomViewModel;
import com.hongyuan.coach.databinding.ActivityMyStudentsBinding;
import com.hongyuan.coach.ui.view_page_adapter.MyStudentsPagerAdapter;

public class MyStudentsViewModel extends CustomViewModel {

    private ActivityMyStudentsBinding binding;

    public MyStudentsViewModel(CustomActivity mActivity, ActivityMyStudentsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        MyStudentsPagerAdapter meunAdapter = new MyStudentsPagerAdapter(mActivity.getSupportFragmentManager());
        binding.viewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.viewPager);

        binding.viewPager.setOffscreenPageLimit(2);
    }
}
