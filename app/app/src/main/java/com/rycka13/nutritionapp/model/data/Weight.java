package com.rycka13.nutritionapp.model.data;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

public class Weight {

    private Double weight;
    private String dateString;

    public Weight(double weight, String dateString){
        this.weight = weight;
        this.dateString = dateString;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
}
