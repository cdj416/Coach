package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;

import java.util.List;

public class CoachImgBeans extends BaseBean {


    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"cp_id":6,"img_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/f2746f47064b850d1c6fa16a2d72db65f813ee8b_1767x2208.jpg","photo_category_id":1,"m_id":3},{"cp_id":5,"img_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/618e88892779371f3d9d5472e2113a69777f4c4b_1077x2208.jpg","photo_category_id":1,"m_id":3},{"cp_id":4,"img_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/aea47c3da13456b185d1be88f712cee5b6b81c72_1242x867.jpg","photo_category_id":1,"m_id":3},{"cp_id":3,"img_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/46dc2740e38d0c77a62eca3fa057d4ea0c6a980a_1706x2208.jpg","photo_category_id":1,"m_id":3},{"cp_id":2,"img_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/f1397e6848e5218042b47e2ef79b614ebd180277_1559x2208.jpg","photo_category_id":1,"m_id":3},{"cp_id":1,"img_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/359cc7d80c9d3878affcab0438c68c48631df3d6_1654x2208.jpg","photo_category_id":1,"m_id":3}]}
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
             * cp_id : 6
             * img_src : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/f2746f47064b850d1c6fa16a2d72db65f813ee8b_1767x2208.jpg
             * photo_category_id : 1
             * m_id : 3
             */

            private int cp_id;
            private String img_src;
            private int photo_category_id;
            private int m_id;

            //编辑状态下是否选中
            private boolean select;
            //是否在编辑状态下
            private boolean isEdite;

            public boolean isEdite() {
                return isEdite;
            }

            public void setEdite(boolean edite) {
                isEdite = edite;
            }

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }

            public int getCp_id() {
                return cp_id;
            }

            public void setCp_id(int cp_id) {
                this.cp_id = cp_id;
            }

            public String getImg_src() {
                return img_src;
            }

            public void setImg_src(String img_src) {
                this.img_src = img_src;
            }

            public int getPhoto_category_id() {
                return photo_category_id;
            }

            public void setPhoto_category_id(int photo_category_id) {
                this.photo_category_id = photo_category_id;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }
        }
    }
}
