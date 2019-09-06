package com.hongyuan.coach.custom_view.version_change;

import com.hongyuan.coach.base.BaseBean;

public class CheckVersionBeans extends BaseBean {

    /**
     * data : {"info":{"id":4,"app_version":"v1.0","updatemsg":"最新版本","downloadurl":"http://","updatetype":1,"updatetime":1563973773,"app_type":1,"is_new":1}}
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
         * info : {"id":4,"app_version":"v1.0","updatemsg":"最新版本","downloadurl":"http://","updatetype":1,"updatetime":1563973773,"app_type":1,"is_new":1}
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
             * id : 4
             * app_version : v1.0
             * updatemsg : 最新版本
             * downloadurl : http://
             * updatetype : 1
             * updatetime : 1563973773
             * app_type : 1
             * is_new : 1
             */

            private int id;
            private String app_version;
            private String updatemsg;
            private String downloadurl;
            private int updatetype;
            private int updatetime;
            private int app_type;
            private int is_new;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getApp_version() {
                return app_version;
            }

            public void setApp_version(String app_version) {
                this.app_version = app_version;
            }

            public String getUpdatemsg() {
                return updatemsg;
            }

            public void setUpdatemsg(String updatemsg) {
                this.updatemsg = updatemsg;
            }

            public String getDownloadurl() {
                return downloadurl;
            }

            public void setDownloadurl(String downloadurl) {
                this.downloadurl = downloadurl;
            }

            public int getUpdatetype() {
                return updatetype;
            }

            public void setUpdatetype(int updatetype) {
                this.updatetype = updatetype;
            }

            public int getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(int updatetime) {
                this.updatetime = updatetime;
            }

            public int getApp_type() {
                return app_type;
            }

            public void setApp_type(int app_type) {
                this.app_type = app_type;
            }

            public int getIs_new() {
                return is_new;
            }

            public void setIs_new(int is_new) {
                this.is_new = is_new;
            }
        }
    }
}
