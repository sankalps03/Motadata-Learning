package Feb16;

class Employee{
    private String name;
    private int empID;

    public Employee(){

        this("NULL");
    }

    public Employee(String name){

        this(name, 0);
    }

    public Employee(String name, int empID){

        this.name = name;

        this.empID = empID;
    }

    public String getName(){

        return name;
    }

    public int getEmpID(){

        return empID;
    }
}
public class ConstructorChaining {

    public static void main(String args[]) {

        Employee employee = new Employee();

        System.out.println("Employee Name: " + employee.getName());

        System.out.println("Employee ID: " + employee.getEmpID());
    }

}
