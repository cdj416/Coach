package com.hongyuan.coach.ui.activity;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.databinding.ActivityVtwoModifyPasswordBinding;
import com.hongyuan.coach.ui.view_model.VtwoModifyPasswordViewModel;

public class VtwoModifyPasswordActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_vtwo_modify_password;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR6,R.drawable.shape_soid_ffffff,"");
        ActivityVtwoModifyPasswordBinding binding = ActivityVtwoModifyPasswordBinding.bind(mView);
        VtwoModifyPasswordViewModel viewModel = new VtwoModifyPasswordViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
