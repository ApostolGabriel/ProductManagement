package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PersonBox extends JComboBox implements ItemListener {

    private String isWhat;

    PersonBox(String[] names) {
        super(names);
        this.setBackground(new Color(0x3B3535));
        this.setForeground(Color.WHITE);
        this.setPreferredSize(new Dimension(250, 40));
        this.setFont(new Font("Consolas", Font.PLAIN, 20));
        this.addItemListener(this);
        isWhat = "Administrator";
    }

    public String getIsWhat() {
        return isWhat;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == this){
            isWhat = (String) this.getSelectedItem();
        }
    }
}
