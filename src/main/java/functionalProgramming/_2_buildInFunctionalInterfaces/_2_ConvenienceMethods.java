package functionalProgramming._2_buildInFunctionalInterfaces;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class _2_ConvenienceMethods {

    public static void main(String[] args) {
        //predicatesConvenienceMethods();
        //consumersConvenienceMethods();
        functionsConvenienceMethods();
    }

    private static void predicatesConvenienceMethods() {
        Predicate<String> egg = s -> s.contains("egg");
        Predicate<String> brown = s -> s.contains("brown");

        // Predicates for brown and other colored eggs
        Predicate<String> brownEggs = s -> s.contains("egg") && s.contains("brown");
        Predicate<String> otherEggs = s -> s.contains("egg") && !s.contains("brown");

        // Cleaner way to achieve the same thing
        Predicate<String> brownEggsNeat = egg.and(brown);
        Predicate<String> otherEggsNeat = egg.and(brown.negate());

        var str1 = "brown_eggs";
        var str2 = "white_eggs";
        System.out.println(brownEggsNeat.test(str1)); // true
        System.out.println(otherEggsNeat.test(str2)); // true
        System.out.println(brownEggsNeat.test(str2)); // false
        System.out.println(otherEggsNeat.test(str1)); // false
    }

    private static void consumersConvenienceMethods() {
        Consumer<String> c1 = x -> System.out.print("1: " + x);
        Consumer<String> c2 = x -> System.out.println(", 2: " + x);

        Consumer<String> combined = c1.andThen(c2);
        combined.accept("Annie");
    }

    private static void functionsConvenienceMethods() {
        Function<Integer, Integer> before = x -> x + 1;
        Function<Integer, Integer> after = x -> x * 2;

        Function<Integer, Integer> combined = after.compose(before);
        System.out.println(combined.apply(3));
    }
}
