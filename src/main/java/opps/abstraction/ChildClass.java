package opps.abstraction;

public class ChildClass extends ParentClass implements ParentInterface, ChildInterface {

    @Override
    void printAbs() {
        System.out.println("this abstract method");
    }

    public void print3() {
        System.out.println("this child class method");
    }

    @Override
    public void print1() {
        System.out.println("this parent interface method");

    }

    @Override
    public void print2() {
        System.out.println("this child interface method");

    }
}
