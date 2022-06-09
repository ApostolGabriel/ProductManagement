package model;

import java.io.Serial;
import java.io.Serializable;

public abstract class MenuItem implements Serializable {

    private String title;
    private double rating;

    @Serial
    private static final long serialVersionUID = 1L;

    MenuItem(){}

    public MenuItem(String title, double rating) {
        this.title = title;
        this.rating = rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    abstract public int getCalories();

    abstract public int getProtein();

    abstract public int getFat();

    abstract public int getSodium();

    abstract public int computePrice();

    @Override
    public String toString(){
        return getTitle()+" Rating :" + getRating() + " Calories :"+getCalories()+" Protein :"+getProtein()+" Fat :"+getFat()+" Sodium :"+getSodium() + " Price :"+computePrice();
    }

}
