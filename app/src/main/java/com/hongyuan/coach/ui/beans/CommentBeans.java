package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;

import java.util.List;

public class CommentBeans extends BaseBean {


    /**
     * hasmore : true
     * curpage : 1
     * page_total : 4
     * data : {"list":[{"cr_id":11,"m_id":3,"cr_content":"地自以为是行尸走肉\n投入破人\n台球厅","cr_time":1565184963,"cp_id":2,"coach_s":"5.00","cr_state":1,"coach_mid":3,"cpa_id":2,"m_mobile":"18183185173","m_name":"m85173","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190807/3b2bb712582f72eaeb9cf7e0fd7370ffad2e7b37_968x1425.jpg","cp_name":"啦啦","cri":[{"cri_id":33,"cri_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190807/5cf6a4458624275f359fa1003fc0d5db8102b023_1020x2208.jpg","cr_id":11,"cp_id":2},{"cri_id":34,"cri_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190807/5daacc14608f19bfa1880dfac71d472534931caa_1517x2208.jpg","cr_id":11,"cp_id":2},{"cri_id":35,"cri_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190807/788ce549809266b007727d8fb764eec269636d2a_814x2208.jpg","cr_id":11,"cp_id":2},{"cri_id":36,"cri_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190807/bd0e98111aefc46c98170b8fe3afc5fdb86b841b_1497x2208.jpg","cr_id":11,"cp_id":2},{"cri_id":37,"cri_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190807/c6012f60c11723135cabb8303ebb28d207caf2fd_1242x867.jpg","cr_id":11,"cp_id":2}],"cr_date":"2019-08-07 21:36:03","is_review":1}]}
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
             * cr_id : 11
             * m_id : 3
             * cr_content : 地自以为是行尸走肉
             投入破人
             台球厅
             * cr_time : 1565184963
             * cp_id : 2
             * coach_s : 5.00
             * cr_state : 1
             * coach_mid : 3
             * cpa_id : 2
             * m_mobile : 18183185173
             * m_name : m85173
             * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190807/3b2bb712582f72eaeb9cf7e0fd7370ffad2e7b37_968x1425.jpg
             * cp_name : 啦啦
             * cri : [{"cri_id":33,"cri_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190807/5cf6a4458624275f359fa1003fc0d5db8102b023_1020x2208.jpg","cr_id":11,"cp_id":2},{"cri_id":34,"cri_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190807/5daacc14608f19bfa1880dfac71d472534931caa_1517x2208.jpg","cr_id":11,"cp_id":2},{"cri_id":35,"cri_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190807/788ce549809266b007727d8fb764eec269636d2a_814x2208.jpg","cr_id":11,"cp_id":2},{"cri_id":36,"cri_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190807/bd0e98111aefc46c98170b8fe3afc5fdb86b841b_1497x2208.jpg","cr_id":11,"cp_id":2},{"cri_id":37,"cri_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190807/c6012f60c11723135cabb8303ebb28d207caf2fd_1242x867.jpg","cr_id":11,"cp_id":2}]
             * cr_date : 2019-08-07 21:36:03
             * is_review : 1
             */

            private int cr_id;
            private int m_id;
            private String cr_content;
            private int cr_time;
            private int cp_id;
            private String coach_s;
            private int cr_state;
            private int coach_mid;
            private int cpa_id;
            private String m_mobile;
            private String m_name;
            private String mi_head;
            private String cp_name;
            private String cr_date;
            private int is_review;
            private List<CriBean> cri;

            public int getCr_id() {
                return cr_id;
            }

            public void setCr_id(int cr_id) {
                this.cr_id = cr_id;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public String getCr_content() {
                return cr_content;
            }

            public void setCr_content(String cr_content) {
                this.cr_content = cr_content;
            }

            public int getCr_time() {
                return cr_time;
            }

            public void setCr_time(int cr_time) {
                this.cr_time = cr_time;
            }

            public int getCp_id() {
                return cp_id;
            }

            public void setCp_id(int cp_id) {
                this.cp_id = cp_id;
            }

            public String getCoach_s() {
                return coach_s;
            }

            public void setCoach_s(String coach_s) {
                this.coach_s = coach_s;
            }

            public int getCr_state() {
                return cr_state;
            }

            public void setCr_state(int cr_state) {
                this.cr_state = cr_state;
            }

            public int getCoach_mid() {
                return coach_mid;
            }

            public void setCoach_mid(int coach_mid) {
                this.coach_mid = coach_mid;
            }

            public int getCpa_id() {
                return cpa_id;
            }

            public void setCpa_id(int cpa_id) {
                this.cpa_id = cpa_id;
            }

            public String getM_mobile() {
                return m_mobile;
            }

            public void setM_mobile(String m_mobile) {
                this.m_mobile = m_mobile;
            }

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

            public String getCp_name() {
                return cp_name;
            }

            public void setCp_name(String cp_name) {
                this.cp_name = cp_name;
            }

            public String getCr_date() {
                return cr_date;
            }

            public void setCr_date(String cr_date) {
                this.cr_date = cr_date;
            }

            public int getIs_review() {
                return is_review;
            }

            public void setIs_review(int is_review) {
                this.is_review = is_review;
            }

            public List<CriBean> getCri() {
                return cri;
            }

            public void setCri(List<CriBean> cri) {
                this.cri = cri;
            }

            public static class CriBean {
                /**
                 * cri_id : 33
                 * cri_src : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190807/5cf6a4458624275f359fa1003fc0d5db8102b023_1020x2208.jpg
                 * cr_id : 11
                 * cp_id : 2
                 */

                private int cri_id;
                private String cri_src;
                private int cr_id;
                private int cp_id;

                public int getCri_id() {
                    return cri_id;
                }

                public void setCri_id(int cri_id) {
                    this.cri_id = cri_id;
                }

                public String getCri_src() {
                    return cri_src;
                }

                public void setCri_src(String cri_src) {
                    this.cri_src = cri_src;
                }

                public int getCr_id() {
                    return cr_id;
                }

                public void setCr_id(int cr_id) {
                    this.cr_id = cr_id;
                }

                public int getCp_id() {
                    return cp_id;
                }

                public void setCp_id(int cp_id) {
                    this.cp_id = cp_id;
                }
            }
        }
    }
}
