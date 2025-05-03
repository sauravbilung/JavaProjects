package sb.designpatterns.corepatterns._6_flyweightpattern;

public class Rectangle extends Shape {
    private String label;

    public Rectangle() {
        label = "Rectangle";
    }

    @Override
    public void draw(int length, int breadth, String fillStyle) {
        String message = String.format("Drawing a %s with length %d, breadth %d and fill style %s", label, length, breadth, fillStyle);
        System.out.println(message);
    }
}
