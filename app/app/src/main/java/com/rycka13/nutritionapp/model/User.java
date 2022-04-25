package com.rycka13.nutritionapp.model;

import java.util.ArrayList;

public class User {
    private String userNumber;
    private String firstName;
    private ArrayList<Food> foods;
    private double weight;
    private char gender;
    private char goal;

    public User(String userNumber) {
        this.userNumber = userNumber;
        foods = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void addFood(Food food) {
        foods.add(food);
    }

    public void setFirstName(String name) {
        firstName = name;
    }

    public void removeFood(Food food) {
        foods.remove(food);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public char getGoal() {
        return goal;
    }

    public void setGoal(char LGweight) {
        this.goal = LGweight;
    }

    public String getUserNumber() {
        return userNumber;
    }
}
