package com.hongyuan.coach.ui.my_view;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.coach.R;
import com.hongyuan.coach.ui.beans.CoachHomeBean;
import com.makeramen.roundedimageview.RoundedImageView;

public class PersonHeaderView extends LinearLayout {

    private RoundedImageView headImg;
    private TextView userName;

    public PersonHeaderView(Context context) {
        super(context);
        initLayoutView();
    }

    public PersonHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public PersonHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_person_header, this);
        headImg = view.findViewById(R.id.headImg);
        userName = view.findViewById(R.id.userName);

        headImg.setOnClickListener(v -> {
            //((CustomActivity)getContext()).startActivity(PersonMessageActivity.class);
            //upHeadimg();
        });
    }

    public void setHeadImg(CoachHomeBean.DataBean.InfoBean bean){
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img).centerCrop();
        Glide.with(getContext()).load(bean.getCoach_head()).apply(options).into(headImg);
        userName.setText(bean.getCoach_nickname());
    }
}
