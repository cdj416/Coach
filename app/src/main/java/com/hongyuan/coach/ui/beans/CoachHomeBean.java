package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;

import java.io.Serializable;
import java.util.List;

public class CoachHomeBean extends BaseBean implements Serializable{

    /**
     * data : {"info":{"mi_id":14,"m_id":3,"mi_contract_mobile":" 18183185173","mi_email":"","mi_wx":"","mi_qq":"","mi_level":1,"mi_birth":1563782049,"pid":35,"cid":3505,"did":350502,"mi_address":"","mi_gold":0,"mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190728/b38bc13c39b68c84ef0fa12fec241e2e5c5e5664_1560x2080.jpg","mi_sex":1,"mi_realname":"陈道明","coach_level":3,"coach_sfz":"","coach_os_id":16,"coach_desc":" 发电房的撒发的","coach_skill":"范德萨范德萨","coach_sfz_img1":"","coach_sfz_img2":"","coach_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190722/3749235a38c65966c07dbcc49d7855af6c726aff_800x600.jpg","coach_banner1":"","coach_banner2":"","role_id":2,"coach_ft_ids":"7,6,4,3","coach_experience":" 范德萨范德萨","cc_ids":"5,3","coach_nickname":"小明","saler_os_ids":"","sl_id":0,"mi_point":10040,"coach_s":"5.00","coach_review_count":0,"m_mobile":"18183185173","os_name":"首玺健身（环城西路店）","os_lng":"120.089035","os_lat":"30.872727"},"certs":["赛普高级私人教练认证","北京健美协会认证"],"count_c":0,"c_photo1":{"cp_id":1,"img_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/359cc7d80c9d3878affcab0438c68c48631df3d6_1654x2208.jpg","photo_category_id":1,"m_id":3},"c_photo2":{},"c_photo3":{"cp_id":7,"img_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/53fd5438a2b4152e5089f30a8ad8d10b3d55ab26_1618x2208.jpg","photo_category_id":3,"m_id":3},"count_courses":0,"count_students":0}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * info : {"mi_id":14,"m_id":3,"mi_contract_mobile":" 18183185173","mi_email":"","mi_wx":"","mi_qq":"","mi_level":1,"mi_birth":1563782049,"pid":35,"cid":3505,"did":350502,"mi_address":"","mi_gold":0,"mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190728/b38bc13c39b68c84ef0fa12fec241e2e5c5e5664_1560x2080.jpg","mi_sex":1,"mi_realname":"陈道明","coach_level":3,"coach_sfz":"","coach_os_id":16,"coach_desc":" 发电房的撒发的","coach_skill":"范德萨范德萨","coach_sfz_img1":"","coach_sfz_img2":"","coach_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190722/3749235a38c65966c07dbcc49d7855af6c726aff_800x600.jpg","coach_banner1":"","coach_banner2":"","role_id":2,"coach_ft_ids":"7,6,4,3","coach_experience":" 范德萨范德萨","cc_ids":"5,3","coach_nickname":"小明","saler_os_ids":"","sl_id":0,"mi_point":10040,"coach_s":"5.00","coach_review_count":0,"m_mobile":"18183185173","os_name":"首玺健身（环城西路店）","os_lng":"120.089035","os_lat":"30.872727"}
         * certs : ["赛普高级私人教练认证","北京健美协会认证"]
         * count_c : 0
         * c_photo1 : {"cp_id":1,"img_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/359cc7d80c9d3878affcab0438c68c48631df3d6_1654x2208.jpg","photo_category_id":1,"m_id":3}
         * c_photo2 : {}
         * c_photo3 : {"cp_id":7,"img_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/53fd5438a2b4152e5089f30a8ad8d10b3d55ab26_1618x2208.jpg","photo_category_id":3,"m_id":3}
         * count_courses : 0
         * count_students : 0
         */

        private InfoBean info;
        private int count_c;
        private CPhoto1Bean c_photo1;
        private CPhoto2Bean c_photo2;
        private CPhoto3Bean c_photo3;
        private int count_courses;
        private int count_students;
        private List<String> certs;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public int getCount_c() {
            return count_c;
        }

        public void setCount_c(int count_c) {
            this.count_c = count_c;
        }

        public CPhoto1Bean getC_photo1() {
            return c_photo1;
        }

        public void setC_photo1(CPhoto1Bean c_photo1) {
            this.c_photo1 = c_photo1;
        }

        public CPhoto2Bean getC_photo2() {
            return c_photo2;
        }

        public void setC_photo2(CPhoto2Bean c_photo2) {
            this.c_photo2 = c_photo2;
        }

        public CPhoto3Bean getC_photo3() {
            return c_photo3;
        }

        public void setC_photo3(CPhoto3Bean c_photo3) {
            this.c_photo3 = c_photo3;
        }

        public int getCount_courses() {
            return count_courses;
        }

