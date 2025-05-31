package generics;

interface Shippable<T> {
    void ship(T t);
}

class ShippableRobotCreate implements Shippable<Robot> {
    @Override
    public void ship(Robot robot) {
    }
}

class ShippableAbstractCrate<U> implements Shippable<U> {
    @Override
    public void ship(U u) {
    }
}

class ShippableCreate implements Shippable {
    @Override
    public void ship(Object o) {
    }
}

record Robot(String name) {
}

/*
Interfaces can use type parameters just like classes.

Three ways to implement a generic interface:

Fixed type: Shippable<Robot>
Generic type: Shippable<U>
Raw type (legacy): Shippable (no type safety)
*/