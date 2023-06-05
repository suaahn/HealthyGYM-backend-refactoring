package com.healthy.gym.dto;

public class BbsParam extends PageParam {

    private int bbstag;
    private String order;
    private String search;

    public BbsParam() {}

    public BbsParam(int bbstag, String order, int page, int start, int end) {
        super(page, start, end);
        this.bbstag = bbstag;
        this.order = order;
    }

    public BbsParam(int bbstag, String order, String search, int page, int start, int end) {
        super(page, start, end);
        this.bbstag = bbstag;
        this.order = order;
        this.search = search;
    }

    public int getBbstag() {
        return bbstag;
    }

    public void setBbstag(int bbstag) {
        this.bbstag = bbstag;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    public String toString() {
        return "BbsParam{" +
                "bbstag=" + bbstag +
                ", order='" + order + '\'' +
                ", search='" + search + '\'' +
                "} " + super.toString();
    }
}
