package com.hongyuan.coach.ui.view_model;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.coach.R;
import com.hongyuan.coach.base.Constants;
import com.hongyuan.coach.base.Controller;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.base.CustomViewModel;
import com.hongyuan.coach.base.SingleClick;
import com.hongyuan.coach.databinding.ActivityFriendsBinding;
import com.hongyuan.coach.ui.adapter.FriendsAdapter;
import com.hongyuan.coach.ui.beans.AttentionBean;
import com.hongyuan.coach.ui.beans.FriendsBeans;
import com.hongyuan.coach.util.CustomDialog;

public class FriendsViewModel extends CustomViewModel {

    private ActivityFriendsBinding binding;
    private FriendsAdapter adapter;
    private FriendsBeans friendsBeans;

    public FriendsViewModel(CustomActivity mActivity , ActivityFriendsBinding binding) {
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
        adapter = new FriendsAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick(2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                CustomDialog.promptDialog(mActivity, "确定要取消关注吗？", "暂不取消", "取消关注", false, v -> {
                    if(v.getId() == R.id.isCannel){
                        sendAttention(position);
                    }
                });
            }
        });
    }

    @Override
    public void refreshData() {
        friendsBeans = null;
        lazyLoad();
    }

    @Override
    protected void loadMoreData() {
        lazyLoad();
    }

    @Override
    public void lazyLoad() {
        //获取好友列表
        Controller.myRequest(Constants.GET_MY_FRIENDS,Controller.TYPE_POST,getParams(), FriendsBeans.class,this);
    }

    /*
     *取消关注
     * */
    private void sendAttention(int position){
        clearParams().setParams("f_mid",String.valueOf(friendsBeans.getData().getList().get(position).getF_mid()))
        .setParams("f_type","reduce");
        Controller.myRequest(Constants.ADD_FRIEND,Controller.TYPE_POST,getParams(), AttentionBean.class,this);
    }

    @Override
    public void onSuccess(int code,Object data) {
        if(data instanceof FriendsBeans){
            FriendsBeans pageData = (FriendsBeans)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    friendsBeans = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    friendsBeans.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(friendsBeans != null && friendsBeans.getData() != null &&
                    friendsBeans.getData().getList() != null &&
                    friendsBeans.getData().getList().size() > 0){
                adapter.setNewData(friendsBeans.getData().getList());
                mActivity.setPromtView(mActivity.SHOW_DATA);
            }else{
                mActivity.setPromtView( mActivity.SHOW_EMPTY);
            }
        }
        if(code == Constants.ADD_FRIEND){
            refreshData();
            //EventBus.getDefault().post(ConstantsCode.EB_ADD_CIRCLE,"已取消关注");
            showSuccess("已取消关注！");
        }
    }

}
