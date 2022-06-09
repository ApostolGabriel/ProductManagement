package start;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import connection.Serializator;
import model.BaseProduct;
import model.DeliveryService;
import model.MenuItem;
import model.Order;
import presentation.Login;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args){
        new Login();

        //Serializator s = new Serializator();

       // DeliveryService ds = new DeliveryService();
        //System.out.println(ds.getMenuItems().stream().findAny().toString());
        //System.out.println(ds.getOrderCollectionMap().entrySet().stream().findAny().toString());
        //ArrayList<Order> list = new ArrayList<>(ds.getOrderCollectionMap().keySet());
        //list.forEach(order -> System.out.println(order.toString()));
        //ds.generateReportByHour(17,19).forEach(e -> System.out.println(e.toString()));
        //System.out.println(LocalDateTime.now().getHour());
        //ds.importProducts();
        //ds.getOrderCollectionMap().clear();

        //s.save(ds);
        //Collection<MenuItem> list = ds.

        //System.out.println(menuItems.size());


        /*ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(5);
        Collection<Integer> collection = list;
        collection.forEach(System.out::println);*/
    }
}
