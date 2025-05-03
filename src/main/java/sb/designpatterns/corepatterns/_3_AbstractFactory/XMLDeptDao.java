package sb.designpatterns.corepatterns._3_AbstractFactory;

public class XMLDeptDao implements Dao {

    @Override
    public void save() {
        System.out.println("Saving Department to XML");
    }
}
