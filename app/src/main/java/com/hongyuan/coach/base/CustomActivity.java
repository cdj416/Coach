package com.hongyuan.coach.base;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.hongyuan.coach.R;
import com.hongyuan.coach.custom_view.StatusBarHeightView;
import com.hongyuan.coach.custom_view.TitleView;
import com.hongyuan.coach.util.HourMeterUtil;
import com.hongyuan.coach.util.StatusBarUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;

import net.lemonsoft.lemonbubble.LemonBubble;

import io.reactivex.annotations.Nullable;

/*
* 所有activity需继承本activity（可以方便接下来的快速开发哦^_^）
* */
public abstract class CustomActivity extends AppCompatActivity implements HourMeterUtil.TimeCallBack{
    private Handler handler = new Handler();
    //加载器
    public View mView;
    //父类中的标题栏
    private TitleView mainTitle;
    //父类中的刷新控件
    public SmartRefreshLayout refresh;
    //子类中的主要布局内容
    private FrameLayout mainView;
    //全局使用的信息
    public TokenSingleBean userToken;
    //状态栏高度
    private StatusBarHeightView barHeight;

    //存储token需要的key值常亮
    protected final String TOKEN_SESSION = "token_session";
    //页面效果
    private RelativeLayout load_box;
    private TextView isEmpty,isTvErr;
    public static final int SHOW_ERR = 0X1;//显示错误页面
    public static final int SHOW_EMPTY = 0X2;//显示空数据页面
    public static final int SHOW_DATA = 0X3;//显示数据页面

    //已下是标题样式
    public final int TYPE_BAR1 = 0X1;
    public final int TYPE_BAR2 = 0X2;
    public final int TYPE_BAR3 = 0X3;
    public final int TYPE_BAR4 = 0X4;
    public final int TYPE_BAR5 = 0X5;
    public final int TYPE_BAR6 = 0X6;

