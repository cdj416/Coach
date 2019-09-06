package com.hongyuan.coach.base;

import java.util.HashMap;
import java.util.Map;

public class Constants {


    private Map<Integer,String> pathMap = new HashMap<>();

    private static Constants constants = null;

    private Constants(){
        addPath();
    }

    public static Constants getInstance(){
        if(constants == null){
            constants = new Constants();
        }
        return constants;
    }

    /*
    * 组装请求路径
    * */
    private void addPath(){
        pathMap.put(GET_PLAN_DATE,ADRESS + "/api/coachhome/get_plan_date");
        pathMap.put(MEMBER_LOGIN,ADRESS + "/api/index/memberlogin");
        pathMap.put(SEND_REGI_MSG,ADRESS + "/api/index/send_regi_msg");
        pathMap.put(GET_MESSAGE_TOKEN,ADRESS + "/api/index/get_token");
        pathMap.put(DO_FIND_PASSWORD,ADRESS + "/api/index/do_find_password");
        pathMap.put(CHECK_REGI_MOBILE,ADRESS + "/api/index/check_regi_mobile");
        pathMap.put(MEMBER_REGISTER,ADRESS + "/api/index/memberregister");
        pathMap.put(MEMBERLOGIN_DXM,ADRESS + "/api/index/memberlogin_dxm");
        pathMap.put(GET_TOKEN,ADRESS + "/api/index/api_token");
        pathMap.put(GET_COACH_APPOINTMENT_COURSE_PRIVITE_LIST,ADRESS + "/api/coachmember/get_coach_appointment_course_privite_list");
        pathMap.put(GET_IMG_LIST,ADRESS + "/api/appsethome/get_img_list");
        pathMap.put(ADD_CIRCLE_PRAISE,ADRESS + "/api/circle/add_circle_praise");
        pathMap.put(CANCEL_CIRCLE_PRAISE,ADRESS + "/api/circle/cancel_circle_praise");
        pathMap.put(GET_MY_FRIENDS,ADRESS + "/api/member/get_my_friends");
        pathMap.put(GET_CIRCLE_LIST,ADRESS + "/api/circlehome/get_circle_list");
        pathMap.put(UPFILE_OSS_MORE,ADRESS + "/api/index/upfile_oss_more");
        pathMap.put(ADD_CIRCLE,ADRESS + "/api/circle/add_circle");
        pathMap.put(GET_RIRCLE_REVIEWLIST,ADRESS + "/api/circlehome/get_circle_reviewList");
        pathMap.put(ADD_CIRCLE_REVIEW,ADRESS + "/api/circle/add_circle_review");
        pathMap.put(ADD_CIRCLE_REVIEW_PRAISE,ADRESS + "/api/circle/add_circle_review_praise");
        pathMap.put(CANCEL_CIRCLE_REVIEW_PRAISE,ADRESS + "/api/circle/cancel_circle_review_praise");
        pathMap.put(ADD_FRIEND,ADRESS + "/api/member/add_friend");
        pathMap.put(GET_CIRCLE_INFO,ADRESS + "/api/circlehome/get_circle_info");
        pathMap.put(GET_CIRCLE_REVIEWLIST_SON,ADRESS + "/api/circlehome/get_circle_reviewList_son");
        pathMap.put(PRIVITE_COURSE_QD,ADRESS + "/api/coursemember/privite_course_qd");
        pathMap.put(GET_COACH_SALE_CP,ADRESS + "/api/coachmember/get_coach_sale_cp");
        pathMap.put(GET_COURSE_PRIVITE_LIST,ADRESS + "/api/coursea/get_course_privite_list");
        pathMap.put(GET_COURSE_PRIVITE_INFO,ADRESS + "/api/coursea/get_course_privite_info");
        pathMap.put(GET_COACH_REVIEW_SUM,ADRESS + "/api/coachhome/get_coach_review_sum");
        pathMap.put(GET_COACH_LAST_KONG_TIME,ADRESS + "/api/coachhome/get_coach_last_kong_time");
        pathMap.put(GET_COACH_REVIEW_LIST,ADRESS + "/api/member/get_coach_review_list");
        pathMap.put(GET_COACH_REVIEW_IMG_LIST,ADRESS + "/api/member/get_coach_review_img_list");
        pathMap.put(GET_FIT_TYPE_LIST,ADRESS + "/api/coursemember/get_fit_type_list");
        pathMap.put(ADD_COURSE_PRIVITE,ADRESS + "/api/coursemember/add_course_privite");
        pathMap.put(UPFILE_OSS,ADRESS + "/api/index/upfile_oss");
        pathMap.put(GET_COACH_TIMEPLAN_LIST,ADRESS + "/api/coachmember/get_coach_timeplan_list");
        pathMap.put(GET_COACH_TIMEPLAN_DAY_LIST_COACH,ADRESS + "/api/coachmember/get_coach_timeplan_day_list_coach");
        pathMap.put(ADD_COACH_TIMEPLAN,ADRESS + "/api/coachmember/add_coach_timeplan");
        pathMap.put(DEL_COACH_TIMEPLAN,ADRESS + "/api/coachmember/del_coach_timeplan");
        pathMap.put(GET_COACH_INFEX_INFO,ADRESS + "/api/coachmember/get_coach_index_info");
        pathMap.put(GET_COACH_MEMBER,ADRESS + "/api/coachmember/get_coach_members");
        pathMap.put(GET_COACH_PHOTO_LIST,ADRESS + "/api/coachmember/get_coach_photo_list");
        pathMap.put(DEL_PHOTOS,ADRESS + "/api/coachmember/del_photos");
        pathMap.put(ADD_COACH_PHOTO,ADRESS + "/api/coachmember/add_coach_photo");
        pathMap.put(CHECK_APP_VERSION,ADRESS + "/api/appsethome/check_app_version");

    }

