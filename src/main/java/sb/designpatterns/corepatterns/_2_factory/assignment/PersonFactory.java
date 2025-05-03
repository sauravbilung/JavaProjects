package sb.designpatterns.corepatterns._2_factory.assignment;

public class PersonFactory {
    public static Person createPerson(String gender) {
        Person person = null;

        if(gender.equalsIgnoreCase("male")){
            person = new Male();
        } else if (gender.equalsIgnoreCase("Female")) {
            person = new Female();
        }
        return person;
    }
}
