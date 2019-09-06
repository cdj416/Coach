package com.hongyuan.coach.ui.activity;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.databinding.ActivityEditCourseBinding;
import com.hongyuan.coach.ui.view_model.EditCourseViewModel;

public class EditCourseActivity extends CustomActivity {

    private EditCourseViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_course;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,1,"编辑授课时间");
        ActivityEditCourseBinding binding = ActivityEditCourseBinding.bind(mView);
        viewModel = new EditCourseViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewModel.onActivityResult(requestCode,resultCode,data);
    }
}
