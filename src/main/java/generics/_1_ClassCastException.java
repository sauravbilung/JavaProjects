package generics;

import java.util.ArrayList;
import java.util.List;

public class _1_ClassCastException {
    public static void main(String[] args) {
        //exception1();
        //exception2();
        notException1();
    }

    private static void exception1() {
        List list = new ArrayList();
        list.add("Hello");
        list.add(123);
        String result = (String) list.get(1);
        System.out.println(result);
    }

    private static void exception2() {
        List list = new ArrayList<>(); // <> is ignored here.
        list.add(new StringBuilder("Webby"));
        String result = (String) list.get(0);
        System.out.println(result);
    }

    private static void notException1() {
        Object obj = "Hello world";
        List list = new ArrayList();
        list.add(obj);
        String result = (String) list.get(0);
        System.out.println(result);
    }
}
