package Feb23.StackImplementation;

import Feb23.ImplementationSinglyLL.Employee;

public class Main {
    public static void main(String[] args) {

        try {

            Employee janeJones = new Employee("Jane", "Jones", 123);

            Employee johnDoe = new Employee("John", "Doe", 4567);

            Employee marySmith = new Employee("Mary", "Smith", 22);

            Employee mikeWilson = new Employee("Mike", "Wilson", 3245);

            Employee billEnd = new Employee("Bill", "End", 78);

            LinkedStack stack = new LinkedStack();

            stack.push(janeJones);

            stack.push(johnDoe);

            stack.push(marySmith);

            stack.push(mikeWilson);

            stack.push(billEnd);

            stack.printStack();

            System.out.println("peek: "+stack.peek());

            stack.printStack();

            System.out.println("Popped: " + stack.pop());

            System.out.println(stack.peek());

        }catch (Exception e){

            e.printStackTrace();
        }
    }
}
