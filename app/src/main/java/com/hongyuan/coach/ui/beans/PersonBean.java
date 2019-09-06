package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;

public class PersonBean extends BaseBean {

    /**
     * data : {"info":{"m_name":"m85173","m_mobile":"18183185173","m_id":3,"mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190728/b40712608fa0dd847109e5d2ade29acb8a63713d_2080x1560.jpg","card_count":0}}
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
         * info : {"m_name":"m85173","m_mobile":"18183185173","m_id":3,"mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190728/b40712608fa0dd847109e5d2ade29acb8a63713d_2080x1560.jpg","card_count":0}
         */

        private InfoBean info;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * m_name : m85173
             * m_mobile : 18183185173
             * m_id : 3
             * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190728/b40712608fa0dd847109e5d2ade29acb8a63713d_2080x1560.jpg
             * card_count : 0
             */

            private String m_name;
            private String m_mobile;
            private int m_id;
            private String mi_head;
            private int card_count;

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

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public String getMi_head() {
                return mi_head;
            }

            public void setMi_head(String mi_head) {
                this.mi_head = mi_head;
            }

            public int getCard_count() {
                return card_count;
            }

            public void setCard_count(int card_count) {
                this.card_count = card_count;
            }
        }
    }
}
