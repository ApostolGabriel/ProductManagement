package presentation;

import connection.Serializator;
import model.*;
import model.MenuItem;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AdminController extends JFrame {

    JTextField t1;
    JTextField t2;
    ArrayList<MenuItem> list;
    boolean update;

    AdminController(Administrator administrator) {



        list = new ArrayList<>();

        DeliveryService ds = new DeliveryService();

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
        table.setSize(new Dimension(800,300));
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

        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JCheckBox c1= new JCheckBox("Update",false);
        c1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getSource() == c1){
                    update = e.getStateChange() == ItemEvent.SELECTED;
                }
            }
        });
        c1.setPreferredSize(new Dimension(100,24));
        c1.setBackground(new Color(0x3B3535));
        c1.setForeground(Color.WHITE);
        c1.setFont(new Font("Calibri", Font.BOLD, 20));

        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(update) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    Object data = model.getValueAt(row, column);

                    int i = 0;
                    for (MenuItem menuItem : ds.getMenuItems()) {
                        if (row == i) {
                            ds.manageProducts("Update", menuItem, (String) data, column);
                            break;
                        }
                        i++;
                    }
                }
            }
        });

        JButton b1 = new JButton("Import products");
        b1.setBackground(new Color(0x3B3535));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("Calibri", Font.BOLD, 20));
        b1.setSize(80, 200);
        b1.setBorder(BorderFactory.createLineBorder(Color.black));
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b1) {
                    Serializator s = new Serializator();
                    ds.importProducts();
                    s.save(ds);
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
                    model.setDataVector(data,new String[]{"Title","Rating","Calories","Protein","Fat","Sodium","Price"});
                    table.getColumnModel().getColumn(0).setPreferredWidth(250);
                }
            }
        });

        t1 = new JTextField("Enter value");
        t1.setFont(new Font("Consolas", Font.PLAIN, 20));
        t1.setPreferredSize(new Dimension(300, 30));

        t2 = new JTextField("Enter value");
        t2.setFont(new Font("Consolas", Font.PLAIN, 20));
        t2.setPreferredSize(new Dimension(300, 30));

        JButton b2 = new JButton("Report On Orders In Time Interval");
        b2.setBackground(new Color(0x3B3535));
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("Calibri", Font.BOLD, 20));
        b2.setBorder(BorderFactory.createLineBorder(Color.black));
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == b2) {
                    new GenerateReport(1, ds, t1.getText(), t2.getText());
                }
            }
        });


        JButton b3 = new JButton("Report On Order Times");
        b3.setBackground(new Color(0x3B3535));
        b3.setForeground(Color.WHITE);
        b3.setFont(new Font("Calibri", Font.BOLD, 20));
        b3.setSize(80, 200);
        b3.setBorder(BorderFactory.createLineBorder(Color.black));
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b3) {
                    new GenerateReport(2,ds,t1.getText(), t2.getText());
                }
            }
        });

        JButton b4 = new JButton("Report On Clients Order Numbers");
        b4.setBackground(new Color(0x3B3535));
        b4.setForeground(Color.WHITE);
        b4.setFont(new Font("Calibri", Font.BOLD, 20));
        b4.setSize(80, 200);
        b4.setBorder(BorderFactory.createLineBorder(Color.black));
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b4) {
                    new GenerateReport(3,ds,t1.getText(), t2.getText());
                }
            }
        });

        JButton b5 = new JButton("Report On Products Per Day");
        b5.setBackground(new Color(0x3B3535));
        b5.setForeground(Color.WHITE);
        b5.setFont(new Font("Calibri", Font.BOLD, 20));
        b5.setSize(80, 200);
        b5.setBorder(BorderFactory.createLineBorder(Color.black));
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b5) {
                    new GenerateReport(4,ds,t1.getText(), t2.getText());
                }
            }
        });

        JButton b6 = new JButton("Add Base Product");
        b6.setBackground(new Color(0x3B3535));
        b6.setForeground(Color.WHITE);
        b6.setFont(new Font("Calibri", Font.BOLD, 20));
        b6.setSize(80, 200);
        b6.setBorder(BorderFactory.createLineBorder(Color.black));
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b6) {
                    new AddProduct(1,ds,model,list);
                }
            }
        });

        JButton b7 = new JButton("Add Composite Product");
        b7.setBackground(new Color(0x3B3535));
        b7.setForeground(Color.WHITE);
        b7.setFont(new Font("Calibri", Font.BOLD, 20));
        b7.setSize(80, 200);
        b7.setBorder(BorderFactory.createLineBorder(Color.black));
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b7) {
                    for(int i = 0; i < table.getSelectedRows().length; i++) {
                        list.add((MenuItem) ds.getMenuItems().toArray()[table.getSelectedRows()[i]]);
                    }
                    if(list.isEmpty()){
                        JOptionPane.showMessageDialog(AdminController.super.rootPane,"There are no items selected\nPlease select some items from the list of products","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        new AddProduct(2, ds, model, list);
                    }
                }
            }
        });

        JButton b8 = new JButton("Delete Products");
        b8.setBackground(new Color(0x3B3535));
        b8.setForeground(Color.WHITE);
        b8.setFont(new Font("Calibri", Font.BOLD, 20));
        b8.setSize(80, 200);
        b8.setBorder(BorderFactory.createLineBorder(Color.black));
        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b8) {
                    for(int i = 0; i < table.getSelectedRows().length; ){
                        ds.manageProducts("Delete",(MenuItem) ds.getMenuItems().toArray()[table.getSelectedRows()[i]],null,0);
                        model.removeRow(table.getSelectedRows()[i]);
                    }
                }
            }
        });

        this.add(scrollPane);
        this.add(b1);
        this.add(t1);
        this.add(t2);
        this.add(b2);
        this.add(b3);
        this.add(b4);
        this.add(b5);
        this.add(b6);
        this.add(b7);
        this.add(b8);
        this.add(c1);

        this.addWindowListener(new WindowListener() {
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
        });

        this.setSize(900, 450);
        this.setLayout(new FlowLayout());
        this.setTitle("Admin Controller");
        this.getContentPane().setBackground(new Color(0xFF2E4F55));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

    }

}
