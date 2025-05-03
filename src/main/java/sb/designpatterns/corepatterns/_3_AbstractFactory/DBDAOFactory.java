package sb.designpatterns.corepatterns._3_AbstractFactory;

public class DBDAOFactory extends DAOAbstractFactory {

    @Override
    public Dao createDao(String type) {
        Dao dao = null;
        if (type.equalsIgnoreCase("emp")) {
            dao = new DBEmpDao();
        } else if (type.equalsIgnoreCase("dept")) {
            dao = new DBDeptDao();
        }
        return dao;
    }
}
