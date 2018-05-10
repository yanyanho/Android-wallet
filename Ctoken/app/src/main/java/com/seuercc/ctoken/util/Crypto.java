package com.seuercc.ctoken.util;

/**
 * <BR>
 *
 * @author c00373278
 * @version [v1.0.0.301, 2018/4/23]
 */


public class Crypto {
    private String cipher;

    private String ciphertext;

    private Cipherparams cipherparams;

    private String kdf;

    private Kdfparams kdfparams;

    private String mac;


    public String getCipher() {
        return this.cipher;
    }

    public void setCipher(String cipher) {
        this.cipher = cipher;
    }

    public String getCiphertext() {
        return this.ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

    public Cipherparams getCipherparams() {
        return this.cipherparams;
    }

    public void setCipherparams(Cipherparams cipherparams) {
        this.cipherparams = cipherparams;
    }

    public String getKdf() {
        return this.kdf;
    }

    public void setKdf(String kdf) {
        this.kdf = kdf;
    }

    public Kdfparams getKdfparams() {
        return this.kdfparams;
    }

    public void setKdfparams(Kdfparams kdfparams) {
        this.kdfparams = kdfparams;
    }

    public String getMac() {
        return this.mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
