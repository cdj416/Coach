package com.hongyuan.coach.custom_view.table_view;

import com.hongyuan.coach.ui.beans.CourseCalendarUseBeans;
import com.hongyuan.coach.util.BaseUtil;
import com.hongyuan.coach.util.TimeUtil;

import java.util.List;

/**
 * Created by JKWANG-PC on 2017/2/13.
 */

public class TableHeaderColumnModel {
    private static final int DEFAULT_COLUMN_WEIGHT = 1;

    private final List<CourseCalendarUseBeans.PageTitleBeans> columns;
    private int columnCount;

    public TableHeaderColumnModel(List<CourseCalendarUseBeans.PageTitleBeans> columns) {
        this.columns = columns;
        this.columnCount = columns.size();
    }

    /**
     * Sets the column count to this model.
     *
     * @param columnCount
     *         The number of columns that shall be set.
     */
    public void setColumnCount(final int columnCount) {
        this.columnCount = columnCount;
    }

    /**
     * Gives the column count of this model.
     *
     * @return The number of columns that is currently set.
     */
    public int getColumnCount() {
        return columnCount;
    }

    public String getColumnValue(final int columnIndex){
        return getShowText(columns.get(columnIndex));
    }

    /**
     * Gives the column weight (the relative width of a column) of the column at the given index.
     *
     * @param columnIndex
     *         The index of the column to receive the column weight.
     * @return The column weight of the column at the given index.
     */
    public int getColumnWeight(final int columnIndex) {
        //Integer columnWeight = columns.get(columnIndex).second;
        //横向比重，目前项目中无横向合并，所以暂时写死为1
        Integer columnWeight = 1;
        if (columnWeight == null) {
            columnWeight = DEFAULT_COLUMN_WEIGHT;
        }
        return columnWeight;
    }

    /*
    * 组装需要显示的数据
    * */
    private String getShowText(CourseCalendarUseBeans.PageTitleBeans titleBeans){
        if(titleBeans.getIs_cur_date() == 1){
            if(!BaseUtil.isValue(titleBeans.getWeek())) return "";
            return titleBeans.getWeek()+"\n"+"今日";
        }else{
            if(!BaseUtil.isValue(titleBeans.getWeek())) return "";
            return titleBeans.getWeek()+"\n"+TimeUtil.formatDate(titleBeans.getNow_day(),TimeUtil.dateFormatYMD,TimeUtil.dateFormatDotNoZoonMD);
        }
    }

    /*
    * 是否为当日
    * */
    public boolean isToday(final int columnIndex){
        return this.columns.get(columnIndex).getIs_cur_date() == 1;
    }
}
