package com.seuercc.ctoken.util;

/**
 * <BR>
 *
 * @author c00373278
 * @version [v1.0.0.301, 2018/4/23]
 */


public class Cipherparams {
    private String iv;

    @Override
    public String toString() {
        return "iv:" + getIv();
    }

    public String getIv() {
        return this.iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

}
