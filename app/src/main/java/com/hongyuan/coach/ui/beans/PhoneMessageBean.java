package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;

public class PhoneMessageBean extends BaseBean {

    /**
     * data : {"token":"2b686b2e18b43028d6aafb298cfd"}
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
         * token : 2b686b2e18b43028d6aafb298cfd
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
