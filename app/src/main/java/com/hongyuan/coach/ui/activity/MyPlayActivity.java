package com.hongyuan.coach.ui.activity;

import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Handler;
import android.transition.Transition;
import android.view.View;
import android.widget.ImageView;

import androidx.core.view.ViewCompat;

import com.bumptech.glide.Glide;
import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.base.OnTransitionListener;
import com.hongyuan.coach.ui.beans.FeatureBean;
import com.hongyuan.coach.ui.beans.SwitchVideoModel;
import com.hongyuan.coach.ui.my_view.SampleVideo;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;

import java.util.ArrayList;
import java.util.List;

public class MyPlayActivity extends CustomActivity {
    public final static String IMG_TRANSITION = "IMG_TRANSITION";
    public final static String TRANSITION = "TRANSITION";
    private SampleVideo videoPlayer;
    private OrientationUtils orientationUtils;
    private boolean isTransition;
    private Transition transition;

    //数据
    private FeatureBean.DataBean.ListBean ciBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_play;
    }

    @Override
    protected void initView() {
        hideTitle(true);
        setImmersive();

        ciBean = (FeatureBean.DataBean.ListBean)getIntent().getExtras().getSerializable("ciBean");
        String videoPath = getIntent().getExtras().getString("videoPath");
        String videoImgPath = getIntent().getExtras().getString("videoImgPath");

        videoPlayer = findViewById(R.id.video_player);
        isTransition = getIntent().getBooleanExtra(TRANSITION, false);

        String source1 = null;
        if(ciBean != null){
            source1 = ciBean.getCi().get(0).getFile_src();
        }else if(videoPath != null){
            source1 = videoPath;
        }
        String name = "普通";
        SwitchVideoModel switchVideoModel = new SwitchVideoModel(name, source1);


        List<SwitchVideoModel> list = new ArrayList<>();
        list.add(switchVideoModel);

        videoPlayer.setUp(list, true, "");

        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if(ciBean != null){
            Glide.with(this).load(ciBean.getCircle_img()).into(imageView);
        }
        if(videoImgPath != null){
            Glide.with(this).load(videoImgPath).into(imageView);
        }
        videoPlayer.setThumbImageView(imageView);

        //增加title
        //videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        videoPlayer.setShowPauseCover(false);

        //videoPlayer.setSpeed(2f);

        //设置返回键
        videoPlayer.getBackButton().setVisibility(View.GONE);
        //设置旋转
        orientationUtils = new OrientationUtils(this, videoPlayer);
        //禁止旋转
        orientationUtils.setEnable(false);

        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
            }
        });

        videoPlayer.setBottomProgressBarDrawable(getResources().getDrawable(R.drawable.video_new_progress));
        //videoPlayer.setDialogVolumeProgressBar(getResources().getDrawable(R.drawable.video_new_volume_progress_bg));
        //videoPlayer.setDialogProgressBar(getResources().getDrawable(R.drawable.video_new_progress));
        videoPlayer.setBottomShowProgressBarDrawable(getResources().getDrawable(R.drawable.video_new_seekbar_progress),
        getResources().getDrawable(R.drawable.video_new_seekbar_thumb));
        //videoPlayer.setDialogProgressColor(getResources().getColor(R.color.colorAccent), -11);

        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);

        //设置返回按键功能
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //过渡动画
        initTransition();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.onVideoResume();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onBackPressed() {
        //先返回正常状态
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            videoPlayer.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        videoPlayer.setVideoAllCallBack(null);
        GSYVideoManager.releaseAllVideos();
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            super.onBackPressed();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                }
            }, 500);
        }
    }


    private void initTransition() {
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition();
            ViewCompat.setTransitionName(videoPlayer, IMG_TRANSITION);
            addTransitionListener();
            startPostponedEnterTransition();
        } else {
            videoPlayer.startPlayLogic();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private boolean addTransitionListener() {
        transition = getWindow().getSharedElementEnterTransition();
        if (transition != null) {
            transition.addListener(new OnTransitionListener(){
                @Override
                public void onTransitionEnd(Transition transition) {
                    super.onTransitionEnd(transition);
                    videoPlayer.startPlayLogic();
                    transition.removeListener(this);
                }
            });
            return true;
        }
        return false;
    }
}
