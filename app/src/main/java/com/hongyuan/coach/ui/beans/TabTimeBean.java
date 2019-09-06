package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;
import com.hongyuan.coach.util.TimeUtil;

import java.util.List;

public class TabTimeBean extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * now_day : 2019-07-03
         * is_cur_date : 1
         * week : 三
         */

        private String now_day;
        private int is_cur_date;
        private String week;
        private String showName;
        private boolean select;

        public DataBean(String now_day, int is_cur_date, String week, String showName, boolean select) {
            this.now_day = now_day;
            this.is_cur_date = is_cur_date;
            this.week = week;
            this.showName = showName;
            this.select = select;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

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

        public String getShowName(){
            return TimeUtil.getMdotD(now_day);
        }

        public String getShowWeek(){
            return "周"+this.week;
        }


    }
}
