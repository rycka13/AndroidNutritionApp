package com.rycka13.nutritionapp.model.data;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

public class Weight {

    private Double weight;
    private LocalDate localDate;
    private String dateString;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Weight(double weight, String dateString){
        this.weight = weight;
        this.dateString = dateString;
        localDate = LocalDate.parse(dateString);
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
}
