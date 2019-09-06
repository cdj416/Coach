package com.hongyuan.coach.custom_view.table_view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.coach.R;
import com.hongyuan.coach.custom_view.bubble_frame.BubblePopupWindow;
import com.hongyuan.coach.ui.beans.CourseCalendarUseBeans;
import com.hongyuan.coach.util.BaseUtil;
import com.hongyuan.coach.util.TimeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

/**
 * Created by JKWANG-PC on 2017/2/16.
 */

public final class SimpleTableDataAdapter extends TableDataAdapter {

    private float textSize = 12;
    private int paddingLeft = 0;
    private int paddingTop = 0;
    private int paddingRight = 0;
    private int paddingBottom = 0;
    private int typeface = Typeface.NORMAL;
    private int textColor = 0xFF999999;
    private int reservationTextColor = 0xFFFFFFFF;

    //气泡弹框
    private BubblePopupWindow topWindow;
    private LayoutInflater layoutInflater;
    private View bubbleView;

    public SimpleTableDataAdapter(Context context, List<CourseCalendarUseBeans.PageContentBeans> data, int columnCount) {
        super(context, data, columnCount);
    }

    @Override
    protected void addGridLayoutView(Context context, List<CourseCalendarUseBeans.PageContentBeans> cellDatas) {

        layoutInflater = LayoutInflater.from(context);
        topWindow = new BubblePopupWindow(context);

        for (int i = 0, n = cellDatas.size(); i < n; ++i) {
            CourseCalendarUseBeans.PageContentBeans cellData = cellDatas.get(i);
            TextView view = new TextView(context);
            view.setText(cellData.getM_name());
            GridLayout.Spec rowSpec = GridLayout.spec(cellData.getRow(), cellData.getRowSpan());
            GridLayout.Spec colomnSpce = GridLayout.spec(cellData.getColumn(), cellData.getColumnSpan());
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, colomnSpce);
            params.setGravity(Gravity.FILL);
            params.width = (int) (tableDataViewWidth * cellData.getColumnSpan() / columnCount);
            params.height = params.width/2;

            if(cellData.getColumn() == 0){
                view.setBackgroundResource(R.drawable.tableview_onecolumn_bg);
                view.setTextColor(textColor);
            }else{
                if(cellData.getRow()%2 == 0 ){
                    view.setBackgroundResource(R.drawable.tableview_bg);
                }else{
                    view.setBackgroundResource(R.drawable.tableview_no_top_bg);
                }

                if(cellData.isAddClick()){
                    view.setTextColor(reservationTextColor);
                    if(cellData.isExpired()){
                        view.setBackgroundResource(R.drawable.tableview_expired_bg);
                    }else{
                        view.setBackgroundResource(R.drawable.tableview_reservation_bg);
                    }

                }else{
                    view.setTextColor(textColor);
                }
            }
            view.setGravity(Gravity.CENTER);
            view.setTextSize(textSize);
            view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
            view.setTypeface(view.getTypeface(), typeface);
            tableDataView.addView(view, params);
            //第一行不添加点击事件
            if(cellData.isAddClick()){
                view.setOnClickListener(view1 -> {
                    showPopoWind(view1,cellData);
                });
            }

        }
    }
    /*
    * 展示气泡弹框
    * */
    private void showPopoWind(View view,CourseCalendarUseBeans.PageContentBeans cellData){
        //加载popowindow
        bubbleView = layoutInflater.inflate(R.layout.popuwindow_course_calendar, null);
        topWindow.setBubbleView(bubbleView);//添加内容

        RoundedImageView headImg = bubbleView.findViewById(R.id.headImg);
        TextView userName = bubbleView.findViewById(R.id.userName);
        TextView cpName = bubbleView.findViewById(R.id.cpName);
        TextView dataText = bubbleView.findViewById(R.id.dataText);
        TextView timeText = bubbleView.findViewById(R.id.timeText);

        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img).centerCrop();
        Glide.with(context).load(cellData.getMi_head()).apply(options).into(headImg);
        userName.setText(cellData.getM_name());
        cpName.setText(cellData.getCp_name());
        dataText.setText(getShowText(cellData));
        timeText.setText(cellData.getShowTime());


        if(cellData.getColumn() == 1){
            topWindow.show(view, Gravity.RIGHT);
        }
        if(cellData.getColumn() == 7){
            topWindow.show(view, Gravity.LEFT);
        }
        if(cellData.getColumn() != 1 && cellData.getColumn() != 7 && cellData.getRow() <= 11){
            topWindow.show(view, Gravity.BOTTOM);
        }
        if(cellData.getColumn() != 1 && cellData.getColumn() != 7 && cellData.getRow() > 11){
            topWindow.show(view, Gravity.TOP);
        }
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public void setPaddings(final int left, final int top, final int right, final int bottom) {
        this.paddingLeft = left;
        this.paddingTop = top;
        this.paddingRight = right;
        this.paddingBottom = bottom;
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    public void setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
    }

    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
    }

    public void setPaddingBottom(int paddingBottom) {
        this.paddingBottom = paddingBottom;
    }

    public void setTypeface(int typeface) {
        this.typeface = typeface;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    /*
    * 获取显示日期
    * */
    private String getShowText(CourseCalendarUseBeans.PageContentBeans cellData){

        if(TimeUtil.isToday(cellData.getStart_date(),TimeUtil.dateFormatYMDHMS)){
            return "今日";
        }else{
            return cellData.getShowNowDay();
        }
    }
}
