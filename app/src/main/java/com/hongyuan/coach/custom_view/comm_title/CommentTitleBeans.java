package com.hongyuan.coach.custom_view.comm_title;

public class CommentTitleBeans {

    private int num;
    private String name;
    private int score;
    private boolean select;

    public CommentTitleBeans(int num, String name, int score) {
        this.num = num;
        this.name = name;
        this.score = score;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
