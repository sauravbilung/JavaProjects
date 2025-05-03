package sb.designpatterns.corepatterns._1_singleton._6_reflectionProblem;

public enum EnumSingletonDemo {
    INSTANCE;
    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
