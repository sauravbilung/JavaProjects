package sb.designpatterns.corepatterns._1_singleton._4_handlingSerialisationAndDeserialisation;

import java.io.Serializable;

public class DateUtil implements Serializable {
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

    /*
    The readResolve method is part of the serialization mechanism defined in the Java platform,
    and its invocation is built into the Java Object Serialization Specification.

    How It Works:
    1.During Deserialization: ObjectInputStream reads the serialized object data from the input stream.
    2.Object Reconstruction: The object is reconstructed without calling any constructors (for serialized objects).
    3.readResolve Invocation: Before the deserialized object is returned to the caller, ObjectInputStream checks if
    the class of the deserialized object has a readResolve method. If such a method is present and accessible, it is invoked.
    4.Object Replacement: The object returned by readResolve is then returned to the caller, effectively replacing the newly created deserialized object with the object returned by readResolve.

    The readResolve method is not part of any class or interface that user-defined classes must implement (like Serializable or Externalizable).
    Instead, it is a "magic method" that the serialization mechanism looks for by reflection.

    Note : readResolve's purpose is not to merge or reconcile the state of the deserialized object with the existing singleton instance.
    Instead, its purpose is to ensure that the singleton instance is returned upon deserialization, effectively discarding the newly created deserialized object.
    */
    protected Object readResolve() {
        return instance;
    }
}
