package com.seuercc.ctoken.util;

/**
 * <BR>
 *
 * @author c00373278
 * @version [v1.0.0.301, 2018/4/23]
 */


public class Kdfparams {
    private int dklen;

    private int n;

    private int p;

    private int r;

    private String salt;

    public int getDklen() {
        return this.dklen;
    }

    public void setDklen(int dklen) {
        this.dklen = dklen;
    }

    public int getN() {
        return this.n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getP() {
        return this.p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getR() {
        return this.r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

}
