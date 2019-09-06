package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;

import java.io.Serializable;

public class LoginBean extends BaseBean implements Serializable {


    /**
     * data : {"m_id":"k5eTnZac","m_mobile":"15220219931","mi_head":"","role_id":2}
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
         * m_id : k5eTnZac
         * m_mobile : 15220219931
         * mi_head :
         * role_id : 2
         */

        private String m_id;
        private String m_mobile;
        private String mi_head;
        private int role_id;

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

        public String getMi_head() {
            return mi_head;
        }

        public void setMi_head(String mi_head) {
            this.mi_head = mi_head;
        }

        public int getRole_id() {
            return role_id;
        }

        public void setRole_id(int role_id) {
            this.role_id = role_id;
        }
    }
}
