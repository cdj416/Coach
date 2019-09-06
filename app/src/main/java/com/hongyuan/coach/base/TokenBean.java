package com.hongyuan.coach.base;

import java.io.Serializable;

public class TokenBean extends BaseBean implements Serializable {


    /**
     * data : {"at_id":1,"at_name":"fit","at_pwd":"14e1b600b1fd579f47433b88e8d85291","token":"8910349115cffa3c12d3d62.66901630"}
     * status : {"succeed":"0","error_code":600,"error_desc":"账号密码有误"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * at_id : 1
         * at_name : fit
         * at_pwd : 14e1b600b1fd579f47433b88e8d85291
         * token : 8910349115cffa3c12d3d62.66901630
         */

        private int at_id;
        private String at_name;
        private String at_pwd;
        private String token;

        //用户登录成功时获取的id
        private String m_id;
        private String m_mobile;

        public String getM_id() {
            return m_id;
        }

        public void setM_id(String m_id) {
            this.m_id = m_id;
        }

        public String getM_mobile() {
            return m_mobile;
        }

        public void setM_mobile(String m_mobile) {
            this.m_mobile = m_mobile;
        }

        public int getAt_id() {
            return at_id;
        }

        public void setAt_id(int at_id) {
            this.at_id = at_id;
        }

        public String getAt_name() {
            return at_name;
        }

        public void setAt_name(String at_name) {
            this.at_name = at_name;
        }

        public String getAt_pwd() {
            return at_pwd;
        }

        public void setAt_pwd(String at_pwd) {
            this.at_pwd = at_pwd;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
