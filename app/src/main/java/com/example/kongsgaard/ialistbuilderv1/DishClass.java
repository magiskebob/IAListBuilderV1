package com.example.kongsgaard.ialistbuilderv1;

import java.io.Serializable;

/**
 * Created by magiskebob on 07-04-2017.
 */

public class DishClass implements Serializable{
    public String Description;
    public int Alcohol;
    public int Carbohydrates;
    public int Energy;
    public double Fat;
    public double Price;
    public double Protein;
    public String Title;

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getProtein() {
        return Protein;
    }

    public void setProtein(double protein) {
        Protein = protein;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public DishClass(double fat, int energy, int carbohydrates, int alcohol, String description, double price, double protein, String title) {
        Fat = fat;
        Energy = energy;
        Carbohydrates = carbohydrates;
        Alcohol = alcohol;
        Description = description;
        Price = price;
        Protein = protein;
        Title = title;

    }

    public DishClass() {
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getAlcohol() {
        return Alcohol;
    }

    public void setAlcohol(int alcohol) {
        Alcohol = alcohol;
    }

    public int getCarbohydrates() {
        return Carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        Carbohydrates = carbohydrates;
    }

    public int getEnergy() {
        return Energy;
    }

    public void setEnergy(int energy) {
        Energy = energy;
    }

    public double getFat() {
        return Fat;
    }

    public void setFat(double fat) {
        Fat = fat;
    }
}
