package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;

public class PostDetailsLikeBean extends BaseBean {

    /**
     * data : {"circle_id":"45"}
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
         * circle_id : 45
         */

        private String circle_id;

        public String getCircle_id() {
            return circle_id;
        }

        public void setCircle_id(String circle_id) {
            this.circle_id = circle_id;
        }
    }
}
