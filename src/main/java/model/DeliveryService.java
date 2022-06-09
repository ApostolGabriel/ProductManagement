package model;

import bll.ClientBLL;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import connection.FileWriter;
import connection.Serializator;
import dao.AdminDAO;

import java.io.*;
import java.security.KeyStore;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DeliveryService implements IDeliveryServiceProcessing, Serializable {

    Map<Order, Collection<MenuItem>> orderCollectionMap;
    Collection<MenuItem> menuItems;

    @Serial
    private static final long serialVersionUID = 1L;

    public DeliveryService(){
        Serializator s = new Serializator();
        DeliveryService ds = s.load();
        this.orderCollectionMap = ds.orderCollectionMap;
        if(orderCollectionMap == null){
            orderCollectionMap = new HashMap<Order, Collection<MenuItem>>();
        }
        this.menuItems = ds.menuItems;
    }

    public Map<Order, Collection<MenuItem>> getOrderCollectionMap() {
        return orderCollectionMap;
    }

    public Collection<MenuItem> getMenuItems() {
        return menuItems;
    }

    @Override
    public void importProducts() {

        try (CSVReader reader = new CSVReader(new FileReader("products.csv"))) {
            List<String[]> r = reader.readAll();
            menuItems = r.stream().skip(1)
                    .map(a -> new BaseProduct(a[0], Double.parseDouble(a[1]), Integer.parseInt(a[2]), Integer.parseInt(a[3]), Integer.parseInt(a[4]), Integer.parseInt(a[5]), Integer.parseInt(a[6])))
                    .distinct()
                    .collect(Collectors.toList());
        } catch (CsvException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void manageProducts(String op, MenuItem menuItem, String data, int column) {// Adminul poate sterge adauga ... produse
        if(op.equals("Add")) {
            menuItems.add(menuItem);
        }
        if(op.equals("Delete")) {
            if(getMenuItems().contains(menuItem)){
                menuItems.remove(menuItem);
            }
        }
        if(op.equals("Update")){
            switch(column){
                case 0 ->{
                    menuItem.setTitle(data);
                }
                case 1 ->{
                    menuItem.setRating(Double.parseDouble(data));
                }
                case 2 -> {
                    if(menuItem instanceof BaseProduct){
                        ((BaseProduct) menuItem).setCalories(Integer.parseInt(data));
                    }
                }
                case 3 -> {
                    if(menuItem instanceof BaseProduct){
                        ((BaseProduct) menuItem).setProtein(Integer.parseInt(data));
                    }
                }
                case 4 -> {
                    if(menuItem instanceof BaseProduct){
                        ((BaseProduct) menuItem).setFat(Integer.parseInt(data));
                    }
                }
                case 5 -> {
                    if(menuItem instanceof BaseProduct){
                        ((BaseProduct) menuItem).setSodium(Integer.parseInt(data));
                    }
                }
                case 6 -> {
                    if(menuItem instanceof BaseProduct){
                        ((BaseProduct) menuItem).setPrice(Integer.parseInt(data));
                    }
                }
            }
        }
    }

    @Override
    public Order placeOrder(int clientId, Collection<MenuItem> menuItems) {
        LocalDateTime date = LocalDateTime.now();
        long orderid = (long) orderCollectionMap.keySet().size() +1;
        Order order = new Order(orderid,clientId,date);
        orderCollectionMap.put(order,menuItems);
        return order;
    }

    @Override
    public void generateBill(Order order, Collection<MenuItem> menuItems1,int sum) {
        ClientBLL clientBLL = new ClientBLL();
        Client cl = clientBLL.findById(order.getClientId());
        FileWriter fw = new FileWriter();
        fw.writeBill(order,cl,menuItems1,sum);
    }

    @Override
    public List<MenuItem> searchProductBasedOn(String title, String rating, String calories, String protein, String fat, String sodium, String price) {
        List<MenuItem> list = new ArrayList<>(menuItems);
        if(title != null && !title.equals("Title")) {
            list = list.stream().filter(menuItem -> menuItem.getTitle().contains(title)).collect(Collectors.toList());
        }
        if (rating != null && !rating.equals("Rating")) {
            list = list.stream().filter(menuItem -> menuItem.getRating() == Double.parseDouble(rating)).collect(Collectors.toList());
        }
        if(protein != null && !protein.equals("Protein")){
            list = list.stream().filter(menuItem -> menuItem.getProtein() == Integer.parseInt(protein)).collect(Collectors.toList());
        }
        if(sodium != null && !sodium.equals("Sodium")){
            list = list.stream().filter(menuItem -> menuItem.getSodium() == Integer.parseInt(sodium)).collect(Collectors.toList());
        }
        if(fat != null && !fat.equals("Fat")){
            list = list.stream().filter(menuItem -> menuItem.getFat() == Integer.parseInt(fat)).collect(Collectors.toList());
        }
        if(calories != null && !calories.equals("Calories")){
            list = list.stream().filter(menuItem -> menuItem.getCalories() == Integer.parseInt(calories)).collect(Collectors.toList());
        }
        if(price != null && !price.equals("Price")){
            list = list.stream().filter(menuItem -> menuItem.computePrice() == Integer.parseInt(price)).collect(Collectors.toList());
        }
        return list;
    }

    @Override
    public List<Order> generateReportByHour(int hour1, int hour2) {
        List<Order> orders;
        orders = orderCollectionMap.keySet().stream().filter(order -> order.getOrderDate().getHour() >= hour1 && order.getOrderDate().getHour() < hour2 ).collect(Collectors.toList());
        return orders;
    }

    @Override
    public List<MenuItem> generateReportMostProducts(int nr){
        Map<MenuItem,Integer> menuItemList;
        menuItemList = orderCollectionMap.values().stream().flatMap(Collection<MenuItem>::stream).collect(Collectors.toMap(Function.identity(), e -> 1, Math::addExact));
        return menuItemList.entrySet().stream().filter(menuItemIntegerEntry -> menuItemIntegerEntry.getValue() >= nr).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    @Override
    public List<Client> generateReportClient(int nr, int amount){
        ClientBLL clientBLL = new ClientBLL();
        Map<Integer,Integer> map;
        map = orderCollectionMap.keySet().stream().collect(Collectors.toMap(Order::getClientId,e->1,Math::addExact));
        Map<Integer,Integer> map2;
        map2 = orderCollectionMap.entrySet().stream().collect(Collectors.toMap(e->e.getKey().getClientId(),e->e.getValue().stream().mapToInt(MenuItem::computePrice).sum(),Math::addExact));
        return map.keySet().stream().filter(clientId -> map.get(clientId) >= nr && map2.get(clientId) >= amount).map(clientBLL::findById).collect(Collectors.toList());
    }

    @Override
    public Map<MenuItem,Integer> generateReportDay(int day){
        Map<MenuItem,Integer> map = null;
        map = orderCollectionMap.entrySet().stream()
                .filter(orderCollectionEntry -> orderCollectionEntry.getKey().getOrderDate().getDayOfMonth() == day )
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(e->e, e->1,Math::addExact));
        return map;
    }
}
