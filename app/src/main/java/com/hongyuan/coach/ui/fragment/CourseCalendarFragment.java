package com.hongyuan.coach.ui.fragment;
import android.view.View;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomFragment;
import com.hongyuan.coach.custom_view.table_view.SimpleTableDataAdapter;
import com.hongyuan.coach.custom_view.table_view.SimpleTableHeaderAdapter;
import com.hongyuan.coach.custom_view.table_view.TableHeaderColumnModel;
import com.hongyuan.coach.custom_view.table_view.TableView;
import com.hongyuan.coach.ui.beans.CourseCalendarUseBeans;
import com.hongyuan.coach.util.BaseUtil;
import com.hongyuan.coach.util.TimeUtil;

import java.util.Calendar;
import java.util.List;

public class CourseCalendarFragment extends CustomFragment {

    private TableView tableview;

    //数据集(当前页所对应的数据)
    private CourseCalendarUseBeans calendarUseBeans;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_course_calendar;
    }

    @Override
    public void initView(View mView) {

        setEnableOverScrollDrag(true);

        //获取数据集
        calendarUseBeans = (CourseCalendarUseBeans) getSerializableBeans("calendarData");
        if(!BaseUtil.isValue(calendarUseBeans)){
            return;
        }

        tableview = mView.findViewById(R.id.tableview);

        //先去合并已经约了的课程数据
        setMerge(calendarUseBeans.getPageContentBeansList());

        SimpleTableDataAdapter dataAdapter = new SimpleTableDataAdapter(getContext(),calendarUseBeans.getPageContentBeansList(), 8);
        TableHeaderColumnModel columnModel = new TableHeaderColumnModel(calendarUseBeans.getPageTitleBeansList());
        SimpleTableHeaderAdapter headerAdapter = new SimpleTableHeaderAdapter(getContext(),columnModel);
        tableview.setTableAdapter(headerAdapter,dataAdapter);
        tableview.setHeaderElevation(20);
    }

    /*
    * 遍历设置合并项
    * */
    private void setMerge(List<CourseCalendarUseBeans.PageContentBeans> mList){
        for(int i = 0 ; i < mList.size() ; i++){
            if(mList.get(i).getIs_kong() == 3 && mList.get(i).getRowSpan() == 1){

                CourseCalendarUseBeans.PageContentBeans rowBeans = getRowBeans(mList,(mList.get(i).getRow()+1),mList.get(i).getColumn());
                if(BaseUtil.isValue(rowBeans)){
                    mList.get(i).setRowSpan(2);
                    mList.get(i).setAddClick(true);
                    mList.get(i).setShowTime(getShowTime(mList.get(i).getStart_date()));
                    rowBeans.setRowSpan(0);
                }
            }
        }
    }

    /*
    * 通过row查找相对应的另一半（哈哈哈哈哈）
    * */
    private CourseCalendarUseBeans.PageContentBeans getRowBeans(List<CourseCalendarUseBeans.PageContentBeans> mList,int row,int column){
        for(CourseCalendarUseBeans.PageContentBeans contentBeans:mList){
            if(contentBeans.getRow() ==  row && contentBeans.getColumn() == column){
                if(contentBeans.getIs_kong() == 3){
                    return contentBeans;
                }

            }
        }
        return null;
    }

    /*
    * 拼装需要显示的时间
    * */
    private String getShowTime(String time){
        String startTime = TimeUtil.formatDate(time,TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM);
        String endTime = TimeUtil.formatDate(TimeUtil.getStringByOffset(time,TimeUtil.dateFormatYMDHMS,
                Calendar.HOUR_OF_DAY,1),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM);

        return startTime+"-"+endTime;
    }
}