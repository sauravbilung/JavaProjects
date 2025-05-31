package generics;

import java.util.ArrayList;
import java.util.List;

class Mammal {
    public List<CharSequence> play() {
        return List.of("Empty char sequence");
    }

    public CharSequence sleep() {
        return "CharSequence";
    }
}

class Monkey extends Mammal {
    public ArrayList<CharSequence> play() {
        return new ArrayList<>();
    }
}

class Goat extends Mammal {
    //public List<String> play() {} Does not compile
    public String sleep(){
        return "string";
    }
}

/*
* The return type of overridden method must be the same or a subtype (covariant) of the return type in the superclass.
* However, when the return type uses generics, the full generic type must match.
*/