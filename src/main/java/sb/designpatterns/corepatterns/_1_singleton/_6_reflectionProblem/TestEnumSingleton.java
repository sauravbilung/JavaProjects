package sb.designpatterns.corepatterns._1_singleton._6_reflectionProblem;

public class TestEnumSingleton {
    public static void main(String[] args) {
        EnumSingletonDemo instance = EnumSingletonDemo.INSTANCE;
        System.out.println(instance.getName());
        instance.setName("Saurav");
        System.out.println(instance.getName());
    }
}
