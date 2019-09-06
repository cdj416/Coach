package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;

import java.util.List;

public class HomeBannerBean extends BaseBean {

    /**
     * data : {"list":[{"img_name":"首页幻灯4","img_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/58ee7a9197e7d4f2fb805122188453e8e7102ea1_690x300.jpg","img_href_type":1,"img_href_id":4,"img_href":"3","img_sort":4},{"img_name":"首页幻灯3","img_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/3dca80741dfcd4a7c875bf505461b1fa25c5947a_690x300.jpg","img_href_type":1,"img_href_id":6,"img_href":"45","img_sort":3},{"img_name":"首页幻灯2","img_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/c00b4bd58c2f74bf03c3347bbc8890fc7d00d830_690x300.jpg","img_href_type":1,"img_href_id":5,"img_href":"4","img_sort":2},{"img_name":"首页幻灯5","img_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/c7b1e4cadf5b4fd5c5cfda31e44ed53212d28a69_680x300.jpg","img_href_type":1,"img_href_id":1,"img_href":"fsdafdsaf2","img_sort":1},{"img_name":"首页幻灯1","img_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/3fe6341c581cd44b4a20eed23afc5fdca47aa546_680x300.jpg","img_href_type":1,"img_href_id":2,"img_href":"3","img_sort":1}]}
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
             * img_name : 首页幻灯4
             * img_src : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/58ee7a9197e7d4f2fb805122188453e8e7102ea1_690x300.jpg
             * img_href_type : 1
             * img_href_id : 4
             * img_href : 3
             * img_sort : 4
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
