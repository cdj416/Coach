package com.hongyuan.coach.custom_view.table_view;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Simple implementation of the {@link TableHeaderAdapter}. This adapter will render the given header
 * Strings as {@link TextView}.
 */
public final class SimpleTableHeaderAdapter extends TableHeaderAdapter {

    private int paddingLeft = 0;
    private int paddingTop = 0;
    private int paddingRight = 0;
    private int paddingBottom = 0;
    private int textSize = 14;
    private int typeface = Typeface.NORMAL;
    //默认的字体颜色
    private int textColor = 0xff999999;
    //当天的字体颜色
    private int todayTextColor = 0xff3DA1E7;

    public SimpleTableHeaderAdapter(Context context, TableHeaderColumnModel columnModel) {
        super(context, columnModel);
    }


    /**
     * Sets the padding that will be used for all table headers.
     *
     * @param left
     *         The padding on the left side.
     * @param top
     *         The padding on the top side.
     * @param right
     *         The padding on the right side.
     * @param bottom
     *         The padding on the bottom side.
     */
    public void setPaddings(final int left, final int top, final int right, final int bottom) {
        paddingLeft = left;
        paddingTop = top;
        paddingRight = right;
        paddingBottom = bottom;
    }

    /**
     * Sets the padding that will be used on the left side for all table headers.
     *
     * @param paddingLeft
     *         The padding on the left side.
     */
    public void setPaddingLeft(final int paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    /**
     * Sets the padding that will be used on the top side for all table headers.
     *
     * @param paddingTop
     *         The padding on the top side.
     */
    public void setPaddingTop(final int paddingTop) {
        this.paddingTop = paddingTop;
    }

    /**
     * Sets the padding that will be used on the right side for all table headers.
     *
     * @param paddingRight
     *         The padding on the right side.
     */
    public void setPaddingRight(final int paddingRight) {
        this.paddingRight = paddingRight;
    }

    /**
     * Sets the padding that will be used on the bottom side for all table headers.
     *
     * @param paddingBottom
     *         The padding on the bottom side.
     */
    public void setPaddingBottom(final int paddingBottom) {
        this.paddingBottom = paddingBottom;
    }

    /**
     * Sets the text size that will be used for all table headers.
     *
     * @param textSize
     *         The text size that shall be used.
     */
    public void setTextSize(final int textSize) {
        this.textSize = textSize;
    }

    /**
     * Sets the typeface that will be used for all table headers.
     *
     * @param typeface
     *         The type face that shall be used.
     */
    public void setTypeface(final int typeface) {
        this.typeface = typeface;
    }

    /**
     * Sets the text color that will be used for all table headers.
     *
     * @param textColor
     *         The text color that shall be used.
     */
    public void setTextColor(final int textColor) {
        this.textColor = textColor;
    }

    @Override
    public View getHeaderView(final int columnIndex, final ViewGroup parentView) {
        final TextView textView = new TextView(getContext());

        if (columnIndex < columnModel.getColumnCount()) {
            textView.setText(columnModel.getColumnValue(columnIndex));
        }

        textView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        textView.setTypeface(textView.getTypeface(), typeface);
        textView.setTextSize(textSize);
        if(columnModel.isToday(columnIndex)){
            textView.setTextColor(todayTextColor);
        }else{
            textView.setTextColor(textColor);
        }
        textView.setGravity(Gravity.CENTER);

        return textView;
    }
}
