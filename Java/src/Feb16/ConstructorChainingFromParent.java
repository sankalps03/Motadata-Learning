package Feb16;

class Vehicle{
    private String name;
    private String type;

    public Vehicle(String name, String type) {

        this.name = name;

        this.type = type;

        System.out.println("Vehicle constructor invoked!");
    }

    public String getName(){

        return name;
    }

    public String getType(){

        return type;
    }
}


class Car extends Vehicle{
    private int doors;
    private int wheels;

    public Car(String name, String type, int doors, int wheels){

        super(name, type);

        this.doors = doors;

        this.wheels = wheels;

        System.out.println("Car constructor invoked!");
    }

    public int getDoors(){

        return doors;
    }

    public int getWheels(){

        return wheels;
    }
}

class WagonR extends Car{
    private int seats;

    public WagonR(String name, String type, int doors, int wheels, int seats){

        super(name, type, doors, wheels);

        this.seats = seats;

        System.out.println("WagonR constructor invoked!");
    }

    public int getSeats(){

        return seats;
    }
}
public class ConstructorChainingFromParent {

    public static void main(String args[]) {

        WagonR car1 = new WagonR("WagonR 2022", "Automobile", 4, 4, 5);

        System.out.println("Vehicle name: " + car1.getName());

        System.out.println("Vehicle type: " + car1.getType());

        System.out.println("Car doors: " + car1.getDoors());

        System.out.println("Car wheels: " + car1.getWheels());

        System.out.println("WagonR seats: " + car1.getSeats());
    }

}
