package com.hongyuan.coach.custom_view.table_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;

import com.hongyuan.coach.R;

public class TableView extends RelativeLayout {

    private static final int DEFAULT_HEADER_COLOR = 0xFF999999;

    private TableHeaderView tableHeaderView;
    private GridLayout tableDataView;

    private TableHeaderAdapter tableHeaderAdapter;
    private TableDataAdapter tableDataAdapter;

    private int headerElevation = 20;
    private int headerColor = DEFAULT_HEADER_COLOR;

    public TableView(Context context) {
        this(context,null);
    }

    public TableView(Context context, AttributeSet attributes) {
        super(context, attributes);

        //setOrientation(LinearLayout.VERTICAL);
        tableHeaderView = new TableHeaderView(getContext());
        tableDataView = new GridLayout(getContext());
    }

    public void setTableAdapter(final TableHeaderAdapter tableHeaderAdapter, final TableDataAdapter tableDataAdapter){
        this.tableHeaderAdapter = tableHeaderAdapter;
        this.tableDataAdapter = tableDataAdapter;

        if(isInEditMode()){
            return;
        }

        setHeaderView();

        ViewTreeObserver viewTreeObserver = tableHeaderView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tableDataAdapter.setTableDataViewWidth(tableHeaderView.getWidth());
                tableHeaderView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                setupTableDataView();
                forceRefresh();
            }
        });
    }

    protected void setHeaderView() {

        tableHeaderView.setAdapter(tableHeaderAdapter);
        //有阴影的背景线
        //tableHeaderView.setBackgroundColor(headerColor);
        tableHeaderView.setId(R.id.table_header_view);

        if (getChildCount() == 2) {
            removeViewAt(0);
        }

        addView(tableHeaderView, 0);
        setHeaderElevation(headerElevation);

        forceRefresh();
    }

    /**
     * Sets the elevation level of the header view. If you are not able to see the elevation shadow
     * you should set a background(-color) to the header.
     *
     * @param elevation
     *         The elevation that shall be set to the table header.
     */
    public void setHeaderElevation(final int elevation) {
        ViewCompat.setElevation(tableHeaderView, elevation);
    }

    private void setupTableDataView() {


        final LayoutParams scrollViewLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        NestedScrollView scrollView = new NestedScrollView(getContext());
        scrollView.setLayoutParams(scrollViewLayoutParams);
        addView(scrollView);


        final LayoutParams dataViewLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        tableDataView.setLayoutParams(dataViewLayoutParams);
        tableDataView.setId(R.id.table_data_view);
        tableDataAdapter.setTableDataView(tableDataView);
        scrollView.addView(tableDataView);
    }

    private void forceRefresh() {
        if (tableHeaderView != null) {
            tableHeaderView.invalidate();

        }
        if (tableDataView != null) {
            tableDataView.invalidate();
        }
    }
}
