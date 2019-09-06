package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;

public class RegisterdBean extends BaseBean {


    /**
     * data : {"m_id":"k5eTnZad","m_mobile":"18183185173","role_id":1}
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
         * m_id : k5eTnZad
         * m_mobile : 18183185173
         * role_id : 1
         */

        private String m_id;
        private String m_mobile;
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

        public int getRole_id() {
            return role_id;
        }

        public void setRole_id(int role_id) {
            this.role_id = role_id;
        }
    }
}
