package com.hongyuan.coach.custom_view.table_view;

/**
 * Created by JKWANG-PC on 2017/2/13.
 */

public class TableCellData {
    private String value;
    private int row;
    private int column;
    private int rowSpan;
    private int columnSpan;
    //是否添加点击事件
    private boolean addClick;

    public TableCellData(String value, int row, int column) {
        this(value, row, column, 1, 1,false);
    }

    public TableCellData(String value, int row, int column, int rowSpan, int columnSpan,boolean addClick) {
        this.value = value;
        this.row = row;
        this.column = column;
        this.rowSpan = rowSpan;
        this.columnSpan = columnSpan;
        this.addClick = addClick;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRowSpan() {
        return rowSpan;
    }

    public void setRowSpan(int rowSpan) {
        this.rowSpan = rowSpan;
    }

    public int getColumnSpan() {
        return columnSpan;
    }

    public void setColumnSpan(int columnSpan) {
        this.columnSpan = columnSpan;
    }

    public boolean isAddClick() {
        return addClick;
    }

    public void setAddClick(boolean addClick) {
        this.addClick = addClick;
    }
}
