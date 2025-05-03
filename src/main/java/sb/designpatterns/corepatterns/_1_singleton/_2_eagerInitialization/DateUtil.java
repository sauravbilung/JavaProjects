package sb.designpatterns.corepatterns._1_singleton._2_eagerInitialization;

/*
Eager initialization : In eager initialization, an instance of a class is created at the time of class loading.
This is the simplest method but has the disadvantage of creating an instance even before it is used,
potentially causing unnecessary resource consumption if the instance is never used.

Lazy initialization : Lazy initialization delays the creation of an instance until the first time it is needed.
This approach is beneficial for resource-heavy objects or instances whose creation is computationally intensive.
*/
public class DateUtil {
    private static DateUtil instance;

    static {
        instance = new DateUtil();
    }

    private DateUtil() {
    }

    public static DateUtil getInstance() {
        return instance;
    }
}
