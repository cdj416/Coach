package com.hongyuan.coach.ui.activity;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.databinding.ActivityMyWebviewBinding;
import com.hongyuan.coach.ui.view_model.MyWebViewModelView;
public class MyWebViewActivity extends CustomActivity {

    private ActivityMyWebviewBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_webview;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR3, R.drawable.shape_soid_ffffff,"隐私政策");
        binding = ActivityMyWebviewBinding.bind(mView);
        MyWebViewModelView modelView = new MyWebViewModelView(this,binding);
        binding.setViewModel(modelView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.webView.destroy();
    }
}
