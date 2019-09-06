package com.hongyuan.coach.ui.activity;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.databinding.ActivityVtwoVerificationCodeLoginBinding;
import com.hongyuan.coach.ui.view_model.VtwoVerificationLoginViewModel;

public class VtwoVerificationLoginActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_vtwo_verification_code_login;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR6,R.drawable.shape_soid_ffffff,"");
        ActivityVtwoVerificationCodeLoginBinding binding = ActivityVtwoVerificationCodeLoginBinding.bind(mView);
        VtwoVerificationLoginViewModel viewModel = new VtwoVerificationLoginViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
