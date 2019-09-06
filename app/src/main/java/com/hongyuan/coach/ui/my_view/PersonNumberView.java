package com.hongyuan.coach.ui.my_view;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.base.SingleClick;
import com.hongyuan.coach.ui.activity.CourseListActivity;
import com.hongyuan.coach.ui.activity.MyStudentsActivity;
import com.hongyuan.coach.ui.beans.CoachHomeBean;
public class PersonNumberView extends LinearLayout implements View.OnClickListener {

    private TextView courseNum,balance,studentsNum;
    private LinearLayout courseBox,studentsBox;

    private CoachHomeBean.DataBean dataBean;

    public PersonNumberView(Context context) {
        super(context);
        initLayoutView();
    }

    public PersonNumberView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public PersonNumberView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_person_number, this);
        courseNum = view.findViewById(R.id.courseNum);
        balance = view.findViewById(R.id.balance);
        studentsNum = view.findViewById(R.id.studentsNum);
        courseBox = view.findViewById(R.id.courseBox);
        studentsBox = view.findViewById(R.id.studentsBox);

        courseBox.setOnClickListener(this);
        studentsBox.setOnClickListener(this);
    }

    public void setData(CoachHomeBean.DataBean dataBean){
        this.dataBean = dataBean;
        courseNum.setText(String.valueOf(dataBean.getCount_courses()));
        studentsNum.setText(String.valueOf(dataBean.getCount_students()));
    }

    @SingleClick(2000)
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.courseBox){
            Bundle bundle = new Bundle();
            bundle.putString("jl_mid",String.valueOf(dataBean.getInfo().getM_id()));
            ((CustomActivity)getContext()).startActivity(CourseListActivity.class,bundle);
        }
        if(v.getId() == R.id.studentsBox){
            ((CustomActivity)getContext()).startActivity(MyStudentsActivity.class,null);
        }
    }
}
