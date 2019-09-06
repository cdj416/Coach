package com.hongyuan.coach.ui.view_model;

import android.Manifest;
import android.annotation.SuppressLint;
import android.view.View;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.Constants;
import com.hongyuan.coach.base.Controller;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.base.CustomViewModel;
import com.hongyuan.coach.base.TokenBean;
import com.hongyuan.coach.databinding.ActivityStartupPageBinding;
import com.hongyuan.coach.ui.activity.MainActivity;
import com.hongyuan.coach.ui.activity.VtwoLoginActivity;
import com.hongyuan.coach.ui.beans.LoginBean;
import com.hongyuan.coach.util.CustomDialog;
import com.hongyuan.coach.util.EncryptionUtil;
import com.hongyuan.coach.util.SharedPreferencesUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

public class StartupPageVeiwModel extends CustomViewModel implements CustomDialog.DialogClick {

    //登录所存储的数据
    private LoginBean.DataBean loginBean;

    //需要申请的权限，必须先在AndroidManifest.xml有声明，才可以动态获取权限
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.VIBRATE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.ACCESS_FINE_LOCATION};
    private RxPermissions rxPermissions = null;
    private String promptMessage = "请授权，否则无法使用该应用,请谅解！";

    public StartupPageVeiwModel(CustomActivity mActivity, ActivityStartupPageBinding binding) {
        super(mActivity);
        initView();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initView() {
        //权限申请
        rxPermissions = new RxPermissions(mActivity);
        rxPermissions.request(PERMISSIONS_STORAGE).subscribe(aBoolean -> {
            if (!aBoolean) {// 用户拒绝了权限
                CustomDialog.promptDialog(mActivity,promptMessage,"开启授权","取消",false,this);
            }else{
                goGoGo();
            }
        });
    }

    /*
     * 当权限通过时，需要做的操作
     * */
    private void goGoGo(){
        //获取登录信息
        loginBean = (LoginBean.DataBean) SharedPreferencesUtil.getBean(mActivity,LOGIN_SESSION);
        lazyLoad();
    }

    @Override
    public void lazyLoad() {
        clearParams().setParams("client","Android").setParams("at_name","fit").setParams("at_pwd", EncryptionUtil.md5DecodeTwo("123456"));
        Controller.myRequest(Constants.GET_TOKEN,Controller.TYPE_POST,getParams(), TokenBean.class,this);
    }

    @Override
    public void onSuccess(int code, Object data) {
        //存储token信息
        if(data instanceof TokenBean){
            TokenBean.DataBean reqToken = ((TokenBean)data).getData();
            userToken.setAt_id(reqToken.getAt_id());
            userToken.setAt_name(reqToken.getAt_name());
            userToken.setAt_pwd(reqToken.getAt_pwd());
            userToken.setToken(reqToken.getToken());

            //如果之前有登录过，就设置登录信息
            if(loginBean != null && loginBean.getM_id() != null){
                userToken.setM_id(loginBean.getM_id());
                userToken.setM_mobile(loginBean.getM_mobile());

                startActivity(MainActivity.class,null);
                mActivity.finish();
            }else{
                startActivity(VtwoLoginActivity.class,null);
                mActivity.finish();
            }

        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void dialogClick(View v) {
        if(v.getId() == R.id.isCannel){
            System.exit(0);
        }
        if(v.getId() == R.id.isOk){
            rxPermissions.request(PERMISSIONS_STORAGE).subscribe(aBoolean -> {
                if (!aBoolean) {// 用户拒绝了权限
                    CustomDialog.promptDialog(mActivity,promptMessage,"开启授权","取消",false,this);
                }else{
                    goGoGo();
                }
            });
        }
    }
}
