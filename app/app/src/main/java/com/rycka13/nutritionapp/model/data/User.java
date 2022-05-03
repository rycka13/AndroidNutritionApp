package com.rycka13.nutritionapp.model.data;

import java.util.ArrayList;

public class User {
    private Double weight;
    private Double height;
    private String gender;
    private Double limit;

    public User() {
        weight = null;
        height = null;
        gender = null;
        limit = null;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }
}
