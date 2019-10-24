package com.kpe.objects;

import java.util.ArrayList;
import java.util.List;

public class Parcel {
    String shipper;
    String consignee;
    String notifyParty;
    String value;
    String cod;
    List<SkuItem> items = new ArrayList<SkuItem>();
    String from;
    String to;
}
