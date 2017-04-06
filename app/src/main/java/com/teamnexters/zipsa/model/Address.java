package com.teamnexters.zipsa.model;

/**
 * Created by Hwasoo.Sung on 2017-03-15.
 */

public class Address {
    private String country;
    private String siDo;
    private String siGuGun;
    private String dongMyun;
    private String rest;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSiDo() {
        return siDo;
    }

    public void setSiDo(String siDo) {
        this.siDo = siDo;
    }

    public String getSiGuGun() {
        return siGuGun;
    }

    public void setSiGuGun(String siGuGun) {
        this.siGuGun = siGuGun;
    }

    public String getDongMyun() {
        return dongMyun;
    }

    public void setDongMyun(String dongMyun) {
        this.dongMyun = dongMyun;
    }

    public String getRest() {
        return rest;
    }

    public void setRest(String rest) {
        this.rest = rest;
    }
}
