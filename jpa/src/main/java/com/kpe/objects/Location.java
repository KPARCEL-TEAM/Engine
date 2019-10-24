package com.kpe.objects;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Location {
    private String countryName;
    @Id
    private String countryCode;
    private String locationCode;
    private String locationName;
    private String postalCode;
    private String latitude;
    private String longitude;
    private String vendorLocationName;

    public String getVendorLocationName() {
        return vendorLocationName;
    }

    public void setVendorLocationName(String vendorLocationName) {
        this.vendorLocationName = vendorLocationName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
