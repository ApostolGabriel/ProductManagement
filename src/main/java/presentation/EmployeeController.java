package presentation;

import model.MenuItem;
import model.Order;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

public class EmployeeController extends JFrame implements Observer {

    JLabel l1;
    StringBuilder string;
    int nrorders;

    EmployeeController(){

        string = new StringBuilder();
        nrorders = 0;
        string.append("<html>");

        l1 = new JLabel("<html>There are no orders</html>");
        l1.setFont(new Font("Consolas", Font.PLAIN, 16));
        l1.setPreferredSize(new Dimension(750, 750));
        l1.setForeground(Color.WHITE);

        this.add(l1);

        this.setSize(800,800);
        this.setLayout(new FlowLayout());
        this.setTitle("Employee Controller");
        this.getContentPane().setBackground(new Color(0xD5473130));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

    }

    @Override
    public void update(Observable o, Object arg) {
        if(nrorders > 0) {
            string.delete(string.length() - 6, string.length() - 1);
        }
        string.append((String) arg).append("<br />");
        string.append("</html>");
        l1.setText(string.toString());
        nrorders++;
    }
}
