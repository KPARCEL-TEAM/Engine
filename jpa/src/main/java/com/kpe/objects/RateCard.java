package com.kpe.objects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RateCard {
    Customer customer;
    Map<Service,List<ChargeItem>> charges = new HashMap();

}
