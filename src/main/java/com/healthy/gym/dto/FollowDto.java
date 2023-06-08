package com.healthy.gym.dto;

public class FollowDto {
    private int followseq;
    private int memberseq;
    private int foltarget;

    public FollowDto() {}

    public FollowDto(int memberseq, int foltarget) {
        this.memberseq = memberseq;
        this.foltarget = foltarget;
    }

    public FollowDto(int followseq, int memberseq, int foltarget) {
        this.followseq = followseq;
        this.memberseq = memberseq;
        this.foltarget = foltarget;
    }

    public int getFollowseq() {
        return followseq;
    }

    public void setFollowseq(int followseq) {
        this.followseq = followseq;
    }

    public int getMemberseq() {
        return memberseq;
    }

    public void setMemberseq(int memberseq) {
        this.memberseq = memberseq;
    }

    public int getFoltarget() {
        return foltarget;
    }

    public void setFoltarget(int foltarget) {
        this.foltarget = foltarget;
    }

    @Override
    public String toString() {
        return "FollowDto{" +
                "followseq=" + followseq +
                ", memberseq=" + memberseq +
                ", foltarget=" + foltarget +
                '}';
    }
}
