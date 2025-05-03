package sb.designpatterns.corepatterns._2_factory;

public class PizzaStore {
    public Pizza orderPizza(String type) {
        Pizza pizza = PizzaFactory.createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        return pizza;
    }
}
