package com.hongyuan.coach.ui.fragment;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.coach.R;
import com.hongyuan.coach.base.BaseBean;
import com.hongyuan.coach.base.Constants;
import com.hongyuan.coach.base.Controller;
import com.hongyuan.coach.base.CustomFragment;
import com.hongyuan.coach.base.SingleClick;
import com.hongyuan.coach.custom_view.CustomRecyclerView;
import com.hongyuan.coach.ui.activity.ScanActivity;
import com.hongyuan.coach.ui.adapter.HomeAdapter;
import com.hongyuan.coach.ui.adapter.TabTimeAdapter;
import com.hongyuan.coach.ui.beans.AppointmentCoursePrivitListBeans;
import com.hongyuan.coach.ui.beans.HomeBannerBean;
import com.hongyuan.coach.ui.beans.TabTimeBean;
import com.hongyuan.coach.util.CustomDialog;
import com.hongyuan.coach.util.TimeUtil;
import com.hongyuan.coach.util.UseGlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends CustomFragment {

    private Banner banner;
    private CustomRecyclerView menuRecycler;
    private RecyclerView mRecycler;
    private RelativeLayout load_box;
    private TabTimeAdapter tabAdapter;
    private HomeAdapter adapter;
    private TextView addressName;

    private TabTimeBean tabTimeBean;
    private AppointmentCoursePrivitListBeans listBeans;
    //选中日期
    private String selectData = "";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View mView) {
        setEnableRefresh(true);
        getmTitle().setCentreText("首页");
        getmTitle().addLeftContentView(getLocation());
        getmTitle().setRightImage(R.mipmap.scan_img);

        getmTitle().getLeftView().setOnClickListener(view -> {
            CustomDialog.selectLocation(mActivity);
        });
        getmTitle().getRightView().setOnClickListener(view ->
                startActivity(ScanActivity.class,null));

        banner = mView.findViewById(R.id.banner);
        menuRecycler = mView.findViewById(R.id.menuRecycler);
        mRecycler = mView.findViewById(R.id.mRecycler);
        load_box = mView.findViewById(R.id.load_box);

        //时间坐标
        LinearLayoutManager tabManager = new LinearLayoutManager(getContext());
        tabManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        menuRecycler.setLayoutManager(tabManager);
        tabAdapter = new TabTimeAdapter();
        menuRecycler.setAdapter(tabAdapter);
        //头部日期的选择
        tabAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            selectData = changeTab(position);
            //日期变了，去获取一整天的团课列表
            listBeans = null;
            loadCoachData();
        });


        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);
        adapter = new HomeAdapter();
        mRecycler.setAdapter(adapter);
        adapter.addFooterView(getFooterHeight(mRecycler));
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId() == R.id.telStudent){
                    CustomDialog.callTel(mActivity, listBeans.getData().getList().get(position).getM_mobile(), v -> {
                        callTel(listBeans.getData().getList().get(position).getM_mobile());
                    });
                }
                if(view.getId() == R.id.checkIn){
                    getQD(String.valueOf(listBeans.getData().getList().get(position).getCpa_id()));
                }
            }
        });
    }

    /*
    * 加载坐标定位视图控件
    * */
    private View getLocation(){
        View view = View.inflate(getContext(), R.layout.view_left_location_address,null);
        addressName = view.findViewById(R.id.addressName);
        return view;
    }

    /*
     * 更改头部日期选中状态,并获取选中日期
     * */
    private String changeTab(int position){
        for(int i = 0 ; i < tabTimeBean.getData().size() ; i++){
            if(i == position){
                tabTimeBean.getData().get(i).setSelect(true);
            }else{
                tabTimeBean.getData().get(i).setSelect(false);
            }
        }
        tabAdapter.setNewData(tabTimeBean.getData());
        return tabTimeBean.getData().get(position).getNow_day();
    }

    /*
     * 查找今日坐标
     * */
    private int getTodayPosition(List<TabTimeBean.DataBean> mList){
        for(int i = 0 ; i < mList.size() ; i++){
            if(mList.get(i).getIs_cur_date() == 1){
                return i;
            }
        }
        return 0;
    }

    @Override
    protected void onRefreshData() {
        loadCoachData();
    }

    @Override
    protected void lazyLoad() {
        //显示加载框
        mActivity.showLoading();
        //请求时间
        Controller.myRequest(Constants.GET_PLAN_DATE,Controller.TYPE_POST,getParams(), TabTimeBean.class,this);

        //读取banner图
        clearParams().setParams("img_code","index_hd").setParams("img_num","8");
        Controller.myRequest(Constants.GET_IMG_LIST,Controller.TYPE_POST,getParams(), HomeBannerBean.class,this);

        //读取教练时间安排
        loadCoachData();
    }

    /*
    * 教练端--教练被约私教课列表
    * */
    private void loadCoachData(){
        clearParams().setParams("start_date", selectData+" 00:00:00")
                .setParams("end_date",selectData+" 23:59:59");
        Controller.myRequest(Constants.GET_COACH_APPOINTMENT_COURSE_PRIVITE_LIST,Controller.TYPE_POST,getParams(), AppointmentCoursePrivitListBeans.class,this);
    }

    /*
    * 教练签到签退
    * */
    private void getQD(String cpa_id){
        clearParams().setParams("cpa_id",cpa_id).setParams("mtype","jl")
                .setParams("type","qd");
        Controller.myRequest(Constants.PRIVITE_COURSE_QD,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(data instanceof TabTimeBean){
            tabTimeBean = (TabTimeBean)data;
            //更改默认选中项
            selectData = changeTab(getTodayPosition(tabTimeBean.getData()));
            tabAdapter.setNewData(tabTimeBean.getData());
        }

        if(data instanceof HomeBannerBean){
            HomeBannerBean homeBannerBean = (HomeBannerBean)data;
            setBanner(homeBannerBean.getData().getList());
        }

        if(data instanceof AppointmentCoursePrivitListBeans){
            listBeans = (AppointmentCoursePrivitListBeans)data;
            if(listBeans.getData().getList() != null && listBeans.getData().getList().size() > 0){
                adapter.setNewData(listBeans.getData().getList());
                load_box.setVisibility(View.GONE);
                mRecycler.setVisibility(View.VISIBLE);
            }else{
                load_box.setVisibility(View.VISIBLE);
                mRecycler.setVisibility(View.GONE);
            }

        }

        if(code == Constants.PRIVITE_COURSE_QD){
            loadCoachData();
            CustomDialog.priviteCoursePunchSuccess(mActivity,TimeUtil.formatDataMsec(TimeUtil.dateFormatDotMD,System.currentTimeMillis()),
                    TimeUtil.getWeek());
        }

    }

    /*
     * 轮播图的设定
     * */
    private void setBanner(List<HomeBannerBean.DataBean.ListBean> bannerList){
        List<String> bList = new ArrayList<>();
        for(int i = 0 ; i < bannerList.size() ; i++){
            bList.add(bannerList.get(i).getImg_src());
        }
        banner.setImages(bList)
                .setImageLoader(new UseGlideImageLoader())
                .setDelayTime(3000)
                .isAutoPlay(true)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR )
                .setIndicatorGravity(BannerConfig.CENTER).start();
    }
}
