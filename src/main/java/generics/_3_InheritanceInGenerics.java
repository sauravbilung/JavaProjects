package generics;

public class _3_InheritanceInGenerics {

    public static void main(String[] args) {

        var word = "Java";
        var word2 = "Hello World";

        Test<String> test = (str) -> {
            System.out.println("Initializing generic functional interface, input value : " + str);
        };

        test.print(word2);

        Test2<String> test2 = () -> {
            System.out.println("Initializing functional interface with no method parameters");
        };

        test2.print();

        var crateProcessor = new CrateProcessor();
        crateProcessor.set(word);
        crateProcessor.process(word2);
        System.out.printf("Fetching word from crate : %s", crateProcessor.get());
    }
}

interface Crate<T> {

    void set(T arg);
    T get();
}

interface DerivedCrate<T> extends Crate<T> {
    void process(T arg);
}

interface Test<T> {
    void print(T arg);
}

interface Test2<T> {
    void print();
}

class CrateProcessor implements DerivedCrate<String> {

    String word;

    @Override
    public void process(String arg) {
        System.out.println("Processing word : " + arg);
    }

    @Override
    public void set(String arg) {
        word = arg;
    }

    @Override
    public String get() {
        return word;
    }
}