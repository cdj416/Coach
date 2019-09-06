package com.hongyuan.coach.ui.view_model;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.hongyuan.coach.R;
import com.hongyuan.coach.base.BaseBean;
import com.hongyuan.coach.base.Constants;
import com.hongyuan.coach.base.Controller;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.base.CustomViewModel;
import com.hongyuan.coach.base.SingleClick;
import com.hongyuan.coach.custom_view.bubble_frame.BubblePopupWindow;
import com.hongyuan.coach.databinding.ActivityEditTimeBinding;
import com.hongyuan.coach.ui.activity.PostDetailsActivity;
import com.hongyuan.coach.ui.adapter.EditTimeTabAdapter;
import com.hongyuan.coach.ui.adapter.TimeAdapter;
import com.hongyuan.coach.ui.beans.TabTimeBean;
import com.hongyuan.coach.ui.beans.TimeBean;
import com.hongyuan.coach.util.BaseUtil;
import com.hongyuan.coach.util.DividerItemDecoration;

import java.util.List;

public class EditTimeViewModel extends CustomViewModel {

    private ActivityEditTimeBinding binding;
    private EditTimeTabAdapter tabAdapter;
    private TimeAdapter timeAdapter;

    private TabTimeBean tabTimeBean;
    private TimeBean timeBean;

    //气泡弹框
    private BubblePopupWindow topWindow;
    private LayoutInflater layoutInflater;
    private View bubbleView;
    private TextView tvText;

    //选中的日期字符串
    private String nowData = "";

    public EditTimeViewModel(CustomActivity mActivity, ActivityEditTimeBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        layoutInflater = LayoutInflater.from(mActivity);
        topWindow = new BubblePopupWindow(mActivity);

        LinearLayoutManager tabManager = new LinearLayoutManager(mActivity);
        tabManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.tabRecyc.addItemDecoration(new DividerItemDecoration(
                mActivity, DividerItemDecoration.VERTICAL_LIST,100,0x00000000));
        binding.tabRecyc.setLayoutManager(tabManager);
        tabAdapter = new EditTimeTabAdapter();
        binding.tabRecyc.setAdapter(tabAdapter);
        tabAdapter.setOnItemChildClickListener((adapter, view1, position) -> {
            changeTab(position);
            nowData = tabTimeBean.getData().get(position).getNow_day();
            reqNewTime(tabTimeBean.getData().get(position).getNow_day());
        });

        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 4);
        binding.contentRecyc.setLayoutManager(layoutManager);
        binding.contentRecyc.setLayoutManager(layoutManager);
        timeAdapter = new TimeAdapter();
        binding.contentRecyc.setAdapter(timeAdapter);
        timeAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            List<TimeBean.DataBean> mlist = adapter.getData();
            for(TimeBean.DataBean dataBean:mlist){
                dataBean.setSelect(false);
            }
            mlist.get(position).setSelect(true);
            adapter.setNewData(mlist);
            showPopoWind(view,position);
        });
    }

    private boolean isRefresh;

    /*
     * 展示气泡弹框
     * */
    @SuppressLint("ClickableViewAccessibility")
    private void showPopoWind(View view, int position){
        //加载popowindow
        if(bubbleView == null){
            bubbleView = layoutInflater.inflate(R.layout.popu_content_text, null);
            topWindow.setBubbleView(bubbleView);//添加内容
            tvText = bubbleView.findViewById(R.id.tvText);
            //消失监听听
            topWindow.setOnDismissListener(() -> {
                List<TimeBean.DataBean> mlist = timeAdapter.getData();
                for(TimeBean.DataBean dataBean:mlist){
                    dataBean.setSelect(false);
                }
                if(!isRefresh){
                    timeAdapter.setNewData(mlist);
                }
            });
        }
        tvText.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View view) {
                //用来屏蔽关闭刷新数据这步
                isRefresh = true;
                topWindow.dismiss();
                changData(position);
            }
        });

        if(timeAdapter.getData().get(position).getIs_kong() == 1){
            tvText.setText("休息");
        }else{
            tvText.setText("没课");
        }

        topWindow.editTimeshow(view);
    }

    /*
     * 更改头部时间选中状态
     * */
    private void changeTab(int position){
        for(int i = 0 ; i < tabTimeBean.getData().size() ; i++){
            if(i == position){
                tabTimeBean.getData().get(i).setSelect(true);
            }else{
                tabTimeBean.getData().get(i).setSelect(false);
            }
        }
        tabAdapter.setNewData(tabTimeBean.getData());
    }

    @Override
    public void lazyLoad() {
        mActivity.showLoading();
        //获取头部时间栏
        clearParams();
        Controller.myRequest(Constants.GET_PLAN_DATE,Controller.TYPE_POST,getParams(), TabTimeBean.class,this);
    }

    /*
     * 请求新的教练时间安排
     * */
    private void reqNewTime(String day){
        mActivity.showLoading();
        //去请求教练某天的时间安排
        clearParams().setParams("day",day);
        Controller.myRequest(Constants.GET_COACH_TIMEPLAN_DAY_LIST_COACH,Controller.TYPE_POST,getParams(), TimeBean.class,this);
    }

    /*
    * 编辑教练时间安排
    * */
    private void changData(int positon){
        mActivity.showLoading();
        if(timeAdapter.getData().get(positon).getIs_kong() == 0){
            clearParams().setParams("ct_id",String.valueOf(timeAdapter.getData().get(positon).getCt_id()));
            Controller.myRequest(Constants.DEL_COACH_TIMEPLAN,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        }else{
            mActivity.showLoading();
            clearParams().setParams("start_time",getSelectDT(positon)).setParams("end_time",getSelectDT(positon+1));
            Controller.myRequest(Constants.ADD_COACH_TIMEPLAN,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        }
    }

    @Override
    public void onSuccess(int code,Object data) {
        if(data instanceof TabTimeBean){
            tabTimeBean = (TabTimeBean)data;
            if(tabTimeBean.getData() != null && tabTimeBean.getData().size() > 0){
                tabAdapter.setNewData(tabTimeBean.getData());
                reqNewTime(getTodayPosition(tabTimeBean.getData()));
            }
        }

        if(data instanceof TimeBean){
            timeBean = (TimeBean)data;
            if(timeAdapter != null){
                timeAdapter.setNewData(timeBean.getData());
                isRefresh = false;
            }
        }

        if(code == Constants.ADD_COACH_TIMEPLAN || code == Constants.DEL_COACH_TIMEPLAN){
            reqNewTime(nowData);
            showSuccess("编辑成功！");
        }
    }

    /*
     * 查找今日坐标
     * */
    private String getTodayPosition(List<TabTimeBean.DataBean> mList){
        for(int i = 0 ; i < mList.size() ; i++){
            if(mList.get(i).getIs_cur_date() == 1){
                tabTimeBean.getData().get(i).setSelect(true);
                nowData = mList.get(i).getNow_day();
                return mList.get(i).getNow_day();
            }
        }
        return mList.get(0).getNow_day();
    }

    /*
    * 获取选中的开始时间或者结束时间
    * */
    private String getSelectDT(int mPosition){
        return nowData + " "+timeBean.getData().get(mPosition).getNow_time();
    }
}
