package sb.designpatterns.corepatterns._3_AbstractFactory;

public class Test {
    public static void main(String[] args) {
        DAOAbstractFactory daoAbstractFactory = DaoFactoryProducer.produce("db");
        Dao dao = daoAbstractFactory.createDao("emp");
        dao.save();
    }
}
