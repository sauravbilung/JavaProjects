package generics.boundinggenerictypes;

import java.util.ArrayList;
import java.util.List;

public class _3_UpperBoundWildcards2 {

    public static void main(String[] args) {
        List<? extends Bird> birds = new ArrayList<Bird>();
        //birds.add(new Sparrow()); // Does not compile
        //birds.add(new Bird()); // Does not compile
        // Java does not know the exact type. It could be anything so java does not allow anything to be added.
        // This is the classic "you can read from it but not write to it" behavior with ? extends T.

        List<Flyer> genericFlyers = new ArrayList<>();
        List<HangGlider> hangGliders = new ArrayList<>();
        List<Goose> geese = new ArrayList<>();

        anyFlyer(genericFlyers);
        // anyFlyer(hangGliders); // Does not compile
        // anyFlyer(geese); // Does not compile

        /**
         * Important
         *
         * List<HangGlider> is not a subtype of List<Flyer>, even though HangGlider is a subtype of Flyer.
         *
         * 🤔 Why?
         * This happens because Java generics are invariant.
         *
         * That means:
         * List<A> is not a subtype or supertype of List<B>, unless A == B.
         * Even if A is a subtype of B, List<A> and List<B> are treated as completely unrelated types.
         **/

        groupOfFlyers(genericFlyers);
        groupOfFlyers(hangGliders);
        groupOfFlyers(geese);
    }

    static class Bird {
    }

    static class Sparrow extends Bird {
    }

    interface Flyer {
        void fly();
    }

    class HangGlider implements Flyer {
        @Override
        public void fly() {
        }
    }

    class Goose implements Flyer {
        @Override
        public void fly() {
        }
    }

    private static void anyFlyer(List<Flyer> flyers) {
    }

    private static void groupOfFlyers(List<? extends Flyer> flyers) {
        // Accepts any list who elements are a subtype of flyer.
        // Upper bounds are like anonymous classes in that they use extends regardless of whether we are working with a class or interface.
    }
}

