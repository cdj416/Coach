package com.hongyuan.coach.ui.view_model;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.EditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.coach.R;
import com.hongyuan.coach.base.BaseBean;
import com.hongyuan.coach.base.Constants;
import com.hongyuan.coach.base.Controller;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.base.CustomViewModel;
import com.hongyuan.coach.base.SingleClick;
import com.hongyuan.coach.custom_view.FlowLayoutManager;
import com.hongyuan.coach.databinding.ActivityEditCourseBinding;
import com.hongyuan.coach.ui.adapter.CourseTypeAdapter;
import com.hongyuan.coach.ui.adapter.EditPriceAdapter;
import com.hongyuan.coach.ui.beans.CourseDetailsBean;
import com.hongyuan.coach.ui.beans.FileBean;
import com.hongyuan.coach.ui.beans.FitTypeListBeans;
import com.hongyuan.coach.ui.beans.RetrunImgBean;
import com.hongyuan.coach.util.BaseUtil;
import com.hongyuan.coach.util.CustomDialog;
import com.hongyuan.coach.util.DividerItemDecoration;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EditCourseViewModel extends CustomViewModel {

    private ActivityEditCourseBinding binding;
    private CourseDetailsBean.DataBean detailsBean;

    private CourseTypeAdapter typeAdapter;
    private FitTypeListBeans typeListBeans;

    private EditPriceAdapter priceAdapter;

    //需要上传的课程价格数据
    private List<CourseDetailsBean.DataBean.PriceListBean> priceList = new ArrayList<>();

    //课程类型选中下标
    private int typePosition = 0;

    //更改课程背景图片路径
    private String coverImgUrl = "";

    public EditCourseViewModel(CustomActivity mActivity, ActivityEditCourseBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {

        binding.changeCoverBox.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View view) {
                CustomDialog.upImg(mActivity);
            }
        });
        //点击添加
        binding.addPrice.setOnClickListener(view -> {
            //添加一条空数据
            CourseDetailsBean.DataBean.PriceListBean priceListBean
                    = new CourseDetailsBean.DataBean.PriceListBean(0,0,0,
                    0,null);
            priceAdapter.getData().add(priceListBean);
            //同步适配器中的数据
            priceAdapter.setNewData(getPriceListData());
        });

        FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
        binding.courseTypeRec.addItemDecoration(new DividerItemDecoration(
                mActivity, DividerItemDecoration.VERTICAL_LIST,30,0x00000000));
        binding.courseTypeRec.setLayoutManager(flowLayoutManager);
        typeAdapter = new CourseTypeAdapter();
        binding.courseTypeRec.setAdapter(typeAdapter);
        typeAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            typePosition = position;
            for(FitTypeListBeans.DataBean dataBean : typeListBeans.getData()){
                dataBean.setSelect(false);
            }
            typeListBeans.getData().get(position).setSelect(true);
            typeAdapter.setNewData(typeListBeans.getData());
        });

        //价格列表
        LinearLayoutManager manager1 = new LinearLayoutManager(mActivity);
        manager1.setOrientation(RecyclerView.VERTICAL);
        binding.priceRec.addItemDecoration(new DividerItemDecoration(
                mActivity, DividerItemDecoration.HORIZONTAL_LIST,1,0xffF1F1F1));
        binding.priceRec.setLayoutManager(manager1);
        priceAdapter = new EditPriceAdapter();
        binding.priceRec.setAdapter(priceAdapter);
        priceAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            priceAdapter.getData().remove(position);
            priceAdapter.setNewData(priceAdapter.getData());
        });

        //发布修改课程
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View view) {
                sendData();
            }
        });

        //区分发布课程还是编辑课程的显示样式
        if(BaseUtil.isValue(getBundle().getString("cp_id"))){
            binding.coverImgText.setText("更换封面");
            binding.submit.setText("保存");
        }else{
            mActivity.getMainTitle().setCentreText("发布课程");
            binding.coverImgText.setText("上传封面");
            binding.submit.setText("发布");
            //添加一条课程价格空数据
            CourseDetailsBean.DataBean.PriceListBean priceListBean
                    = new CourseDetailsBean.DataBean.PriceListBean(0,0,0,
                    0,null);
            priceAdapter.getData().add(priceListBean);
            //同步适配器中的数据
            priceAdapter.setNewData(getPriceListData());
        }
    }

    /*
     * 同步适配器和ui上的数据（简称同步数据）
     * */
    private List<CourseDetailsBean.DataBean.PriceListBean> getPriceListData(){
        for(int i = 0 ; i < priceAdapter.getData().size() ; i++){
            EditText priceEt = (EditText) priceAdapter.getViewByPosition(binding.priceRec,i,R.id.coursePrice);
            EditText nowNumEt = (EditText) priceAdapter.getViewByPosition(binding.priceRec,i,R.id.courseNum);
            String priceStr = "";
            int nowMinNum = 0;
            try {
                priceStr = priceEt.getText().toString();
                nowMinNum = Integer.valueOf(nowNumEt.getText().toString());
            }catch (Exception e){
                e.printStackTrace();
            }
            priceAdapter.getData().get(i).setPrice(priceStr);
            priceAdapter.getData().get(i).setMin_num(nowMinNum);
        }

        return priceAdapter.getData();
    }

    /*
     * 图片选择回调
     * */
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    if(selectList.get(0).getCompressPath() != null){
                        shangChuan(new File(selectList.get(0).getCompressPath()));
                    }
                    break;
            }
        }
    }

    /*
     * 课程背景图片
     * */
    public void shangChuan(File imgFile) {
        clearParams().setParams("type","1");
        FileBean fileBean = new FileBean();
        fileBean.setFileKey("oss_file");
        fileBean.setmFile(imgFile);
        Controller.myRequest(Constants.UPFILE_OSS,Controller.TYPE_POST,getParams(),fileBean, RetrunImgBean.class,this);
    }

    /*
    * 发布或修改课程
    * */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void sendData(){
        //拼凑多个价格字符串
        String cp_price_str = "";

        //使用前，需同步数据
        priceList = getPriceListData();
        if(!BaseUtil.isValue(coverImgUrl)){
            CustomDialog.showMessage(mActivity,"请设置课程背景图片！");
            return;
        }
        if(!BaseUtil.isValue(binding.cpName.getText().toString())){
            CustomDialog.showMessage(mActivity,"请填写课程名！");
            return;
        }
        if(!BaseUtil.isValue(binding.cpContent.getText().toString())){
            CustomDialog.showMessage(mActivity,"请填写课程内容！");
            return;
        }
        if(!BaseUtil.isValue(binding.bePerson.getText().toString())){
            CustomDialog.showMessage(mActivity,"请填写课程适合人群！");
            return;
        }
        if(!BaseUtil.isValue(binding.cpTime.getText().toString())){
            CustomDialog.showMessage(mActivity,"请填写课程时长！");
            return;
        }

        for(int i = 0 ; i < priceList.size() ; i++){
            EditText priceEt = (EditText) priceAdapter.getViewByPosition(binding.priceRec,i,R.id.coursePrice);
            EditText nowNumEt = (EditText) priceAdapter.getViewByPosition(binding.priceRec,i,R.id.courseNum);

            if(!BaseUtil.isValue(priceEt.getText().toString())){
                CustomDialog.showMessage(mActivity,"请填写课程价格！");
                return;
            }
            if(!BaseUtil.isValue(nowNumEt.getText().toString()) || "0".equals(nowNumEt.getText().toString())){
                CustomDialog.showMessage(mActivity,"请填写课程数量！");
                return;
            }
            if(i > 0){
                EditText preNumEt = (EditText) priceAdapter.getViewByPosition(binding.priceRec,i - 1,R.id.courseNum);
                if(Integer.valueOf(nowNumEt.getText().toString()) < Integer.valueOf(preNumEt.getText().toString())){
                    CustomDialog.showMessage(mActivity,"数量需要递增！");
                    return;
                }
            }


            if(i == 0){//等于第一个价格时
                if(priceList.size() <= 1){
                    cp_price_str = priceList.get(i).getMin_num()+","+(priceList.get(i).getMin_num()+1) + "," + priceList.get(i).getPrice();
                }else{
                    cp_price_str = priceList.get(i).getMin_num()+","+(priceList.get(i+1).getMin_num()-1) + "," + priceList.get(i).getPrice();
                }
                continue;
            }else if(i == (priceList.size()-1)){
                cp_price_str += ";"+priceList.get(i).getMin_num()+","+(priceList.get(i).getMin_num()+1) + "," + priceList.get(i).getPrice();
            }else{
                cp_price_str += ";"+priceList.get(i).getMin_num()+","+(priceList.get(i+1).getMin_num()-1) + "," + priceList.get(i).getPrice();
            }
        }
        clearParams().setParams("cp_name",binding.cpName.getText().toString())
                .setParams("ft_id",String.valueOf(typeListBeans.getData().get(typePosition).getFt_id()))
                .setParams("cp_duration",binding.cpTime.getText().toString())
                .setParams("cp_info",binding.cpContent.getText().toString())
                .setParams("cp_people",binding.bePerson.getText().toString())
                .setParams("cp_suggest","")
                .setParams("cp_note","")
                .setParams("cp_price",priceList.get(0).getPrice())
                .setParams("cp_img",coverImgUrl)
                .setParams("cp_num",String.valueOf(priceList.get(0).getMin_num()))
                .setParams("cp_price_str",cp_price_str)
                .setParams("is_ty",binding.switchText.getShowText() ? "1" : "2");
        if(BaseUtil.isValue(getBundle().getString("cp_id"))){
            setParams("cp_id",getBundle().getString("cp_id"));
        }
        //显示加载框
        mActivity.showLoading();
        Controller.myRequest(Constants.ADD_COURSE_PRIVITE,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void lazyLoad() {
        //显示加载框
        mActivity.showLoading();
        //获取课程类型
        Controller.myRequest(Constants.GET_FIT_TYPE_LIST,Controller.TYPE_POST,getParams(), FitTypeListBeans.class,this);

    }

    /*
    * 请求课程详情
    * */
    private void getCourseDetails(){
        //显示加载框
        mActivity.showLoading();
        //获取课程详情
        clearParams().setParams("cp_id",getBundle().getString("cp_id"));
        Controller.myRequest(Constants.GET_COURSE_PRIVITE_INFO,Controller.TYPE_POST,getParams(), CourseDetailsBean.class,this);
    }



    @Override
    public void onSuccess(int code, Object data) {
        if(code == Constants.GET_COURSE_PRIVITE_INFO){
            detailsBean = ((CourseDetailsBean)data).getData();
            RequestOptions options = new RequestOptions().placeholder(R.mipmap.a_test2).error(R.mipmap.a_test2).centerCrop();
            Glide.with(mActivity).load(detailsBean.getCp_img()).apply(options).into(binding.coverImg);

            binding.cpName.setText(detailsBean.getCp_name());
            binding.cpContent.setText(detailsBean.getCp_info());
            binding.cpAddress.setText(detailsBean.getOs_name());
            binding.bePerson.setText(detailsBean.getCp_people());
            binding.cpTime.setText(detailsBean.getCp_duration());

            for(FitTypeListBeans.DataBean dataBean : typeListBeans.getData()){
                if(detailsBean.getFt_id() == dataBean.getFt_id()){
                    dataBean.setSelect(true);
                    break;
                }
            }
            typeAdapter.setNewData(typeListBeans.getData());

            //组装数据
            if (detailsBean.getPrice_list() == null || detailsBean.getPrice_list().size() <= 0){
                List<CourseDetailsBean.DataBean.PriceListBean> priceListBeanList = new ArrayList<>();
                CourseDetailsBean.DataBean.PriceListBean priceListBean
                        = new CourseDetailsBean.DataBean.PriceListBean(0,1,100,
                        0,detailsBean.getCp_price());

                priceListBeanList.add(priceListBean);
                detailsBean.getPrice_list().addAll(priceListBeanList);
            }
            priceAdapter.setNewData(detailsBean.getPrice_list());

            //初始化背景图片链接
            coverImgUrl = detailsBean.getCp_img();

        }

        if(code == Constants.GET_FIT_TYPE_LIST){
            typeListBeans = (FitTypeListBeans)data;

            //区分是编辑还是发布
            if(BaseUtil.isValue(getBundle().getString("cp_id"))){
                //去请求详情数据
                getCourseDetails();
            }else{
                typeListBeans.getData().get(0).setSelect(true);
                typeAdapter.setNewData(typeListBeans.getData());
            }

        }

        if(code == Constants.UPFILE_OSS){
            RetrunImgBean imgBean = (RetrunImgBean)data;
            coverImgUrl = imgBean.getData().getFile_url();
            Glide.with(mActivity).load(coverImgUrl).into(binding.coverImg);
        }

        if(code == Constants.ADD_COURSE_PRIVITE){
            if(BaseUtil.isValue(getBundle().getString("cp_id"))){
                mActivity.showSuccess("课程编辑成！");
            }else{
                mActivity.showSuccess("课程发布成功！");
            }
        }
    }
}
