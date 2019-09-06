package com.hongyuan.coach.custom_view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.SingleClick;


/*
* 标题自定义view
* */
public class TitleView extends LinearLayout implements View.OnClickListener {



    //获取初始数据
    private Drawable leftImageDrawble;
    private Drawable centerImageDrawble;
    private Drawable rightImageDrawble;
    private String leftText;
    private String centerText;
    private String rightText;
    private int text_color;
    private boolean lineShow;

    //获取view控件
    private TextView tv_left;
    private TextView tv_center;
    private TextView tv_right;
    private ImageView iv_left;
    private ImageView iv_center;
    private ImageView iv_right;
    private LinearLayout ll_left;
    private LinearLayout ll_center;
    private LinearLayout ll_right;
    private LinearLayout ll_left_content;
    private LinearLayout ll_center_content;
    private LinearLayout ll_right_content;
    private View title_bg;
    private View title_line;


    //view
    private View view;
    //获取activity
    private Activity activity;

    public TitleView(Context context) {
        super(context);
        initLayoutView();

    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TitleView);

        leftImageDrawble = a.getDrawable(R.styleable.TitleView_left_image);
        centerImageDrawble = a.getDrawable(R.styleable.TitleView_center_image);
        rightImageDrawble = a.getDrawable(R.styleable.TitleView_right_image);

        leftText = a.getString(R.styleable.TitleView_left_text);
        centerText = a.getString(R.styleable.TitleView_center_text);
        rightText = a.getString(R.styleable.TitleView_right_text);
        text_color = a.getColor(R.styleable.TitleView_text_color, getResources().getColor(R.color.black));

        lineShow = a.getBoolean(R.styleable.TitleView_line_show,false);
        initLayoutView();
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView() {
        activity = (Activity) getContext();
        view = View.inflate(getContext(), R.layout.view_title_bar, this);

        title_bg = view.findViewById(R.id.title_bg);

        tv_left = view.findViewById(R.id.tv_left_text);
        tv_center = view.findViewById(R.id.tv_center_text);
        tv_right = view.findViewById(R.id.tv_right_text);

        iv_left = view.findViewById(R.id.iv_left_image);
        iv_center = view.findViewById(R.id.iv_center_image);
        iv_right = view.findViewById(R.id.iv_right_image);

        ll_left_content = view.findViewById(R.id.ll_left_content);
        ll_center_content = view.findViewById(R.id.ll_center_content);
        ll_right_content = view.findViewById(R.id.ll_right_content);

        ll_left = view.findViewById(R.id.ll_left);
        ll_center = view.findViewById(R.id.ll_center);
        ll_right = view.findViewById(R.id.ll_right);
        title_line = view.findViewById(R.id.title_line);

        ll_left.setOnClickListener(this);
        //ll_center.setOnClickListener(this);
        //ll_right.setOnClickListener(this);


        //设置文字
        if (!TextUtils.isEmpty(leftText)) {
            tv_left.setText(leftText);
            tv_left.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(centerText)) {
            tv_center.setText(centerText);
            tv_center.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(rightText)) {
            tv_right.setText(rightText);
            tv_left.setVisibility(View.VISIBLE);
        }

        //设置图片
        if (leftImageDrawble != null) {
            iv_left.setVisibility(View.VISIBLE);
            iv_left.setImageDrawable(leftImageDrawble);
        }
        if (centerImageDrawble != null) {
            iv_center.setVisibility(View.VISIBLE);
            iv_center.setImageDrawable(centerImageDrawble);
        }
        if (rightImageDrawble != null) {
            iv_right.setVisibility(View.VISIBLE);
            iv_right.setImageDrawable(rightImageDrawble);
        }

        //设置是否显示分割线
        if(lineShow){
            title_line.setVisibility(View.VISIBLE);
        }else{
            title_line.setVisibility(View.GONE);
        }

        tv_center.setTextColor(text_color);
    }


    /*
    * 设置左边的文字
    * */
    public void setLeftText(String leftText) {
        if (leftText != null) {
            tv_left.setText(leftText);
            tv_left.setVisibility(View.VISIBLE);
        }
    }

