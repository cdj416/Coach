package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;

public class RetrunImgBean extends BaseBean {

    /**
     * data : {"file_url":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190728/c7be1064e96626ba8d27211e169f26b49e59605a_2080x1560.jpg"}
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
         * file_url : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190728/c7be1064e96626ba8d27211e169f26b49e59605a_2080x1560.jpg
         */

        private String file_url;

        public String getFile_url() {
            return file_url;
        }

        public void setFile_url(String file_url) {
            this.file_url = file_url;
        }
    }
}
