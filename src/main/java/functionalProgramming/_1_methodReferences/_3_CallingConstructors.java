package functionalProgramming._1_methodReferences;

public class _3_CallingConstructors {
    public static void main(String[] args) {
        situation1();
        situation2();
    }

    private static void situation1() {
        EmptyStringCreator methodRef = String::new;
        //EmptyStringCreator lambda = () -> new String();

        var str = methodRef.create();
        System.out.println(str.equals("snake"));
    }

    private static void situation2() {
        StringCopier methodRef = String::new;
        //StringCopier lambda = str -> new String(str);

        var str = methodRef.copy("Zebra");
        System.out.println(str.equals("Zebra"));
    }
}

interface EmptyStringCreator {
    String create();
}

interface StringCopier {
    String copy(String value);
}

// A constructor reference is a special type of method reference that uses new instead of a method and instantiates an object.

/*
| Interface Method Type   | Constructor Used       | Reference Syntax |
| ----------------------- | ---------------------- | ---------------- |
| `String create()`       | `new String()`         | `String::new`    |
| `String copy(String s)` | `new String(String s)` | `String::new`    |

Even though both use String::new, the correct constructor is chosen based on functional interface signature (parameter count and types).
*/