package com.hongyuan.coach.ui.activity;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.databinding.ActivityFriendsBinding;
import com.hongyuan.coach.ui.view_model.FriendsViewModel;

public class FriendsActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_friends;
    }

    @Override
    protected void initView() {
        setTitle("我关注的人");
        setsetImmersive(0x55000000);
        ActivityFriendsBinding binding = ActivityFriendsBinding.bind(mView);
        FriendsViewModel viewModel = new FriendsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
