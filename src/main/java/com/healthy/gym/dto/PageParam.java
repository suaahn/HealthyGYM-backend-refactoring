package com.healthy.gym.dto;

public class PageParam {

    private int page;
    private int start;
    private int end;

    public PageParam() {}

    public PageParam(int page, int start, int end) {
        this.page = page;
        this.start = start;
        this.end = end;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "PageParam{" +
                "page=" + page +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
