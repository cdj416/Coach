package com.hongyuan.coach.ui.view_model;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.view.View;
import com.hongyuan.coach.base.Constants;
import com.hongyuan.coach.base.Controller;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.base.CustomViewModel;
import com.hongyuan.coach.base.SingleClick;
import com.hongyuan.coach.custom_view.ChooseImgOrVideoView;
import com.hongyuan.coach.databinding.ActivityEditPostBinding;
import com.hongyuan.coach.ui.beans.LocationBean;
import com.hongyuan.coach.ui.beans.MoreImgBean;
import com.hongyuan.coach.ui.beans.UpdataSueccse;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class EditPostViewModel extends CustomViewModel {

    //一下变量是发帖需要的参数
    private String circle_img;//视频第一帧以上传的图片地址
    private String circlefile = "";//已上传的文件地址拼接字符串
    private String circle_type;//上传的文件类型

    private ActivityEditPostBinding binding;
    public EditPostViewModel(CustomActivity mActivity, ActivityEditPostBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        mActivity.getMainTitle().setRightText("发布");
        //发布圈子
        mActivity.getMainTitle().getRightView().setOnClickListener(new View.OnClickListener() {
            @SingleClick(2000)
            @Override
            public void onClick(View v) {
                //先把图片上传了
                submitFiles();
            }
        });
    }

    //跳转到选择圈子页面
    public BindingCommand goChooseCircle = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //startActivity(ChooseCircletActivity.class,null);
        }
    });
    //选择图片
    public BindingCommand chooseImg = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            binding.imgOrVideo.selectContent(ChooseImgOrVideoView.SHOW_IMG);
        }
    });
    //选择视频
    public BindingCommand chooseVideo = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            binding.imgOrVideo.selectContent(ChooseImgOrVideoView.SHOW_VEDIO);
        }
    });
    //选择位置
    public BindingCommand chooseLocation = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //startActivity(GetNearyLocationActivity.class,null);
        }
    });

    /*
    * 上传图片
    * */
    @SuppressLint("CheckResult")
    public void submitFiles(){
        if(!isValue(binding.editContent.getText().toString())){
            showErr("请输入你要分享的文字内容哦？");
            return;
        }
        updataFile();
    }

    /*
    * 开始上传文件
    * */
    private void updataFile(){
        if(binding.imgOrVideo.getImgList() != null && binding.imgOrVideo.getImgList().size() > 0){
            onLoading("正在努力的上传数据，请耐心等待！");
            Controller.myRequest(Constants.UPFILE_OSS_MORE,Controller.TYPE_POST,getKeyValueList(binding.imgOrVideo.getImgList()),MoreImgBean.class,this);
        }
    }

    /*
    * 发布圈子
    * */
    public void submitData(){
        clearParams().setParams("circle_categoryid","1").setParams("circle_name","减肥交流")
                .setParams("circle_content",binding.editContent.getText().toString())
                .setParams("lat", "11.0255465633255")
                .setParams("lng","11.0255465633255")
                .setParams("os_id",String.valueOf(LocationBean.getInstance().getOs_id()))
                .setParams("circlefile",circlefile)
                .setParams("circle_type",circle_type);
        if(isValue(circle_img)){
            setParams("circle_img",circle_img);
        }
        Controller.myRequest(Constants.ADD_CIRCLE,Controller.TYPE_POST,getParams(), UpdataSueccse.class,this);
    }


    @Override
    public void onSuccess(int code,Object data) {
        if(data instanceof MoreImgBean){
            MoreImgBean imgBeans = (MoreImgBean)data;
            //去区分
            setReqPar(imgBeans);
            //去请求
            submitData();
        }

        if(data instanceof UpdataSueccse){
            mActivity.showSuccess("发布成功！");
        }
    }

    /*
    * 拼接circlefile参数
    * */
    public void setReqPar(MoreImgBean imgBeans){
        if(binding.imgOrVideo.nowType == ChooseImgOrVideoView.SHOW_IMG){
            circle_img = "";
            circle_type = "1";
            for (int i = 0 ; i < imgBeans.getData().getFile_url().size() ; i++){
                if(isValue(imgBeans.getData().getFile_url().get(i).getOss_url())){
                    if(i ==  (imgBeans.getData().getFile_url().size() -1)){
                        circlefile += imgBeans.getData().getFile_url().get(i).getOss_url();
                    }else{
                        circlefile += imgBeans.getData().getFile_url().get(i).getOss_url() + ",";
                    }

                }
            }
        }else{
            circle_type = "2";
            for(int i = 0 ; i < imgBeans.getData().getFile_url().size() ; i++){
                if(isVideoUrl(imgBeans.getData().getFile_url().get(i).getOss_url())){
                    circlefile = imgBeans.getData().getFile_url().get(i).getOss_url();
                }else{
                    circle_img = imgBeans.getData().getFile_url().get(i).getOss_url();
                }
            }
        }

    }

    /*
    * 判断是否是视频路径
    * */
    public boolean isVideoUrl(String url){
        if(url.contains(".mp4") || url.contains(".avi") || url.contains(".flv") || url.contains(".rmvb") || url.contains(".swf")){
            return true;
        }
        return false;
    }
}
