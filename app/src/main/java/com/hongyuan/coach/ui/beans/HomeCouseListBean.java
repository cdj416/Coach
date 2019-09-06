package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;

import java.io.Serializable;
import java.util.List;

public class HomeCouseListBean extends BaseBean implements Serializable {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"cp_id":3,"cp_name":"fdsafdsafsd","m_id":1,"ft_id":3,"cp_duration":"fdsafdsa","cp_info":"fdsafdsafds","cp_people":"fdsafdsa","cp_suggest":"ffdsafdsa","cp_note":"fdsafdsafds","cp_price":"1000.00","cp_img":"1.jpg","cp_num":1,"ft_name":"塑形","os_name":"首玺健身(旗舰店)","os_id":12,"mi_realname":"范德萨"},{"cp_id":2,"cp_name":"fdsafdsafsd","m_id":1,"ft_id":2,"cp_duration":"fdsafdsa","cp_info":"fdsafdsafds","cp_people":"fdsafdsa","cp_suggest":"ffdsafdsa","cp_note":"fdsafdsafds","cp_price":"1000.00","cp_img":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190612/fe75bf6da44cd12c8c974b8cdf682c7e601e170e_800x600_.jpg","cp_num":1,"ft_name":"增肌","os_name":"首玺健身(旗舰店)","os_id":12,"mi_realname":"范德萨"},{"cp_id":1,"cp_name":"fdsafdsafsd","m_id":1,"ft_id":1,"cp_duration":"fdsafdsa","cp_info":"fdsafdsafds","cp_people":"fdsafdsa","cp_suggest":"ffdsafdsa","cp_note":"fdsafdsafds","cp_price":"1000.00","cp_img":"","cp_num":1,"ft_name":"减脂","os_name":"首玺健身(旗舰店)","os_id":12,"mi_realname":"范德萨"}]}
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

    public static class DataBean implements Serializable {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            /**
             * cp_id : 3
             * cp_name : fdsafdsafsd
             * m_id : 1
             * ft_id : 3
             * cp_duration : fdsafdsa
             * cp_info : fdsafdsafds
             * cp_people : fdsafdsa
             * cp_suggest : ffdsafdsa
             * cp_note : fdsafdsafds
             * cp_price : 1000.00
             * cp_img : 1.jpg
             * cp_num : 1
             * ft_name : 塑形
             * os_name : 首玺健身(旗舰店)
             * os_id : 12
             * mi_realname : 范德萨
             */

            private int cp_id;
            private String cp_name;
            private int m_id;
            private int ft_id;
            private String cp_duration;
            private String cp_info;
            private String cp_people;
            private String cp_suggest;
            private String cp_note;
            private String cp_price;
            private String cp_img;
            private int cp_num;
            private String ft_name;
            private String os_name;
            private int os_id;
            private String mi_realname;

            public int getCp_id() {
                return cp_id;
            }

            public void setCp_id(int cp_id) {
                this.cp_id = cp_id;
            }

            public String getCp_name() {
                return cp_name;
            }

            public void setCp_name(String cp_name) {
                this.cp_name = cp_name;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public int getFt_id() {
                return ft_id;
            }

            public void setFt_id(int ft_id) {
                this.ft_id = ft_id;
            }

            public String getCp_duration() {
                return cp_duration;
            }

            public void setCp_duration(String cp_duration) {
                this.cp_duration = cp_duration;
            }

            public String getCp_info() {
                return cp_info;
            }

            public void setCp_info(String cp_info) {
                this.cp_info = cp_info;
            }

            public String getCp_people() {
                return cp_people;
            }

            public void setCp_people(String cp_people) {
                this.cp_people = cp_people;
            }

            public String getCp_suggest() {
                return cp_suggest;
            }

            public void setCp_suggest(String cp_suggest) {
                this.cp_suggest = cp_suggest;
            }

            public String getCp_note() {
                return cp_note;
            }

            public void setCp_note(String cp_note) {
                this.cp_note = cp_note;
            }

            public String getCp_price() {
                return cp_price;
            }

            public void setCp_price(String cp_price) {
                this.cp_price = cp_price;
            }

            public String getCp_img() {
                return cp_img;
            }

            public void setCp_img(String cp_img) {
                this.cp_img = cp_img;
            }

            public int getCp_num() {
                return cp_num;
            }

            public void setCp_num(int cp_num) {
                this.cp_num = cp_num;
            }

            public String getFt_name() {
                return ft_name;
            }

            public void setFt_name(String ft_name) {
                this.ft_name = ft_name;
            }

            public String getOs_name() {
                return os_name;
            }

            public void setOs_name(String os_name) {
                this.os_name = os_name;
            }

            public int getOs_id() {
                return os_id;
            }

            public void setOs_id(int os_id) {
                this.os_id = os_id;
            }

            public String getMi_realname() {
                return mi_realname;
            }

            public void setMi_realname(String mi_realname) {
                this.mi_realname = mi_realname;
            }
        }
    }
}
