package com.hongyuan.coach.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.hongyuan.coach.R;
import com.hongyuan.coach.base.Constants;
import com.hongyuan.coach.base.Controller;
import com.hongyuan.coach.base.CustomFragment;
import com.hongyuan.coach.base.SingleClick;
import com.hongyuan.coach.custom_view.CustomRecyclerView;
import com.hongyuan.coach.ui.activity.FriendsActivity;
import com.hongyuan.coach.ui.activity.PostDetailsActivity;
import com.hongyuan.coach.ui.adapter.FindTopFriendsAdapter;
import com.hongyuan.coach.ui.adapter.V2FindContentAdapter;
import com.hongyuan.coach.ui.beans.FeatureBean;
import com.hongyuan.coach.ui.beans.FriendsBeans;
import com.hongyuan.coach.ui.beans.PostDetailsLikeBean;
import com.hongyuan.coach.util.DividerItemDecoration;


public class FeaturedFragment extends CustomFragment {

    private CustomRecyclerView topRecycler;
    private RecyclerView mRecycler;
    private FindTopFriendsAdapter topAdapter;
    private V2FindContentAdapter adapter;
    private FeatureBean featureBean;
    private TextView topTitle,moreFriends;
    private RelativeLayout topBox;

    //当前（点赞/取消点赞/关注/取消关注）等操作的数据位置
    private int mPosition;
    //区分当前是请求的添加好友（关注）还是删除好友（取关）
    private String attentionType;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find_featured;
    }

    @Override
    public void initView(View mView) {
        //开启刷新功能
        setEnableRefresh(true);
        //开启加载更多功能
        setEnableLoadMore(true);

        topRecycler = mView.findViewById(R.id.topRecycler);
        mRecycler = mView.findViewById(R.id.mRecycler);
        topTitle = mView.findViewById(R.id.topTitle);
        moreFriends = mView.findViewById(R.id.moreFriends);
        topBox = mView.findViewById(R.id.topBox);

        moreFriends.setOnClickListener(new View.OnClickListener() {
            @SingleClick(2000)
            @Override
            public void onClick(View v) {
                startActivity(FriendsActivity.class,null);
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        topRecycler.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.VERTICAL_LIST,30,0x00000000));
        topRecycler.setLayoutManager(manager);
        topAdapter = new FindTopFriendsAdapter();
        topRecycler.setAdapter(topAdapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mRecycler.setLayoutManager(layoutManager);
        adapter = new V2FindContentAdapter();
        mRecycler.setAdapter(adapter);

        topAdapter.setOnItemChildClickListener((adapter, view, position) ->{});

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            if(view.getId() == R.id.jumpDetails){
                Bundle bundle = new Bundle();
                bundle.putString("circle_id",String.valueOf(featureBean.getData().getList().get(position).getCircle_id()));
                startActivity(PostDetailsActivity.class,bundle);
            }

            //关注/取关（加好友）
            if(view.getId() == R.id.attention){
                getBaikeLike(position);
            }
        });

    }

    /*
     * 帖子点赞/取消
     * */
    private void getBaikeLike(int position){
        mPosition = position;
        clearParams().setParams("circle_id",String.valueOf(featureBean.getData().getList().get(position).getCircle_id()));
        if(featureBean.getData().getList().get(position).getIs_praise() == 0){
            Controller.myRequest(Constants.ADD_CIRCLE_PRAISE,Controller.TYPE_POST,getParams(), PostDetailsLikeBean.class,this);
        }else{
            Controller.myRequest(Constants.CANCEL_CIRCLE_PRAISE,Controller.TYPE_POST,getParams(), PostDetailsLikeBean.class,this);
        }
    }

    @Override
    protected void lazyLoad() {
        if(mActivity.userToken.getM_id() != null && "gz".equals(getFragType())){
            //获取好友列表
            Controller.myRequest(Constants.GET_MY_FRIENDS,Controller.TYPE_POST,getParams(), FriendsBeans.class,this);
        }

        //获取推荐圈子
        getCircleList();

    }

    /*
     * 加载更多
     * */
    @Override
    public void loadMoreData() {
        getCircleList();
    }

    /*
    * 下拉刷新
    * */
    @Override
    public void onRefreshData() {
        featureBean = null;
        lazyLoad();
    }

    /*
    * 获取发现帖子
    * */
    private void getCircleList(){
        //获取帖子
        clearParams().setParams("circle_state","1").setParams("circle_type",getFragType()).setParams("city_name","湖州市");
        Controller.myRequest(Constants.GET_CIRCLE_LIST,Controller.TYPE_POST,getParams(), FeatureBean.class,this);
    }

    /*
    * 不需要做区分走这里
    * */
    @Override
    public void onSuccess(int code,Object data) {
        if(data instanceof FeatureBean){
            FeatureBean pageData = (FeatureBean)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    featureBean = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    featureBean.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(featureBean != null && featureBean.getData() != null &&
                    featureBean.getData().getList() != null &&
                    featureBean.getData().getList().size() > 0){
                adapter.setNewData(featureBean.getData().getList());
                setPromtView(SHOW_DATA);
            }else{
                setPromtView(SHOW_EMPTY);
            }

        }
        if(data instanceof FriendsBeans){
            FriendsBeans friendsBeans = (FriendsBeans)data;
            if(friendsBeans.getData().getList() != null && friendsBeans.getData().getList().size() > 0){
                topRecycler.setVisibility(View.VISIBLE);
                topBox.setVisibility(View.VISIBLE);
                topAdapter.setNewData(friendsBeans.getData().getList());
            }else{
                topRecycler.setVisibility(View.GONE);
                topBox.setVisibility(View.GONE);
            }
        }

        if(code == Constants.ADD_CIRCLE_PRAISE){
            featureBean.getData().getList().get(mPosition).setIs_praise(1);
            featureBean.getData().getList().get(mPosition).setPraise_num(featureBean.getData().getList().get(mPosition).getPraise_num()+1);
            adapter.setNewData(featureBean.getData().getList());
        }
        if(code == Constants.CANCEL_CIRCLE_PRAISE){
            featureBean.getData().getList().get(mPosition).setIs_praise(0);
            featureBean.getData().getList().get(mPosition).setPraise_num(featureBean.getData().getList().get(mPosition).getPraise_num()-1);
            adapter.setNewData(featureBean.getData().getList());
        }
    }
}
