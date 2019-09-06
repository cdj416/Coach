package com.hongyuan.coach.ui.view_model;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.coach.R;
import com.hongyuan.coach.base.Constants;
import com.hongyuan.coach.base.Controller;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.base.CustomViewModel;
import com.hongyuan.coach.base.SingleClick;
import com.hongyuan.coach.custom_view.KeyboardLayout;
import com.hongyuan.coach.custom_view.nine_gridimg.NineGridImageViewAdapter;
import com.hongyuan.coach.databinding.ActivityPostDetailsBinding;
import com.hongyuan.coach.ui.activity.CommentDetailsActivity;
import com.hongyuan.coach.ui.adapter.PostDetailsAdapter;
import com.hongyuan.coach.ui.beans.AttentionBean;
import com.hongyuan.coach.ui.beans.CommentDetailsLikeBean;
import com.hongyuan.coach.ui.beans.PostDetailsCommentsBean;
import com.hongyuan.coach.ui.beans.PostDetailsLikeBean;
import com.hongyuan.coach.ui.beans.PostDetailsSendMeassageBean;
import com.hongyuan.coach.ui.beans.PostDetailsTopBean;
import com.hongyuan.coach.util.DividerItemDecoration;
import com.hongyuan.coach.util.JumpUtils;
import com.hongyuan.coach.util.TimeUtil;
import com.hongyuan.coach.util.ViewChangeUtil;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.enitity.UserViewInfo;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

import static me.goldze.mvvmhabit.utils.Utils.getContext;

