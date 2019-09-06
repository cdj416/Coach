package com.hongyuan.coach.ui.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.coach.R;
import com.hongyuan.coach.base.Constants;
import com.hongyuan.coach.base.Controller;
import com.hongyuan.coach.base.CustomFragment;
import com.hongyuan.coach.ui.adapter.MyStudentsAdapter;
import com.hongyuan.coach.ui.beans.MyStudentsBeans;
import com.hongyuan.coach.util.CustomDialog;

public class MyStudentsFragment extends CustomFragment {

    private MyStudentsBeans studentsBeans;

    private RecyclerView mRecycler;
    private MyStudentsAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my_students;
    }

    @Override
    public void initView(View mView) {
        setEnableRefresh(true);
        setEnableLoadMore(true);

        mRecycler = mView.findViewById(R.id.mRecycler);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);
        adapter = new MyStudentsAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            CustomDialog.callTel(mActivity, studentsBeans.getData().getList().get(position).getM_mobile(), v -> {
                callTel(studentsBeans.getData().getList().get(position).getM_mobile());
            });
        });
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("type",getFragType());
        Controller.myRequest(Constants.GET_COACH_MEMBER,Controller.TYPE_POST,getParams(), MyStudentsBeans.class,this);
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == Constants.GET_COACH_MEMBER){
            MyStudentsBeans pageData = (MyStudentsBeans)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    studentsBeans = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    studentsBeans.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(studentsBeans != null && studentsBeans.getData() != null &&
                    studentsBeans.getData().getList() != null &&
                    studentsBeans.getData().getList().size() > 0){
                adapter.setNewData(studentsBeans.getData().getList());
                setPromtView(mActivity.SHOW_DATA);
            }else{
                setPromtView( mActivity.SHOW_EMPTY);
            }
        }
    }
}
