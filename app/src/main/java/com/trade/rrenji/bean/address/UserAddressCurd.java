package com.trade.rrenji.bean.address;

import java.io.Serializable;

public class UserAddressCurd implements Serializable {

    public String addressId;
    public String consigneeName;
    public String consigneeTel;
    public String province;
    public String city;
    public String district;
    public String location;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeTel() {
        return consigneeTel;
    }

    public void setConsigneeTel(String consigneeTel) {
        this.consigneeTel = consigneeTel;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "UserAddressCurd{" +
                "addressId='" + addressId + '\'' +
                ", consigneeName='" + consigneeName + '\'' +
                ", consigneeTel='" + consigneeTel + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