public class PostDetailsViewModel extends CustomViewModel implements View.OnClickListener, KeyboardLayout.onKeyboaddsChangeListener,
        PostDetailsAdapter.ReturnClick {
    private ActivityPostDetailsBinding binding;
    private PostDetailsAdapter adapter;
    private String circle_id;
    private PostDetailsTopBean topBean;
    private PostDetailsCommentsBean commentsBean;


    private String cr_id_father = "0";//当前帖子的id
    private String first_cr_id = "0";//当前帖子的父类id

    //要更改的数据position
    private int mPosition = -1;

    public PostDetailsViewModel(CustomActivity mActivity, ActivityPostDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        setEnableLoadMore(true);
        setEnableRefresh(true);
        circle_id = getBundle().getString("circle_id");

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.HORIZONTAL_LIST,1,0xFFEEEEEE));
        binding.mRecycler.setLayoutManager(manager);
        adapter = new PostDetailsAdapter(this);
        binding.mRecycler.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick(2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId() == R.id.attention){
                    mPosition = position;
                    sendLike(commentsBean.getData().getList().get(position).getIs_praise(),
                            commentsBean.getData().getList().get(position).getCr_id());
                }else if(view.getId() == R.id.commentNum){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("crBib",commentsBean.getData().getList().get(position));
                    startActivity(CommentDetailsActivity.class,bundle);
                }else{
                    cr_id_father = String.valueOf(commentsBean.getData().getList().get(position).getCr_id());
                    first_cr_id = String.valueOf(commentsBean.getData().getList().get(position).getCr_id());
                    showEditext();
                }
            }
        });

        binding.outBox.setOnkbdStateListener(this);
    }

    @Override
    public void refreshData() {
        commentsBean = null;
        lazyLoad();
    }

    @Override
    protected void loadMoreData() {
        getComment();
    }

    @Override
    public void lazyLoad() {
        getTopBean();
        getComment();
    }
    //帖子点赞
    public BindingCommand postLike = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            getBaikeLike();
        }
    });
    //打开软键盘输入评论
    public BindingCommand showEditext = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            cr_id_father = "0";
            first_cr_id = "0";
            showEditext();
        }
    });
    //关闭软键盘输入
    public BindingCommand closeEidt = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            hideEditext();
        }
    });
    //提交评论
    public BindingCommand sendMessage = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            sendComment();
        }
    });
    //关注、取消关注
    public BindingCommand sendAttention = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            mPosition = -1;
            sendAttention();
        }
    });


    /*
     * 帖子点赞/取消
     * */
    private void getBaikeLike(){
        clearParams().setParams("circle_id",String.valueOf(circle_id));
        if(topBean.getData().getIs_praise() == 0){
            Controller.myRequest(Constants.ADD_CIRCLE_PRAISE,Controller.TYPE_POST,getParams(), PostDetailsLikeBean.class,this);
        }else{
            Controller.myRequest(Constants.CANCEL_CIRCLE_PRAISE,Controller.TYPE_POST,getParams(), PostDetailsLikeBean.class,this);
        }
    }

    /*
    * 获取圈子的评论列表
    * */
    private void getComment(){
        //读取圈子帖子评论列表
        clearParams().setParams("circle_id",circle_id);
        Controller.myRequest(Constants.GET_RIRCLE_REVIEWLIST,Controller.TYPE_POST,getParams(), PostDetailsCommentsBean.class,this);
    }

    /*
    * 提交评论信息
    * */
    private void sendComment(){
        if(!isValue(binding.editText.getText().toString())){
            showErr("请求输入评论内容！");
            return;
        }

        clearParams().setParams("circle_id",circle_id).setParams("cr_id_father",cr_id_father)
                .setParams("cr_content",binding.editText.getText().toString())
                .setParams("first_cr_id",first_cr_id);
        Controller.myRequest(Constants.ADD_CIRCLE_REVIEW,Controller.TYPE_POST,getParams(), PostDetailsSendMeassageBean.class,this);
    }

    /*
     * 点赞/取消点赞
     * */
    private void sendLike(int is_praise,int cr_id){
        if(is_praise == 0){
            clearParams().setParams("cr_id",String.valueOf(cr_id));
            Controller.myRequest(Constants.ADD_CIRCLE_REVIEW_PRAISE,Controller.TYPE_POST,getParams(), CommentDetailsLikeBean.class,this);
        }else{
            clearParams().setParams("cr_id",String.valueOf(cr_id));
            Controller.myRequest(Constants.CANCEL_CIRCLE_REVIEW_PRAISE,Controller.TYPE_POST,getParams(), CommentDetailsLikeBean.class,this);
        }
    }

    /*
    * 关注/取消关注
    * */
    private void sendAttention(){
        clearParams().setParams("f_mid",String.valueOf(topBean.getData().getM_id()));
        if(topBean.getData().getIs_friend() == 0){
            setParams("f_type","add");
        }else{
            setParams("f_type","reduce");
        }
        Controller.myRequest(Constants.ADD_FRIEND,Controller.TYPE_POST,getParams(), AttentionBean.class,this);
    }

    /*
    * 获取帖子基础信息
    * */
    private void getTopBean(){
        //圈子帖子详情
        clearParams().setParams("circle_id",circle_id);
        Controller.myRequest(Constants.GET_CIRCLE_INFO,Controller.TYPE_POST,getParams(), PostDetailsTopBean.class,this);
    }


    @Override
    public void onSuccess(int code,Object data) {
        if(data instanceof PostDetailsTopBean){
            topBean = (PostDetailsTopBean)data;
            setData(topBean);
        }
        if(data instanceof PostDetailsCommentsBean){
            PostDetailsCommentsBean pageData = (PostDetailsCommentsBean)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    commentsBean = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    commentsBean.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(commentsBean != null && commentsBean.getData() != null &&
                    commentsBean.getData().getList() != null &&
                    commentsBean.getData().getList().size() > 0){
                adapter.setNewData(commentsBean.getData().getList());
                //mActivity.setPromtView(mActivity.SHOW_DATA);
            }else{
                //mActivity.setPromtView( mActivity.SHOW_EMPTY);
            }
        }
        if(data instanceof PostDetailsSendMeassageBean){
            //提交成功隐藏软键盘
            hideEditext();
            //刷新评论数据
            getComment();
        }
        if(data instanceof CommentDetailsLikeBean){
            if(mPosition != -1){
                if(commentsBean.getData().getList().get(mPosition).getIs_praise() == 0){
                    commentsBean.getData().getList().get(mPosition).setIs_praise(1);
                    commentsBean.getData().getList().get(mPosition).setPraise_num(commentsBean.getData().getList().get(mPosition).getPraise_num()+1);
                    showSuccess("点赞成功！");
                }else{
                    commentsBean.getData().getList().get(mPosition).setIs_praise(0);
                    commentsBean.getData().getList().get(mPosition).setPraise_num(commentsBean.getData().getList().get(mPosition).getPraise_num()-1);
                    showSuccess("已取消点赞！");
                }
                adapter.setNewData(commentsBean.getData().getList());
            }
        }

        if(data instanceof PostDetailsLikeBean){
            if(topBean.getData().getIs_praise() == 0){
                topBean.getData().setIs_praise(1);
                topBean.getData().setPraise_num(topBean.getData().getPraise_num()+1);
                showSuccess("点赞成功！");
                ViewChangeUtil.changeLeftDrawable(mActivity,binding.tvLike,R.mipmap.like_chengse_img);
            }else {
                topBean.getData().setIs_praise(0);
                topBean.getData().setPraise_num(topBean.getData().getPraise_num()-1);
                showSuccess("已取消点赞！");
                ViewChangeUtil.changeLeftDrawable(mActivity,binding.tvLike,R.mipmap.like_huise_img);
            }
            binding.tvLike.setText(String.valueOf(topBean.getData().getPraise_num()));
        }

        if(code == Constants.ADD_FRIEND){
            if(topBean.getData().getIs_friend() == 0){
                topBean.getData().setIs_friend(1);
                binding.attention.setText("已关注");
                binding.attention.setBackgroundResource(R.drawable.shape_radius18_e8e8f4);
                showSuccess("关注成功！");
            }else{
                topBean.getData().setIs_friend(0);
                binding.attention.setText("关注");
                binding.attention.setBackgroundResource(R.drawable.shape_radius16_ef5b48);
                showSuccess("已取消关注！");
            }
        }
    }

    /*
    * 给页面数据赋值
    * */
    private void setData(PostDetailsTopBean data){
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
        Glide.with(mActivity).load(data.getData().getMi_head()).apply(options).into(binding.headImg);
        binding.fName.setText(data.getData().getCircle_name());
        //binding.label.setText(data.getData().getCircle_name());目前标签内容还没有
        binding.timeAfter.setText(TimeUtil.friendly_time(data.getData().getAdd_date()));
        binding.postContent.setText(data.getData().getCircle_content());
        binding.tvComment.setText(String.valueOf(data.getData().getReview_num()));
        binding.tvLike.setText(String.valueOf(data.getData().getPraise_num()));
        if(data.getData().getCi() != null && data.getData().getCi().size() > 0 && data.getData().getCi().get(0).getCircle_type() == 2){
            binding.videoBox.setVisibility(View.VISIBLE);
            binding.nineGridImg.setVisibility(View.GONE);
            binding.videoBox.setOnClickListener(this);
            Glide.with(mActivity).load(data.getData().getCircle_img()).into(binding.videoImg);
        }else
        if(data.getData().getCi() != null && data.getData().getCi().size() > 0 && data.getData().getCi().get(0).getCircle_type() == 1){
            binding.videoBox.setVisibility(View.GONE);
            binding.nineGridImg.setVisibility(View.VISIBLE);
            binding.nineGridImg.setAdapter(nineGridImageAdapter);
            binding.nineGridImg.setImagesData(data.getData().getCi());
        }else{
            binding.videoBox.setVisibility(View.GONE);
            binding.nineGridImg.setVisibility(View.GONE);
        }

        if(data.getData().getIs_friend() == 0){
            binding.attention.setText("关注");
            binding.attention.setBackgroundResource(R.drawable.shape_radius16_ef5b48);
        }else{
            binding.attention.setText("已关注");
            binding.attention.setBackgroundResource(R.drawable.shape_radius18_e8e8f4);
        }

        if(data.getData().getIs_praise() == 0){
            ViewChangeUtil.changeLeftDrawable(mActivity,binding.tvLike,R.mipmap.like_huise_img);
        }else{
            ViewChangeUtil.changeLeftDrawable(mActivity,binding.tvLike,R.mipmap.like_chengse_img);
        }

    }

    /*
     * 九宫格适配器
     * */
    private NineGridImageViewAdapter<PostDetailsTopBean.DataBean.CiBean> nineGridImageAdapter = new NineGridImageViewAdapter<PostDetailsTopBean.DataBean.CiBean>() {
        @Override
        protected void onDisplayImage(Context context, ImageView imageView, PostDetailsTopBean.DataBean.CiBean s) {
            if(s.getCircle_type() == 1){
                RequestOptions options = new RequestOptions().placeholder(R.mipmap.a_test2).error(R.mipmap.a_test2).centerCrop();
                Glide.with(mActivity).load(s.getFile_src()).apply(options).into(imageView);
            }
        }

        @Override
        protected ImageView generateImageView(Context context) {
            return super.generateImageView(context);
        }

        @Override
        protected void onItemImageClick(Context context, List<ImageView> mImageViewList, ImageView imageView, int index, List<PostDetailsTopBean.DataBean.CiBean> list) {

            //点击查看大图功能
            GPreviewBuilder.from((Activity) context)
                    .setData(getInfoList(list,mImageViewList))
                    .setCurrentIndex(index)
                    .setType(GPreviewBuilder.IndicatorType.Dot)
                    .start();
        }
    };

    /*
     * 获取图片集和图片所处位置
     * */
    private List<UserViewInfo> getInfoList(List<PostDetailsTopBean.DataBean.CiBean> list, List<ImageView> mImageViewList){
        List<UserViewInfo> imgList = new ArrayList<>();
        for(int i = 0 ; i < list.size() ; i++){
            imgList.add(new UserViewInfo(list.get(i).getFile_src()));
            Rect bounds = new Rect();
            mImageViewList.get(i).getGlobalVisibleRect(bounds);
            imgList.get(i).setBounds(bounds);
        }

        return imgList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.videoBox:
                Bundle bundle = new Bundle();
                bundle.putString("videoPath",topBean.getData().getCi().get(0).getFile_src());
                bundle.putString("videoImgPath",topBean.getData().getCircle_img());
                JumpUtils.goToVideoPlayer(mActivity, v,bundle);
                break;
        }
    }

    /*
    * 显示软键盘
    * */
    private void showEditext(){
        InputMethodManager imm = (InputMethodManager) binding.editText.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            binding.editText.requestFocus();
            imm.showSoftInput(binding.editText, 0);
        }
    }

    /*
    *隐藏软键盘
    * */
    private void hideEditext(){
        binding.editText.setText("");
        cr_id_father = "0";
        first_cr_id = "0";
        InputMethodManager imm = (InputMethodManager) binding.editText.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(binding.editText.getWindowToken(),0);
        }
    }


    @Override
    public void onKeyBoardStateChange(int state) {
        switch (state) {
            case KeyboardLayout.KEYBOARD_STATE_HIDE:
                binding.mengCheng.setVisibility(View.GONE);
                break;
            case KeyboardLayout.KEYBOARD_STATE_SHOW:
                binding.mengCheng.setVisibility(View.VISIBLE);
                break;
        }
    }

    /*
    * 子项评论点击事件
    * */
    @Override
    public void returnClick(int partentPosition, int position) {
        cr_id_father = String.valueOf(commentsBean.getData().getList().get(partentPosition).getList_s().get(position).getCr_id());
        first_cr_id = String.valueOf(commentsBean.getData().getList().get(partentPosition).getCr_id());
        //打开输入框
        showEditext();
    }
}
