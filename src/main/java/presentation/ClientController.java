package presentation;

import connection.Serializator;
import model.*;
import model.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class ClientController extends Observable implements WindowListener {

    JLabel l1;
    ArrayList<MenuItem> bag;
    DeliveryService ds;
    JFrame frame;
    int sum;

    ClientController(Client client){

        frame = new JFrame();

        sum = 0;
        ds = new DeliveryService();

        String[] fields = new String[]{"Title","Rating","Calories","Protein","Fat","Sodium","Price"};

        Object[][] data = new Object[ds.getMenuItems().size()][fields.length];
        int i = 0;
        for(model.MenuItem item:ds.getMenuItems()){

            data[i][0] = item.getTitle();
            data[i][1] = item.getRating();
            data[i][2] = item.getCalories();
            data[i][3] = item.getProtein();
            data[i][4] = item.getFat();
            data[i][5] = item.getSodium();
            data[i][6] = item.computePrice();

            i++;
        }

        JTable table = new JTable();

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        scrollPane.setPreferredSize(new Dimension(850,300));

        DefaultTableModel model = new DefaultTableModel(data, fields);
        table.setModel(model);
        model.fireTableDataChanged();
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(250);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(new Color(0x3B3535));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setFont(new Font("Calibri", Font.BOLD, 18));

        scrollPane.setBackground(new Color(0x3B3535));

        bag = new ArrayList<>();
        Object[][] data2 = new Object[0][fields.length];

        JTable shoppingCart = new JTable();
        DefaultTableModel model2 = new DefaultTableModel(data2, fields);
        shoppingCart.setModel(model2);
        model2.fireTableDataChanged();
        TableColumnModel columnModel2 = shoppingCart.getColumnModel();
        columnModel2.getColumn(0).setPreferredWidth(250);

        JScrollPane scrollPane2 = new JScrollPane(shoppingCart);
        shoppingCart.setFillsViewportHeight(true);
        scrollPane2.setPreferredSize(new Dimension(800,300));

        JTableHeader tableHeader2 = shoppingCart.getTableHeader();
        tableHeader2.setBackground(new Color(0x3B3535));
        tableHeader2.setForeground(Color.WHITE);
        tableHeader2.setFont(new Font("Calibri", Font.BOLD, 18));

        scrollPane2.setBackground(new Color(0x3B3535));

        JButton b2 = new JButton("Add to shopping cart");
        b2.setBackground(new Color(0x3B3535));
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("Calibri",Font.BOLD,20));
        b2.setSize(100,24);
        b2.setBorder(BorderFactory.createLineBorder(Color.black));
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == b2){
                    List<MenuItem> itemsSelected = new ArrayList<>();
                    if(table.getSelectedRows().length == 0){
                        JOptionPane.showMessageDialog(frame,"Select items first","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        for (int i = 0; i < table.getSelectedRows().length; i++) {
                            itemsSelected.add((MenuItem) ds.getMenuItems().toArray()[table.getSelectedRows()[i]]);
                        }
                        bag.addAll(itemsSelected);
                        itemsSelected.forEach(menuItem -> {
                            model2.addRow(new Object[]{menuItem.getTitle(), menuItem.getRating(), menuItem.getCalories(), menuItem.getProtein(), menuItem.getFat(), menuItem.getSodium(), menuItem.computePrice()});
                            sum += menuItem.computePrice();
                            l1.setText("Total Price: " + sum);
                        });
                    }
                }
            }
        });

        JButton b3 = new JButton("Remove from shopping cart");
        b3.setBackground(new Color(0x3B3535));
        b3.setForeground(Color.WHITE);
        b3.setFont(new Font("Calibri",Font.BOLD,20));
        b3.setSize(100,24);
        b3.setBorder(BorderFactory.createLineBorder(Color.black));
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == b3) {
                    if (shoppingCart.getSelectedRows().length == 0) {
                        JOptionPane.showMessageDialog(frame, "Select items first", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (int i = 0; i < shoppingCart.getSelectedRows().length; ) {
                            MenuItem menuItem = bag.remove(shoppingCart.getSelectedRows()[i]);
                            model2.removeRow(shoppingCart.getSelectedRows()[i]);
                            sum -= menuItem.computePrice();
                        }
                        l1.setText("Total price: " + sum);
                    }
                }
            }
        });

        JTextField t1 = new JTextField("Title");
        t1.setFont(new Font("Consolas", Font.PLAIN, 16));
        t1.setBounds(20,540,40,24);

        JTextField t2 = new JTextField("Rating");
        t2.setFont(new Font("Consolas", Font.PLAIN, 16));
        t2.setSize(100,20);

        JTextField t3 = new JTextField("Calories");
        t3.setFont(new Font("Consolas", Font.PLAIN, 16));
        t3.setSize(100,20);

        JTextField t4 = new JTextField("Protein");
        t4.setFont(new Font("Consolas", Font.PLAIN, 16));
        t3.setSize(100,20);

        JTextField t5 = new JTextField("Fat");
        t5.setFont(new Font("Consolas", Font.PLAIN, 16));
        t5.setSize(100,20);

        JTextField t6 = new JTextField("Sodium");
        t6.setFont(new Font("Consolas", Font.PLAIN, 16));
        t6.setSize(100,20);

        JTextField t7 = new JTextField("Price");
        t7.setFont(new Font("Consolas", Font.PLAIN, 16));
        t7.setSize(100,20);

        SearchButton sb1 = new SearchButton("Search",table,model,ds,t1,t2,t3,t4,t5,t6,t7);

        l1 = new JLabel("Total Price: "+sum);
        l1.setFont(new Font("Calibri",Font.BOLD,16));
        l1.setForeground(Color.WHITE);
        l1.setSize(200,18);


        JButton b4 = new JButton("Place order");
        b4.setBackground(new Color(0x3B3535));
        b4.setForeground(Color.WHITE);
        b4.setFont(new Font("Calibri",Font.BOLD,20));
        b4.setSize(80,200);
        b4.setBorder(BorderFactory.createLineBorder(Color.black));
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == b4){
                    if(bag.isEmpty()){
                        JOptionPane.showMessageDialog(frame,"There are no items in the shopping cart\nPlease add at least one","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        Order order = ds.placeOrder(client.getIdClient(), new ArrayList<>(bag));
                        ds.generateBill(order, bag, sum);
                        setChanged();
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(order.toString()).append("<br />");
                        bag.forEach(menuItem -> stringBuilder.append(menuItem.getTitle()).append(", "));
                        notifyObservers(stringBuilder.toString());
                        model2.setRowCount(0);
                        bag.clear();
                        sum = 0;
                        l1.setText("Total Price: " + sum);
                    }
                }
            }
        });

        frame.add(scrollPane);
        frame.add(t1);
        frame.add(t2);
        frame.add(t3);
        frame.add(t4);
        frame.add(t5);
        frame.add(t6);
        frame.add(t7);
        frame.add(sb1);
        frame.add(b2);
        frame.add(b3);
        frame.add(l1);
        frame.add(scrollPane2);
        frame.add(b4);

        frame.addWindowListener(this);
        frame.setSize(900,800);
        frame.setLayout(new FlowLayout());
        frame.setTitle("Client Controller");
        frame.getContentPane().setBackground(new Color(0xD5A14F33));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        Serializator s = new Serializator();
        s.save(ds);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
