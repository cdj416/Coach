package com.hongyuan.coach.ui.activity;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.databinding.ActivityScanBinding;
import com.hongyuan.coach.ui.view_model.ScanViewModel;

public class ScanActivity extends CustomActivity {

    private ActivityScanBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scan;
    }

    @Override
    protected void initView() {
        hideTitle(true);
        setImmersive();
        binding = ActivityScanBinding.bind(mView);
        ScanViewModel viewModel = new ScanViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.mQRCodeView.startCamera();//打开相机
        binding.mQRCodeView.startSpotAndShowRect();//显示扫描框并开始识别
    }

    @Override
    protected void onStop() {
        binding.mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        binding.mQRCodeView.onDestroy();
        super.onDestroy();
    }
}
