package com.hongyuan.coach.ui.activity;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.databinding.ActivityCoachProfileBinding;
import com.hongyuan.coach.ui.view_model.CoachProfileViewModel;


public class CoachProfileActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coach_profile;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR2,R.drawable.shape_soid_ffffff,"");
        ActivityCoachProfileBinding binding = ActivityCoachProfileBinding.bind(mView);
        CoachProfileViewModel viewModel = new CoachProfileViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
