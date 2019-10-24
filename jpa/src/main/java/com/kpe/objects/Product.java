package com.kpe.objects;

import java.util.ArrayList;
import java.util.List;

public class Product {
    String code;
    String name;
    List<Location> serviceLocations = new ArrayList<>();
    String timeliness;
    RateCard rateCard;
}
