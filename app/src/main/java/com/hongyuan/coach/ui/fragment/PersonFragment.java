package com.hongyuan.coach.ui.fragment;

import android.view.View;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.Constants;
import com.hongyuan.coach.base.Controller;
import com.hongyuan.coach.base.CustomFragment;
import com.hongyuan.coach.ui.beans.CoachHomeBean;
import com.hongyuan.coach.ui.my_view.PersonContentView;
import com.hongyuan.coach.ui.my_view.PersonHeaderView;
import com.hongyuan.coach.ui.my_view.PersonNumberView;

public class PersonFragment extends CustomFragment {


    private CoachHomeBean coachHomeBean;
    private PersonHeaderView headView;
    private PersonNumberView numberView;
    private PersonContentView contentView;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_person;
    }

    @Override
    public void initView(View mView) {
        headView = mView.findViewById(R.id.headView);
        numberView = mView.findViewById(R.id.numberView);
        contentView = mView.findViewById(R.id.contentView);
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        //读取个人信息
        clearParams();
        Controller.myRequest(Constants.GET_COACH_INFEX_INFO,Controller.TYPE_POST,getParams(), CoachHomeBean.class,this);
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == Constants.GET_COACH_INFEX_INFO){
            coachHomeBean = (CoachHomeBean)data;
            headView.setHeadImg(coachHomeBean.getData().getInfo());
            numberView.setData(coachHomeBean.getData());
            contentView.setCoachId(String.valueOf(coachHomeBean.getData().getInfo().getM_id()));
        }
    }
}
