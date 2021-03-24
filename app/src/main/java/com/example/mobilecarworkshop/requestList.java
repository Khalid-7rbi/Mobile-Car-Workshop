package com.example.mobilecarworkshop;

import com.google.firebase.firestore.Exclude;

public class requestList {

    private String fName,phone;
    private String CompanyType,carType,lat,lng,problem;


    private requestList() {
    }

    private requestList(String fName, String phone, String CompanyType, String carType, String lat, String lng, String problem) {
        this.fName = fName;
        this.phone = phone;
        CompanyType = CompanyType;
        this.carType = carType;
        this.lat = lat;
        this.lng = lng;
        this.problem = problem;
    }

    public CharSequence getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public CharSequence getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyType() {
        return CompanyType;
    }

    public void setCompanyType(String companyType) {
        CompanyType = companyType;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }
}

