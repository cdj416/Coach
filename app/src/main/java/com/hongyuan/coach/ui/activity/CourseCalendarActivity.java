package com.hongyuan.coach.ui.activity;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.databinding.ActivityCourseCalendarBinding;
import com.hongyuan.coach.ui.view_model.CourseCalendarViewModel;

public class CourseCalendarActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_course_calendar;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,1,"课程日历");
        ActivityCourseCalendarBinding binding = ActivityCourseCalendarBinding.bind(mView);
        CourseCalendarViewModel viewModel = new CourseCalendarViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
