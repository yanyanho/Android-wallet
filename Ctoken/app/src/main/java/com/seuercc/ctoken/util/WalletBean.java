package com.seuercc.ctoken.util;

/**
 * <BR>
 *
 * @author c00373278
 * @version [v1.0.0.301, 2018/4/23]
 */


public class WalletBean {
    private String address;

    private String id;

    private int version;

    private Crypto crypto;

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Crypto getCrypto() {
        return this.crypto;
    }

    public void setCrypto(Crypto crypto) {
        this.crypto = crypto;
    }


}
