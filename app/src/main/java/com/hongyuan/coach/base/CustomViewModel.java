package com.hongyuan.coach.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.gson.Gson;
import com.hongyuan.coach.ui.beans.FileBean;
import com.hongyuan.coach.util.EncryptionUtil;
import com.hongyuan.coach.util.SharedPreferencesUtil;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import net.lemonsoft.lemonbubble.LemonBubble;

import org.xutils.common.util.KeyValue;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * 所有ViewModel需继承本ViewModel（可以方便接下来的快速开发哦^_^）
 * */
public abstract class CustomViewModel implements RetrofitListener {
    protected CustomActivity mActivity;
    private Map<String,String> params;
    public TokenSingleBean userToken;
    protected Gson gson;

    //请求成功状态码
    private final int SUCCESS = 1;
    //需要登录的错误码
    private final int ISLOGIN = 600;
    //存储用户登录的信息（避免每次登录）
    protected final String LOGIN_SESSION = "login_session";

    //分页需要的数据
    protected int curPage = 1;//当前页数
    private int pageSize = 10;//一页条目数
    private boolean isLoadMore = false;//是否开启了分页功能
    protected final int FIRST_PAGE = 1;//表示第一页

    public CustomViewModel(CustomActivity mActivity) {
        this.mActivity = mActivity;
        gson = new Gson();
        userToken = TokenSingleBean.getInstance();
    }


    /*
     * 是否开启上拉加载(默认不开启)
     * */
    public void setEnableLoadMore(boolean flag){
        if(mActivity.refresh != null){
            isLoadMore = flag;
            //加载更多监听
            mActivity.refresh.setOnLoadMoreListener(onLoadMore());
            mActivity.refresh.setEnableLoadMore(flag);
        }
    }

    /*
     * 是否开启自动刷新(默认不开启)
     * */
    public void setAutoRefresh(boolean flag){
        if(flag){
            mActivity.refresh.autoRefresh();
        }
    }

    /*
     * 是否开启刷新功能（默认不开启）
     * */
    public void setEnableRefresh(boolean flag){
        if(mActivity.refresh != null){
            mActivity.refresh.setOnRefreshListener(onRefresh());
            mActivity.refresh.setEnableRefresh(flag);
        }
    }

    /*
     * 下拉刷新监听
     * */
    private OnRefreshListener onRefresh() {
        return refreshLayout -> {
            curPage = 1;
            refreshData();
        };
    }

    /*
     * 上啦加载更多监听
     * */
    private OnLoadMoreListener onLoadMore(){
        return refreshLayout -> {
            curPage++;
            loadMoreData();
        };
    }


    /*
     * 页面首次打开需要直接加载数据的方法
     * */
    public void lazyLoad(){
    }

    /*
     * 初始化一些控件的方法
     * */
    protected void initView(){

    }

    /*
     * 初始化数据需要的
     * */
    protected void setData(){

    }

    /*
     * 刷新数据使用的
     * */
    public void refreshData(){

    }

    /*
     * 加载更多
     * */
    protected void loadMoreData(){

    }