    //跳转处理需要的对象
    private Class<?> clz;
    private Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);

        //初始化token，全局使用
        userToken = TokenSingleBean.getInstance();
        //禁止使用横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //加载父布局
        setContentView(R.layout.activity_custom);

        barHeight = findViewById(R.id.barHeight);
        mainTitle = findViewById(R.id.mainTitle);
        mainView = findViewById(R.id.mainView);
        //加载主布局
        mView = LayoutInflater.from(this).inflate(getLayoutId(), null);
        mainView.addView(mView);
        refresh = findViewById(R.id.refresh);

        //加载提示布局
        initPrompt();
        //初始化刷新控件
        setOnRefresh();
        //加载子布局视图
        initView();
        //setImmersive();
    }

    /*
    * 沉浸式（默认白色主题）
    * */
    public void setImmersive(){
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,false);
        StatusBarUtil.setTranslucentStatus(this);
    }

    /*
    * 设置状态栏文字颜色（灰色）
    * */
    public void setsetImmersive(int color){
        //true往下移，flase会被标题栏遮住（还可以使用相应的view来实现）
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this,color);
        }
    }



    /*
     * 通用标题样式
     * barType:标题和bar的样式
     * drawableId：使用沉溺式显示的北京样式
     * titleName：标题显示内容
     *
     * TYPE_BAR1：没标题，沉浸式，有顶高,状态栏字体灰色
     * TYPE_BAR2:有标题，沉浸式，有顶高，状态栏字体白色，标题字体颜色白色，没有返回键,
     *           有右边title,（该样式仅限个人中心使用）
     * TYPE_BAR3:有标题，非沉浸式，黑色字体，无标题底线,白色背景
     * TYPE_BAR4：有标题，沉浸式，有顶高，白色字体，自定义背景
     * TYPE_BAR5:有标题，非沉浸式，黑色字体，有标题底线，白色背景
     * TYPE_BAR6:没标题，沉浸式，无顶高，状态栏字体灰色
     * */
    public void setTitleBar(int barType,int drawableId,String titleName){
        if(barType == TYPE_BAR1){
            //隐藏标题
            hideTitle(true);
            //沉浸式
            setImmersive();
            //显示顶高
            barHeight.setVisibility(View.VISIBLE);
            barHeight.setBackgroundResource(drawableId);
            StatusBarUtil.setCommonUI(this,true);
        }
        if(barType == TYPE_BAR2){
            //隐藏标题
            hideTitle(true);
            //沉浸式
            setImmersive();
            //隐藏顶高
            barHeight.setVisibility(View.GONE);
            //StatusBarUtil.setCommonUI(this,false);
        }
        if(barType == TYPE_BAR3){
            setTitle(titleName);
            setsetImmersive(0x55000000);
        }
        if(barType == TYPE_BAR4){
            //沉浸式
            setImmersive();
            mainTitle.setCenterTextColor(titleName,0xFFFFFFFF);
            mainTitle.setLeftImage(R.mipmap.white_left_img);
            mainTitle.getBgView().setBackgroundResource(drawableId);
            mainTitle.hideLine();
            barHeight.setVisibility(View.VISIBLE);
            barHeight.setBackgroundResource(drawableId);
        }
        if(barType == TYPE_BAR5){
            mainTitle.setCentreText(titleName);
            setsetImmersive(0x55000000);
        }
        if(barType == TYPE_BAR6){
            //隐藏标题
            hideTitle(true);
            //沉浸式
            setImmersive();
            StatusBarUtil.setCommonUI(this,true);
        }
    }


    /*
    * 个人中心设置沉浸式
    * */
    public void setPersonImmer(){
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setRootViewFitsSystemWindows(this,false);
    }

    /*
     * 加载页面显示效果
     * */
    private void initPrompt(){
        load_box = findViewById(R.id.load_box);
        isEmpty = findViewById(R.id.isEmpty);
        isTvErr = findViewById(R.id.isErr);
    }

    /*
     * 初始化加载控件各个设置
     * */
    private void setOnRefresh(){
        //关闭滚动到底部自动加载
        refresh.setEnableAutoLoadMore(false);
        //设置主题颜色
        refresh.setPrimaryColors(0xFFF2F2F2);
        //初始刷新动画
        refresh.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));
        //初始化加载动画
        refresh.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        //关闭上拉加载更多
        refresh.setEnableLoadMore(false);
        //是否开启自动刷新
        refresh.setEnableRefresh(false);
        //是否开启刷新功能
        refresh.setEnableRefresh(false);
    }

    /*
     * 设置标题
     * */
    protected void setTitle(String title){
        mainTitle.setCentreText(title);
        mainTitle.hideLine();
    }
    /*
     * 获取标题控件
     * */
    public TitleView getMainTitle(){
        return this.mainTitle;
    }

    /*
    * 隐藏标题
    * */
    protected void hideTitle(boolean flag){
        if(flag){
            mainTitle.setVisibility(View.GONE);
        }else{
            mainTitle.setVisibility(View.VISIBLE);
        }
    }

    /*
    * 隐藏左边返回键
    * */
    protected void hideTitleLeft(){
        mainTitle.hideLeft();
    }

    /*
     * 加载布局文件
     * */
    protected abstract int getLayoutId();

    /*
     * 加载布局控件
     * */
    protected abstract void initView();

    /*
     * 显示页面效果切换
     * */
    public void setPromtView(int type){
        if(type == SHOW_ERR){
            mainView.setVisibility(View.GONE);
            load_box.setVisibility(View.VISIBLE);
            isTvErr.setVisibility(View.VISIBLE);
            isEmpty.setVisibility(View.GONE);
        }else if(type == SHOW_EMPTY){
            mainView.setVisibility(View.GONE);
            load_box.setVisibility(View.VISIBLE);
            isTvErr.setVisibility(View.GONE);
            isEmpty.setVisibility(View.VISIBLE);
        }else{
            load_box.setVisibility(View.GONE);
            mainView.setVisibility(View.VISIBLE);
        }
    }

    /*
     * 关闭刷新的回调方法
     * */
    public void closeRefresh() {
        if(refresh != null){
            refresh.finishRefresh();
            refresh.finishLoadMore();
        }
    }

    /**
     * 跳转页面
     */
    public void startActivity(Class<?> clz) {
        Intent intent = new Intent();
        intent.setClass(this,clz);
        if(bundle != null){
            intent.putExtra("bundle",bundle);
        }
        startActivity(intent);
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this,clz);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /*
    * 获取跳转过来的序列化对象数据
    * */
    public Object getIntentData(String key){
        return getIntent().getSerializableExtra(key);
    }

    /*
    * 成功提示弹框，并过三秒关掉当前activity
    * */
    public void showSuccess(String message){
        LemonBubble.showRight(this,message,2000);
        handler.postDelayed(runnableClose, 2000);
    }
    /*
    * 成功提示弹框，并过三秒关掉当前activity
    * */
    public void showSuccess(String message,Class<?> clz, Bundle bundle){
        this.clz = clz;
        this.bundle = bundle;
        LemonBubble.showRight(this,message,2000);
        handler.postDelayed(runnableClose, 2000);
    }

    /*
    * 关闭当前页面
    * */
    Runnable runnableClose = new Runnable() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        public void run() {
            if(clz != null){
                startActivity(clz);
            }
            finish();
        }
    };

    /**************************************************加载动画成处理********************************/
    private Dialog loadingDialog = null;
    private AnimationDrawable animationDrawable = null;
    private HourMeterUtil hourUtil;//超时计数

    private int requestsNum = 0;//请求接口数
    private int requestsCompletionNum = 0;//请求接口完成数

    /*
     * 显示加载弹框
     * */
    public void showLoading(){
        //初始化计时对象
        if(hourUtil == null){
            //计时回调
            hourUtil = new HourMeterUtil();
            hourUtil.setTimeCallBack(this);
        }
        //开始计时
        hourUtil.startCount();

        if(loadingDialog == null){
            loadingDialog = new Dialog(this, R.style.MessageTheme);
            loadingDialog.setCanceledOnTouchOutside(false);
            View view = View.inflate(this, R.layout.view_base_loading,null);
            loadingDialog.setContentView(view);
            Window window = loadingDialog.getWindow();
            window.setGravity(Gravity.CENTER);
            window.setWindowAnimations(R.style.main_menu_animStyle);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            ImageView loadImg = view.findViewById(R.id.loadImg);
            animationDrawable = (AnimationDrawable) loadImg.getBackground();
        }
        Log.e("cdj","===============弹框是否显示出来了===="+loadingDialog.isShowing());
        if(!loadingDialog.isShowing()){
            loadingDialog.show();
            animationDrawable.start();
        }
    }

    /*
     * 关闭加载弹框
     * */
    public void closeLoading(boolean forcedClose){
        requestsCompletionNum++;
        if(loadingDialog != null && loadingDialog.isShowing() && requestsNum == requestsCompletionNum){
            loadingDialog.dismiss();
            hourUtil.stopCount();

            //所有接口请求完成，初始化计数数据为0
            requestsNum = 0;
            requestsCompletionNum = 0;
        }
        //强制关闭
        if(forcedClose){
            loadingDialog.dismiss();
            hourUtil.stopCount();

            //所有接口请求完成，初始化计数数据为0
            requestsNum = 0;
            requestsCompletionNum = 0;
        }
    }

    /*
    * 设置请求计数
    * */
    public void setRequestsNum() {
        //接口请求数添加1
        requestsNum++;
    }

    @Override
    public void onTime(int passedTime) {
        if(passedTime%30 == 0){
            closeLoading(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
    }
}
