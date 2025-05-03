package sb.designpatterns.corepatterns._1_singleton._4_handlingSerialisationAndDeserialisation;

import java.io.*;

public class Test {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DateUtil dateUtil1 = DateUtil.getInstance();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("./dateUtil.ser")));
        oos.writeObject(dateUtil1);

        DateUtil dateUtil2 = null;
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("./dateUtil.ser")));
        dateUtil2 = (DateUtil) ois.readObject();

        oos.close();
        ois.close();
        System.out.println("Is dateUtil1 == dateUtil2 :" + (dateUtil1 == dateUtil2));
    }
}
