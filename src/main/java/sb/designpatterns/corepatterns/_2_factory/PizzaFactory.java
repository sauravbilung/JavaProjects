package sb.designpatterns.corepatterns._2_factory;

public class PizzaFactory {
    public static Pizza createPizza(String type) {
        Pizza pizza = null;
        if (type.equalsIgnoreCase("Cheese")) {
            pizza = new CheesePizza();
        } else if (type.equalsIgnoreCase("veggie")) {
            pizza = new VeggiePizza();
        } else if (type.equalsIgnoreCase("Chicken")) {
            pizza = new CheesePizza();
        }
        return pizza;
    }
}
