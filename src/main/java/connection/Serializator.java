package connection;

import model.DeliveryService;

import java.io.*;

public class Serializator {

    public Serializator(){}

    public void save(DeliveryService ds) {
        try {
            FileOutputStream fout = new FileOutputStream("savestation.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(ds);
            oos.close();
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public DeliveryService load() {
        ObjectInputStream objectinputstream = null;
        DeliveryService ds = null;
        try {
            FileInputStream streamIn = new FileInputStream("savestation.txt");
            objectinputstream = new ObjectInputStream(streamIn);
            ds = (DeliveryService) objectinputstream.readObject();
            objectinputstream.close();
            streamIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
}
