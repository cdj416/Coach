package com.hongyuan.coach.ui.view_model;
import android.view.View;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.Constants;
import com.hongyuan.coach.base.Controller;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.base.CustomViewModel;
import com.hongyuan.coach.base.MyApplication;
import com.hongyuan.coach.base.SingleClick;
import com.hongyuan.coach.databinding.ActivityVtwoLoginBinding;
import com.hongyuan.coach.ui.activity.AgreementActivity;
import com.hongyuan.coach.ui.activity.MainActivity;
import com.hongyuan.coach.ui.activity.MyWebViewActivity;
import com.hongyuan.coach.ui.activity.VtwoModifyPasswordActivity;
import com.hongyuan.coach.ui.activity.VtwoVerificationLoginActivity;
import com.hongyuan.coach.ui.beans.LoginBean;
import com.hongyuan.coach.util.CustomDialog;
import com.hongyuan.coach.util.SharedPreferencesUtil;
import com.hongyuan.coach.util.ViewChangeUtil;

import cn.jpush.android.api.JPushInterface;

public class VtwoLoginViewModel extends CustomViewModel {

    private ActivityVtwoLoginBinding binding;

    private boolean isSelect = true;

    public VtwoLoginViewModel(CustomActivity mActivity, ActivityVtwoLoginBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {

        //验证码登录
        binding.goVerificationLogin.setOnClickListener(v -> {
            startActivity(VtwoVerificationLoginActivity.class,null);
        });
        //修改密码
        binding.goModifyPassword.setOnClickListener(v -> {
            startActivity(VtwoModifyPasswordActivity.class,null);
        });
        //点击同意
        binding.clickAgree.setOnClickListener(v -> {
            if(isSelect){
                isSelect = false;
                ViewChangeUtil.changeLeftDrawable(mActivity,binding.clickAgree, R.mipmap.vtwo_login_xieyi_default_img);
            }else{
                isSelect = true;
                ViewChangeUtil.changeLeftDrawable(mActivity,binding.clickAgree, R.mipmap.vtwo_login_xieyi_select_img);
            }

        });
        //查看用户协议
        binding.userAgreement.setOnClickListener(v -> {
            startActivity(AgreementActivity.class,null);
        });
        //隐私政策
        binding.privacyPolicy.setOnClickListener(v -> {
            startActivity(MyWebViewActivity.class,null);
        });
        //去注册
        binding.goRegister.setOnClickListener(v -> {
            //startActivity(VtwoRegisterdActivity.class,null);
        });
        //登录按钮
        binding.loginSubmit.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                getLogin();
            }
        });

    }

    /*
     * 调用登录接口
     * */
    private void getLogin(){
        if(!isSelect){
            CustomDialog.showMessage(mActivity,"请阅读并同意相关协议@");
            return;
        }

        if(!isValue(binding.phoneNumber.getText())){
            CustomDialog.showMessage(mActivity,"请输入手机号！");
            return;
        }
        if(!isValue(binding.passwordNumber.getText())){
            CustomDialog.showMessage(mActivity,"请输入密码！");
            return;
        }
        clearParams().setParams("m_mobile", binding.phoneNumber.getText()).setParams("m_pwd",binding.passwordNumber.getText());
        //clearParams().setParams("m_mobile", "18183185173").setParams("m_pwd","cdj123456");
        Controller.myRequest(Constants.MEMBER_LOGIN,Controller.TYPE_POST,getParams(), LoginBean.class,this);
    }

    @Override
    public void onSuccess(int code,Object data) {
        if(data instanceof LoginBean){
            LoginBean.DataBean login = ((LoginBean) data).getData();

            if(login.getRole_id() == 2){
                //存储用户登录信息
                SharedPreferencesUtil.putBean(mActivity,LOGIN_SESSION,login);
                //登录之后去添加登录账户信息
                userToken.setM_id(login.getM_id());
                userToken.setM_mobile(login.getM_mobile());
                userToken.setRole_id(String.valueOf(login.getRole_id()));
                //通过EventBus去通知MainActivity去更新数据
                //EventBus.getDefault().postSticky(new MessageEvent(null));
                mActivity.showSuccess("登录成功", MainActivity.class,null);

                JPushInterface.setAlias(MyApplication.getInstance(), 100, userToken.getM_mobile());//设置别名或标签
            }else{
                showErr("非教练用户！");
            }


        }
    }


}
