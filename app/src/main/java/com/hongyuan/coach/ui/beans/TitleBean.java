package com.hongyuan.coach.ui.beans;

public class TitleBean {

    private String titleName;
    private int titlePosition;

    public TitleBean(String titleName, int titlePosition) {
        this.titleName = titleName;
        this.titlePosition = titlePosition;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public int getTitlePosition() {
        return titlePosition;
    }

    public void setTitlePosition(int titlePosition) {
        this.titlePosition = titlePosition;
    }
}