        public void setCount_courses(int count_courses) {
            this.count_courses = count_courses;
        }

        public int getCount_students() {
            return count_students;
        }

        public void setCount_students(int count_students) {
            this.count_students = count_students;
        }

        public List<String> getCerts() {
            return certs;
        }

        public void setCerts(List<String> certs) {
            this.certs = certs;
        }

        public static class InfoBean implements Serializable {
            /**
             * mi_id : 14
             * m_id : 3
             * mi_contract_mobile :  18183185173
             * mi_email :
             * mi_wx :
             * mi_qq :
             * mi_level : 1
             * mi_birth : 1563782049
             * pid : 35
             * cid : 3505
             * did : 350502
             * mi_address :
             * mi_gold : 0
             * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190728/b38bc13c39b68c84ef0fa12fec241e2e5c5e5664_1560x2080.jpg
             * mi_sex : 1
             * mi_realname : 陈道明
             * coach_level : 3
             * coach_sfz :
             * coach_os_id : 16
             * coach_desc :  发电房的撒发的
             * coach_skill : 范德萨范德萨
             * coach_sfz_img1 :
             * coach_sfz_img2 :
             * coach_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190722/3749235a38c65966c07dbcc49d7855af6c726aff_800x600.jpg
             * coach_banner1 :
             * coach_banner2 :
             * role_id : 2
             * coach_ft_ids : 7,6,4,3
             * coach_experience :  范德萨范德萨
             * cc_ids : 5,3
             * coach_nickname : 小明
             * saler_os_ids :
             * sl_id : 0
             * mi_point : 10040
             * coach_s : 5.00
             * coach_review_count : 0
             * m_mobile : 18183185173
             * os_name : 首玺健身（环城西路店）
             * os_lng : 120.089035
             * os_lat : 30.872727
             */

            private int mi_id;
            private int m_id;
            private String mi_contract_mobile;
            private String mi_email;
            private String mi_wx;
            private String mi_qq;
            private int mi_level;
            private int mi_birth;
            private int pid;
            private int cid;
            private int did;
            private String mi_address;
            private int mi_gold;
            private String mi_head;
            private int mi_sex;
            private String mi_realname;
            private int coach_level;
            private String coach_sfz;
            private int coach_os_id;
            private String coach_desc;
            private String coach_skill;
            private String coach_sfz_img1;
            private String coach_sfz_img2;
            private String coach_head;
            private String coach_banner1;
            private String coach_banner2;
            private int role_id;
            private String coach_ft_ids;
            private String coach_experience;
            private String cc_ids;
            private String coach_nickname;
            private String saler_os_ids;
            private int sl_id;
            private int mi_point;
            private String coach_s;
            private int coach_review_count;
            private String m_mobile;
            private String os_name;
            private String os_lng;
            private String os_lat;

            public int getMi_id() {
                return mi_id;
            }

