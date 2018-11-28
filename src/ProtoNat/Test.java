/**
 * RasberryPi ProtoNat
 * A very basic control to servo component, eyes animation and very basic AI approach. 
 * Written in Java using Pi4J. A Pi4J is a friendly object-oriented I/O API and implementation 
 * libraries for Java Programmers to access the full I/O capabilities of the Raspberry Pi platform.
 *
 * @author Christopher M. Natan
 */
package ProtoNat;

public class Test{

    private int counter;

    public void printIt(){
        System.out.println("printIt() no param");
    }

    public void printItString(String temp){
        System.out.println("printIt() with param String : " + temp);
    }

    public void printItInt(int temp){
        System.out.println("printIt() with param int : " + temp);
    }

    public void setCounter(int counter){
        this.counter = counter;
        System.out.println("setCounter() set counter to : " + counter);
    }

    public void printCounter(){
        System.out.println("printCounter() : " + this.counter);
    }
}