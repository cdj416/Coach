package com.hongyuan.coach.custom_view.version_change;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.azhon.appupdate.config.UpdateConfiguration;
import com.azhon.appupdate.listener.OnDownloadListener;
import com.azhon.appupdate.manager.DownloadManager;
import com.hongyuan.coach.R;
import com.hongyuan.coach.custom_view.my_progress.WaveProgress;
import com.hongyuan.coach.util.CacheUtil;
import com.hongyuan.coach.util.HourMeterUtil;

import java.io.File;

public class VersionChangeView extends FrameLayout implements HourMeterUtil.TimeCallBack {

    private FrameLayout bgBox;
    private WaveProgress wavePro;

    //开始下载
    private final int START_DOWN = 0x1;
    //下载的最大值
    private int maxValue = 0;
    //当前进度的值
    private int mValue = 0;
    //计时回调（进度条的优化显示）
    private HourMeterUtil hourUtil;

    //更新数据
    private CheckVersionBeans.DataBean.InfoBean versionBeans;

    //判断常亮(需要更新)
    private final int NEED_CHANGE = 1;
    //判断常亮(需要强制更新)
    private final int NEED_FORCE = 2;

    public VersionChangeView(Context context) {
        super(context);
        initLayoutView();
    }

    public VersionChangeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public VersionChangeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_version_change, this);
        bgBox = view.findViewById(R.id.bgBox);
        wavePro = view.findViewById(R.id.wavePro);

    }

    public void startChange(CheckVersionBeans.DataBean.InfoBean versionBeans){
        //设置初始数据
        this.versionBeans = versionBeans;
        if(versionBeans.getIs_new() == NEED_CHANGE){
            //在下载前，清楚下缓存，避免因下载中被杀死进程而导致一直无法更新
            CacheUtil.clearAllCache(getContext());
            //初始化计时回调
            hourUtil = new HourMeterUtil();
            hourUtil.setTimeCallBack(this);
            //显示对话框
            startUpdate(versionBeans.getUpdatetype() == NEED_FORCE);

        }
    }

    /*
    * 初始化配置
    * */
    private void startUpdate(boolean force){
        Log.e("phm","======来的了===");

        //配置内容
        UpdateConfiguration configuration = new UpdateConfiguration()
                //输出错误日志
                .setEnableLog(true)
                //设置自定义的下载
                //.setHttpManager()
                //下载完成自动跳动安装页面
                .setJumpInstallPage(true)
                //设置对话框背景图片 (图片规范参照demo中的示例图)
                //.setDialogImage(R.drawable.ic_dialog)
                //设置按钮的颜色
                //.setDialogButtonColor(Color.parseColor("#E743DA"))
                //设置按钮的文字颜色
                .setDialogButtonTextColor(Color.WHITE)
                //支持断点下载
                .setBreakpointDownload(true)
                //设置是否显示通知栏进度
                .setShowNotification(true)
                //设置是否提示后台下载toast
                .setShowBgdToast(false)
                //设置强制更新
                .setForcedUpgrade(force)
                //设置对话框按钮的点击监听
                .setButtonClickListener(id -> {

                })
                //设置下载过程的监听
                .setOnDownloadListener(new OnDownloadListener() {
                    @Override
                    public void start() {
                        Message msg = new Message();
                        msg.what = START_DOWN;
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void downloading(int max, int progress) {
                        maxValue = max;
                        mValue = progress;
                    }

                    @Override
                    public void done(File apk) {
                        Log.e("phm","=====下载完成======");
                    }

                    @Override
                    public void cancel() {
                    }

                    @Override
                    public void error(Exception e) {
                        e.printStackTrace();
                    }
                });

        DownloadManager manager = DownloadManager.getInstance(getContext());
        manager.setApkName("suiDong.apk")
                .setApkUrl(versionBeans.getDownloadurl())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setShowNewerToast(true)
                .setConfiguration(configuration)
//                .setDownloadPath(Environment.getExternalStorageDirectory() + "/AppUpdate")
                .setApkVersionCode(2)
                .setApkVersionName("2.1.8")
                .setApkSize("20.4")
                .setApkVersionName(versionBeans.getApp_version().substring(1))
                .download();
    }


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case START_DOWN:
                    bgBox.setVisibility(View.VISIBLE);
                    //开始计时
                    hourUtil.startCount();
                    break;
            }
        }
    };

    @Override
    public void onTime(int passedTime) {
        if(maxValue != wavePro.getMaxValue()){
            wavePro.setMaxValue(maxValue);
        }
        wavePro.setValue(mValue);
    }

    /*
    * 当在更新时，屏蔽返回键
    * */



}
