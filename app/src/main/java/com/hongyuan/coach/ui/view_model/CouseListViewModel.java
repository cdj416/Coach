package com.hongyuan.coach.ui.view_model;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.coach.base.Constants;
import com.hongyuan.coach.base.Controller;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.base.CustomViewModel;
import com.hongyuan.coach.databinding.ActivityCourseListBinding;
import com.hongyuan.coach.ui.activity.CourseDetailsActivity;
import com.hongyuan.coach.ui.adapter.CouseListAdapter;
import com.hongyuan.coach.ui.beans.CouseListBean;

public class CouseListViewModel extends CustomViewModel {

    private CouseListAdapter adapter;
    private ActivityCourseListBinding binding;
    private CouseListBean listBean;

    public CouseListViewModel(CustomActivity mActivity, ActivityCourseListBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableRefresh(true);
        setEnableLoadMore(true);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(manager);
        adapter = new CouseListAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            //把教练信息和选中的课程信息传过去
            Bundle bundle = new Bundle();
            bundle.putString("cp_id",String.valueOf(listBean.getData().getList().get(position).getCp_id()));
            startActivity(CourseDetailsActivity.class,bundle);
        });
    }

    @Override
    public void refreshData(){
        listBean = null;
        lazyLoad();
    }

    @Override
    protected void loadMoreData() {
        lazyLoad();
    }

    @Override
    public void lazyLoad() {
        mActivity.showLoading();
        //加载私教课类型
        clearParams().setParams("cp_mid",mActivity.userToken.getM_id());
        Controller.myRequest(Constants.GET_COURSE_PRIVITE_LIST,Controller.TYPE_POST,getParams(), CouseListBean.class,this);
    }

    @Override
    public void onSuccess(int code,Object data) {
        if(data instanceof CouseListBean){
            CouseListBean pageData = (CouseListBean)data;
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
                mActivity.setPromtView(mActivity.SHOW_DATA);
            }else{
                mActivity.setPromtView( mActivity.SHOW_EMPTY);
            }
        }
    }
}
