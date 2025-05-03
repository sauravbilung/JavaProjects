package sb.designpatterns.corepatterns._3_AbstractFactory;

public abstract class DAOAbstractFactory {
    public abstract Dao createDao(String type);
}
