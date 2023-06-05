package com.healthy.gym.dto;

public class BbsCommentParam extends PageParam {

    private int bbsseq;

    public BbsCommentParam() {}

    public BbsCommentParam(int bbsseq, int page, int start, int end) {
        super(page, start, end);
        this.bbsseq = bbsseq;
    }

    public int getBbsseq() {
        return bbsseq;
    }

    public void setBbsseq(int bbsseq) {
        this.bbsseq = bbsseq;
    }

    @Override
    public String toString() {
        return "BbsCommentParam{" +
                "bbsseq=" + bbsseq +
                "} " + super.toString();
    }
}
