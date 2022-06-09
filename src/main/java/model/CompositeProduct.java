package model;

import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem {

    ArrayList<MenuItem> menuItems;

    public CompositeProduct(){}

    public CompositeProduct(String name, double rating, ArrayList<MenuItem> list){
        super(name,rating);
        menuItems = list;
    }

    @Override
    public int getCalories() {
        return menuItems.stream().mapToInt(MenuItem::getCalories).sum();
    }

    @Override
    public int getProtein() {
        return menuItems.stream().mapToInt(MenuItem::getProtein).sum();
    }

    @Override
    public int getFat() {
        return menuItems.stream().mapToInt(MenuItem::getFat).sum();
    }

    @Override
    public int getSodium() {
        return menuItems.stream().mapToInt(MenuItem::getSodium).sum();
    }

    @Override
    public int computePrice() {
        return menuItems.stream().mapToInt(MenuItem::computePrice).sum();
    }
}
