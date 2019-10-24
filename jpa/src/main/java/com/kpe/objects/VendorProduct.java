package com.kpe.objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VendorProduct {
    @Id
    private
    String productCode;
    private String nativeDesc;
    private String enDesc;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getNativeDesc() {
        return nativeDesc;
    }

    public void setNativeDesc(String nativeDesc) {
        this.nativeDesc = nativeDesc;
    }

    public String getEnDesc() {
        return enDesc;
    }

    public void setEnDesc(String enDesc) {
        this.enDesc = enDesc;
    }
}
