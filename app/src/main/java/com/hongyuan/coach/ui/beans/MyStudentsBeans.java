package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;

import java.util.List;

public class MyStudentsBeans extends BaseBean {


    /**
     * hasmore : false
     * curpage : 1
     * page_total : 0
     * data : {"list":[{"ocp_num":"100","have_num":"100","m_id":1,"m_name":"m88888","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190820/99960903c5df39754e5e150f10467bc39fe09364_2436x2436.jpg","m_mobile":"18621854159","last_date":0,"is_in":0}]}
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
             * ocp_num : 100
             * have_num : 100
             * m_id : 1
             * m_name : m88888
             * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190820/99960903c5df39754e5e150f10467bc39fe09364_2436x2436.jpg
             * m_mobile : 18621854159
             * last_date : 0
             * is_in : 0
             */

            private String ocp_num;
            private String have_num;
            private int m_id;
            private String m_name;
            private String mi_head;
            private String m_mobile;
            private String last_date;
            private int is_in;

            public String getOcp_num() {
                return ocp_num;
            }

            public void setOcp_num(String ocp_num) {
                this.ocp_num = ocp_num;
            }

            public String getHave_num() {
                return have_num;
            }

            public void setHave_num(String have_num) {
                this.have_num = have_num;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public String getM_name() {
                return m_name;
            }

            public void setM_name(String m_name) {
                this.m_name = m_name;
            }

            public String getMi_head() {
                return mi_head;
            }

            public void setMi_head(String mi_head) {
                this.mi_head = mi_head;
            }

            public String getM_mobile() {
                return m_mobile;
            }

            public void setM_mobile(String m_mobile) {
                this.m_mobile = m_mobile;
            }

            public String getLast_date() {
                return last_date;
            }

            public void setLast_date(String last_date) {
                this.last_date = last_date;
            }

            public int getIs_in() {
                return is_in;
            }

            public void setIs_in(int is_in) {
                this.is_in = is_in;
            }
        }
    }
}
