package com.healthy.gym.dto;

public class MemberDto {

    private int memberseq;
    private String email;
    private String pwd;
    private String profileimg;
    private String nickname;
    private int auth;
    private String authority;
    private String provider;	// own, google, kakao
    private String mailkey;

    public MemberDto() {}

    public MemberDto(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }

    public MemberDto(String email, String pwd, String nickname, String authority, String provider) {
        this.email = email;
        this.pwd = pwd;
        this.nickname = nickname;
        this.authority = authority;
        this.provider = provider;
    }

    public MemberDto(int memberseq, String email, String pwd, String profileimg, String nickname, int auth, String authority, String provider, String mailkey) {
        this.memberseq = memberseq;
        this.email = email;
        this.pwd = pwd;
        this.profileimg = profileimg;
        this.nickname = nickname;
        this.auth = auth;
        this.authority = authority;
        this.provider = provider;
        this.mailkey = mailkey;
    }

    public int getMemberseq() {
        return memberseq;
    }

    public void setMemberseq(int memberseq) {
        this.memberseq = memberseq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getProfileimg() {
        return profileimg;
    }

    public void setProfile(String profileimg) {
        this.profileimg = profileimg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getMailkey() {
        return mailkey;
    }

    public void setMailkey(String mailkey) {
        this.mailkey = mailkey;
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "memberseq=" + memberseq +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", profileimg='" + profileimg + '\'' +
                ", nickname='" + nickname + '\'' +
                ", auth=" + auth +
                ", authority='" + authority + '\'' +
                ", provider='" + provider + '\'' +
                ", mailkey='" + mailkey + '\'' +
                '}';
    }
}
