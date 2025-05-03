package sb.designpatterns.corepatterns._4_templateMethodPattern;

/*
* A Template method provides a standard base class method.
*/
public class Test {
    public static void main(String[] args) {
        DataRenderer renderer = new XMLDataRenderer();
        renderer.render();
    }
}
