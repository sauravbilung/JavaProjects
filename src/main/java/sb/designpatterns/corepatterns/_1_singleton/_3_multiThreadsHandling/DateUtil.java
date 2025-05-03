package sb.designpatterns.corepatterns._1_singleton._3_multiThreadsHandling;

public class DateUtil {

    // volatile keyword ensures that changes to the instance variable are immediately visible to all threads.
    // It also prevents the compiler from making optimizations that could cause a thread to see a partially constructed instance of DateUtil.
    private static volatile DateUtil instance;

    private DateUtil() {
    }

    public static DateUtil getInstance() {
        if (instance == null) {
            synchronized (DateUtil.class) {
                if (instance == null) {
                    // without this check the waiting threads might create a new instance again, violating the singleton principle.
                    instance = new DateUtil();
                }
            }
        }
        return instance;
    }
}
