package com.hongyuan.coach.ui.activity;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.databinding.ActivityAboutUsBinding;
import com.hongyuan.coach.ui.view_model.AboutUsViewModel;

public class AboutUsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initView() {
        setTitle("关于我们");
        setsetImmersive(0x55000000);
        ActivityAboutUsBinding binding = ActivityAboutUsBinding.bind(mView);
        AboutUsViewModel viewModel = new AboutUsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
