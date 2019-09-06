package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.util.TimeUtil;

import java.io.Serializable;
import java.util.List;

public class CourseCalendarUseBeans implements Serializable {


    private int pageNum;

    private List<PageTitleBeans> pageTitleBeansList;

    private List<PageContentBeans> pageContentBeansList;

    public List<PageContentBeans> getPageContentBeansList() {
        return pageContentBeansList;
    }

    public void setPageContentBeansList(List<PageContentBeans> pageContentBeansList) {
        this.pageContentBeansList = pageContentBeansList;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<PageTitleBeans> getPageTitleBeansList() {
        return pageTitleBeansList;
    }

    public void setPageTitleBeansList(List<PageTitleBeans> pageTitleBeansList) {
        this.pageTitleBeansList = pageTitleBeansList;
    }

    public static class PageTitleBeans implements Serializable{
        private String now_day;
        private int is_cur_date;
        private String week;

        public String getNow_day() {
            return now_day;
        }

        public void setNow_day(String now_day) {
            this.now_day = now_day;
        }

        public int getIs_cur_date() {
            return is_cur_date;
        }

        public void setIs_cur_date(int is_cur_date) {
            this.is_cur_date = is_cur_date;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }
    }

    public static class PageContentBeans implements Serializable{
        private int weekIndex;
        private int is_kong;
        private String m_name;
        private String mi_head;
        private String mi_realname;
        private String cp_name;
        private int state;
        private String start_date;
        private String week;
        private String now_day;
        private String showNowDay;
        private String showTime;

        //布局显示控制需要的
        private int row;
        private int column;
        private int rowSpan;
        private int columnSpan;
        //是否添加点击事件
        private boolean addClick;
        //判断时间是否已过期
        private boolean isExpired;

        public boolean isExpired() {
            return TimeUtil.getExpired(this.start_date,TimeUtil.dateFormatYMDHMS);
        }

        public void setExpired(boolean expired) {
            isExpired = expired;
        }

        public int getWeekIndex() {
            return weekIndex;
        }

        public void setWeekIndex(int weekIndex) {
            this.weekIndex = weekIndex;
        }

        public int getIs_kong() {
            return is_kong;
        }

        public void setIs_kong(int is_kong) {
            this.is_kong = is_kong;
        }

        public String getM_name() {
            return m_name;
        }

        public void setM_name(String m_name) {
            this.m_name = m_name;
        }

        public String getMi_head() {
            return mi_head;
        }

        public void setMi_head(String mi_head) {
            this.mi_head = mi_head;
        }

        public String getMi_realname() {
            return mi_realname;
        }

        public void setMi_realname(String mi_realname) {
            this.mi_realname = mi_realname;
        }

        public String getCp_name() {
            return cp_name;
        }

        public void setCp_name(String cp_name) {
            this.cp_name = cp_name;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
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

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getNow_day() {
            return now_day;
        }

        public void setNow_day(String now_day) {
            this.now_day = now_day;
        }

        public String getShowNowDay() {
            return showNowDay;
        }

        public void setShowNowDay(String showNowDay) {
            this.showNowDay = showNowDay;
        }

        public String getShowTime() {
            return showTime;
        }

        public void setShowTime(String showTime) {
            this.showTime = showTime;
        }
    }
}
