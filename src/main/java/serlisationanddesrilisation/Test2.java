package serlisationanddesrilisation;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Test2 {

    public static void main(String[] args) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("person.ser"))) {
            Person p2 = (Person) in.readObject();
            System.out.println("Name: " + p2.name);       // John
            System.out.println("Age: " + p2.age);         // 30
            System.out.println("Password: " + p2.password); // null because it's transient
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

