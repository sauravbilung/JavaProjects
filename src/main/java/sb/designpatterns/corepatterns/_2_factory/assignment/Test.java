package sb.designpatterns.corepatterns._2_factory.assignment;

public class Test {
    public static void main(String[] args) {
        Person person = PersonFactory.createPerson("male");
        person.wish("I am born a male");
    }
}
