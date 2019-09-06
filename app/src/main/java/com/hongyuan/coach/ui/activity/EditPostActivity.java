package com.hongyuan.coach.ui.activity;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.databinding.ActivityEditPostBinding;
import com.hongyuan.coach.ui.view_model.EditPostViewModel;

public class EditPostActivity extends CustomActivity {
    private ActivityEditPostBinding binding;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_post;
    }

    @Override
    protected void initView() {
        setTitle("我的");
        setsetImmersive(0x55000000);
        binding = ActivityEditPostBinding.bind(mView);
        EditPostViewModel viewModel = new EditPostViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        binding.imgOrVideo.onActivityResult(requestCode,resultCode,data);
    }
}
