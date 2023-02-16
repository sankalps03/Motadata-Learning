package Feb16;

abstract class MotorBike {
    abstract void brake();
}

class SportsBike extends MotorBike {

    // implementation of abstract method
    public void brake() {

        System.out.println("SportsBike Brake");
    }
}

class MountainBike extends MotorBike {

    public void brake() {

        System.out.println("MountainBike Brake");
    }
}
public class Abstraction {
    public static void main(String[] args) {

        MountainBike mBike = new MountainBike();

        mBike.brake();

        SportsBike sBike = new SportsBike();

        sBike.brake();
    }

}
