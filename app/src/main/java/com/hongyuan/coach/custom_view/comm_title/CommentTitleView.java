package com.hongyuan.coach.custom_view.comm_title;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.coach.R;
import com.hongyuan.coach.base.SingleClick;
import com.hongyuan.coach.custom_view.FlowLayoutManager;
import com.hongyuan.coach.custom_view.NestedRecyclerView;
import com.hongyuan.coach.ui.beans.CommentsNumBeans;
import com.hongyuan.coach.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class CommentTitleView extends RelativeLayout {

    private TextView titleName;
    private NestedRecyclerView mRecycler;
    private CommentTitleAdapter adapter;
    //各个定死了的状态
    public static final int ALL_COMMENT = 0;
    public static final int HAVE_IMG_COMMENT = -1;
    public static final int FIVE_COMMENT = 5;
    public static final int FOURE_COMMENT = 4;
    public static final int THIRTH_COMMENT = 3;
    public static final int TWO_COMMENT = 2;
    public static final int ONE_COMMENT = 1;

    //数据
    private List<CommentTitleBeans> mList = new ArrayList<>();

    public interface ScoreChange{
        void valueChange(int score);
    }
    private ScoreChange change;

    public CommentTitleView(Context context) {
        super(context);
        initLayoutView();
    }

    public CommentTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public CommentTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_comment_top, this);
        titleName = view.findViewById(R.id.titleName);
        mRecycler = view.findViewById(R.id.mRecycler);


        FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
        mRecycler.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.VERTICAL_LIST,30,0x00000000));
        mRecycler.setLayoutManager(flowLayoutManager);
        adapter = new CommentTitleAdapter();
        adapter.setNewData(getData());
        mRecycler.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick(2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                for(CommentTitleBeans beans:mList){
                    beans.setSelect(false);
                }
                mList.get(position).setSelect(true);
                adapter.setNewData(mList);
                if(change != null){
                    change.valueChange(mList.get(position).getScore());
                }
            }
        });
    }

    /*
    * 初始回调函数
    * */
    public void setScoreChange(ScoreChange change){
        this.change = change;
    }

    /*
    * 初始化数量
    * */
    public void setNum(CommentsNumBeans.DataBean bean){
        for(int i = 0 ; i < bean.getItem().size() ; i++){
            for(int j = 0 ; j < mList.size() ; j++){
                if(mList.get(j).getName().equals(bean.getItem().get(i).getName())){
                    mList.get(j).setNum(bean.getItem().get(i).getCount());
                    break;
                }
            }
        }

        mList.get(0).setNum(bean.getAmount());
        mList.get(1).setNum(bean.getCount_img());

        adapter.setNewData(mList);
    }


    /*
    * 获取写死的数据
    * */
    private List<CommentTitleBeans> getData(){
        mList.add(new CommentTitleBeans(0,"全部",0));
        mList.add(new CommentTitleBeans(0,"有图",-1));
        mList.add(new CommentTitleBeans(0,"超出预期",5));
        mList.add(new CommentTitleBeans(0,"非常满意",4));
        mList.add(new CommentTitleBeans(0,"满意",3));
        mList.add(new CommentTitleBeans(0,"一般",2));
        mList.add(new CommentTitleBeans(0,"不满意",1));
        mList.get(0).setSelect(true);
        return mList;
    }
}
