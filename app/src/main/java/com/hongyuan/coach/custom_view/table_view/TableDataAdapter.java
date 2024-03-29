package com.hongyuan.coach.custom_view.table_view;

import android.content.Context;
import android.widget.GridLayout;

import com.hongyuan.coach.ui.beans.CourseCalendarUseBeans;

import java.util.List;

/**
 * Created by JKWANG-PC on 2017/2/13.
 */

public abstract class TableDataAdapter {

    protected final Context context;
    private final List<CourseCalendarUseBeans.PageContentBeans> data;

    protected GridLayout tableDataView;

    protected int tableDataViewWidth = 0;
    protected int columnCount;


    public TableDataAdapter(Context context, List<CourseCalendarUseBeans.PageContentBeans> data, int columnCount) {
        this.context = context;
        this.data = data;
        this.columnCount = columnCount;
    }

    protected void setTableDataViewWidth(int tableDataViewWidth) {
        this.tableDataViewWidth = tableDataViewWidth;
    }

    protected void setTableDataView(GridLayout tableDataView) {
        this.tableDataView = tableDataView;
        addGridLayoutView(context,data);
    }

    protected abstract void addGridLayoutView(Context context, List<CourseCalendarUseBeans.PageContentBeans> cellDatas);
}
