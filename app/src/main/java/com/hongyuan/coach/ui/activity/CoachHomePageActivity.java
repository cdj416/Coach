package com.hongyuan.coach.ui.activity;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.databinding.ActivityCoachHomepageBinding;
import com.hongyuan.coach.ui.view_model.CoachHomePageViewModel;

public class CoachHomePageActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coach_homepage;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR2,R.drawable.shape_soid_ffffff,"");
        ActivityCoachHomepageBinding binding = ActivityCoachHomepageBinding.bind(mView);
        CoachHomePageViewModel viewModel = new CoachHomePageViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
