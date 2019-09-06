package com.hongyuan.coach.base;

public class MessageEvent {

    public MessageEvent(Object dataBean){
        this.dataBean = dataBean;
    }

    private Object dataBean;

    public Object getDataBean() {
        return dataBean;
    }

    public void setDataBean(Object dataBean) {
        this.dataBean = dataBean;
    }
}
