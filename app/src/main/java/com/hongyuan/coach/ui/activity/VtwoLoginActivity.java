package com.hongyuan.coach.ui.activity;

import android.view.KeyEvent;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.AppManager;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.databinding.ActivityVtwoLoginBinding;
import com.hongyuan.coach.ui.view_model.VtwoLoginViewModel;
import com.hongyuan.coach.util.CustomDialog;

public class VtwoLoginActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_vtwo_login;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR6, R.drawable.shape_soid_ffffff,"");
        ActivityVtwoLoginBinding binding = ActivityVtwoLoginBinding.bind(mView);
        VtwoLoginViewModel viewModel = new VtwoLoginViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    //安卓重写返回键事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            CustomDialog.promptDialog(this, "确定要退出程序？", "确定", "取消", false, v -> {
                if(v.getId() == R.id.isOk){
                    //退出
                    AppManager.getAppManager().AppExit(this);
                }
            });
            return false;
        }
        return true;
    }
}
