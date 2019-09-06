package com.hongyuan.coach.ui.activity;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.databinding.ActivityCommentDetailsBinding;
import com.hongyuan.coach.ui.view_model.CommentDetailsViewModel;

public class CommentDetailsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment_details;
    }

    @Override
    protected void initView() {
        setTitle("评论详情");
        setsetImmersive(0x55000000);
        ActivityCommentDetailsBinding binding = ActivityCommentDetailsBinding.bind(mView);
        CommentDetailsViewModel viewModel = new CommentDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
