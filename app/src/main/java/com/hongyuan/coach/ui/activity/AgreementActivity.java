package com.hongyuan.coach.ui.activity;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.databinding.ActivityAgreementBinding;
import com.hongyuan.coach.ui.view_model.AgreementViewModel;

public class AgreementActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_agreement;
    }

    @Override
    protected void initView() {
        setTitle("服务协议");
        setsetImmersive(0x55000000);
        ActivityAgreementBinding binding = ActivityAgreementBinding.bind(mView);
        AgreementViewModel viewModel = new AgreementViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
