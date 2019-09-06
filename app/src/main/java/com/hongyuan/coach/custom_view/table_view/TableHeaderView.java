package com.hongyuan.coach.custom_view.table_view;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hongyuan.coach.R;

/**
 * Created by JKWANG-PC on 2017/2/13.
 */

class TableHeaderView extends LinearLayout {

    protected TableHeaderAdapter adapter;
    private Activity mActivity;

    public TableHeaderView(Context context) {
        super(context);

        mActivity = (Activity) context;

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        final LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);
    }

    /**
     * Sets the {@link TableHeaderAdapter} that is used to render the header views of every single column.
     *
     * @param adapter
     *         The {@link TableHeaderAdapter} that should be set.
     */
    public void setAdapter(final TableHeaderAdapter adapter) {
        this.adapter = adapter;
        renderHeaderViews();
    }

    @Override
    public void invalidate() {
        renderHeaderViews();
        super.invalidate();
    }

    /**
     * This method renders the header views for every single column.
     */
    protected void renderHeaderViews() {
        removeAllViews();

        for (int columnIndex = 0; columnIndex < adapter.getColumnCount(); columnIndex++) {
            View headerView = adapter.getHeaderView(columnIndex, this);
            if (headerView == null) {
                headerView = new TextView(getContext());
            }

            if(columnIndex == 0){
                headerView.setBackgroundColor(getContext().getResources().getColor(R.color.transparent));
            }else{
                headerView.setBackgroundColor(getContext().getResources().getColor(R.color.color_FFFFFF));
            }

            //获取屏宽
            int screenWidth = mActivity.getWindowManager().getDefaultDisplay().getWidth();

            final int width = 0;
            final int height = screenWidth/adapter.getColumnCount();
            final int weight = adapter.getColumnWeight(columnIndex);
            final LayoutParams headerLayoutParams = new LayoutParams(width, height, weight);

            addView(headerView, headerLayoutParams);
        }
    }
}
