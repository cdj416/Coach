package com.hongyuan.coach.ui.beans;

import com.hongyuan.coach.base.BaseBean;

import java.util.List;

public class MoreImgBean extends BaseBean {


    /**
     * data : {"file_url":[{"oss_url":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190706/0b6d4e8ee0806dd44e49bf8bd0864f6d45ce2fe2_1080x1920.jpg","relative_path":"uplaod/image/20190706/0b6d4e8ee0806dd44e49bf8bd0864f6d45ce2fe2_1080x1920.jpg"},{"oss_url":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190706/a4ccec7fe442c0e47b0838b8f652d03ec82bdf1a_1080x1920.jpg","relative_path":"uplaod/image/20190706/a4ccec7fe442c0e47b0838b8f652d03ec82bdf1a_1080x1920.jpg"},{"oss_url":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190706/1d14434d4655a7ed6236871ca19b6d3ae42583a7_1080x1920.jpg","relative_path":"uplaod/image/20190706/1d14434d4655a7ed6236871ca19b6d3ae42583a7_1080x1920.jpg"},{"oss_url":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190706/e078a9a2284cdedc267b391fc95b8a593c705fea_1080x1920.jpg","relative_path":"uplaod/image/20190706/e078a9a2284cdedc267b391fc95b8a593c705fea_1080x1920.jpg"},{"oss_url":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190706/639f54e2253407942a2732d007cfbdf4971cff90_1080x1920.jpg","relative_path":"uplaod/image/20190706/639f54e2253407942a2732d007cfbdf4971cff90_1080x1920.jpg"},{"oss_url":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190706/8373008d152f97055604d695db6fcf96c9f94866_1080x1920.jpg","relative_path":"uplaod/image/20190706/8373008d152f97055604d695db6fcf96c9f94866_1080x1920.jpg"},{"oss_url":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190706/979e5986c517b67dc0acadd9211023678af7522c_1080x1920.jpg","relative_path":"uplaod/image/20190706/979e5986c517b67dc0acadd9211023678af7522c_1080x1920.jpg"},{"oss_url":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190706/a82a21435954c26ebcecce22a1c02f5a9ff52a30_1080x1920.jpg","relative_path":"uplaod/image/20190706/a82a21435954c26ebcecce22a1c02f5a9ff52a30_1080x1920.jpg"},{"oss_url":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190706/44a2f04831e5b0f1d46a99bb83880fdb0d37e94c_1080x1920.jpg","relative_path":"uplaod/image/20190706/44a2f04831e5b0f1d46a99bb83880fdb0d37e94c_1080x1920.jpg"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<FileUrlBean> file_url;

        public List<FileUrlBean> getFile_url() {
            return file_url;
        }

        public void setFile_url(List<FileUrlBean> file_url) {
            this.file_url = file_url;
        }

        public static class FileUrlBean {
            /**
             * oss_url : http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190706/0b6d4e8ee0806dd44e49bf8bd0864f6d45ce2fe2_1080x1920.jpg
             * relative_path : uplaod/image/20190706/0b6d4e8ee0806dd44e49bf8bd0864f6d45ce2fe2_1080x1920.jpg
             */

            private String oss_url;
            private String relative_path;

            public String getOss_url() {
                return oss_url;
            }

            public void setOss_url(String oss_url) {
                this.oss_url = oss_url;
            }

            public String getRelative_path() {
                return relative_path;
            }

            public void setRelative_path(String relative_path) {
                this.relative_path = relative_path;
            }
        }
    }
}
