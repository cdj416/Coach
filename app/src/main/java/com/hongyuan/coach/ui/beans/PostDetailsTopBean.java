package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;

import java.io.Serializable;
import java.util.List;

public class PostDetailsTopBean extends BaseBean implements Serializable{


    /**
     * data : {"circle_id":39,"m_id":7,"circle_categoryid":1,"circle_name":"减肥交流","circle_content":"易烊千玺说一下","circle_type":2,"circle_video":"","circle_state":0,"add_time":1562401673,"update_admin":0,"update_time":0,"praise_num":0,"review_num":0,"lat":"11.0255465633255","lng":"11.0255465633255","os_id":12,"is_tj":0,"circle_img":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190706/d512071e8291f5e479312a4cc8288f1fa31f30ab_3840x2160.jpg","m_mobile":"15220219931","mi_head":"","ci":[{"ci_id":49,"file_src":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20190706/e774dad4498694f4f77e348877d5a23556bc6d75.mp4","circle_id":39,"circle_type":2}],"add_date":"2019-07-06 16:27:53","is_praise":0,"is_friend":0}
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
         * circle_id : 39
         * m_id : 7
         * circle_categoryid : 1
         * circle_name : 减肥交流
         * circle_content : 易烊千玺说一下
         * circle_type : 2
         * circle_video :
         * circle_state : 0
         * add_time : 1562401673
         * update_admin : 0
         * update_time : 0
         * praise_num : 0
         * review_num : 0
         * lat : 11.0255465633255
         * lng : 11.0255465633255
         * os_id : 12
         * is_tj : 0
         * circle_img : http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190706/d512071e8291f5e479312a4cc8288f1fa31f30ab_3840x2160.jpg
         * m_mobile : 15220219931
         * mi_head :
         * ci : [{"ci_id":49,"file_src":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20190706/e774dad4498694f4f77e348877d5a23556bc6d75.mp4","circle_id":39,"circle_type":2}]
         * add_date : 2019-07-06 16:27:53
         * is_praise : 0
         * is_friend : 0
         */

        private int circle_id;
        private int m_id;
        private int circle_categoryid;
        private String circle_name;
        private String circle_content;
        private int circle_type;
        private String circle_video;
        private int circle_state;
        private long add_time;
        private int update_admin;
        private int update_time;
        private int praise_num;
        private int review_num;
        private String lat;
        private String lng;
        private int os_id;
        private int is_tj;
        private String circle_img;
        private String m_mobile;
        private String mi_head;
        private String add_date;
        private int is_praise;
        private int is_friend;
        private List<CiBean> ci;

        public int getCircle_id() {
            return circle_id;
        }

        public void setCircle_id(int circle_id) {
            this.circle_id = circle_id;
        }

        public int getM_id() {
            return m_id;
        }

        public void setM_id(int m_id) {
            this.m_id = m_id;
        }

        public int getCircle_categoryid() {
            return circle_categoryid;
        }

        public void setCircle_categoryid(int circle_categoryid) {
            this.circle_categoryid = circle_categoryid;
        }

        public String getCircle_name() {
            return circle_name;
        }

        public void setCircle_name(String circle_name) {
            this.circle_name = circle_name;
        }

        public String getCircle_content() {
            return circle_content;
        }

        public void setCircle_content(String circle_content) {
            this.circle_content = circle_content;
        }

        public int getCircle_type() {
            return circle_type;
        }

        public void setCircle_type(int circle_type) {
            this.circle_type = circle_type;
        }

        public String getCircle_video() {
            return circle_video;
        }

        public void setCircle_video(String circle_video) {
            this.circle_video = circle_video;
        }

        public int getCircle_state() {
            return circle_state;
        }

        public void setCircle_state(int circle_state) {
            this.circle_state = circle_state;
        }

        public long getAdd_time() {
            return add_time;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }

        public int getUpdate_admin() {
            return update_admin;
        }

        public void setUpdate_admin(int update_admin) {
            this.update_admin = update_admin;
        }

        public int getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(int update_time) {
            this.update_time = update_time;
        }

        public int getPraise_num() {
            return praise_num;
        }

        public void setPraise_num(int praise_num) {
            this.praise_num = praise_num;
        }

        public int getReview_num() {
            return review_num;
        }

        public void setReview_num(int review_num) {
            this.review_num = review_num;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public int getOs_id() {
            return os_id;
        }

        public void setOs_id(int os_id) {
            this.os_id = os_id;
        }

        public int getIs_tj() {
            return is_tj;
        }

        public void setIs_tj(int is_tj) {
            this.is_tj = is_tj;
        }

        public String getCircle_img() {
            return circle_img;
        }

        public void setCircle_img(String circle_img) {
            this.circle_img = circle_img;
        }

        public String getM_mobile() {
            return m_mobile;
        }

        public void setM_mobile(String m_mobile) {
            this.m_mobile = m_mobile;
        }

        public String getMi_head() {
            return mi_head;
        }

        public void setMi_head(String mi_head) {
            this.mi_head = mi_head;
        }

        public String getAdd_date() {
            return add_date;
        }

        public void setAdd_date(String add_date) {
            this.add_date = add_date;
        }

        public int getIs_praise() {
            return is_praise;
        }

        public void setIs_praise(int is_praise) {
            this.is_praise = is_praise;
        }

        public int getIs_friend() {
            return is_friend;
        }

        public void setIs_friend(int is_friend) {
            this.is_friend = is_friend;
        }

        public List<CiBean> getCi() {
            return ci;
        }

        public void setCi(List<CiBean> ci) {
            this.ci = ci;
        }

        public static class CiBean implements Serializable {
            /**
             * ci_id : 49
             * file_src : http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20190706/e774dad4498694f4f77e348877d5a23556bc6d75.mp4
             * circle_id : 39
             * circle_type : 2
             */

            private int ci_id;
            private String file_src;
            private int circle_id;
            private int circle_type;

            public int getCi_id() {
                return ci_id;
            }

            public void setCi_id(int ci_id) {
                this.ci_id = ci_id;
            }

            public String getFile_src() {
                return file_src;
            }

            public void setFile_src(String file_src) {
                this.file_src = file_src;
            }

            public int getCircle_id() {
                return circle_id;
            }

            public void setCircle_id(int circle_id) {
                this.circle_id = circle_id;
            }

            public int getCircle_type() {
                return circle_type;
            }

            public void setCircle_type(int circle_type) {
                this.circle_type = circle_type;
            }
        }
    }
}
