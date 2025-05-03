package sb.designpatterns.corepatterns._3_AbstractFactory;

public class XMLDAOFactory extends DAOAbstractFactory {

    @Override
    public Dao createDao(String type) {
        Dao dao = null;
        if (type.equalsIgnoreCase("emp")) {
            dao = new XMLEmpDao();
        } else if (type.equalsIgnoreCase("dept")) {
            dao = new XMLDeptDao();
        }
        return dao;
    }
}
