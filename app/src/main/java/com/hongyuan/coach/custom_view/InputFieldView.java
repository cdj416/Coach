package com.hongyuan.coach.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.SingleClick;
import com.hongyuan.coach.util.BaseUtil;
import com.hongyuan.coach.util.HourMeterUtil;

public class InputFieldView extends LinearLayout implements View.OnClickListener, HourMeterUtil.TimeCallBack {
    private TextView titleName;
    private EditText et_content;
    private Button sendMessage;
    private ImageView iv_content;
    private View bottomLine;
    private RelativeLayout sendMessageBox;

    private HourMeterUtil hourUtil;//超时计数

    //控制类型
    private String typeName;
    private boolean showLine;
    private int inputType;
    private int showType;

    /*
     * 验证码回调的接口
     * */
    public interface CodeClick{
        void getClickCode();
    }
    private CodeClick codeClick;
    public void setCodeClick(CodeClick codeClick){
        this.codeClick = codeClick;
    }

    //功能需要的
    private boolean passwordShow = false;

    public InputFieldView(Context context) {
        super(context);
        initLayoutView();
    }

    public InputFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.InputFiledView);
        typeName = a.getString(R.styleable.InputFiledView_title_name);
        inputType = a.getInt(R.styleable.InputFiledView_input_type,0);
        showType = a.getInt(R.styleable.InputFiledView_show_type,0);
        showLine = a.getBoolean(R.styleable.InputFiledView_input_line_show,true);
        initLayoutView();
    }

    public InputFieldView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView() {
        View view = View.inflate(getContext(), R.layout.view_input_field, this);
        titleName = view.findViewById(R.id.titleName);
        et_content = view.findViewById(R.id.et_content);
        sendMessage = view.findViewById(R.id.sendMessage);
        iv_content = view.findViewById(R.id.iv_content);
        bottomLine = view.findViewById(R.id.bottomLine);
        sendMessageBox = view.findViewById(R.id.sendMessageBox);

        setTitleName(typeName);
        setInputType(inputType);
        setShowType(showType);
        setShowLine(showLine);

        iv_content.setOnClickListener(this);
        sendMessage.setOnClickListener(this);

        //计时回调
        hourUtil = new HourMeterUtil();
        hourUtil.setTimeCallBack(this);
    }

    /*
     * 填入标题类容
     * */
    private void setTitleName(String typeName){
        if(BaseUtil.isValue(typeName)) titleName.setText(typeName);
    }

    /*
     * 控制输入的类型
     * */
    private void setInputType(int inputType){
        if(inputType == 0) et_content.setInputType(InputType.TYPE_CLASS_TEXT);
        if(inputType == 1) et_content.setInputType(InputType.TYPE_CLASS_PHONE);
        if(inputType == 2) et_content.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    /*
     * 控制展示的类型（目前有三种，1.普通输入文本类型，2.发送验证码类型，3.密码可见与否类型）
     * */
    private void setShowType(int showType){
        if(showType == 0){
            sendMessageBox.setVisibility(GONE);
            sendMessage.setVisibility(GONE);
            iv_content.setVisibility(GONE);
        }

        if(showType == 1){
            sendMessageBox.setVisibility(VISIBLE);
            sendMessage.setVisibility(VISIBLE);
            iv_content.setVisibility(GONE);
        }

        if(showType == 2){
            sendMessageBox.setVisibility(GONE);
            sendMessage.setVisibility(GONE);
            iv_content.setVisibility(VISIBLE);
        }
    }

    /*
     * 是否显示底部线条
     * */
    private void setShowLine(boolean showLine){
        if(showLine){
            bottomLine.setVisibility(VISIBLE);
        }else{
            bottomLine.setVisibility(GONE);
        }
    }


    /********************************************下面是功能性的代码**************************************************/
    /*
     * 明文密码和非明文密码的切换
     * */
    private void pwdIsVisible(boolean isShow){
        if(!isShow){
            passwordShow = true;
            iv_content.setImageResource(R.mipmap.vtwo_login_eye_outline_img_);
            et_content.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            Editable etext = et_content.getText();
            Selection.setSelection(etext, etext.length());
        }else{
            passwordShow = false;
            iv_content.setImageResource(R.mipmap.vtwo_login_eye_off_img_);
            et_content.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
            Editable etext = et_content.getText();
            Selection.setSelection(etext, etext.length());
        }
    }

    /*
     * 输入值
     * */
    public String getText(){
        return et_content.getText().toString();
    }

    @Override
    public void onTime(int passedTime) {
        if(passedTime <= 60){
            sendMessage.setText("已发送"+(60 - passedTime)+"s");
        }else{
            sendMessage.setText("发送验证码");
            sendMessage.setClickable(true);
            hourUtil.pauseCount();
        }
    }

    /*
     * 开始倒计时
     * */
    public void startDown(){
        sendMessage.setClickable(false);
        hourUtil.reStartCount();
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_content:
                pwdIsVisible(passwordShow);
                break;
            case R.id.sendMessage:
                if(codeClick != null){
                    codeClick.getClickCode();
                }
                break;
        }
    }


}