    /*
    * 隐藏分割线
    * */
    public void hideLine(){
        title_line.setVisibility(View.GONE);
    }
    /*
    * 显示分割线
    * */
    public void showLine(){
        title_line.setVisibility(View.VISIBLE);
    }

    /*
    * 设置中间文字
    * */
    public void setCentreText(String centreText) {
        if (!TextUtils.isEmpty(centreText)) {
            tv_center.setText(centreText);
            tv_center.setVisibility(View.VISIBLE);
        }
    }

    /*
     * 设置右边文字
     * */
    public void setRightText(String rightText) {
        if (!TextUtils.isEmpty(rightText)) {
            tv_right.setText(rightText);
            tv_right.setVisibility(View.VISIBLE);
            ll_right.setVisibility(VISIBLE);
        }
    }

    /*
     * 设置左边的内容和字的颜色
     * */
    public void setLeftTextColor(String leftText, int color) {
        if (!TextUtils.isEmpty(leftText)) {
            tv_left.setVisibility(View.VISIBLE);
            tv_left.setText(leftText);
            tv_left.setTextColor(color);
        }else {
            tv_left.setVisibility(View.GONE);
        }
    }

    /*
     * 设置中间的内容和字的颜色
     * */
    public void setCenterTextColor(String centerText, int color) {
        if (!TextUtils.isEmpty(centerText)) {
            tv_center.setVisibility(View.VISIBLE);
            tv_center.setText(centerText);
            tv_center.setTextColor(color);
        }else {
            tv_center.setVisibility(View.GONE);
        }
    }

    /*
     * 设置左边的内容和字的颜色
     * */
    public void setRightTextColor(String rightText, int color) {
        if (!TextUtils.isEmpty(rightText)) {
            tv_right.setVisibility(View.VISIBLE);
            tv_right.setText(rightText);
            tv_right.setTextColor(color);
        }else {
            tv_right.setVisibility(View.GONE);
        }
    }


    /*
    * 设置左边的图片
    * */
    public void setLeftImage(int id) {
        iv_left.setVisibility(View.VISIBLE);
        if (0 == id) {
            iv_left.setVisibility(View.GONE);
        }else {
            iv_left.setImageResource(id);
        }

    }

    /*
    * 关闭左边返回按钮
    * */
    public void hideLeft(){
        iv_left.setVisibility(GONE);
    }

    /*
     * 设置中间的图片
     * */
    public void setCenterImage(int id) {
        iv_center.setVisibility(View.VISIBLE);
        if (0 == id) {
            iv_center.setVisibility(View.GONE);
        }else {
            iv_center.setImageResource(id);
        }
    }

    /*
    * 设置右边的图片
    * */
    public void setRightImage(int id) {
        iv_right.setVisibility(View.VISIBLE);
        if (0 == id) {
            iv_right.setVisibility(View.GONE);
        }else {
            iv_right.setImageResource(id);
        }
    }

    /*
    * 点击事件回调
    * */
    @SingleClick
    @Override
    public void onClick(View v) {
        activity.onBackPressed();
    }

    /*
    * 添加左边view
    * */
    public void addLeftContentView(View view) {
        ll_left_content.addView(view);
        ll_left_content.setVisibility(View.VISIBLE);
    }

    /*
    * 添加中间view
    * */
    public void addCenterContentView(View view) {
        ll_center_content.addView(view);
        ll_center_content.setVisibility(View.VISIBLE);
    }

    /*
    * 添加左边view
    * */
    public void addRightContentView(View view) {
        ll_right_content.removeAllViews();
        iv_right.setVisibility(GONE);
        tv_right.setVisibility(GONE);
        ll_right_content.addView(view);
        ll_right_content.setVisibility(View.VISIBLE);
    }

    /*
    * 获取背景颜色控件
    * */
    public View getBgView(){
        return this.title_bg;
    }

    /*
    * 获取右边的view
    * */
    public View getRightView(){
        return this.ll_right;
    }

    /*
    * 获取左边的View
    * */
    public View getLeftView(){
        return this.ll_left;
    }

    /*
    * 获取中间textview
    * */
    public TextView getTv_center(){
        return this.tv_center;
    }
}
