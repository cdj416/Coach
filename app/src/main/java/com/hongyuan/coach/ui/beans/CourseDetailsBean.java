package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;
import java.io.Serializable;
import java.util.List;

public class CourseDetailsBean extends BaseBean implements Serializable {


    /**
     * data : {"cp_id":2,"cp_name":"啦啦","m_id":3,"ft_id":6,"cp_duration":"9:00","cp_info":"进攻 Mr","cp_people":"这泼猴","cp_suggest":"哈哈额","cp_note":"","cp_price":"0.01","cp_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/21e7d3e7fa354e8f2d76b59c865a476cf807b6f6_1242x699.jpg","cp_num":1,"os_id":16,"is_ty":2,"ft_name":"拉伸","os_name":"首玺健身（环城西路店）","mi_realname":"陈道明","m_mobile":"18183185173","coach_nickname":"小明","coach_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190722/3749235a38c65966c07dbcc49d7855af6c726aff_800x600.jpg","have_num":0,"bnum":4,"price_list":[]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * cp_id : 2
         * cp_name : 啦啦
         * m_id : 3
         * ft_id : 6
         * cp_duration : 9:00
         * cp_info : 进攻 Mr
         * cp_people : 这泼猴
         * cp_suggest : 哈哈额
         * cp_note :
         * cp_price : 0.01
         * cp_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/21e7d3e7fa354e8f2d76b59c865a476cf807b6f6_1242x699.jpg
         * cp_num : 1
         * os_id : 16
         * is_ty : 2
         * ft_name : 拉伸
         * os_name : 首玺健身（环城西路店）
         * mi_realname : 陈道明
         * m_mobile : 18183185173
         * coach_nickname : 小明
         * coach_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190722/3749235a38c65966c07dbcc49d7855af6c726aff_800x600.jpg
         * have_num : 0
         * bnum : 4
         * price_list : []
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
        private int os_id;
        private int is_ty;
        private String ft_name;
        private String os_name;
        private String mi_realname;
        private String m_mobile;
        private String coach_nickname;
        private String coach_head;
        private int have_num;
        private int bnum;
        private List<PriceListBean> price_list;

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

        public int getOs_id() {
            return os_id;
        }

        public void setOs_id(int os_id) {
            this.os_id = os_id;
        }

        public int getIs_ty() {
            return is_ty;
        }

        public void setIs_ty(int is_ty) {
            this.is_ty = is_ty;
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

        public String getMi_realname() {
            return mi_realname;
        }

        public void setMi_realname(String mi_realname) {
            this.mi_realname = mi_realname;
        }

        public String getM_mobile() {
            return m_mobile;
        }

        public void setM_mobile(String m_mobile) {
            this.m_mobile = m_mobile;
        }

        public String getCoach_nickname() {
            return coach_nickname;
        }

        public void setCoach_nickname(String coach_nickname) {
            this.coach_nickname = coach_nickname;
        }

        public String getCoach_head() {
            return coach_head;
        }

        public void setCoach_head(String coach_head) {
            this.coach_head = coach_head;
        }

        public int getHave_num() {
            return have_num;
        }

        public void setHave_num(int have_num) {
            this.have_num = have_num;
        }

        public int getBnum() {
            return bnum;
        }

        public void setBnum(int bnum) {
            this.bnum = bnum;
        }

        public List<PriceListBean> getPrice_list() {
            return price_list;
        }

        public void setPrice_list(List<PriceListBean> price_list) {
            this.price_list = price_list;
        }

        public static class PriceListBean implements Serializable{
            /**
             * cpp_id : 12
             * min_num : 1
             * max_num : 9
             * cp_id : 14
             * price : 600.00
             */

            private int cpp_id;
            private int min_num;
            private int max_num;
            private int cp_id;
            private String price;
            private boolean select;

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }

            public PriceListBean(int cpp_id, int min_num, int max_num, int cp_id, String price) {
                this.cpp_id = cpp_id;
                this.min_num = min_num;
                this.max_num = max_num;
                this.cp_id = cp_id;
                this.price = price;
            }

            public int getCpp_id() {
                return cpp_id;
            }

            public void setCpp_id(int cpp_id) {
                this.cpp_id = cpp_id;
            }

            public int getMin_num() {
                return min_num;
            }

            public void setMin_num(int min_num) {
                this.min_num = min_num;
            }

            public int getMax_num() {
                return max_num;
            }

            public void setMax_num(int max_num) {
                this.max_num = max_num;
            }

            public int getCp_id() {
                return cp_id;
            }

            public void setCp_id(int cp_id) {
                this.cp_id = cp_id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
    }
}
