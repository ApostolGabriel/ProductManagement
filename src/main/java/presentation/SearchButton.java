package presentation;

import model.DeliveryService;
import model.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SearchButton extends JButton implements ActionListener {

    private List<MenuItem> menuItemsFound;
    DeliveryService ds;
    JTextField t1;
    JTextField t2;
    JTextField t3;
    JTextField t4;
    JTextField t5;
    JTextField t6;
    JTextField t7;
    JTable table;
    DefaultTableModel model;


    SearchButton(String name, JTable table, DefaultTableModel model, DeliveryService ds,JTextField t1,JTextField t2,JTextField t3,JTextField t4,JTextField t5,JTextField t6,JTextField t7){
        super(name);
        this.setBackground(new Color(0x3B3535));
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Calibri",Font.BOLD,20));
        this.setSize(80,200);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.addActionListener(this);
        this.table = table;
        this.model = model;
        this.ds = ds;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.t5 = t5;
        this.t6 = t6;
        this.t7 = t7;
        menuItemsFound = new ArrayList<>();
    }

    private String hasString(JTextField t){
        if(t.getText().equals("") || t.getText().equals("Title") || t.getText().equals("Rating") || t.getText().equals("Calories") || t.getText().equals("Protein") || t.getText().equals("Fat") || t.getText().equals("Sodium") || t.getText().equals("Price")){
            return null;
        } else {
            return t.getText();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this){
            menuItemsFound = ds.searchProductBasedOn(hasString(t1),hasString(t2),hasString(t3),hasString(t4),hasString(t5),hasString(t6),hasString(t7));
            model.setRowCount(0);
            menuItemsFound.forEach(menuItem -> model.addRow(new Object[]{menuItem.getTitle(),menuItem.getRating(),menuItem.getCalories(),menuItem.getProtein(),menuItem.getFat(),menuItem.getSodium(),menuItem.computePrice()}));
        }
    }
}
