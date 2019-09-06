package com.hongyuan.coach.ui.activity;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.databinding.ActivityPostDetailsBinding;
import com.hongyuan.coach.ui.view_model.PostDetailsViewModel;

public class PostDetailsActivity extends CustomActivity {
    private PostDetailsViewModel viewModel;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_post_details;
    }

    @Override
    protected void initView() {
        setTitle("详情");
        setsetImmersive(0x55000000);
        ActivityPostDetailsBinding binding = ActivityPostDetailsBinding.bind(mView);
        viewModel = new PostDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.lazyLoad();
    }
}
