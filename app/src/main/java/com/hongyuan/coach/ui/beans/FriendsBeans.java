package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;

import java.util.List;

public class FriendsBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"f_id":18,"m_id":3,"f_mid":3,"add_time":1564727505,"is_xh":2,"m_mobile":"18183185173","m_name":"m85173","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190728/b38bc13c39b68c84ef0fa12fec241e2e5c5e5664_1560x2080.jpg"}]}
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
             * f_id : 18
             * m_id : 3
             * f_mid : 3
             * add_time : 1564727505
             * is_xh : 2
             * m_mobile : 18183185173
             * m_name : m85173
             * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190728/b38bc13c39b68c84ef0fa12fec241e2e5c5e5664_1560x2080.jpg
             */

            private int f_id;
            private int m_id;
            private int f_mid;
            private int add_time;
            private int is_xh;
            private String m_mobile;
            private String m_name;
            private String mi_head;

            public int getF_id() {
                return f_id;
            }

            public void setF_id(int f_id) {
                this.f_id = f_id;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public int getF_mid() {
                return f_mid;
            }

            public void setF_mid(int f_mid) {
                this.f_mid = f_mid;
            }

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public int getIs_xh() {
                return is_xh;
            }

            public void setIs_xh(int is_xh) {
                this.is_xh = is_xh;
            }

            public String getM_mobile() {
                return m_mobile;
            }

            public void setM_mobile(String m_mobile) {
                this.m_mobile = m_mobile;
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
        }
    }
}
