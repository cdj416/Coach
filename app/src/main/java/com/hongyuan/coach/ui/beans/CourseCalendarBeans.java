package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;

import java.util.List;

public class CourseCalendarBeans extends BaseBean {

    /**
     * data : {"list":[[{"now_day":"2019-08-26","now_time":"09:00:00","is_cur_date":0,"week":"一","is_kong":0,"item_cpa":{"m_name":"","mi_head":"","mi_realname":"","cp_name":"","state":0,"start_date":0}},{"now_day":"2019-08-26","now_time":"09:30:00","is_cur_date":0,"week":"一","is_kong":0,"item_cpa":{"m_name":"","mi_head":"","mi_realname":"","cp_name":"","state":0,"start_date":0}},{"now_day":"2019-08-26","now_time":"10:00:00","is_cur_date":0,"week":"一","is_kong":0,"item_cpa":{"m_name":"","mi_head":"","mi_realname":"","cp_name":"","state":0,"start_date":0}},{"now_day":"2019-08-26","now_time":"10:30:00","is_cur_date":0,"week":"一","is_kong":3,"item_cpa":{"m_name":"嗯哼","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190820/0d47d7ed38f213b679f4c6abfaaf26670b3ae695_1228x1008.png","mi_realname":"陈道明","cp_name":"希望xpy","state":1,"start_date":"2019-08-26 10:30:00"}},{"now_day":"2019-08-26","now_time":"11:00:00","is_cur_date":0,"week":"一","is_kong":3,"item_cpa":{"m_name":"嗯哼","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190820/0d47d7ed38f213b679f4c6abfaaf26670b3ae695_1228x1008.png","mi_realname":"陈道明","cp_name":"希望xpy","state":1,"start_date":"2019-08-26 10:30:00"}}]]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<List<ListBean>> list;

        public List<List<ListBean>> getList() {
            return list;
        }

        public void setList(List<List<ListBean>> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * now_day : 2019-08-26
             * now_time : 09:00:00
             * is_cur_date : 0
             * week : 一
             * is_kong : 0
             * item_cpa : {"m_name":"","mi_head":"","mi_realname":"","cp_name":"","state":0,"start_date":0}
             */

            private String now_day;
            private String now_time;
            private int is_cur_date;
            private String week;
            private int is_kong;
            private ItemCpaBean item_cpa;

            public String getNow_day() {
                return now_day;
            }

            public void setNow_day(String now_day) {
                this.now_day = now_day;
            }

            public String getNow_time() {
                return now_time;
            }

            public void setNow_time(String now_time) {
                this.now_time = now_time;
            }

            public int getIs_cur_date() {
                return is_cur_date;
            }

            public void setIs_cur_date(int is_cur_date) {
                this.is_cur_date = is_cur_date;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public int getIs_kong() {
                return is_kong;
            }

            public void setIs_kong(int is_kong) {
                this.is_kong = is_kong;
            }

            public ItemCpaBean getItem_cpa() {
                return item_cpa;
            }

            public void setItem_cpa(ItemCpaBean item_cpa) {
                this.item_cpa = item_cpa;
            }

            public static class ItemCpaBean {
                /**
                 * m_name :
                 * mi_head :
                 * mi_realname :
                 * cp_name :
                 * state : 0
                 * start_date : 0
                 */

                private String m_name;
                private String mi_head;
                private String mi_realname;
                private String cp_name;
                private int state;
                private String start_date;

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

                public String getMi_realname() {
                    return mi_realname;
                }

                public void setMi_realname(String mi_realname) {
                    this.mi_realname = mi_realname;
                }

                public String getCp_name() {
                    return cp_name;
                }

                public void setCp_name(String cp_name) {
                    this.cp_name = cp_name;
                }

                public int getState() {
                    return state;
                }

                public void setState(int state) {
                    this.state = state;
                }

                public String getStart_date() {
                    return start_date;
                }

                public void setStart_date(String start_date) {
                    this.start_date = start_date;
                }
            }
        }
    }
}
