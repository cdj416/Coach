package com.hongyuan.coach.ui.view_model;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

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
import com.hongyuan.coach.databinding.ActivityCommentDetailsBinding;
import com.hongyuan.coach.ui.adapter.CommentDetailsAdapter;
import com.hongyuan.coach.ui.beans.CommentDetailsBean;
import com.hongyuan.coach.ui.beans.CommentDetailsLikeBean;
import com.hongyuan.coach.ui.beans.PostDetailsCommentsBean;
import com.hongyuan.coach.ui.beans.PostDetailsSendMeassageBean;
import com.hongyuan.coach.util.DividerItemDecoration;
import com.hongyuan.coach.util.TimeUtil;
import com.hongyuan.coach.util.ViewChangeUtil;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

import static me.goldze.mvvmhabit.utils.Utils.getContext;

public class CommentDetailsViewModel extends CustomViewModel implements KeyboardLayout.onKeyboaddsChangeListener {

    private ActivityCommentDetailsBinding binding;
    private PostDetailsCommentsBean.DataBean.ListBean crBin;
    private CommentDetailsAdapter adapter;
    private CommentDetailsBean dataBeans;

    private String cr_id_father = "0";//当前帖子的id
    private String first_cr_id = "0";//当前帖子的父类id

    //要更改的数据position
    private int mPosition = -1;

    public CommentDetailsViewModel(CustomActivity mActivity, ActivityCommentDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableRefresh(true);
        setEnableLoadMore(true);

        crBin = (PostDetailsCommentsBean.DataBean.ListBean)mActivity.getIntent().getExtras().getSerializable("crBib");
        cr_id_father = String.valueOf(crBin.getCr_id());
        first_cr_id = String.valueOf(crBin.getCr_id());

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.HORIZONTAL_LIST,1,0xFFEEEEEE));
        binding.mRecycler.setLayoutManager(manager);
        adapter = new CommentDetailsAdapter();
        binding.mRecycler.setAdapter(adapter);

        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
        Glide.with(mActivity).load(crBin.getMi_head()).apply(options).into(binding.headImg);
        binding.fName.setText(crBin.getM_name());
        binding.postContent.setText(crBin.getCr_content());
        binding.attention.setText(String.valueOf(crBin.getPraise_num()));
        binding.timeAfter.setText(TimeUtil.friendly_time(crBin.getAdd_date()));

        if(crBin.getIs_praise() == 0){
            ViewChangeUtil.changeLeftDrawable(mActivity,binding.attention,R.mipmap.like_huise_img);
        }else{
            ViewChangeUtil.changeLeftDrawable(mActivity,binding.attention,R.mipmap.like_chengse_img);
        }

        binding.outBox.setOnkbdStateListener(this);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick(2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId() == R.id.attention){
                    mPosition = position;
                    sendLike(dataBeans.getData().getList().get(position).getIs_praise(),dataBeans.getData().getList().get(position).getCr_id());
                }else{
                    cr_id_father = String.valueOf(dataBeans.getData().getList().get(position).getCr_id());
                    first_cr_id = String.valueOf(crBin.getCr_id());
                    showEditext();
                }
            }
        });
        binding.goBack.setOnClickListener(v -> mActivity.finish());
    }

    @Override
    public void refreshData() {
        dataBeans = null;
        lazyLoad();
    }

    @Override
    protected void loadMoreData() {
        lazyLoad();
    }

    @Override
    public void lazyLoad() {
        //读取圈子帖子评论列表
        clearParams().setParams("cr_id",String.valueOf(crBin.getCr_id()));
        Controller.myRequest(Constants.GET_CIRCLE_REVIEWLIST_SON, Controller.TYPE_POST,getParams(), CommentDetailsBean.class,this);
    }


    //打开软键盘输入评论
    public BindingCommand showEditext = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            cr_id_father = String.valueOf(crBin.getCr_id());
            first_cr_id = String.valueOf(crBin.getCr_id());
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
    //点赞
    public BindingCommand likeClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            mPosition = -1;
            sendLike(crBin.getIs_praise(),crBin.getCr_id());
        }
    });

    /*
     * 提交评论信息
     * */
    private void sendComment(){
        if(!isValue(binding.editText.getText().toString())){
            showErr("请求输入评论内容！");
            return;
        }

        clearParams().setParams("circle_id",String.valueOf(crBin.getCircle_id())).setParams("cr_id_father",cr_id_father)
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
        cr_id_father = String.valueOf(crBin.getCr_id());
        first_cr_id = String.valueOf(crBin.getCr_id());
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

    @Override
    public void onSuccess(int code,Object data) {
        if(data instanceof CommentDetailsBean){
            CommentDetailsBean pageData = (CommentDetailsBean)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    dataBeans = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    dataBeans.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(dataBeans != null && dataBeans.getData() != null &&
                    dataBeans.getData().getList() != null &&
                    dataBeans.getData().getList().size() > 0){
                adapter.setNewData(dataBeans.getData().getList());
                //mActivity.setPromtView(mActivity.SHOW_DATA);
            }else{
                //mActivity.setPromtView( mActivity.SHOW_EMPTY);
            }
        }

        if(data instanceof PostDetailsSendMeassageBean){
            //提交成功隐藏软键盘
            hideEditext();
            //刷新评论数据
            lazyLoad();
        }

        //点赞，取消点赞
        if(data instanceof CommentDetailsLikeBean){

            if(mPosition != -1){
                if(dataBeans.getData().getList().get(mPosition).getIs_praise() == 0){
                    dataBeans.getData().getList().get(mPosition).setIs_praise(1);
                    dataBeans.getData().getList().get(mPosition).setPraise_num(dataBeans.getData().getList().get(mPosition).getPraise_num()+1);
                    showSuccess("点赞成功！");
                }else{
                    dataBeans.getData().getList().get(mPosition).setIs_praise(0);
                    dataBeans.getData().getList().get(mPosition).setPraise_num(dataBeans.getData().getList().get(mPosition).getPraise_num()-1);
                    showSuccess("已取消点赞！");
                }
                adapter.setNewData(dataBeans.getData().getList());
            }else{
                if(crBin.getIs_praise() == 0){
                    crBin.setIs_praise(1);
                    crBin.setPraise_num(crBin.getPraise_num()+1);
                    showSuccess("点赞成功！");
                    ViewChangeUtil.changeLeftDrawable(mActivity,binding.attention,R.mipmap.like_chengse_img);
                }else {
                    crBin.setIs_praise(0);
                    crBin.setPraise_num(crBin.getPraise_num()-1);
                    showSuccess("已取消点赞！");
                    ViewChangeUtil.changeLeftDrawable(mActivity,binding.attention,R.mipmap.like_huise_img);
                }
                binding.attention.setText(String.valueOf(crBin.getPraise_num()));
            }
        }
    }
}
