package com.hongyuan.coach.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.coach.R;
import com.hongyuan.coach.base.Constants;
import com.hongyuan.coach.base.Controller;
import com.hongyuan.coach.base.CustomFragment;
import com.hongyuan.coach.base.SingleClick;
import com.hongyuan.coach.custom_view.CustomRecyclerView;
import com.hongyuan.coach.ui.activity.CourseCalendarActivity;
import com.hongyuan.coach.ui.activity.CourseDetailsActivity;
import com.hongyuan.coach.ui.activity.EditCourseActivity;
import com.hongyuan.coach.ui.adapter.HomeCourseTopAdapter;
import com.hongyuan.coach.ui.adapter.HomeCouseListAdapter;
import com.hongyuan.coach.ui.beans.HomeCourseTopBeans;
import com.hongyuan.coach.ui.beans.HomeCouseListBean;

public class HomeCourseFragment extends CustomFragment {

    private CustomRecyclerView topRecycler;
    private RecyclerView mRecycler;
    private HomeCourseTopAdapter topAdapter;
    private HomeCouseListAdapter adapter;
    private TextView soldNum,isEmpty;
    private ImageView courseCalendar,releaseCourse;

    private HomeCouseListBean listBean;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_hong_course;
    }

    @Override
    public void initView(View mView) {
        setEnableRefresh(true);
        setEnableLoadMore(true);
        getmTitle().setCentreText("课程");
        getmTitle().showLine();

        topRecycler = mView.findViewById(R.id.topRecycler);
        mRecycler = mView.findViewById(R.id.mRecycler);
        soldNum = mView.findViewById(R.id.soldNum);
        courseCalendar = mView.findViewById(R.id.courseCalendar);
        releaseCourse = mView.findViewById(R.id.releaseCourse);
        isEmpty = mView.findViewById(R.id.isEmpty);

        LinearLayoutManager topmanager = new LinearLayoutManager(getContext());
        topmanager.setOrientation(LinearLayoutManager.HORIZONTAL);
        topRecycler.setLayoutManager(topmanager);
        topAdapter = new HomeCourseTopAdapter();
        topRecycler.setAdapter(topAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);
        adapter = new HomeCouseListAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("cp_id",String.valueOf(listBean.getData().getList().get(position).getCp_id()));
                startActivity(CourseDetailsActivity.class,bundle);
            }
        });

        //发布课程
        releaseCourse.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("cp_id","");
            startActivity(EditCourseActivity.class,bundle);
        });
        //课程日历
        courseCalendar.setOnClickListener(view -> startActivity(CourseCalendarActivity.class,null));
    }

    @Override
    protected void lazyLoad() {
        //显示加载框
        mActivity.showLoading();
        Controller.myRequest(Constants.GET_COACH_SALE_CP,Controller.TYPE_POST,getParams(), HomeCourseTopBeans.class,this);
        getCourseData();
    }

    @Override
    protected void onRefreshData() {
        getCourseData();
    }

    @Override
    public void loadMoreData() {
        getCourseData();
    }

    /*
    * 请求课程
    * */
    private void getCourseData(){
        clearParams().setParams("cp_mid",mActivity.userToken.getM_id());
        Controller.myRequest(Constants.GET_COURSE_PRIVITE_LIST,Controller.TYPE_POST,getParams(), HomeCouseListBean.class,this);
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == Constants.GET_COACH_SALE_CP){
            HomeCourseTopBeans topBeans = (HomeCourseTopBeans)data;
            topAdapter.setNewData(topBeans.getData().getItem());
        }
        if(code == Constants.GET_COURSE_PRIVITE_LIST){
            HomeCouseListBean pageData = (HomeCouseListBean)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    listBean = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    listBean.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(listBean != null && listBean.getData() != null &&
                    listBean.getData().getList() != null &&
                    listBean.getData().getList().size() > 0){
                adapter.setNewData(listBean.getData().getList());

                mRecycler.setVisibility(View.VISIBLE);
                isEmpty.setVisibility(View.GONE);
            }else{
                mRecycler.setVisibility(View.GONE);
                isEmpty.setVisibility(View.VISIBLE);
            }
        }
    }
}
