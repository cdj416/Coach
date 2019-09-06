package com.hongyuan.coach.ui.my_view;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.base.SingleClick;
import com.hongyuan.coach.ui.activity.AboutUsActivity;
import com.hongyuan.coach.ui.activity.CoachHomePageActivity;
import com.hongyuan.coach.ui.activity.MyStudentsActivity;
import com.hongyuan.coach.ui.activity.SettingActivity;

public class PersonContentView extends LinearLayout implements View.OnClickListener {

    private TextView titleName,rightText,tv1,tv2,tv3;
    private ImageView iv1,iv2,iv3;
    private LinearLayout box1,box2,box3,box4,box5,secondBox;

    //显示类型
    private int showType;
    private final int MY_COURSE = 0;
    private final int MY_SERVICE = 1;

    //需要传的值
    private String coach_mid;

    public PersonContentView(Context context) {
        super(context);
        initLayoutView();
    }

    public PersonContentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PersonContentView);
        showType = a.getInt(R.styleable.PersonContentView_person_show_type,0);

        initLayoutView();
    }

    public PersonContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_person_content, this);
        titleName = view.findViewById(R.id.titleName);
        rightText = findViewById(R.id.rightText);
        tv1 = view.findViewById(R.id.tv1);
        tv2 = view.findViewById(R.id.tv2);
        tv3 = view.findViewById(R.id.tv3);
        iv1 = view.findViewById(R.id.iv1);
        iv2 = view.findViewById(R.id.iv2);
        iv3 = view.findViewById(R.id.iv3);
        box1 = view.findViewById(R.id.box1);
        box2 = view.findViewById(R.id.box2);
        box3 = view.findViewById(R.id.box3);
        box4 = view.findViewById(R.id.box4);
        box5 = view.findViewById(R.id.box5);
        secondBox = view.findViewById(R.id.secondBox);

        box1.setOnClickListener(this);
        box2.setOnClickListener(this);
        box3.setOnClickListener(this);
        box4.setOnClickListener(this);
        box5.setOnClickListener(this);
        setShowView(showType);
    }

    /*
    * 设置传参值
    * */
    public void setCoachId(String coach_mid){
        this.coach_mid = coach_mid;
    }

    /*
    * 初始化显示类型
    * */
    private void setShowView(int showType){
        if(showType == MY_COURSE){
            secondBox.setVisibility(GONE);
            titleName.setText("我的服务");
            rightText.setVisibility(GONE);
            tv1.setText("我的主页");
            tv2.setText("设置");
            tv3.setText("关于我们");
            iv1.setImageResource(R.mipmap.person_member_img);
            iv2.setImageResource(R.mipmap.person_setting_img);
            iv3.setImageResource(R.mipmap.person_aboutus_img);
        }

        if(showType == MY_SERVICE){

        }
    }

    @SingleClick(2000)
    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()){

            case R.id.box1:
                bundle.putString("coach_mid",coach_mid);
                ((CustomActivity)getContext()).startActivity(CoachHomePageActivity.class,bundle);
                break;

            case R.id.box2:
                ((CustomActivity)getContext()).startActivity(SettingActivity.class,null);
                break;

            case R.id.box3:
                ((CustomActivity)getContext()).startActivity(AboutUsActivity.class,null);
                break;

            case R.id.box4:

                break;
            case R.id.box5:

                break;
        }
    }
}
