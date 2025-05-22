package functionalProgramming._2_buildInFunctionalInterfaces;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.*;

public class _1_UsingFunctionalInterfaces {

    public static void main(String[] args) {

        //suppliers();
        // consumers();
        //predicates();
        //functions();
        operators();
    }

    private static void suppliers() {
        Supplier<Double> randomNumber = Math::random;
        Supplier<Double> randomNumber2 = () -> Math.random();
        System.out.println("Random number 1: " + randomNumber.get());
        System.out.println("Random number 1: " + randomNumber2.get());

        Supplier<LocalDate> s1 = LocalDate::now;
        Supplier<LocalDate> s2 = () -> LocalDate.now();
        System.out.println("Local date now : " + s1.get());
        System.out.println("Local date now : " + s2.get());

        Supplier<StringBuilder> s3 = StringBuilder::new;
        Supplier<StringBuilder> s4 = () -> new StringBuilder();
        System.out.println("Empty String : " + s3.get());
        System.out.println("Empty String : " + s4.get());

        Supplier<ArrayList<String>> s5 = ArrayList::new;
        Supplier<ArrayList<String>> s6 = () -> new ArrayList<>();
        System.out.println("Empty arraylist : " + s5.get());
        System.out.println("Empty arraylist : " + s6.get());
    }

    private static void consumers() {
        Consumer<String> greet = name -> System.out.println("Hello, " + name + "!");
        //Consumer<String> greet2 = System.out::println;
        greet.accept("Superman");

        BiConsumer<String, Integer> printAge = (name, age) -> System.out.println(name + " is " + age + " years old.");
        printAge.accept("Wonder Woman", 1000);

        var map = new HashMap<String, Integer>();
        BiConsumer<String, Integer> b1 = map::put;
        BiConsumer<String, Integer> b2 = (k, v) -> map.put(k, v);

        b1.accept("chicken", 7);
        b2.accept("chick", 1);
        System.out.println(map);
    }

    private static void predicates() {
        var str = "";
        Predicate<String> p1 = String::isEmpty;
        Predicate<String> p2 = s -> s.isEmpty();
        System.out.printf("\nIs \"%s\" empty ? %b ", str, p1.test(str));
        System.out.printf("\nIs \"%s\" empty ? %b ", str, p2.test(str));

        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.printf("\nIs 10 even ? %b", isEven.test(10));

        str = "Apple";
        var p = "Ap";
        BiPredicate<String, String> p3 = String::startsWith;
        BiPredicate<String, String> p4 = (string, prefix) -> string.startsWith(prefix);
        System.out.printf("\nDoes string \"%s\" starts with prefix \"%s\" ? %b", str, p, p3.test(str, p));
        System.out.printf("\nDoes string \"%s\" starts with prefix \"%s\" ? %b", str, p, p4.test(str, p));
    }

    private static void functions() {

        var str = "java";
        Function<String, Integer> f1 = String::length;
        Function<String, Integer> f2 = string -> string.length();
        System.out.printf("\nLength of string \"%s\" : %d", str, f1.apply(str));
        System.out.printf("\nLength of string \"%s\" : %d", str, f2.apply(str));

        var str2 = "_programming";
        BiFunction<String, String, String> f3 = String::concat;
        BiFunction<String, String, String> f4 = (string1, string2) -> string1.concat(string2);
        System.out.printf("\n%s + %s = %s", str, str2, f3.apply(str, str2));
        System.out.printf("\n%s + %s = %s", str, str2, f4.apply(str, str2));
    }

    private static void operators() {
        var str = "java";
        UnaryOperator<String> u1 = String::toUpperCase;
        UnaryOperator<String> u2 = string -> string.toUpperCase();
        System.out.println(u1.apply(str));
        System.out.println(u2.apply(str));

        var str2 = "_programming";
        BinaryOperator<String> b1 = String::concat;
        BinaryOperator<String> b2 = (string, string2) -> string.concat(string2);
        System.out.println(b1.apply(str, str2));
        System.out.println(b2.apply(str, str2));
    }
}
