package sb.designpatterns.corepatterns._6_flyweightpattern;

public class PaintApp {
    public void render(int numberOfShapes) {
        Shape shape = null;

        for (int i = 1; i <= numberOfShapes; i++) {
            if (i % 2 == 0) {
                shape = ShapeFactory.getShape("circle");
                shape.draw(i, "red", "white");
            } else {
                shape = ShapeFactory.getShape("rectangle");
                shape.draw(i, i + 1, "dotted");
            }
        }
    }
}
