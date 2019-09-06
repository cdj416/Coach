package com.hongyuan.coach.ui.activity;

import android.view.KeyEvent;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.databinding.ActivityMainBinding;
import com.hongyuan.coach.ui.view_model.MainViewModel;
import com.hongyuan.coach.util.CustomDialog;

public class MainActivity extends CustomActivity {
    private MainViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR1,R.drawable.shape_soid_ffffff,"");
        ActivityMainBinding binding = ActivityMainBinding.bind(mView);
        viewModel = new MainViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    //安卓重写返回键事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            CustomDialog.promptDialog(this, "确定要退出程序？", "确定", "取消", false, v -> {
                if(v.getId() == R.id.isOk){
                    System.exit(0);
                }
            });
            return false;
        }
        return true;
    }
}
