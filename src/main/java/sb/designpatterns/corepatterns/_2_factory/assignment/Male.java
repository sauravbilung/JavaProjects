package sb.designpatterns.corepatterns._2_factory.assignment;

public class Male implements Person {
    @Override
    public void wish(String message) {
        System.out.println(message);
    }
}
