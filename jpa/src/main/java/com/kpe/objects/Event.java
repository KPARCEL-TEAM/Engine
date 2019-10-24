package com.kpe.objects;

import com.kpe.objects.ids.EventId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(EventId.class)
public class Event {
    @Id
    private String code;
    private String nativeDesc;
    private String description;
    @Id
    private String reasonCode;
    private String reasonDesc;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNativeDesc() {
        return nativeDesc;
    }

    public void setNativeDesc(String nativeDesc) {
        this.nativeDesc = nativeDesc;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getReasonDesc() {
        return reasonDesc;
    }

    public void setReasonDesc(String reasonDesc) {
        this.reasonDesc = reasonDesc;
    }
}
