package com.hongyuan.coach.ui.activity;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.databinding.ActivityMyStudentsBinding;
import com.hongyuan.coach.ui.view_model.MyStudentsViewModel;

public class MyStudentsActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_students;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,0,"我的学员");
        ActivityMyStudentsBinding binding = ActivityMyStudentsBinding.bind(mView);
        MyStudentsViewModel viewModel = new MyStudentsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
