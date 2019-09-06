package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;

public class PostDetailsSendMeassageBean extends BaseBean {


    /**
     * data : {"cr_info":{"cr_id":32,"circle_id":39,"cr_id_father":0,"m_id":7,"to_mid":0,"cr_content":"哈撒啊","cr_state":0,"add_time":1562571165,"update_admin":0,"update_time":0,"reply_num":0,"praise_num":0,"first_cr_id":0,"m_name":"m19931","m_mobile":"15220219931","mi_head":"","circle_content":"易烊千玺说一下","add_date":"2019-07-08 15:32:45","to_m_name":""}}
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
         * cr_info : {"cr_id":32,"circle_id":39,"cr_id_father":0,"m_id":7,"to_mid":0,"cr_content":"哈撒啊","cr_state":0,"add_time":1562571165,"update_admin":0,"update_time":0,"reply_num":0,"praise_num":0,"first_cr_id":0,"m_name":"m19931","m_mobile":"15220219931","mi_head":"","circle_content":"易烊千玺说一下","add_date":"2019-07-08 15:32:45","to_m_name":""}
         */

        private CrInfoBean cr_info;

        public CrInfoBean getCr_info() {
            return cr_info;
        }

        public void setCr_info(CrInfoBean cr_info) {
            this.cr_info = cr_info;
        }

        public static class CrInfoBean {
            /**
             * cr_id : 32
             * circle_id : 39
             * cr_id_father : 0
             * m_id : 7
             * to_mid : 0
             * cr_content : 哈撒啊
             * cr_state : 0
             * add_time : 1562571165
             * update_admin : 0
             * update_time : 0
             * reply_num : 0
             * praise_num : 0
             * first_cr_id : 0
             * m_name : m19931
             * m_mobile : 15220219931
             * mi_head :
             * circle_content : 易烊千玺说一下
             * add_date : 2019-07-08 15:32:45
             * to_m_name :
             */

            private int cr_id;
            private int circle_id;
            private int cr_id_father;
            private int m_id;
            private int to_mid;
            private String cr_content;
            private int cr_state;
            private int add_time;
            private int update_admin;
            private int update_time;
            private int reply_num;
            private int praise_num;
            private int first_cr_id;
            private String m_name;
            private String m_mobile;
            private String mi_head;
            private String circle_content;
            private String add_date;
            private String to_m_name;

            public int getCr_id() {
                return cr_id;
            }

            public void setCr_id(int cr_id) {
                this.cr_id = cr_id;
            }

            public int getCircle_id() {
                return circle_id;
            }

            public void setCircle_id(int circle_id) {
                this.circle_id = circle_id;
            }

            public int getCr_id_father() {
                return cr_id_father;
            }

            public void setCr_id_father(int cr_id_father) {
                this.cr_id_father = cr_id_father;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public int getTo_mid() {
                return to_mid;
            }

            public void setTo_mid(int to_mid) {
                this.to_mid = to_mid;
            }

            public String getCr_content() {
                return cr_content;
            }

            public void setCr_content(String cr_content) {
                this.cr_content = cr_content;
            }

            public int getCr_state() {
                return cr_state;
            }

            public void setCr_state(int cr_state) {
                this.cr_state = cr_state;
            }

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public int getUpdate_admin() {
                return update_admin;
            }

            public void setUpdate_admin(int update_admin) {
                this.update_admin = update_admin;
            }

            public int getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(int update_time) {
                this.update_time = update_time;
            }

            public int getReply_num() {
                return reply_num;
            }

            public void setReply_num(int reply_num) {
                this.reply_num = reply_num;
            }

            public int getPraise_num() {
                return praise_num;
            }

            public void setPraise_num(int praise_num) {
                this.praise_num = praise_num;
            }

            public int getFirst_cr_id() {
                return first_cr_id;
            }

            public void setFirst_cr_id(int first_cr_id) {
                this.first_cr_id = first_cr_id;
            }

            public String getM_name() {
                return m_name;
            }

            public void setM_name(String m_name) {
                this.m_name = m_name;
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

            public String getCircle_content() {
                return circle_content;
            }

            public void setCircle_content(String circle_content) {
                this.circle_content = circle_content;
            }

            public String getAdd_date() {
                return add_date;
            }

            public void setAdd_date(String add_date) {
                this.add_date = add_date;
            }

            public String getTo_m_name() {
                return to_m_name;
            }

            public void setTo_m_name(String to_m_name) {
                this.to_m_name = to_m_name;
            }
        }
    }
}