    /*
     * 当activity再次出现在栈顶时刷新
     * */
    protected void onResume(){

    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mActivity,clz);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        mActivity.startActivity(intent);
    }

    /*
     * 调用直接拨打电话功能
     * */
    @SuppressLint("MissingPermission")
    protected void callTel(String telNum){
        Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+telNum));
        mActivity.startActivity(intent);
    }

    /*
     * 获取传递参数的bundle
     * */
    public Bundle getBundle(){
        return mActivity.getIntent().getExtras();
    }

    /*
     * 组装参数
     * */
    public CustomViewModel setParams(String key,String value){
        if(params == null){
            params = new HashMap<>();
        }
        params.put(key,value);
        return this;
    }

    /*
     * 清空参数集
     * */
    public CustomViewModel clearParams(){
        if(params != null){
            params.clear();
        }
        return this;
    }

    /*
     * 获取参数集合
     * */
    public Map<String,String> getParams(){
        if(getBaseParams() != null){
            if(params == null){
                params = new HashMap<>();
            }
            params.putAll(getBaseParams());
        }
        return this.params;
    }

    /*
     * 组装基础请求参数
     * */
    private Map<String,String> getBaseParams(){
        if(userToken != null && userToken.getToken() != null){
            int randomNum = (int)(Math.random()*100);
            long timeSpan = System.currentTimeMillis();
            //int randomNum = 100;
            //long timeSpan = 1561619095;


            StringBuilder ntoken = new StringBuilder();
            ntoken.append(EncryptionUtil.md5Decode(userToken.getToken()));
            ntoken.append(EncryptionUtil.md5DecodeTwo(String.valueOf(randomNum)));
            ntoken.append(EncryptionUtil.md5DecodeTwo(String.valueOf(timeSpan)));

            Map<String,String> baseParams = new HashMap<>();
            baseParams.put("client","android");
            baseParams.put("token",userToken.getToken());
            baseParams.put("at_id",String.valueOf(userToken.getAt_id()));
            baseParams.put("randomnum",String.valueOf(randomNum));
            baseParams.put("timespan",String.valueOf(timeSpan));
            baseParams.put("ntoken",ntoken.toString());

            //是否开启分页
            if(isLoadMore){
                baseParams.put("curpage",String.valueOf(curPage));
                baseParams.put("page",String.valueOf(pageSize));
            }

            if(userToken.getM_id() != null){
                baseParams.put("m_id",userToken.getM_id());
                baseParams.put("m_mobile",userToken.getM_mobile());
            }

            return baseParams;
        }
        return null;
    }

    /*
     * 多文件上传组装文件集合
     * */
    public List<KeyValue> getKeyValueList(List<FileBean> mList){
        List<KeyValue> files = new ArrayList<>();
        for(FileBean bean:mList){
            if(bean.getmFile() != null){
                files.add(new KeyValue(bean.getFileKey(),bean.getmFile()));
            }
        }
        //组装常规参数
        Set<String> set = getBaseParams().keySet();
        for (String s : set) {
            String key = s;
            String value = getBaseParams().get(s);
            files.add(new KeyValue(key,value));
        }

        return files;
    }

    /*
     * 单文件上传组装文件对象
     * */
    public FileBean getFileBean(String key,File mFile){
        FileBean fileBean = new FileBean();
        fileBean.setFileKey(key);
        fileBean.setmFile(mFile);
        return fileBean;
    }

    /*
     * 显示软键盘
     * */
    public void showEditext(EditText mEdiText){
        InputMethodManager imm = (InputMethodManager) mEdiText.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            mEdiText.requestFocus();
            imm.showSoftInput(mEdiText, 0);
        }
    }

    /*
     *隐藏软键盘
     * */
    public void hideEditext(EditText mEdiText){
        InputMethodManager imm = (InputMethodManager) mEdiText.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(mEdiText.getWindowToken(),0);
        }
    }

    /*
     * 需要做区分子类需要从写该方法
     * */
    @Override
    public void onSuccess(int code, Object data) {

    }

    /*
     * 关闭刷新的回调方法
     * */
    @Override
    public void closeRefresh() {
        mActivity.closeRefresh();
        mActivity.closeLoading(false);
    }

    /*
    * 接口请求计数回调
    * */
    @Override
    public void requestsNum() {
        mActivity.setRequestsNum();
    }

    /*
     * 请求失败的回调
     * */
    @Override
    public void onError(int code,int err_code,String description) {
        if(err_code == ISLOGIN && description.contains("先登录")){
            //startActivity(LoginActivity.class,null);
        }else{
            LemonBubble.showError(mActivity, description, 2000);
        }

    }

    /*
     * 请求成功提示
     * */
    public void showSuccess(String message){
        LemonBubble.showRight(mActivity, message, 2000);
    }
    /*
     * 请求成功提示
     * */
    public void showErr(String message){
        LemonBubble.showError(mActivity, message, 2000);
    }

    /*
     * 加载中动画
     * */
    public void onLoading(String message){
        LemonBubble.showRoundProgress(mActivity, message);
    }

    /*
     * 判断对象是否有值
     * */
    public boolean isValue(Object obj){
        if(obj == null || TextUtils.isEmpty(obj.toString()) || "null".equals(obj.toString())){
            return false;
        }
        return true;
    }
}
