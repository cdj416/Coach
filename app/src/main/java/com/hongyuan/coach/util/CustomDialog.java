package com.hongyuan.coach.util;


import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;


public class CustomDialog {
    /*
     * 点击事件的回调 接口
     * */
    public interface DialogClick{
        void dialogClick(View v);
    }

    /*
     * 黑框提示信息
     * */
    public static void showMessage(Context mContext,String message){
        final Dialog dialog = new Dialog(mContext, R.style.MessageTheme);
        View view = View.inflate(mContext, R.layout.dialog_show_message,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.main_menu_animStyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        HourMeterUtil hourMeterUtil = new HourMeterUtil();
        TextView mengCheng = view.findViewById(R.id.mengCheng);
        TextView messages = view.findViewById(R.id.messages);
        mengCheng.setText(message);
        messages.setText(message);
        hourMeterUtil.startCount();
        hourMeterUtil.setTimeCallBack(passedTime -> {
            if(passedTime >= 3){
                dialog.dismiss();
                hourMeterUtil.stopCount();
            }
        });
    }

    /*
     * 1.普通对话弹框
     * 2.有两个取消和确定两个按钮，并按钮有回调。
     * */
    public static void promptDialog(Context mContext,String messagesText,String isOkText,String isCannelText,boolean outSide,DialogClick dialogClick){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_have_yes_no_prompt,null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(outSide);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.main_menu_animStyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView messages = view.findViewById(R.id.messages);
        TextView isOk = view.findViewById(R.id.isOk);
        TextView isCannel = view.findViewById(R.id.isCannel);

        isOk.setText(isOkText);
        isCannel.setText(isCannelText);
        messages.setText(messagesText);

        isOk.setOnClickListener(v -> {
            if(dialogClick != null){
                dialogClick.dialogClick(v);
            }
            dialog.dismiss();
        });
        isCannel.setOnClickListener(v -> {
            if(dialogClick != null){
                dialogClick.dialogClick(v);
            }
            dialog.dismiss();
        });
    }

    /*
     * 拨打电话弹框
     * */
    public static void callTel(Context mContext,String telNum ,DialogClick dialogClick ){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_call_tel,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.main_menu_animStyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView telContent= view.findViewById(R.id.telContent);
        telContent.setText(telNum);
        view.findViewById(R.id.cancel).setOnClickListener(v -> dialog.dismiss());
        view.findViewById(R.id.call).setOnClickListener(v -> {
            if(dialogClick != null){
                dialogClick.dialogClick(v);
            }
            dialog.dismiss();
        });
    }

    /*
     * 个人中心待上课---私教课签到成功弹框
     * */
    public static void priviteCoursePunchSuccess(Context mContext,String date,String week){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_privitecourse_check_success,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.bottom_in_out);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView showDate = view.findViewById(R.id.showDate);
        TextView showWeek = view.findViewById(R.id.showWeek);
        showDate.setText(date);
        showWeek.setText(week);
        view.findViewById(R.id.closeImg).setOnClickListener(view1 -> dialog.dismiss());

        view.findViewById(R.id.close).setOnClickListener(v -> dialog.dismiss());
    }

    /*
     * 选择定位地址弹框
     * */
    public static void selectLocation(Context mContext){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_select_location,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_in_out);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        view.findViewById(R.id.closeImg).setOnClickListener(v -> dialog.dismiss());
        view.findViewById(R.id.locationAddress).setOnClickListener(v -> dialog.dismiss());
        view.findViewById(R.id.nearbyAddress).setOnClickListener(v -> dialog.dismiss());
    }

    /*
    * 选择图片弹框
    * */
    public static void upImg(CustomActivity mActivity){
        Dialog dialog = new Dialog(mActivity, R.style.DialogTheme);
        View view = View.inflate(mActivity, R.layout.dialog_updata_header,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_in_out);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView camera = view.findViewById(R.id.camera);
        TextView Album = view.findViewById(R.id.Album);
        camera.setOnClickListener(v -> {
            dialog.dismiss();
            openSection(mActivity);
        });
        Album.setOnClickListener(v -> {
            dialog.dismiss();
            openSection(mActivity);
        });
        view.findViewById(R.id.closeDialog).setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    /*
     * 打开相册
     * */
    private static void openSection(CustomActivity mActivity){
        PictureSelector.create(mActivity)
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .isZoomAnim(true)//图片列表点击 缩放效果 默认true
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

}
