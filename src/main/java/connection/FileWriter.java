package connection;

import model.Client;
import model.MenuItem;
import model.Order;

import java.io.IOException;
import java.util.Collection;

public class FileWriter {

    public FileWriter(){}

    public void writeBill(Order o, Client c, Collection<MenuItem> menuItems,int sum) {

        try {
            java.io.FileWriter fw = new java.io.FileWriter("bill"+o.getOrderId()+".txt");
            fw.write("------------------------Bill-----------------------\n\n\n");
            fw.write("Client "+c.getName()+" ordered\n\n");
            menuItems.forEach(item -> {
                try {
                    fw.write(item.toString()+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fw.write("\nThe final payment: "+sum+"\n\n");
            fw.write("-------------------------------"+o.getOrderDate());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
