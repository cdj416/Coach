package com.hongyuan.coach.ui.activity;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.databinding.ActivtiyCourseDetailsBinding;
import com.hongyuan.coach.ui.view_model.CourseDetailsViewModel;

public class CourseDetailsActivity extends CustomActivity {

    private CourseDetailsViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activtiy_course_details;
    }

    @Override
    protected void initView() {
        hideTitle(true);
        setImmersive();
        ActivtiyCourseDetailsBinding binding = ActivtiyCourseDetailsBinding.bind(mView);
        viewModel = new CourseDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.lazyLoad();
    }
}
