package functionalProgramming._1_methodReferences;

public class _1_CallingStaticMethods {

    public static void main(String[] args) {
        Convertor methodRef = Math::round;
        //Convertor lambda = num -> Math.round(num);

        System.out.println(methodRef.round(100.1));
    }
}

interface Convertor {
    long round(double num);
}

/*
* Math.round() is overloaded.
* Java understand which version of method(Math.round()) to call from the abstract method's params.
* It understands the context.
* */