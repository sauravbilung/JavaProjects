package sb.designpatterns.corepatterns._1_singleton._3_multiThreadsHandling;

public class Test {

    public static void main(String[] args) {
        DateUtil dateUtil1 = DateUtil.getInstance();
        DateUtil dateUtil2 = DateUtil.getInstance();
        System.out.println("Is dateUtil1 == dateUtil2 :" + (dateUtil1 == dateUtil2));
    }
}
