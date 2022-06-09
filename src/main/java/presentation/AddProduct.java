package presentation;

import model.BaseProduct;
import model.CompositeProduct;
import model.DeliveryService;
import model.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddProduct extends JFrame {

    DeliveryService ds;
    ArrayList<MenuItem> list;
    JTextField t1;
    JTextField t2;
    JTextField t3;
    JTextField t4;
    JTextField t5;
    JTextField t6;
    JTextField t7;

    AddProduct(int type, DeliveryService ds, DefaultTableModel model, ArrayList<MenuItem> list){
        //, String title, int rat, int cal, int prot, int fat, int sod, int price
        this.ds = ds;
        this.list = list;

        t1 = new JTextField("Title");
        t1.setFont(new Font("Consolas", Font.PLAIN, 20));
        t1.setPreferredSize(new Dimension(200,40));

        t2 = new JTextField("Rating");
        t2.setFont(new Font("Consolas", Font.PLAIN, 20));
        t2.setPreferredSize(new Dimension(200,40));

        this.add(t1);
        this.add(t2);

        if (type == 1) {
            t3 = new JTextField("Calories");
            t3.setFont(new Font("Consolas", Font.PLAIN, 16));
            t3.setPreferredSize(new Dimension(200,40));

            t4 = new JTextField("Protein");
            t4.setFont(new Font("Consolas", Font.PLAIN, 20));
            t4.setPreferredSize(new Dimension(200,40));

            t5 = new JTextField("Fat");
            t5.setFont(new Font("Consolas", Font.PLAIN, 20));
            t5.setPreferredSize(new Dimension(200,40));

            t6 = new JTextField("Sodium");
            t6.setFont(new Font("Consolas", Font.PLAIN, 20));
            t6.setPreferredSize(new Dimension(200,40));

            t7 = new JTextField("Price");
            t7.setFont(new Font("Consolas", Font.PLAIN, 20));
            t7.setPreferredSize(new Dimension(200,40));

            this.add(t3);
            this.add(t4);
            this.add(t5);
            this.add(t6);
            this.add(t7);
        }
        JButton b1 = new JButton("Add");
        b1.setBackground(new Color(0x3B3535));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("Calibri", Font.BOLD, 24));
        b1.setPreferredSize(new Dimension(80, 30));
        b1.setBorder(BorderFactory.createLineBorder(Color.black));
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b1) {
                    if(type == 1) {
                        ds.manageProducts("Add", new BaseProduct(t1.getText(),Double.parseDouble(t2.getText()),Integer.parseInt(t3.getText()),Integer.parseInt(t4.getText()),Integer.parseInt(t5.getText()),Integer.parseInt(t6.getText()),Integer.parseInt(t7.getText())),null,0);
                        model.addRow(new Object[]{t1.getText(),t2.getText(),t3.getText(),t4.getText(),t5.getText(),t6.getText(),t7.getText()});
                        AddProduct.super.dispose();
                    }
                    else {
                        CompositeProduct compositeProduct = new CompositeProduct(t1.getText(),Double.parseDouble(t2.getText()),list);
                        ds.manageProducts("Add", compositeProduct,null,0);
                        model.addRow(new Object[]{t1.getText(),t2.getText(),compositeProduct.getCalories(),compositeProduct.getProtein(),compositeProduct.getFat(),compositeProduct.getSodium(),compositeProduct.computePrice()});
                        AddProduct.super.dispose();
                    }
                }
            }
        });

        this.add(b1);
        this.setSize(300, 400);
        this.setLayout(new FlowLayout());
        this.setTitle("Add product");
        this.getContentPane().setBackground(new Color(0xFF2E4F55));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

}
