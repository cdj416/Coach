package com.hongyuan.coach.ui.view_model;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.coach.R;
import com.hongyuan.coach.base.Constants;
import com.hongyuan.coach.base.Controller;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.base.CustomViewModel;
import com.hongyuan.coach.custom_view.StickyScrollView;
import com.hongyuan.coach.custom_view.comm_title.CommentTitleView;
import com.hongyuan.coach.databinding.ActivityCoachHomepageBinding;
import com.hongyuan.coach.ui.activity.CoachImgActivity;
import com.hongyuan.coach.ui.activity.CourseDetailsActivity;
import com.hongyuan.coach.ui.activity.CourseListActivity;
import com.hongyuan.coach.ui.activity.EditTimeActivity;
import com.hongyuan.coach.ui.adapter.CoachHomePageCouseListAdapter;
import com.hongyuan.coach.ui.adapter.CommentAdapter;
import com.hongyuan.coach.ui.beans.CoachHomeBean;
import com.hongyuan.coach.ui.beans.CoachKongTimeBeans;
import com.hongyuan.coach.ui.beans.CommentBeans;
import com.hongyuan.coach.ui.beans.CommentsNumBeans;
import com.hongyuan.coach.ui.beans.CouseListBean;
import com.hongyuan.coach.util.BaseUtil;
import com.hongyuan.coach.util.CustomDialog;
import com.hongyuan.coach.util.DividerItemDecoration;
import com.hongyuan.coach.util.TimeUtil;
import com.hongyuan.coach.util.ViewChangeUtil;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class CoachHomePageViewModel extends CustomViewModel implements CommentTitleView.ScoreChange, StickyScrollView.ScrollViewListener {
    private ActivityCoachHomepageBinding binding;
    private CoachHomePageCouseListAdapter adapter;
    private CommentAdapter commentAdapter;
    //教练数据实体类
    private CoachHomeBean coachHomeBean;
    //课程实体类
    private CouseListBean listBean;
    //教练id
    private String coach_mid;

    //评论数据
    private CommentBeans commentBeans;
    //评论分数
    private int coach_s;

    //渐变高度
    private int height;

    public CoachHomePageViewModel(CustomActivity mActivity, ActivityCoachHomepageBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableRefresh(true);
        setEnableLoadMore(true);

        coach_mid = getBundle().getString("coach_mid");


        //教练的全部课程
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        binding.mRecycler.setLayoutManager(layoutManager);
        adapter = new CoachHomePageCouseListAdapter();
        binding.mRecycler.setAdapter(adapter);

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("cp_id",String.valueOf(listBean.getData().getList().get(position).getCp_id()));
            startActivity(CourseDetailsActivity.class,bundle);
        });

        //评论的适配
        LinearLayoutManager manager1 = new LinearLayoutManager(mActivity);
        manager1.setOrientation(RecyclerView.VERTICAL);
        binding.commentRecycler.addItemDecoration(new DividerItemDecoration(
                mActivity, DividerItemDecoration.HORIZONTAL_LIST,1,0xffF1F1F1));
        binding.commentRecycler.setLayoutManager(manager1);
        commentAdapter = new CommentAdapter();
        binding.commentRecycler.setAdapter(commentAdapter);

        binding.showSelectTime.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("jl_mid",String.valueOf(coachHomeBean.getData().getInfo().getM_id()));
            startActivity(EditTimeActivity.class,bundle);
        });

        //拨打电话
        binding.myTitle.getRightView().setOnClickListener(v -> {
            CustomDialog.callTel(mActivity, coachHomeBean.getData().getInfo().getM_mobile(), v1 -> {
                callTel(coachHomeBean.getData().getInfo().getM_mobile());
            });
        });

        //初始化评论筛选回调函数
        binding.commentTitle.setScoreChange(this);

        //标题渐变
        initListeners();
    }

    /*
     * 获取头部图片的高度
     * */
    private void initListeners() {
        ViewTreeObserver vto = binding.bgImg.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                binding.titleBox.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = binding.bgImg.getHeight();

                binding.nScroll.setScrollViewListener(CoachHomePageViewModel.this);
            }
        });
    }

    @Override
    public void onScrollChanged(StickyScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 0) {   //设置标题的背景颜色
            binding.titleBox.setBackgroundColor(Color.argb((int) 0, 99,130,236));
        } else if (y > 0 && y <= height) {
            float scale = (float) y / height;
            float alpha = (255 * scale);
            //binding.titleBox.setTextColor(Color.argb((int) alpha, 255,255,255));
            binding.titleBox.setBackgroundColor(Color.argb((int) alpha, 99,130,236));
        } else {
            binding.titleBox.setBackgroundColor(Color.argb( 255, 99,130,236));
        }
    }

    //查看更多课程
    public BindingCommand moreClass = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("jl_mid",String.valueOf(coach_mid));
            startActivity(CourseListActivity.class,bundle);
        }
    });


    @Override
    public void lazyLoad() {
        mActivity.showLoading();

        commentBeans = null;
        //私教--私教教练最近有空时间
        clearParams().setParams("coach_mid",coach_mid);
        Controller.myRequest(Constants.GET_COACH_LAST_KONG_TIME,Controller.TYPE_POST,getParams(), CoachKongTimeBeans.class,this);

        //加载教练信息
        clearParams();
        Controller.myRequest(Constants.GET_COACH_INFEX_INFO,Controller.TYPE_POST,getParams(), CoachHomeBean.class,this);

        //加载私教课
        clearParams().setParams("cp_mid",mActivity.userToken.getM_id());
        Controller.myRequest(Constants.GET_COURSE_PRIVITE_LIST,Controller.TYPE_POST,getParams(), CouseListBean.class,this);

        //获取评论数
        clearParams().setParams("coach_mid",coach_mid);
        Controller.myRequest(Constants.GET_COACH_REVIEW_SUM,Controller.TYPE_POST,getParams(), CommentsNumBeans.class,this);

        if(coach_s != CommentTitleView.HAVE_IMG_COMMENT){
            //加载教练评论
            getComment();
        }else{
            getHaveImgComment();
        }

    }

    /*
    * 刷新
    * */
    @Override
    public void refreshData(){
        commentBeans = null;
        lazyLoad();
    }

    /*
    * 分页
    * */
    @Override
    protected void loadMoreData() {
        if(coach_s != CommentTitleView.HAVE_IMG_COMMENT){
            //加载教练评论
            getComment();
        }else{
            getHaveImgComment();
        }
    }

    /*
    * 筛选条件的回调
    * */
    @Override
    public void valueChange(int score) {
        curPage = FIRST_PAGE;
        commentBeans = null;
        this.coach_s = score;
        if(coach_s != CommentTitleView.HAVE_IMG_COMMENT){
            //加载教练评论
            getComment();
        }else{
            getHaveImgComment();
        }
    }

    /*
    * 读取私教--评论私教课列表
    * */
    private void getComment(){
        //私教--评论私教课列表
        clearParams().setParams("coach_mid",coach_mid);
        if(coach_s != CommentTitleView.ALL_COMMENT){
            setParams("coach_s",String.valueOf(coach_s));
        }
        Controller.myRequest(Constants.GET_COACH_REVIEW_LIST,Controller.TYPE_POST,getParams(), CommentBeans.class,this);
    }
    /*
    * 私教--评论私教课列表--有图片
    * */
    private void getHaveImgComment(){
        //私教--评论私教课列表
        clearParams().setParams("coach_mid",coach_mid);
        Controller.myRequest(Constants.GET_COACH_REVIEW_IMG_LIST,Controller.TYPE_POST,getParams(), CommentBeans.class,this);
    }

    @Override
    protected void setData() {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img).centerCrop();
        Glide.with(mActivity).load(coachHomeBean.getData().getInfo().getCoach_head()).apply(options).into(binding.headImg);

        binding.coachProfile.setData(coachHomeBean.getData());
        binding.storeName.setText(coachHomeBean.getData().getInfo().getOs_name());
        binding.coachName.setText(coachHomeBean.getData().getInfo().getCoach_nickname());
        binding.coachGrade.setText("私教专业P"+coachHomeBean.getData().getInfo().getCoach_level());
        binding.upClassNum.setText(BaseUtil.getNoZoon(coachHomeBean.getData().getInfo().getCoach_review_count()));
        binding.evaluationNum.setText(BaseUtil.getNoZoon(coachHomeBean.getData().getInfo().getCoach_s()));
        binding.myRat.setRating(Float.valueOf(coachHomeBean.getData().getInfo().getCoach_s()));

        if(coachHomeBean.getData().getInfo().getMi_sex() == 1){
            ViewChangeUtil.changeRightDrawable(mActivity,binding.coachName,R.mipmap.person_boby_mark_img);
        }else{
            ViewChangeUtil.changeRightDrawable(mActivity,binding.coachName,R.mipmap.person_girl_mark_img);
        }

        setPhoto(coachHomeBean);
    }

    @Override
    public void onSuccess(int code,Object data) {
        if(data instanceof CommentsNumBeans){
            CommentsNumBeans commentsNumBeans = (CommentsNumBeans)data;
            binding.commentTitle.setNum(commentsNumBeans.getData());
        }

        if(data instanceof CoachHomeBean){
            coachHomeBean = (CoachHomeBean)data;

            setData();
        }

        if(data instanceof CouseListBean){
            listBean = (CouseListBean)data;
            adapter.setNewData(listBean.getData().getList());
        }

        if(data instanceof CoachKongTimeBeans){
            CoachKongTimeBeans timeBeans = (CoachKongTimeBeans)data;
            if(TimeUtil.isToday(timeBeans.getData().getKong_date(),TimeUtil.dateFormatYMDHMS)){
                binding.coachTime.setText("今日可约 "+TimeUtil.formatDate(timeBeans.getData().getKong_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM));
            }else{
                binding.coachTime.setText(TimeUtil.formatDate(timeBeans.getData().getKong_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatMDHM));
            }
        }

        if(data instanceof CommentBeans){
            CommentBeans pageData = (CommentBeans)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    commentBeans = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    commentBeans.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(commentBeans != null && commentBeans.getData() != null &&
                    commentBeans.getData().getList() != null &&
                    commentBeans.getData().getList().size() > 0){
                commentAdapter.setNewData(commentBeans.getData().getList());
                binding.commentRecycler.setVisibility(View.VISIBLE);
                binding.loadBox.setVisibility(View.GONE);
            }else{
                binding.commentRecycler.setVisibility(View.GONE);
                binding.loadBox.setVisibility(View.VISIBLE);
            }
        }
    }

    /*
    * 设置相册数据
    * */
    private void setPhoto(CoachHomeBean coachHomeBean){
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.yueka_bg).error(R.mipmap.yueka_bg).centerCrop();

        if(coachHomeBean.getData().getC_photo1() != null && coachHomeBean.getData().getC_photo1().getImg_src() != null){
            Glide.with(mActivity).load(coachHomeBean.getData().getC_photo1().getImg_src()).apply(options).into(binding.coachStyle);
        }
        if(coachHomeBean.getData().getC_photo2() != null && coachHomeBean.getData().getC_photo2().getImg_src() != null){
            Glide.with(mActivity).load(coachHomeBean.getData().getC_photo2().getImg_src()).apply(options).into(binding.successCase);

        }
        if(coachHomeBean.getData().getC_photo3() != null && coachHomeBean.getData().getC_photo3().getImg_src() != null){
            Glide.with(mActivity).load(coachHomeBean.getData().getC_photo3().getImg_src()).apply(options).into(binding.certificateImg);
        }

        binding.styleBox.setOnClickListener(v -> {
            if(coachHomeBean.getData().getC_photo1() != null && coachHomeBean.getData().getC_photo1().getImg_src() != null){
                Bundle bundle = new Bundle();
                bundle.putString("title","教练风采");
                bundle.putString("coach_mid",String.valueOf(coachHomeBean.getData().getInfo().getM_id()));
                bundle.putString("photo_category_id","1");
                startActivity(CoachImgActivity.class,bundle);
            }else{
                CustomDialog.showMessage(mActivity,"暂无详细照片！");
            }
        });

        binding.caseBox.setOnClickListener(v -> {
            if(coachHomeBean.getData().getC_photo2() != null && coachHomeBean.getData().getC_photo2().getImg_src() != null){
                Bundle bundle = new Bundle();
                bundle.putString("title","成功案例");
                bundle.putString("coach_mid",String.valueOf(coachHomeBean.getData().getInfo().getM_id()));
                bundle.putString("photo_category_id","2");
                startActivity(CoachImgActivity.class,bundle);
            }else{
                CustomDialog.showMessage(mActivity,"暂无详细照片！");
            }
        });

        binding.certificateBox.setOnClickListener(v -> {
            if(coachHomeBean.getData().getC_photo3() != null && coachHomeBean.getData().getC_photo3().getImg_src() != null){
                Bundle bundle = new Bundle();
                bundle.putString("title","专业证书");
                bundle.putString("coach_mid",String.valueOf(coachHomeBean.getData().getInfo().getM_id()));
                bundle.putString("photo_category_id","3");
                startActivity(CoachImgActivity.class,bundle);
            }else{
                CustomDialog.showMessage(mActivity,"暂无详细照片！");
            }

        });
    }

}
