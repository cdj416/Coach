package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;

import java.util.List;

public class HomeCourseTopBeans extends BaseBean {

    /**
     * data : {"amount":28,"item":[{"cp_name":"嗯哼","num":6,"per":"0.21"},{"cp_name":"啦啦","num":4,"per":"0.14"},{"cp_name":"tpmpmpm","num":7,"per":"0.25"},{"cp_name":"希望xpy","num":10,"per":"0.36"},{"cp_name":"万岁万岁万万岁我","num":1,"per":"0.04"}]}
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
         * amount : 28
         * item : [{"cp_name":"嗯哼","num":6,"per":"0.21"},{"cp_name":"啦啦","num":4,"per":"0.14"},{"cp_name":"tpmpmpm","num":7,"per":"0.25"},{"cp_name":"希望xpy","num":10,"per":"0.36"},{"cp_name":"万岁万岁万万岁我","num":1,"per":"0.04"}]
         */

        private int amount;
        private List<ItemBean> item;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public List<ItemBean> getItem() {
            return item;
        }

        public void setItem(List<ItemBean> item) {
            this.item = item;
        }

        public static class ItemBean {
            /**
             * cp_name : 嗯哼
             * num : 6
             * per : 0.21
             */

            private String cp_name;
            private int num;
            private String per;

            public String getCp_name() {
                return cp_name;
            }

            public void setCp_name(String cp_name) {
                this.cp_name = cp_name;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getPer() {
                return per;
            }

            public void setPer(String per) {
                this.per = per;
            }
        }
    }
}
