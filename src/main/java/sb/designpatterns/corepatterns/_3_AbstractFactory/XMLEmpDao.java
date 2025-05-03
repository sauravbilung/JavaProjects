package sb.designpatterns.corepatterns._3_AbstractFactory;

public class XMLEmpDao implements Dao {

    @Override
    public void save() {
        System.out.println("Saving Employee to XML");
    }
}
