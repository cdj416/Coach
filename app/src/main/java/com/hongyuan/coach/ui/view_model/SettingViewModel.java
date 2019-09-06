package com.hongyuan.coach.ui.view_model;

import android.view.View;
import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.base.CustomViewModel;
import com.hongyuan.coach.base.SingleClick;
import com.hongyuan.coach.databinding.ActivitySettingBinding;
import com.hongyuan.coach.ui.activity.VtwoLoginActivity;
import com.hongyuan.coach.ui.beans.LoginBean;
import com.hongyuan.coach.util.CacheUtil;
import com.hongyuan.coach.util.CustomDialog;
import com.hongyuan.coach.util.SharedPreferencesUtil;

public class SettingViewModel extends CustomViewModel {

    private ActivitySettingBinding binding;

    public SettingViewModel(CustomActivity mActivity, ActivitySettingBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        try {
            binding.cacheNum.setText(CacheUtil.getTotalCacheSize(mActivity));
        }catch (Exception e){
            e.printStackTrace();
        }

        binding.cacheBox.setOnClickListener(new View.OnClickListener() {
            @SingleClick(2000)
            @Override
            public void onClick(View v) {
                CacheUtil.clearAllCache(mActivity);
                try {
                    binding.cacheNum.setText(CacheUtil.getTotalCacheSize(mActivity));
                }catch (Exception e){
                    e.printStackTrace();
                }
                showSuccess("缓存清除成功！");
            }
        });

        binding.getOut.setOnClickListener(new View.OnClickListener() {
            @SingleClick(2000)
            @Override
            public void onClick(View v) {
                CustomDialog.promptDialog(mActivity, "确定要退出登录吗？", "确定", "取消", false, v1 -> {
                    if(v1.getId() == R.id.isOk){
                        //置空登录信息
                        LoginBean.DataBean login = new LoginBean.DataBean();
                        //情况页面数据
                        SharedPreferencesUtil.putBean(mActivity,LOGIN_SESSION,login);
                        startActivity(VtwoLoginActivity.class,null);
                        mActivity.finish();
                    }
                });
            }
        });
    }

}
