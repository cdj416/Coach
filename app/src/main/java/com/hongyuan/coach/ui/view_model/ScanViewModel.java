package com.hongyuan.coach.ui.view_model;
import android.os.Vibrator;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.base.CustomViewModel;
import com.hongyuan.coach.databinding.ActivityScanBinding;
import com.hongyuan.coach.ui.beans.ScanBeans;
import com.hongyuan.coach.util.CustomDialog;
import com.hongyuan.coach.util.GsonUtil;

import cn.bingoogolapple.qrcode.core.QRCodeView;

public class ScanViewModel extends CustomViewModel implements QRCodeView.Delegate {

    //灯光是否打开
    private boolean isOpen;

    private ActivityScanBinding binding;
    public ScanViewModel(CustomActivity mActivity, ActivityScanBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        binding.mQRCodeView.setDelegate(this);
        binding.openLight.setOnClickListener(v -> {
            if(!isOpen){
                binding.mQRCodeView.openFlashlight();//开灯
                binding.openLight.setImageResource(R.mipmap.open_diantong_status_img);
                isOpen = true;
            }else{
                binding.mQRCodeView.closeFlashlight();//关灯
                binding.openLight.setImageResource(R.mipmap.close_diantong_status_img);
                isOpen = false;
            }
        });
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        vibrate();
        try {
            Log.e("cdj","======"+result);
            ScanBeans beans = GsonUtil.getGson().fromJson(result, new TypeToken<ScanBeans>(){}.getType());
            if("1".equals(beans.getHref_type())){
                //跳转原生
                //JumpUtils.goAtherPage(this,beans.getHref_code(),beans.getHref_id());
            }else if("2".equals(beans.getHref_type())){
                //跳转h5
            }else{
                showDialog();
            }
        }catch (Exception e){
            e.printStackTrace();
            showDialog();
        }
    }

    /*
    * 抖动效果
    * */
    private void vibrate() {
        Vibrator vibrator = (Vibrator)mActivity.getSystemService(mActivity.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {
//        这里是通过修改提示文案来展示环境是否过暗的状态，接入方也可以根据 isDark 的值来实现其他交互效果
//        String tipText = binding.mQRCodeView.getScanBoxView().getTipText();
//        String ambientBrightnessTip = "\n环境过暗，请打开闪光灯";
//        if (isDark) {
//            if (!tipText.contains(ambientBrightnessTip)) {
//                binding.mQRCodeView.getScanBoxView().setTipText(tipText + ambientBrightnessTip);
//            }
//        } else {
//            if (tipText.contains(ambientBrightnessTip)) {
//                tipText = tipText.substring(0, tipText.indexOf(ambientBrightnessTip));
//                binding.mQRCodeView.getScanBoxView().setTipText(tipText);
//            }
//        }
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        //扫码失败
    }

    /*
    * 弹框提示处理
    * */
    private void showDialog(){
        CustomDialog.promptDialog(mActivity,
                "抱歉，二维码有误，是否在尝试一次？",
                "确定",
                "取消", true,
                v -> {
                    if(v.getId() == R.id.isOk){
                        binding.mQRCodeView.startSpot();
                    }else{
                        mActivity.finish();
                    }
                });
    }
}
