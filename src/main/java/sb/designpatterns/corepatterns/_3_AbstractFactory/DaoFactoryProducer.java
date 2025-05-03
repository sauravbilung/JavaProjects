package sb.designpatterns.corepatterns._3_AbstractFactory;

public class DaoFactoryProducer {
    public static DAOAbstractFactory produce(String factoryType) {
        DAOAbstractFactory daoAbstractFactory = null;

        if(factoryType.equalsIgnoreCase("xml")){
            daoAbstractFactory = new XMLDAOFactory();
        } else if(factoryType.equalsIgnoreCase("DB")) {
            daoAbstractFactory = new DBDAOFactory();
        }
        return daoAbstractFactory;
    }
}
