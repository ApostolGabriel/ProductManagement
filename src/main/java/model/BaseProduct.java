package model;

import java.util.Objects;

public class BaseProduct extends MenuItem {

    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    public BaseProduct(){ }

    public BaseProduct(String title, double rating, int cal, int prot, int fat, int sod, int price){
        super(title,rating);
        this.calories = cal;
        this. protein = prot;
        this.fat = fat;
        this.sodium = sod;
        this.price = price;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setPrice(int price) {
        this.price = price;
    }



    public int getCalories() {
        return calories;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getSodium() {
        return sodium;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseProduct that = (BaseProduct) o;
        return getTitle().equals(that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }

    @Override
    public int computePrice() {
        return price;
    }
}
