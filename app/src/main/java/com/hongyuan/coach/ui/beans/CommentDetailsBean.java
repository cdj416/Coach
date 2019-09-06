package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;

import java.util.List;

public class CommentDetailsBean extends BaseBean {


    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"cr_id":43,"circle_id":43,"cr_id_father":42,"m_id":7,"to_mid":7,"cr_content":"啦啦","cr_state":0,"add_time":1562574991,"update_admin":0,"update_time":0,"reply_num":0,"praise_num":0,"first_cr_id":37,"m_name":"m19931","m_mobile":"15220219931","mi_head":"","circle_content":"性子在一起在一起无所谓","add_date":"2019-07-08 16:36:31","is_praise":0,"to_m_name":"m19931"},{"cr_id":42,"circle_id":43,"cr_id_father":37,"m_id":7,"to_mid":7,"cr_content":"妈妈","cr_state":0,"add_time":1562574892,"update_admin":0,"update_time":0,"reply_num":1,"praise_num":0,"first_cr_id":37,"m_name":"m19931","m_mobile":"15220219931","mi_head":"","circle_content":"性子在一起在一起无所谓","add_date":"2019-07-08 16:34:52","is_praise":0,"to_m_name":"m19931"},{"cr_id":41,"circle_id":43,"cr_id_father":37,"m_id":7,"to_mid":7,"cr_content":"啦啦","cr_state":0,"add_time":1562574881,"update_admin":0,"update_time":0,"reply_num":0,"praise_num":0,"first_cr_id":37,"m_name":"m19931","m_mobile":"15220219931","mi_head":"","circle_content":"性子在一起在一起无所谓","add_date":"2019-07-08 16:34:41","is_praise":0,"to_m_name":"m19931"},{"cr_id":40,"circle_id":43,"cr_id_father":38,"m_id":7,"to_mid":7,"cr_content":"哈哈","cr_state":0,"add_time":1562574869,"update_admin":0,"update_time":0,"reply_num":0,"praise_num":0,"first_cr_id":37,"m_name":"m19931","m_mobile":"15220219931","mi_head":"","circle_content":"性子在一起在一起无所谓","add_date":"2019-07-08 16:34:29","is_praise":0,"to_m_name":"m19931"},{"cr_id":38,"circle_id":43,"cr_id_father":37,"m_id":7,"to_mid":7,"cr_content":"来比赛","cr_state":0,"add_time":1562572956,"update_admin":0,"update_time":0,"reply_num":1,"praise_num":0,"first_cr_id":37,"m_name":"m19931","m_mobile":"15220219931","mi_head":"","circle_content":"性子在一起在一起无所谓","add_date":"2019-07-08 16:02:36","is_praise":0,"to_m_name":"m19931"}]}
     */

    private boolean hasmore;
    private int curpage;
    private int page_total;
    private DataBean data;

    public boolean isHasmore() {
        return hasmore;
    }

    public void setHasmore(boolean hasmore) {
        this.hasmore = hasmore;
    }

    public int getCurpage() {
        return curpage;
    }

    public void setCurpage(int curpage) {
        this.curpage = curpage;
    }

    public int getPage_total() {
        return page_total;
    }

    public void setPage_total(int page_total) {
        this.page_total = page_total;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * cr_id : 43
             * circle_id : 43
             * cr_id_father : 42
             * m_id : 7
             * to_mid : 7
             * cr_content : 啦啦
             * cr_state : 0
             * add_time : 1562574991
             * update_admin : 0
             * update_time : 0
             * reply_num : 0
             * praise_num : 0
             * first_cr_id : 37
             * m_name : m19931
             * m_mobile : 15220219931
             * mi_head :
             * circle_content : 性子在一起在一起无所谓
             * add_date : 2019-07-08 16:36:31
             * is_praise : 0
             * to_m_name : m19931
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
            private int is_praise;
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

            public int getIs_praise() {
                return is_praise;
            }

            public void setIs_praise(int is_praise) {
                this.is_praise = is_praise;
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
