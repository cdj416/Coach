package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;

import java.util.List;

public class TimeBean extends BaseBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * now_time : 09:00:00
         * is_kong : 4
         * ct_id : 103
         */

        private String now_time;
        private int is_kong;
        private int ct_id;
        private boolean select;

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public String getNow_time() {
            return now_time;
        }

        public void setNow_time(String now_time) {
            this.now_time = now_time;
        }

        public int getIs_kong() {
            return is_kong;
        }

        public void setIs_kong(int is_kong) {
            this.is_kong = is_kong;
        }

        public int getCt_id() {
            return ct_id;
        }

        public void setCt_id(int ct_id) {
            this.ct_id = ct_id;
        }
    }
}
