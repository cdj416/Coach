package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;
public class CoachKongTimeBeans extends BaseBean {


    /**
     * data : {"kong_date":"2019-08-05 17:30:00"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * kong_date : 2019-08-05 17:30:00
         */

        private String kong_date;

        public String getKong_date() {
            return kong_date;
        }

        public void setKong_date(String kong_date) {
            this.kong_date = kong_date;
        }
    }
}
