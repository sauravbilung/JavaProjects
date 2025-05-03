package sb.designpatterns.corepatterns._1_singleton._6_reflectionProblem;

import sb.designpatterns.corepatterns._1_singleton._5_cloneable.DateUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectionProblem {

    public static void main(String[] args) {
        DateUtil instance1 = DateUtil.getInstance();
        DateUtil instance2= null;

        Constructor[] constructors = DateUtil.class.getDeclaredConstructors();
        for(Constructor constructor : constructors){
            constructor.setAccessible(true);
            try{
                instance2 = (DateUtil) constructor.newInstance();
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
    }
}
