package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;

import java.util.List;

public class CommentsNumBeans extends BaseBean {

    /**
     * data : {"amount":0,"count_img":21,"item":[{"name":"不满意","count":0},{"name":"一般","count":0},{"name":"满意","count":0},{"name":"非常满意","count":0},{"name":"超出预期","count":4}]}
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
         * amount : 0
         * count_img : 21
         * item : [{"name":"不满意","count":0},{"name":"一般","count":0},{"name":"满意","count":0},{"name":"非常满意","count":0},{"name":"超出预期","count":4}]
         */

        private int amount;
        private int count_img;
        private List<ItemBean> item;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getCount_img() {
            return count_img;
        }

        public void setCount_img(int count_img) {
            this.count_img = count_img;
        }

        public List<ItemBean> getItem() {
            return item;
        }

        public void setItem(List<ItemBean> item) {
            this.item = item;
        }

        public static class ItemBean {
            /**
             * name : 不满意
             * count : 0
             */

            private String name;
            private int count;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }
    }
}
