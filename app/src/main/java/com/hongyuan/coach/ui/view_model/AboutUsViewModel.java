package com.hongyuan.coach.ui.view_model;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;

import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.base.CustomViewModel;
import com.hongyuan.coach.base.SingleClick;
import com.hongyuan.coach.databinding.ActivityAboutUsBinding;
import com.hongyuan.coach.ui.activity.AgreementActivity;
import com.hongyuan.coach.ui.activity.MyWebViewActivity;
import com.hongyuan.coach.util.CustomDialog;

import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class AboutUsViewModel extends CustomViewModel {

    private ActivityAboutUsBinding binding;

    public AboutUsViewModel(CustomActivity mActivity, ActivityAboutUsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        //获取版本信息
        PackageManager manager = mActivity.getPackageManager();
        String name = null;
        try {
            PackageInfo info = manager.getPackageInfo(mActivity.getPackageName(), 0);
            name = info.versionName;
            binding.banBen.setText(name+"版本");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        binding.callTel.setOnClickListener(new View.OnClickListener() {
            @SingleClick(2000)
            @Override
            public void onClick(View v) {
                CustomDialog.callTel(mActivity, "(0572)2075532", v1 -> {
                    callTel("05722075532");
                });
            }
        });

    }

    //查看用户协议
    public BindingCommand goAgreement = new BindingCommand(() -> startActivity(AgreementActivity.class,null));
    public BindingCommand goYinSi = new BindingCommand(() -> startActivity(MyWebViewActivity.class,null));

}
