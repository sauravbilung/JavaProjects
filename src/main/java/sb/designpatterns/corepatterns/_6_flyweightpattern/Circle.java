package sb.designpatterns.corepatterns._6_flyweightpattern;

public class Circle extends Shape {
    private String label;

    public Circle() {
        label = "circle";
    }

    @Override
    public void draw(int radius, String fillColor, String lineColor) {
        String message = String.format("Drawing a %s with radius %d, fill color %s and line color %s", label, radius, fillColor, lineColor);
        System.out.println(message);
    }
}
