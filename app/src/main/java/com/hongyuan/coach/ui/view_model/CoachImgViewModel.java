package com.hongyuan.coach.ui.view_model;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.coach.R;
import com.hongyuan.coach.base.BaseBean;
import com.hongyuan.coach.base.Constants;
import com.hongyuan.coach.base.Controller;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.base.CustomViewModel;
import com.hongyuan.coach.base.SingleClick;
import com.hongyuan.coach.databinding.ActivityCoachPhotoBinding;
import com.hongyuan.coach.ui.adapter.CocahImageAdapter;
import com.hongyuan.coach.ui.beans.CoachImgBeans;
import com.hongyuan.coach.ui.beans.MoreImgBean;
import com.hongyuan.coach.util.BaseUtil;
import com.hongyuan.coach.util.CustomDialog;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.enitity.UserViewInfo;

import java.util.ArrayList;
import java.util.List;

public class CoachImgViewModel extends CustomViewModel {

    private ActivityCoachPhotoBinding binding;
    private CocahImageAdapter adapter;
    private CoachImgBeans.DataBean imgBeans;

    public CoachImgViewModel(CustomActivity mActivity, ActivityCoachPhotoBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableRefresh(true);
        setEnableLoadMore(true);
        mActivity.getMainTitle().setCentreText(getBundle().getString("title"));
        binding.showPhotoTitle.setText(getBundle().getString("title"));


        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 2);
        binding.mRecycler.setLayoutManager(layoutManager);
        adapter = new CocahImageAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {

            if(view.getId() == R.id.markChick){
                for(int i = 0 ; i < imgBeans.getList().size() ; i++){
                    if(i == position){
                        if(imgBeans.getList().get(position).isSelect()){
                            imgBeans.getList().get(position).setSelect(false);
                        }else{
                            imgBeans.getList().get(position).setSelect(true);
                        }
                    }
                }
                adapter.setNewData(imgBeans.getList());
            }else{
                //点击查看大图功能
                GPreviewBuilder.from(mActivity)
                        .setData(getInfoList(imgBeans.getList(),view))
                        .setCurrentIndex(position)
                        .setType(GPreviewBuilder.IndicatorType.Dot)
                        .start();
            }
        });

        //取消编辑
        mActivity.getMainTitle().getRightView().setOnClickListener(view -> {
            goEditStatus(false);
        });
        //编辑图片
        binding.bmageBox.setOnClickListener(view -> {

            goEditStatus(true);
        });
        //删除照片
        binding.delectImgBox.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View view) {
                deletePhotos();
            }
        });
        //弹出上传照片框
        binding.updateBox.setOnClickListener(view -> {
            openOrClose(true);
        });
        //关闭上传弹框
        binding.closeAddImgBox.setOnClickListener(view -> {
            openOrClose(false);
        });
        //开始上传照片
        binding.startUpdate.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View view) {
                updataFile();
            }
        });
    }

    /*
    * 拼装要删除相片的id
    * */
    private String getIds(){
        String ids = "";
        for(int i = 0 ; i < imgBeans.getList().size() ; i++){
            if(imgBeans.getList().get(i).isSelect()){
                ids += ","+imgBeans.getList().get(i).getCp_id();
            }
        }

        return ids;
    }

    /*
    * 进入/退出，编辑状态
    * */
    private void goEditStatus(boolean flag){
        for(CoachImgBeans.DataBean.ListBean imgBeans : imgBeans.getList()){
            imgBeans.setEdite(flag);
        }
        adapter.setNewData(imgBeans.getList());

        if(flag){
            mActivity.getMainTitle().setRightText("取消编辑");
            binding.bmageBox.setVisibility(View.GONE);
            binding.updateBox.setVisibility(View.GONE);
            binding.delectImgBox.setVisibility(View.VISIBLE);
        }else{
            mActivity.getMainTitle().getRightView().setVisibility(View.GONE);
            binding.bmageBox.setVisibility(View.VISIBLE);
            binding.updateBox.setVisibility(View.VISIBLE);
            binding.delectImgBox.setVisibility(View.GONE);
        }
    }

    /*
     * 获取图片集和图片所处位置
     * */
    private List<UserViewInfo> getInfoList(List<CoachImgBeans.DataBean.ListBean> list, View view){
        List<UserViewInfo> imgList = new ArrayList<>();
        for(int i = 0 ; i < list.size() ; i++){
            imgList.add(new UserViewInfo(list.get(i).getImg_src()));
            Rect bounds = new Rect();
            view.getGlobalVisibleRect(bounds);
            imgList.get(i).setBounds(bounds);
        }

        return imgList;
    }

    /*
    * 删除照片
    * */
    private void deletePhotos(){
        if(!BaseUtil.isValue(getIds())){
            CustomDialog.showMessage(mActivity,"请选择删除的照片！");
            return;
        }
        clearParams().setParams("ids",getIds().substring(1));
        Controller.myRequest(Constants.DEL_PHOTOS,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void refreshData(){
        imgBeans = null;
        lazyLoad();
    }

    @Override
    protected void loadMoreData() {
        lazyLoad();
    }

    @Override
    public void lazyLoad() {
        clearParams().setParams("coach_mid",getBundle().getString("coach_mid"))
                .setParams("photo_category_id",getBundle().getString("photo_category_id"));
        Controller.myRequest(Constants.GET_COACH_PHOTO_LIST,Controller.TYPE_POST,getParams(), CoachImgBeans.class,this);
    }

    @Override
    public void onSuccess(int code,Object data) {
        if(data instanceof CoachImgBeans){
            CoachImgBeans.DataBean pageData = ((CoachImgBeans) data).getData();
            if(curPage == FIRST_PAGE){
                if(pageData.getList() != null && pageData.getList().size() > 0){
                    imgBeans = pageData;
                }
            }else{
                if(pageData.getList() != null && pageData.getList().size() > 0){
                    imgBeans.getList().addAll(pageData.getList());
                }
            }

            if(imgBeans != null && imgBeans!= null &&
                    imgBeans.getList() != null &&
                    imgBeans.getList().size() > 0){
                adapter.setNewData(imgBeans.getList());
                mActivity.setPromtView(mActivity.SHOW_DATA);

                //显示上传照片的页面的数据
                RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img);
                Glide.with(mActivity).load(imgBeans.getList().get(0).getImg_src()).apply(options).into(binding.showOneImg);
            }else{
                mActivity.setPromtView( mActivity.SHOW_EMPTY);
            }
        }

        if(code == Constants.DEL_PHOTOS){
            goEditStatus(false);
            lazyLoad();
            showSuccess("删除成功！");
        }

        if(code == Constants.UPFILE_OSS_MORE){
            MoreImgBean moreImgBean = (MoreImgBean)data;
            putImageCoach(moreImgBean.getData());
        }

        if(code == Constants.ADD_COACH_PHOTO){
            lazyLoad();
            openOrClose(false);
            showSuccess("上传成功！");
        }
    }

    /****************************************上传相册*********************************************/


    /*
     * 开始上传文件
     * */
    private void updataFile(){
        if(binding.addImg.getImgList() != null && binding.addImg.getImgList().size() > 0){
            mActivity.showLoading();
            Controller.myRequest(Constants.UPFILE_OSS_MORE,Controller.TYPE_POST,getKeyValueList(binding.addImg.getImgList()), MoreImgBean.class,this);
        }else{
            CustomDialog.showMessage(mActivity,"请选择相片！");
        }
    }

    /*
    * 开始上传相片到教练门下
    * */
    private void putImageCoach(MoreImgBean.DataBean dataBean){
        mActivity.showLoading();

        clearParams().setParams("list_str",getImgStr(dataBean))
                .setParams("photo_category_id",getBundle().getString("photo_category_id"));
        Controller.myRequest(Constants.ADD_COACH_PHOTO,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    /*
    * 拼装需要上传的图片链接
    * */
    private String getImgStr(MoreImgBean.DataBean imgBean){
        String urlStr = "";
        for(MoreImgBean.DataBean.FileUrlBean urlBean:imgBean.getFile_url()){
            urlStr+=","+urlBean.getOss_url();
        }
        return urlStr.substring(1);
    }

    //上传页面的打开与关闭
    private void openOrClose(boolean flag){
        if(flag){
            //初始化一下
            binding.addImg.setNoimg();

            Animation anim = AnimationUtils.loadAnimation(mActivity, R.anim.dialog_in_anim);
            binding.updateImgBox.startAnimation(anim);
            binding.updateImgBox.setVisibility(View.VISIBLE);
            mActivity.getMainTitle().setVisibility(View.GONE);
        }else{
            Animation anim = AnimationUtils.loadAnimation(mActivity, R.anim.dialog_out_anim);
            binding.updateImgBox.startAnimation(anim);
            binding.updateImgBox.setVisibility(View.GONE);
            mActivity.getMainTitle().setVisibility(View.VISIBLE);
        }
    }
}
