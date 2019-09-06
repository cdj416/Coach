package com.hongyuan.coach.ui.activity;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.databinding.ActivitySettingBinding;
import com.hongyuan.coach.ui.view_model.SettingViewModel;

public class SettingActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        setTitle("设置");
        setsetImmersive(0x55000000);
        ActivitySettingBinding binding = ActivitySettingBinding.bind(mView);
        SettingViewModel viewModel = new SettingViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
