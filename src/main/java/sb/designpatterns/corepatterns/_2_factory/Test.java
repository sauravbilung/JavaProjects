package sb.designpatterns.corepatterns._2_factory;

public class Test {
    public static void main(String[] args) {
        PizzaStore pizzaStore = new PizzaStore();
        // Types : chicken, veggie and cheese
        pizzaStore.orderPizza("cheese");
    }
}
