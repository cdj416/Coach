package com.hongyuan.coach.ui.activity;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.databinding.ActivityStartupPageBinding;
import com.hongyuan.coach.ui.view_model.StartupPageVeiwModel;

public class StartupPageActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_startup_page;
    }

    @Override
    protected void initView() {
        hideTitle(true);
        setImmersive();
        ActivityStartupPageBinding binding = ActivityStartupPageBinding.bind(mView);
        StartupPageVeiwModel veiwModel = new StartupPageVeiwModel(this,binding);
        binding.setViewModel(veiwModel);
    }
}
