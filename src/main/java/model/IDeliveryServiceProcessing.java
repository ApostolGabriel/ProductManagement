package model;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IDeliveryServiceProcessing {

    public void importProducts();

    void manageProducts(String op, MenuItem menuItem, String data, int column);

    Order placeOrder(int clientId, Collection<MenuItem> menuItems);

    void generateBill(Order order, Collection<MenuItem> menuItems1, int sum);

    List<MenuItem> searchProductBasedOn(String title, String rating, String protein, String sodium, String fat, String calories, String price);

    List<Order> generateReportByHour(int hour1, int hour2);

    List<MenuItem> generateReportMostProducts(int nr);

    List<Client> generateReportClient(int nr, int amount);

    Map<MenuItem,Integer> generateReportDay(int day);
}
