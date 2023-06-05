package com.healthy.gym.dto;

import java.io.Serializable;

public class BbsDto implements Serializable {

    private int bbsseq;
    private int memberseq;
    private int bbstag;
    private String title;
    private String content;
    private String wdate;
    private String rdate;
    private int likecount;
    private int readcount;
    private int report;
    private int del;
    private String thumbnail;

    public BbsDto() {}

    public BbsDto(int bbsseq, int memberseq, String title, String content, int bbstag, String wdate, String rdate, int likecount, int readcount, int del, String thumbnail, int report) {
        this.bbsseq = bbsseq;
        this.memberseq = memberseq;
        this.title = title;
        this.content = content;
        this.bbstag = bbstag;
        this.wdate = wdate;
        this.rdate = rdate;
        this.likecount = likecount;
        this.readcount = readcount;
        this.del = del;
        this.thumbnail = thumbnail;
        this.report = report;
    }

    public int getBbsseq() {
        return bbsseq;
    }

    public void setBbsseq(int bbsseq) {
        this.bbsseq = bbsseq;
    }

    public int getMemberseq() {
        return memberseq;
    }

    public void setMemberseq(int memberseq) {
        this.memberseq = memberseq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBbstag() {
        return bbstag;
    }

    public void setBbstag(int bbstag) {
        this.bbstag = bbstag;
    }

    public String getWdate() {
        return wdate;
    }

    public void setWdate(String wdate) {
        this.wdate = wdate;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public int getLikecount() {
        return likecount;
    }

    public void setLikecount(int likecount) {
        this.likecount = likecount;
    }

    public int getReadcount() {
        return readcount;
    }

    public void setReadcount(int readcount) {
        this.readcount = readcount;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getReport() {
        return report;
    }

    public void setReport(int report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "BbsDto{" +
                "bbsseq=" + bbsseq +
                ", memberseq=" + memberseq +
                ", bbstag=" + bbstag +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", wdate='" + wdate + '\'' +
                ", rdate='" + rdate + '\'' +
                ", likecount=" + likecount +
                ", readcount=" + readcount +
                ", report=" + report +
                ", del=" + del +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
