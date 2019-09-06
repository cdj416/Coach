package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;

public class CommentDetailsLikeBean extends BaseBean {

    /**
     * data : {"crp_id":"17"}
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
         * crp_id : 17
         */

        private String crp_id;

        public String getCrp_id() {
            return crp_id;
        }

        public void setCrp_id(String crp_id) {
            this.crp_id = crp_id;
        }
    }
}
