package sb.designpatterns.corepatterns._1_singleton._1_creatingSingletonClass;

/*
1. Declare the constructor of the class as private.
2. Declare static method.
3. Declare a static member of the same class type in the class.
*/
public class DateUtil {

    private static DateUtil instance;

    private DateUtil() {
    }

    public static DateUtil getInstance() {
        if (instance == null) {
            instance = new DateUtil();
        }
        return instance;
    }
}
