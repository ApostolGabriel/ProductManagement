package presentation;

import bll.AdminBLL;
import bll.ClientBLL;
import bll.EmployeeBLL;
import model.Administrator;
import model.Client;
import model.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.NoSuchElementException;

public class Login extends JFrame implements ActionListener {

    JButton b1;
    JTextField t1;
    JPasswordField p1;
    PersonBox c1;
    EmployeeController empController;

    public Login(){

        t1 = new JTextField("Name");
        t1.setFont(new Font("Consolas", Font.PLAIN, 20));
        t1.setPreferredSize(new Dimension(300, 30));

        p1 = new JPasswordField("Password");
        p1.setFont(new Font("Consolas", Font.PLAIN, 20));
        p1.setPreferredSize(new Dimension(300, 30));

        String[] strings = {"Administrator","Client","Employee"};

        c1 = new PersonBox(strings);

        b1 = new JButton("Log in");
        b1.setBackground(new Color(0x3B3535));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("Calibri",Font.BOLD,24));
        b1.setPreferredSize(new Dimension(150,40));
        b1.setBorder(BorderFactory.createLineBorder(Color.black));
        b1.addActionListener(this);

        JButton b2 = new JButton("Create account");
        b2.setBackground(new Color(0x3B3535));
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("Calibri",Font.BOLD,24));
        b2.setPreferredSize(new Dimension(200,40));
        b2.setBorder(BorderFactory.createLineBorder(Color.black));
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateAccount();
            }
        });

        this.add(t1);
        this.add(p1);
        this.add(c1);
        this.add(b1);
        this.add(b2);

        this.setSize(400,230);
        this.setLayout(new FlowLayout());
        this.setTitle("Log in");
        this.getContentPane().setBackground(new Color(0x53425E));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1){

            if(c1.getIsWhat().equals("Administrator")){
                AdminBLL adminBLL = new AdminBLL();
                try{
                    Administrator ad = adminBLL.isAdmin(t1.getText(), new String(p1.getPassword()));
                    new AdminController(ad);
                } catch(NoSuchElementException exception) {
                    JOptionPane.showMessageDialog(this,"Username or Password of Admin incorrect\nPlease replace them","Error",JOptionPane.ERROR_MESSAGE);
                }
            }

            if(c1.getIsWhat().equals("Client")){
                ClientBLL clientBLL = new ClientBLL();
                try {
                    Client cl = clientBLL.isClient(t1.getText(), new String(p1.getPassword()));
                    ClientController clientController = new ClientController(cl);
                    if(empController != null){
                        clientController.addObserver(empController);
                    }
                } catch(NoSuchElementException exception) {
                    JOptionPane.showMessageDialog(this,"Username or Password of Client incorrect\nPlease replace them","Error",JOptionPane.ERROR_MESSAGE);
                }
            }

            if(c1.getIsWhat().equals("Employee")){
                EmployeeBLL employeeBLL = new EmployeeBLL();
                try {
                    Employee employee = employeeBLL.isEmployee(t1.getText(), new String(p1.getPassword()));
                    empController = new EmployeeController();
                } catch(NoSuchElementException exception) {
                    JOptionPane.showMessageDialog(this,"Username or Password of employee incorrect\nPlease replace them","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
