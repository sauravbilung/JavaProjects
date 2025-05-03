package sb.designpatterns.corepatterns._1_singleton._5_cloneable;

import java.io.Serializable;

public class DateUtil implements Serializable, Cloneable {
    private static volatile DateUtil instance;

    private DateUtil() {
    }

    public static DateUtil getInstance() {
        if (instance == null) {
            synchronized (DateUtil.class) {
                if (instance == null) {
                    instance = new DateUtil();
                }
            }
        }
        return instance;
    }

    protected Object readResolve() {
        return instance;
    }

    /*
     Cloning is used to create and return a copy of the object it is called on. Recall shallow copy and deep copy. They use clone().

     Here we are explicitly throwing a CloneNotSupportedException. This is a safeguard to ensure the Singleton pattern is not violated through cloning,
     which could otherwise create a new instance of the Singleton class, just like serialization without proper handling could.
    */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
