package generics.boundinggenerictypes;

import java.util.ArrayList;
import java.util.List;

public class _2_UpperBoundWildcards {

    public static void main(String[] args) {
        // We already know
        // ArrayList<Number> list = new ArrayList<Integer>(); // Does not compile

        // Instead we can use
        //List<? extends Number> list = new ArrayList<Integer>(); // Compiles, but we cannot add numbers
        // Bust it does not allow us to add number types to it because we don't know what the exact type is.
        // We cannot add anything (like Integer, Double, etc.) to the list because the compiler doesn’t know the specific subtype of Number it holds.
        // The only value we can add to a List<? extends Number> is null.


        // This is fine.
        //List<? extends Number> list = List.of(1,2,3);
        // Or we can use exact type or lower bound wildcards
        List<Number> list = new ArrayList<>();
        list.add(100);
        list.add(200);
        System.out.println(total(list));
    }

    private static long total(List<? extends Number> list) {
        // Here it is acceptable because we know it is either of only one of the subtype of Number or Number itself.
        long count = 0;
        for (Number number : list) {
            count += number.longValue();
        }

        return count;
    }
}

// Upper-bound wildcard says that any class that extends Number or Number itself can be used as the formal parameter type.