package com.healthy.gym.dto;

import java.util.Arrays;

public class HealthMateParam extends PageParam {

    private int addressfirst;
    private int addresssecond;
    private String mdate;
    private String mtime;
    private String[] bodypart;

    public HealthMateParam() {}

    public HealthMateParam(int addressfirst, int addresssecond, String mdate, String mtime, String[] bodypart, int page, int start, int end) {
        super(page, start, end);
        this.addressfirst = addressfirst;
        this.addresssecond = addresssecond;
        this.mdate = mdate;
        this.mtime = mtime;
        this.bodypart = bodypart;
    }

    public int getAddressfirst() {
        return addressfirst;
    }

    public void setAddressfirst(int addressfirst) {
        this.addressfirst = addressfirst;
    }

    public int getAddresssecond() {
        return addresssecond;
    }

    public void setAddresssecond(int addresssecond) {
        this.addresssecond = addresssecond;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public String[] getBodypart() {
        return bodypart;
    }

    public void setBodypart(String[] bodypart) {
        this.bodypart = bodypart;
    }

    @Override
    public String toString() {
        return "HealthMateParam{" +
                "addressfirst=" + addressfirst +
                ", addresssecond=" + addresssecond +
                ", mdate='" + mdate + '\'' +
                ", mtime='" + mtime + '\'' +
                ", bodypart=" + Arrays.toString(bodypart) +
                "} " + super.toString();
    }
}
