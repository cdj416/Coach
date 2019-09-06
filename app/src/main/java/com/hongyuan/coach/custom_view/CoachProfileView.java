package com.hongyuan.coach.custom_view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hongyuan.coach.R;
import com.hongyuan.coach.ui.activity.CoachProfileActivity;
import com.hongyuan.coach.ui.beans.CoachHomeBean;

public class CoachProfileView extends RelativeLayout{

    private WebView content;
    private TextView textProfile;
    private TextView goCoachProfile;
    private CoachHomeBean.DataBean dataBean;

    public CoachProfileView(Context context) {
        super(context);
        initLayoutView();
    }

    public CoachProfileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public CoachProfileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_coach_homepage_personal_profile, this);
        content = view.findViewById(R.id.content);
        textProfile = view.findViewById(R.id.textProfile);
        goCoachProfile = view.findViewById(R.id.goCoachProfile);

        goCoachProfile.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), CoachProfileActivity.class);
            intent.putExtra("coach",dataBean);
            getContext().startActivity(intent);
        });
    }


    /*
    * 设置数据
    * */
    public void setData(CoachHomeBean.DataBean item){
        this.dataBean = item;
        textProfile.setText(item.getInfo().getCoach_desc());

    }
}
