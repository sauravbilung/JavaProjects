package functionalProgramming._1_methodReferences;

public class _2_CallingInstanceMethods {

    /*
     * Calling instance metod on a particular object
     */
    public static void main(String[] args) {

        situation1();
        situation2();
        situation3();
        situation4();
    }

    /**
     * Functional interface with a method that take params and returns a value.
     * ✅ Used when we already know the object, and we are passing additional params.
     */
    private static void situation1() {
        var str = "zoo";
        var prefix = "A";
        StringStart methodRef = str::startsWith;
        //StringStart lambda = s -> str.startsWith(s);
        System.out.printf("\nDoes string %s starts with prefix %s : %b", str, prefix, methodRef.beginningCheck(prefix));
    }

    /**
     * Functional interface with a method that doesn't take any params and returns a value.
     * ✅ Used when method has no parameters and is tied to a known instance.
     */
    private static void situation2() {
        var str = "";
        StringChecker methodRef = str::isEmpty;
        //StringChecker lambda = () -> str.isEmpty();
        System.out.printf("\nIs string \"%s\" empty ? %b", str, methodRef.check());
    }

    /**
     * ✅ Used when the instance (receiver) is passed as a parameter and not fixed.
     **/
    private static void situation3() {
        var str = "zoo";
        StringParameterChecker methodRef = String::isEmpty;
        //StringParameterChecker lambda = s -> s.isEmpty();
        System.out.printf("\nIs string \"%s\" empty ? %b", str, methodRef.check(str));
    }

    /**
     * ✅ Used when the instance (receiver) and other arguments are provided as parameters,
     * meaning the receiver is not fixed at the time of creating the method reference.
     */

    private static void situation4() {
        var str = "Zoo";
        var prefix = "Z";

        StringTwoParameterChecker methodRef = String::startsWith;
        //StringTwoParameterChecker lambda = (s, p) -> s.startsWith(p);
        System.out.printf("\nDoes string %s starts with prefix %s : %b", str, prefix, methodRef.check(str, prefix));
    }
}

interface StringStart {
    boolean beginningCheck(String prefix);
}

interface StringChecker {
    boolean check();
}

interface StringParameterChecker {
    boolean check(String text);
}

interface StringTwoParameterChecker {
    boolean check(String text, String prefix);
}

/*
*
| Style                  | Syntax               | When to Use                        |
| ---------------------- | -------------------- | ---------------------------------- |
| object::instanceMethod | `str::startsWith`    | Object known in advance            |
| object::instanceMethod | `str::isEmpty`       | Object known, no parameters needed |
| Class::instanceMethod  | `String::isEmpty`    | Object passed as 1st parameter     |
| Class::instanceMethod  | `String::startsWith` | Object and other arguments passed  |
*
*
* In situations 3 and 4. It looks like we are using static methods, but we are not.
* isEmpty(), startsWith() are instance methods and not static methods.
* Java uses the parameter supplied at runtime as the instance on which the method is called.
*/