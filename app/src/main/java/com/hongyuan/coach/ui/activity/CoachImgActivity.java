package com.hongyuan.coach.ui.activity;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.databinding.ActivityCoachPhotoBinding;
import com.hongyuan.coach.ui.view_model.CoachImgViewModel;

public class CoachImgActivity extends CustomActivity {

    private ActivityCoachPhotoBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coach_photo;
    }

    @Override
    protected void initView() {
        setsetImmersive(0x55000000);
        binding = ActivityCoachPhotoBinding.bind(mView);
        CoachImgViewModel viewModel = new CoachImgViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        binding.addImg.onActivityResult(requestCode,resultCode,data);
    }
}