    /*
    * 获取请求路径
    * */
    public String getPath(int code){
        return pathMap.get(code);
    }

    /*==================================================================================*/

    //测试地址
    //private final String ADRESS = "http://192.168.0.104";
    //正式地址
    public static final String ADRESS = "http://www.hongyuangood.com";

    /*==================================================================================*/

    //学员选课的时间坐标
    public static final int GET_PLAN_DATE = 0x001;
    //登录接口
    public static final int MEMBER_LOGIN = 0x002;
    //获取短信验证码
    public static final int SEND_REGI_MSG = 0x003;
    //获取短信码token
    public static final int GET_MESSAGE_TOKEN = 0x004;
    //找回密码
    public static final int DO_FIND_PASSWORD = 0x005;
    //检测手机号是否注册
    public static final int CHECK_REGI_MOBILE = 0x006;
    //注册用户
    public static final int MEMBER_REGISTER = 0x007;
    //短信验证码登录
    public static final int MEMBERLOGIN_DXM = 0x008;
    //token的获取
    public static final int GET_TOKEN = 0x009;

    //教练端--教练被约私教课列表
    public static final int GET_COACH_APPOINTMENT_COURSE_PRIVITE_LIST = 0x010;
    //首页--首页图片(通用读取图片)
    public static final int GET_IMG_LIST = 0x011;
    //圈子帖子点赞
    public static final int ADD_CIRCLE_PRAISE = 0x0012;
    //取消圈子帖子点赞
    public static final int CANCEL_CIRCLE_PRAISE = 0x0013;
    //我的好友列表
    public static final int GET_MY_FRIENDS = 0x0014;
    //读取圈子帖子列表
    public static final int GET_CIRCLE_LIST = 0x0015;
    //发布圈子的帖子
    public static final int ADD_CIRCLE = 0x0016;
    //读取圈子帖子评论列表
    public static final int GET_RIRCLE_REVIEWLIST = 0x0017;
    //圈子帖子评论
    public static final int ADD_CIRCLE_REVIEW = 0x0019;
    //圈子帖子评论点赞
    public static final int ADD_CIRCLE_REVIEW_PRAISE = 0x0020;
    //圈子帖子评论取消点赞
    public static final int CANCEL_CIRCLE_REVIEW_PRAISE = 0x0021;
    //圈子帖子关注
    public static final int ADD_FRIEND = 0x0022;
    //圈子帖子详情
    public static final int GET_CIRCLE_INFO = 0x0023;
    //圈子帖子评论子评论列表
    public static final int GET_CIRCLE_REVIEWLIST_SON = 0x0024;

    //学员教练私教课签到签退
    public static final int PRIVITE_COURSE_QD = 0x0025;
    //教练端--本月读取教练卖私教课的课数量
    public static final int GET_COACH_SALE_CP = 0x0026;
    //私教课程列表
    public static final int GET_COURSE_PRIVITE_LIST = 0x0027;
    //私教课详情
    public static final int GET_COURSE_PRIVITE_INFO = 0x0028;
    // 私教--私教课评论数
    public static final int GET_COACH_REVIEW_SUM = 0x0029;
    //私教--私教教练最近有空时间
    public static final int GET_COACH_LAST_KONG_TIME = 0x0030;
    // 私教--评论私教课列表
    public static final int GET_COACH_REVIEW_LIST = 0x0031;
    // 私教--评论私教课列表--有图片
    public static final int GET_COACH_REVIEW_IMG_LIST = 0x0032;
    // 获取课程类型
    public static final int GET_FIT_TYPE_LIST = 0x0033;
    // 发布私教课
    public static final int ADD_COURSE_PRIVITE = 0x0034;
    // 教练端--教练的课程日历
    public static final int GET_COACH_TIMEPLAN_LIST = 0x0036;
    // 教练端--教练某天的时间安排
    public static final int GET_COACH_TIMEPLAN_DAY_LIST_COACH = 0x0037;
    // 新增教练时间安排
    public static final int ADD_COACH_TIMEPLAN = 0x0038;
    // 教练端--教练取消休息
    public static final int DEL_COACH_TIMEPLAN = 0x0039;
    // 教练端--教练首页数据
    public static final int GET_COACH_INFEX_INFO = 0x0040;
    //  教练端--读取教练的学员
    public static final int GET_COACH_MEMBER = 0x0041;
    //  教练端--读取教练相册列表图片
    public static final int GET_COACH_PHOTO_LIST = 0x0042;
    //  教练--删除相册相片
    public static final int DEL_PHOTOS = 0x0043;
    //  教练上传相册
    public static final int ADD_COACH_PHOTO = 0x0044;
    //  APP--检查app版本
    public static final int CHECK_APP_VERSION = 0x0045;


    //上传多文件
    public static final int  UPFILE_OSS_MORE = 0x0018;
    //单文件上传
    public static final int UPFILE_OSS = 0x0035;
}
