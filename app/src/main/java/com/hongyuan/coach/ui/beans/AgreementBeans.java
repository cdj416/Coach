package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;

import java.util.List;

public class AgreementBeans extends BaseBean {

    /**
     * data : {"list":[{"img_name":"app协议","img_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190731/bb8f4b4931b1e13ac371a8fcb807305e1d2187f6_750x3736.jpg","img_href_type":1,"img_href_id":1,"img_href":"#","img_sort":1}]}
     */

    private DataBean data;

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
             * img_name : app协议
             * img_src : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190731/bb8f4b4931b1e13ac371a8fcb807305e1d2187f6_750x3736.jpg
             * img_href_type : 1
             * img_href_id : 1
             * img_href : #
             * img_sort : 1
             */

            private String img_name;
            private String img_src;
            private int img_href_type;
            private int img_href_id;
            private String img_href;
            private int img_sort;

            public String getImg_name() {
                return img_name;
            }

            public void setImg_name(String img_name) {
                this.img_name = img_name;
            }

            public String getImg_src() {
                return img_src;
            }

            public void setImg_src(String img_src) {
                this.img_src = img_src;
            }

            public int getImg_href_type() {
                return img_href_type;
            }

            public void setImg_href_type(int img_href_type) {
                this.img_href_type = img_href_type;
            }

            public int getImg_href_id() {
                return img_href_id;
            }

            public void setImg_href_id(int img_href_id) {
                this.img_href_id = img_href_id;
            }

            public String getImg_href() {
                return img_href;
            }

            public void setImg_href(String img_href) {
                this.img_href = img_href;
            }

            public int getImg_sort() {
                return img_sort;
            }

            public void setImg_sort(int img_sort) {
                this.img_sort = img_sort;
            }
        }
    }
}
