package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;

import java.util.List;

public class AppointmentCoursePrivitListBeans extends BaseBean {

    /**
     * hasmore : true
     * curpage : 1
     * page_total : 6
     * data : {"list":[{"cpa_id":57,"m_id":3,"jl_mid":3,"cp_id":4,"os_id":16,"ocp_id":10,"num":1,"state":1,"add_time":1566785379,"start_time":"2019-08-26 13:30:00","end_time":"2019-08-26 14:30:00","xy_qd_time":0,"xy_qt_time":0,"jl_qd_time":0,"jl_qt_time":0,"xy_qd_state":0,"xy_qt_state":0,"jl_qd_state":0,"jl_qt_state":0,"cp_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190806/d909b24145cf253a1d1d50b1a56d2371976c0906_1654x2208.jpg","cp_name":"希望xpy","m_name":"嗯哼","m_mobile":"18183185173","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190820/0d47d7ed38f213b679f4c6abfaaf26670b3ae695_1228x1008.png","add_date":"2019-08-26 10:09:39"},{"cpa_id":55,"m_id":3,"jl_mid":3,"cp_id":4,"os_id":16,"ocp_id":10,"num":1,"state":1,"add_time":1566785362,"start_time":"2019-08-26 12:30:00","end_time":"2019-08-26 13:30:00","xy_qd_time":0,"xy_qt_time":0,"jl_qd_time":0,"jl_qt_time":0,"xy_qd_state":0,"xy_qt_state":0,"jl_qd_state":0,"jl_qt_state":0,"cp_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190806/d909b24145cf253a1d1d50b1a56d2371976c0906_1654x2208.jpg","cp_name":"希望xpy","m_name":"嗯哼","m_mobile":"18183185173","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190820/0d47d7ed38f213b679f4c6abfaaf26670b3ae695_1228x1008.png","add_date":"2019-08-26 10:09:22"},{"cpa_id":54,"m_id":3,"jl_mid":3,"cp_id":4,"os_id":16,"ocp_id":10,"num":1,"state":1,"add_time":1566785354,"start_time":"2019-08-26 11:30:00","end_time":"2019-08-26 12:30:00","xy_qd_time":0,"xy_qt_time":0,"jl_qd_time":0,"jl_qt_time":0,"xy_qd_state":0,"xy_qt_state":0,"jl_qd_state":0,"jl_qt_state":0,"cp_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190806/d909b24145cf253a1d1d50b1a56d2371976c0906_1654x2208.jpg","cp_name":"希望xpy","m_name":"嗯哼","m_mobile":"18183185173","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190820/0d47d7ed38f213b679f4c6abfaaf26670b3ae695_1228x1008.png","add_date":"2019-08-26 10:09:14"},{"cpa_id":53,"m_id":3,"jl_mid":3,"cp_id":4,"os_id":16,"ocp_id":10,"num":1,"state":1,"add_time":1566785345,"start_time":"2019-08-26 10:30:00","end_time":"2019-08-26 11:30:00","xy_qd_time":0,"xy_qt_time":0,"jl_qd_time":0,"jl_qt_time":0,"xy_qd_state":0,"xy_qt_state":0,"jl_qd_state":0,"jl_qt_state":0,"cp_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190806/d909b24145cf253a1d1d50b1a56d2371976c0906_1654x2208.jpg","cp_name":"希望xpy","m_name":"嗯哼","m_mobile":"18183185173","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190820/0d47d7ed38f213b679f4c6abfaaf26670b3ae695_1228x1008.png","add_date":"2019-08-26 10:09:05"}]}
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
             * cpa_id : 57
             * m_id : 3
             * jl_mid : 3
             * cp_id : 4
             * os_id : 16
             * ocp_id : 10
             * num : 1
             * state : 1
             * add_time : 1566785379
             * start_time : 2019-08-26 13:30:00
             * end_time : 2019-08-26 14:30:00
             * xy_qd_time : 0
             * xy_qt_time : 0
             * jl_qd_time : 0
             * jl_qt_time : 0
             * xy_qd_state : 0
             * xy_qt_state : 0
             * jl_qd_state : 0
             * jl_qt_state : 0
             * cp_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190806/d909b24145cf253a1d1d50b1a56d2371976c0906_1654x2208.jpg
             * cp_name : 希望xpy
             * m_name : 嗯哼
             * m_mobile : 18183185173
             * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190820/0d47d7ed38f213b679f4c6abfaaf26670b3ae695_1228x1008.png
             * add_date : 2019-08-26 10:09:39
             */

