package generics.boundinggenerictypes;

import java.util.ArrayList;
import java.util.List;

class _1_UnboundedWildcards {

    public static void main(String[] args) {
        List<String> keywords = new ArrayList<>();
        keywords.add("java");
        //printList(keywords); Does not compile

        // But wait string is a subclass of an object. Here Java is trying to protect us from ourselves.
        // We could write code like this :
        List<Integer> numbers = new ArrayList<>();
        numbers.add(42);
        // List<Object> objects = numbers   // does not compile
        // objects.add("forty two");        // does not compile
        // System.out.println(numbers.get(1));

        // by doing this we are breaking the promise that list can only be of integers so java is protecting us from it.
        // We can define a list of whatever using ? (unbounded wildcard)
        printList2(keywords);


        // also, remember
        List<?> x1 = new ArrayList<>();
        var x2 = new ArrayList<>();
        // They are not equivalent. x1 is of type List and x2 is of type ArrayList. We can assign x2 to x1 but not vice versa.
        // But both returns object when we call get().
    }

    public static void printList(List<Object> list) {
        for (Object x : list) {
            System.out.println(x);
        }
    }

    public static void printList2(List<?> list) {
        for (Object x : list) {
            System.out.println(x);
        }
    }
}
