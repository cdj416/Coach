package com.hongyuan.coach.ui.activity;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.databinding.ActivityEditTimeBinding;
import com.hongyuan.coach.ui.view_model.EditTimeViewModel;

public class EditTimeActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_time;
    }

    @Override
    protected void initView() {
        setTitle("教练可约时间");
        setsetImmersive(0x55000000);
        ActivityEditTimeBinding binding = ActivityEditTimeBinding.bind(mView);
        EditTimeViewModel viewModel = new EditTimeViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
