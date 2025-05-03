package serlisationanddesrilisation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Test1 {

    public static void main(String[] args) {
        Person p = new Person("John", 30, "secret123");

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("person.ser"))) {
            out.writeObject(p);
            System.out.println("Serialized!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


class Person implements Serializable {
    private static final long serialVersionUID = 1L; // Important for versioning

    String name;
    int age;
    transient String password; // will not be serialized

    public Person(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }
}