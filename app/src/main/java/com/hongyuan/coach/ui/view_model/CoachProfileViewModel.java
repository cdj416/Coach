package com.hongyuan.coach.ui.view_model;

import android.graphics.Color;
import android.view.ViewTreeObserver;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.base.CustomViewModel;
import com.hongyuan.coach.custom_view.StickyScrollView;
import com.hongyuan.coach.databinding.ActivityCoachProfileBinding;
import com.hongyuan.coach.ui.adapter.CerterAdapter;
import com.hongyuan.coach.ui.beans.CoachHomeBean;
import com.hongyuan.coach.util.DividerItemDecoration;
import com.hongyuan.coach.util.ViewChangeUtil;

public class CoachProfileViewModel extends CustomViewModel implements StickyScrollView.ScrollViewListener {

    private ActivityCoachProfileBinding binding;
    private CoachHomeBean.DataBean coachBean;

    //渐变高度
    private int height;


    public CoachProfileViewModel(CustomActivity mActivity, ActivityCoachProfileBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        coachBean = (CoachHomeBean.DataBean)mActivity.getIntentData("coach");

        RequestOptions options = new RequestOptions().placeholder(R.mipmap.a_test2).error(R.mipmap.a_test2).centerCrop();
        Glide.with(mActivity).load(coachBean.getInfo().getCoach_head()).apply(options).into(binding.headImg);
        binding.coachName.setText(coachBean.getInfo().getCoach_nickname());
        binding.coachGrade.setText("私教专业P"+coachBean.getInfo().getCoach_level());
        binding.experienceText.setText(coachBean.getInfo().getCoach_experience());
        binding.goodAt.setText(coachBean.getInfo().getCoach_skill());
        binding.certificationNUm.setText(coachBean.getCerts().size()+"项");

        if(coachBean.getInfo().getMi_sex() == 1){
            ViewChangeUtil.changeRightDrawable(mActivity,binding.coachName,R.mipmap.person_boby_mark_img);
        }else{
            ViewChangeUtil.changeRightDrawable(mActivity,binding.coachName,R.mipmap.person_girl_mark_img);
        }

        //认证项
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(layoutManager);
        binding.mRecycler.addItemDecoration(new DividerItemDecoration(
                mActivity, DividerItemDecoration.HORIZONTAL_LIST,30,mActivity.getResources().getColor(R.color.transparent)));
        CerterAdapter mtAdapter = new CerterAdapter();
        binding.mRecycler.setAdapter(mtAdapter);
        mtAdapter.setNewData(coachBean.getCerts());

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

                binding.nScroll.setScrollViewListener(CoachProfileViewModel.this);
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
}