            public void setMi_id(int mi_id) {
                this.mi_id = mi_id;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public String getMi_contract_mobile() {
                return mi_contract_mobile;
            }

            public void setMi_contract_mobile(String mi_contract_mobile) {
                this.mi_contract_mobile = mi_contract_mobile;
            }

            public String getMi_email() {
                return mi_email;
            }

            public void setMi_email(String mi_email) {
                this.mi_email = mi_email;
            }

            public String getMi_wx() {
                return mi_wx;
            }

            public void setMi_wx(String mi_wx) {
                this.mi_wx = mi_wx;
            }

            public String getMi_qq() {
                return mi_qq;
            }

            public void setMi_qq(String mi_qq) {
                this.mi_qq = mi_qq;
            }

            public int getMi_level() {
                return mi_level;
            }

            public void setMi_level(int mi_level) {
                this.mi_level = mi_level;
            }

            public int getMi_birth() {
                return mi_birth;
            }

            public void setMi_birth(int mi_birth) {
                this.mi_birth = mi_birth;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }

            public int getDid() {
                return did;
            }

            public void setDid(int did) {
                this.did = did;
            }

            public String getMi_address() {
                return mi_address;
            }

            public void setMi_address(String mi_address) {
                this.mi_address = mi_address;
            }

            public int getMi_gold() {
                return mi_gold;
            }

            public void setMi_gold(int mi_gold) {
                this.mi_gold = mi_gold;
            }

            public String getMi_head() {
                return mi_head;
            }

            public void setMi_head(String mi_head) {
                this.mi_head = mi_head;
            }

            public int getMi_sex() {
                return mi_sex;
            }

            public void setMi_sex(int mi_sex) {
                this.mi_sex = mi_sex;
            }

            public String getMi_realname() {
                return mi_realname;
            }

            public void setMi_realname(String mi_realname) {
                this.mi_realname = mi_realname;
            }

            public int getCoach_level() {
                return coach_level;
            }

            public void setCoach_level(int coach_level) {
                this.coach_level = coach_level;
            }

            public String getCoach_sfz() {
                return coach_sfz;
            }

            public void setCoach_sfz(String coach_sfz) {
                this.coach_sfz = coach_sfz;
            }

            public int getCoach_os_id() {
                return coach_os_id;
            }

            public void setCoach_os_id(int coach_os_id) {
                this.coach_os_id = coach_os_id;
            }

            public String getCoach_desc() {
                return coach_desc;
            }

            public void setCoach_desc(String coach_desc) {
                this.coach_desc = coach_desc;
            }

            public String getCoach_skill() {
                return coach_skill;
            }

            public void setCoach_skill(String coach_skill) {
                this.coach_skill = coach_skill;
            }

            public String getCoach_sfz_img1() {
                return coach_sfz_img1;
            }

            public void setCoach_sfz_img1(String coach_sfz_img1) {
                this.coach_sfz_img1 = coach_sfz_img1;
            }

            public String getCoach_sfz_img2() {
                return coach_sfz_img2;
            }

            public void setCoach_sfz_img2(String coach_sfz_img2) {
                this.coach_sfz_img2 = coach_sfz_img2;
            }

            public String getCoach_head() {
                return coach_head;
            }

            public void setCoach_head(String coach_head) {
                this.coach_head = coach_head;
            }

            public String getCoach_banner1() {
                return coach_banner1;
            }

            public void setCoach_banner1(String coach_banner1) {
                this.coach_banner1 = coach_banner1;
            }

            public String getCoach_banner2() {
                return coach_banner2;
            }

            public void setCoach_banner2(String coach_banner2) {
                this.coach_banner2 = coach_banner2;
            }

            public int getRole_id() {
                return role_id;
            }

            public void setRole_id(int role_id) {
                this.role_id = role_id;
            }

            public String getCoach_ft_ids() {
                return coach_ft_ids;
            }

            public void setCoach_ft_ids(String coach_ft_ids) {
                this.coach_ft_ids = coach_ft_ids;
            }

            public String getCoach_experience() {
                return coach_experience;
            }

            public void setCoach_experience(String coach_experience) {
                this.coach_experience = coach_experience;
            }

            public String getCc_ids() {
                return cc_ids;
            }

            public void setCc_ids(String cc_ids) {
                this.cc_ids = cc_ids;
            }

            public String getCoach_nickname() {
                return coach_nickname;
            }

            public void setCoach_nickname(String coach_nickname) {
                this.coach_nickname = coach_nickname;
            }

            public String getSaler_os_ids() {
                return saler_os_ids;
            }

            public void setSaler_os_ids(String saler_os_ids) {
                this.saler_os_ids = saler_os_ids;
            }

            public int getSl_id() {
                return sl_id;
            }

            public void setSl_id(int sl_id) {
                this.sl_id = sl_id;
            }

            public int getMi_point() {
                return mi_point;
            }

            public void setMi_point(int mi_point) {
                this.mi_point = mi_point;
            }

            public String getCoach_s() {
                return coach_s;
            }

            public void setCoach_s(String coach_s) {
                this.coach_s = coach_s;
            }

            public int getCoach_review_count() {
                return coach_review_count;
            }

            public void setCoach_review_count(int coach_review_count) {
                this.coach_review_count = coach_review_count;
            }

            public String getM_mobile() {
                return m_mobile;
            }

            public void setM_mobile(String m_mobile) {
                this.m_mobile = m_mobile;
            }

            public String getOs_name() {
                return os_name;
            }

            public void setOs_name(String os_name) {
                this.os_name = os_name;
            }

            public String getOs_lng() {
                return os_lng;
            }

            public void setOs_lng(String os_lng) {
                this.os_lng = os_lng;
            }

            public String getOs_lat() {
                return os_lat;
            }

            public void setOs_lat(String os_lat) {
                this.os_lat = os_lat;
            }
        }

        public static class CPhoto1Bean implements Serializable {
            /**
             * cp_id : 7
             * img_src : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/53fd5438a2b4152e5089f30a8ad8d10b3d55ab26_1618x2208.jpg
             * photo_category_id : 3
             * m_id : 3
             */

            private int cp_id;
            private String img_src;
            private int photo_category_id;
            private int m_id;

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

        public static class CPhoto2Bean implements Serializable {
            /**
             * cp_id : 7
             * img_src : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/53fd5438a2b4152e5089f30a8ad8d10b3d55ab26_1618x2208.jpg
             * photo_category_id : 3
             * m_id : 3
             */

            private int cp_id;
            private String img_src;
            private int photo_category_id;
            private int m_id;

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

        public static class CPhoto3Bean implements Serializable {
            /**
             * cp_id : 7
             * img_src : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/53fd5438a2b4152e5089f30a8ad8d10b3d55ab26_1618x2208.jpg
             * photo_category_id : 3
             * m_id : 3
             */

            private int cp_id;
            private String img_src;
            private int photo_category_id;
            private int m_id;

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
