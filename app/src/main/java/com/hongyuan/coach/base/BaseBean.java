package com.hongyuan.coach.base;

/*
* 所有数据实体类需继承本基类
* */
public class BaseBean {

    private StatusBean status;

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public static class StatusBean {

        private String succeed;
        private int error_code;
        private String error_desc;

        public String getSucceed() {
            return succeed;
        }

        public void setSucceed(String succeed) {
            this.succeed = succeed;
        }

        public int getError_code() {
            return error_code;
        }

        public void setError_code(int error_code) {
            this.error_code = error_code;
        }

        public String getError_desc() {
            return error_desc;
        }

        public void setError_desc(String error_desc) {
            this.error_desc = error_desc;
        }
    }
}
