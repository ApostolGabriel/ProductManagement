package presentation;

import bll.ClientBLL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccount extends JFrame implements ActionListener {

    JTextField t1;
    JPasswordField p1;
    JPasswordField p2;
    JButton b1;

    CreateAccount(){

        t1 = new JTextField("Your name");
        t1.setFont(new Font("Consolas", Font.PLAIN, 20));
        t1.setPreferredSize(new Dimension(300, 30));

        JLabel l1 = new JLabel("Password");
        l1.setFont(new Font("Consolas", Font.PLAIN, 24));
        l1.setPreferredSize(new Dimension(200, 40));
        l1.setForeground(Color.WHITE);

        p1 = new JPasswordField("Password");
        p1.setFont(new Font("Consolas", Font.PLAIN, 20));
        p1.setPreferredSize(new Dimension(300, 30));

        JLabel l2 = new JLabel("Repeat password");
        l2.setFont(new Font("Consolas", Font.PLAIN, 24));
        l2.setPreferredSize(new Dimension(300, 40));
        l2.setForeground(Color.WHITE);

        p2 = new JPasswordField("Password");
        p2.setFont(new Font("Consolas", Font.PLAIN, 20));
        p2.setPreferredSize(new Dimension(300, 30));

        b1 = new JButton("Create account");
        b1.setBackground(new Color(0x3B3535));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("Calibri",Font.BOLD,24));
        b1.setPreferredSize(new Dimension(200,40));
        b1.setBorder(BorderFactory.createLineBorder(Color.black));
        b1.addActionListener(this);

        this.add(t1);
        this.add(l1);
        this.add(p1);
        this.add(l2);
        this.add(p2);
        this.add(b1);

        this.setSize(350,320);
        this.setLayout(new FlowLayout());
        this.setTitle("Create Account");
        this.getContentPane().setBackground(new Color(0x609979));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1){
            ClientBLL clientBLL = new ClientBLL();
            if( new String(p1.getPassword()).equals(new String(p2.getPassword())) && clientBLL.notUser(t1.getText())){
                clientBLL.insertClient(t1.getText(),new String( p1.getPassword()));
                this.dispose();
            } else if(clientBLL.notUser(t1.getText())){
                JOptionPane.showMessageDialog(this,"Passwords don't match\nPlease repeat password","Error",JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,"There is already a user with this name\nPlease enter another name","Error",JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}
