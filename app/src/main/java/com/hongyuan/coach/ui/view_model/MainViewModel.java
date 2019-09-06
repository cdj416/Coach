package com.hongyuan.coach.ui.view_model;

import android.util.Log;

import androidx.viewpager.widget.ViewPager;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.Constants;
import com.hongyuan.coach.base.Controller;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.base.CustomViewModel;
import com.hongyuan.coach.custom_view.version_change.CheckVersionBeans;
import com.hongyuan.coach.databinding.ActivityMainBinding;
import com.hongyuan.coach.ui.view_page_adapter.MyViewPagerAdapter;
import com.hongyuan.coach.util.PackageUtils;

import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;


public class MainViewModel extends CustomViewModel {

    private ActivityMainBinding binding;
    private CheckVersionBeans.DataBean.InfoBean versionBeans;

    public MainViewModel(CustomActivity mActivity, ActivityMainBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView(){
        NavigationController navigationController = binding.tab.material()
                .addItem(R.mipmap.home_default, R.mipmap.home_select,"首页",mActivity.getResources().getColor(R.color.color_3DA1E7))
                .addItem(R.mipmap.course_default_icon_img,R.mipmap.course_select_icon_img, "课程",mActivity.getResources().getColor(R.color.color_3DA1E7))
                .addItem(R.mipmap.person_default, R.mipmap.person_select,"我的",mActivity.getResources().getColor(R.color.color_3DA1E7))
                .build();

        binding.viewPager.setAdapter(new MyViewPagerAdapter(mActivity.getSupportFragmentManager()));
        //自动适配ViewPager页面切换
        navigationController.setupWithViewPager(binding.viewPager);
        binding.viewPager.setOffscreenPageLimit(3);

        // 也可以设置Item选中事件的监听
        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                if(index == 2){
                    mActivity.setTitleBar(mActivity.TYPE_BAR2,R.drawable.shape_gradient_h_39_4a,"");
                }else{
                    mActivity.setTitleBar(mActivity.TYPE_BAR1,R.drawable.shape_soid_ffffff,"");
                }
            }
            @Override
            public void onRepeat(int index) {
                Log.e("cdj", "onRepeat selected: " + index);
            }
        });
    }

    @Override
    public void lazyLoad() {
        //检测是否需要更新版本
        clearParams().setParams("app_version", PackageUtils.getVersionName(mActivity))
                .setParams("app_type","1").setParams("app_name","jl");
        Controller.myRequest(Constants.CHECK_APP_VERSION, Controller.TYPE_POST,getParams(), CheckVersionBeans.class,this);
    }

    @Override
    public void onSuccess(int code,Object data) {
        if(data instanceof CheckVersionBeans){
            versionBeans = ((CheckVersionBeans)data).getData().getInfo();
            //版本检测更新
            binding.versionView.startChange(versionBeans);
        }
    }
}
