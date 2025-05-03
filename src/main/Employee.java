public class Employee {

    // Public static constants
    public static final int DEFAULT_SALARY = 50000;

    // Private static variables
    private static int totalEmployees = 0;

    // Private instance variables
    private String name;
    private int age;
    private int salary;

    // Constructor
    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
        this.salary = DEFAULT_SALARY;
        totalEmployees++;
    }

    // Public functions
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    // Private utility function
    private boolean isRetirementEligible() {
        return age >= 60;
    }

    // Public function utilizing private utility
    public String getRetirementStatus() {
        if (isRetirementEligible()) {
            return "Eligible for retirement";
        } else {
            return "Not yet eligible for retirement";
        }
    }

    // Public static function
    public static int getTotalEmployees() {
        return totalEmployees;
    }
}