            private int cpa_id;
            private int m_id;
            private int jl_mid;
            private int cp_id;
            private int os_id;
            private int ocp_id;
            private int num;
            private int state;
            private int add_time;
            private String start_time;
            private String end_time;
            private int xy_qd_time;
            private int xy_qt_time;
            private int jl_qd_time;
            private int jl_qt_time;
            private int xy_qd_state;
            private int xy_qt_state;
            private int jl_qd_state;
            private int jl_qt_state;
            private String cp_img;
            private String cp_name;
            private String m_name;
            private String m_mobile;
            private String mi_head;
            private String add_date;

            public int getCpa_id() {
                return cpa_id;
            }

            public void setCpa_id(int cpa_id) {
                this.cpa_id = cpa_id;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public int getJl_mid() {
                return jl_mid;
            }

            public void setJl_mid(int jl_mid) {
                this.jl_mid = jl_mid;
            }

            public int getCp_id() {
                return cp_id;
            }

            public void setCp_id(int cp_id) {
                this.cp_id = cp_id;
            }

            public int getOs_id() {
                return os_id;
            }

            public void setOs_id(int os_id) {
                this.os_id = os_id;
            }

            public int getOcp_id() {
                return ocp_id;
            }

            public void setOcp_id(int ocp_id) {
                this.ocp_id = ocp_id;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public int getXy_qd_time() {
                return xy_qd_time;
            }

            public void setXy_qd_time(int xy_qd_time) {
                this.xy_qd_time = xy_qd_time;
            }

            public int getXy_qt_time() {
                return xy_qt_time;
            }

            public void setXy_qt_time(int xy_qt_time) {
                this.xy_qt_time = xy_qt_time;
            }

            public int getJl_qd_time() {
                return jl_qd_time;
            }

            public void setJl_qd_time(int jl_qd_time) {
                this.jl_qd_time = jl_qd_time;
            }

            public int getJl_qt_time() {
                return jl_qt_time;
            }

            public void setJl_qt_time(int jl_qt_time) {
                this.jl_qt_time = jl_qt_time;
            }

            public int getXy_qd_state() {
                return xy_qd_state;
            }

            public void setXy_qd_state(int xy_qd_state) {
                this.xy_qd_state = xy_qd_state;
            }

            public int getXy_qt_state() {
                return xy_qt_state;
            }

            public void setXy_qt_state(int xy_qt_state) {
                this.xy_qt_state = xy_qt_state;
            }

            public int getJl_qd_state() {
                return jl_qd_state;
            }

            public void setJl_qd_state(int jl_qd_state) {
                this.jl_qd_state = jl_qd_state;
            }

            public int getJl_qt_state() {
                return jl_qt_state;
            }

            public void setJl_qt_state(int jl_qt_state) {
                this.jl_qt_state = jl_qt_state;
            }

            public String getCp_img() {
                return cp_img;
            }

            public void setCp_img(String cp_img) {
                this.cp_img = cp_img;
            }

            public String getCp_name() {
                return cp_name;
            }

            public void setCp_name(String cp_name) {
                this.cp_name = cp_name;
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

            public String getAdd_date() {
                return add_date;
            }

            public void setAdd_date(String add_date) {
                this.add_date = add_date;
            }
        }
    }
}
