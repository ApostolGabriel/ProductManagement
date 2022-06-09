package presentation;

import model.Client;
import model.DeliveryService;
import model.MenuItem;
import model.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class GenerateReport extends JFrame {

    private String t1;
    private String t2;
    private int type;
    private JButton b;
    private DeliveryService ds;
    private JPanel p1;

    GenerateReport(int type, DeliveryService ds, String t1, String t2){

        this.ds = ds;

        this.type = type;

        p1 = new JPanel(new FlowLayout());
        p1.setBackground(new Color(0x3B3535));
        p1.setPreferredSize(new Dimension(900,800));
        p1.setBorder(BorderFactory.createLineBorder(Color.black));

        switch (type) {

            case 1 -> {

                Collection<Order> list = ds.generateReportByHour(Integer.parseInt(t1), Integer.parseInt(t2));

                list.forEach(order -> {
                    JLabel l = new JLabel(order.toString());
                    l.setFont(new Font("Calibri", Font.PLAIN, 20));
                    l.setPreferredSize(new Dimension(700, 25));
                    l.setForeground(Color.WHITE);
                    p1.add(l);
                });


            }
            case 2 -> {
                Collection<MenuItem> list = ds.generateReportMostProducts(Integer.parseInt(t1));

                list.forEach(menuItem -> {
                    System.out.println(menuItem.toString());
                    JLabel l = new JLabel(menuItem.toString());
                    l.setFont(new Font("Calibri", Font.PLAIN, 20));
                    l.setPreferredSize(new Dimension(700, 25));
                    l.setForeground(Color.WHITE);
                    p1.add(l);
                });
            }
            case 3 -> {
                Collection<Client> list = ds.generateReportClient(Integer.parseInt(t1), Integer.parseInt(t2));
                list.forEach(client -> {
                    JLabel l = new JLabel(client.toString());
                    l.setFont(new Font("Calibri", Font.PLAIN, 20));
                    l.setPreferredSize(new Dimension(700, 25));
                    l.setForeground(Color.WHITE);
                    p1.add(l);
                });
            }
            case 4 -> {
                Map<MenuItem, Integer> map = ds.generateReportDay(Integer.parseInt(t1));

                map.forEach((menuItem, nrOfTimes) -> {
                    JLabel l = new JLabel(menuItem.getTitle() + " ordered " + nrOfTimes + " times");
                    l.setFont(new Font("Calibri", Font.PLAIN, 20));
                    l.setPreferredSize(new Dimension(700, 25));
                    l.setForeground(Color.WHITE);
                    p1.add(l);
                });
            }
        }

        this.add(p1);
        this.setSize(900, 800);
        this.setLayout(new FlowLayout());
        this.setTitle("Report");
        this.getContentPane().setBackground(new Color(0x9B3AF6));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

    }

}
