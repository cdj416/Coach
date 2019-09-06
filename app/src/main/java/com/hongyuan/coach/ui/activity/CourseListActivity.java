package com.hongyuan.coach.ui.activity;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.databinding.ActivityCourseListBinding;
import com.hongyuan.coach.ui.view_model.CouseListViewModel;

public class CourseListActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_course_list;
    }

    @Override
    protected void initView() {
        setTitle("课程列表");
        setsetImmersive(0x55000000);
        ActivityCourseListBinding binding = ActivityCourseListBinding.bind(mView);
        CouseListViewModel viewModel = new CouseListViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
