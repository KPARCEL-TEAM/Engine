package com.kpe.objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VendorSku {
    @Id
    private String code;
    private String description;
    private String vendorDesc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendorDesc() {
        return vendorDesc;
    }

    public void setVendorDesc(String vendorDesc) {
        this.vendorDesc = vendorDesc;
    }
}
