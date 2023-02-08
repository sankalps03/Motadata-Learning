package Methods;

public class Demo {
    int number;
    String name;

    int getNumber() {
        return number;
    }

    String getName() {
        return name;
    }

    void setNumber(int number) {
        this.number = number;
    }

    void setName(String name) {
        this.name = name;
    }

    public void printDetails() {
        System.out.println("Number: " + number);
        System.out.println("Name: " + name);
    }

    public static void main(String[] args){
        Demo method = new Demo();
        method.setName("Sankalp");
        method.setNumber(22);
        method.printDetails();
    }
}
