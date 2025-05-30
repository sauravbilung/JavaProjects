package generics;

import java.util.ArrayList;
import java.util.List;

public class _2_GenericsClasses {

    public static void main(String[] args) {
        genericBox();
        genericPair();
        genericMethods();
    }

    private static void genericBox() {
        var box1 = new Box<String>();
        //var box1 = new Box<>(); // This also works java infers generic type T from method arguments.
        box1.setContent("Hello World");
        //System.out.println(box1.getContent());

        Box<Integer> box2 = new Box<>();
        box2.setContent(123);
        //System.out.println(box2.getContent());

        // We have used raw box instead of generic box.
        // If we specify type here : new ArrayList<Box<String>>() then Box<Integer> will show compilation error.
        var list = new ArrayList<Box>();
        list.add(box1);
        list.add(box2);

        // In arrayList if we don't specify type in ArrayList<>() then java infers it to be ArrayList<Object>().
        // So iteration needs "Object box" instead of "Box box". It will show incompatible type error.
        for (Box box : list) {
            System.out.println(box.getContent());
        }
    }

    private static void genericPair() {
        var pair = new Pair<>("Hello World", 2025);
        System.out.println(pair.getKey() + " " + pair.getValue());
    }

    private static void genericMethods() {
        var genericMethods = new GenericMethods();
        genericMethods.printValue("Hello World from generic method");
        Integer[] intArray = {1, 2, 3, 4};
        String[] strArray = {"Hello", "World", "from", "generic methods", "again"};
        genericMethods.printArray(intArray);
        genericMethods.printArray(strArray);
        System.out.printf("First element is : %s", genericMethods.getFirstElement(strArray));

        var genericMethods2 = new GenericMethods2<Integer>();
        var str = "Java";
        System.out.printf("\nReturning value from generic method which has different type than class : %s", genericMethods2.getItem(str));
        genericMethods2.addElement(1);
        genericMethods2.addElement(2);
        genericMethods2.addElement(3);
        genericMethods2.printList();
    }
}

class Box<T> {
    private T content;

    public void setContent(T content) {
        this.content = content;
    }

    public T getContent() {
        return this.content;
    }
}

class Pair<K, V> {
    private K key;
    private V value;

    Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

class GenericMethods {

    //Instance methods can be generic with or without class-level generics.
    //Static methods must declare their own type parameters using <T> before return type if they want to be generic.

    // We use <T> only when the method's type is independent of the class.
    public <T> void printValue(T arg) {
        System.out.println(arg);
    }

    public <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public <T> T getFirstElement(T[] array) {
        // return array.length > 0 ? array[0] : " ";
        // We cannot return string type when type is T. This is because of type safety.
        return array.length > 0 ? array[0] : null;
    }
}

class GenericMethods2<T> {
    private final List<T> contents;

    // Constructor uses T without needing to declare <T> again
    GenericMethods2() {
        this.contents = new ArrayList<>();
    }

    public <U> U getItem(U arg) {
        return arg;
    }

    public void addElement(T element) {
        contents.add(element);
    }

    public void printList() {
        System.out.println("Elements in list : " + contents);
    }
}