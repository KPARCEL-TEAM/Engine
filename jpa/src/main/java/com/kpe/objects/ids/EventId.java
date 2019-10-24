package com.kpe.objects.ids;

import java.io.Serializable;
import java.util.Objects;

public class EventId implements Serializable {
    private String code;
    private String reasonCode;


    EventId(){

    }

    @Override
    public int hashCode() {

        return Objects.hash(code, reasonCode);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof EventId)) {
            return false;
        }
        EventId key = (EventId) o;
        return code== key.code&&
                Objects.equals(code, key.code) &&
                Objects.equals(reasonCode, key.reasonCode);
    }
}