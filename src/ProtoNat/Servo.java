/**
 * RasberryPi ProtoNat
 * A very basic control to servo component, eyes animation and very basic AI approach. 
 * Written in Java using Pi4J. A Pi4J is a friendly object-oriented I/O API and implementation 
 * libraries for Java Programmers to access the full I/O capabilities of the Raspberry Pi platform.
 *
 * @author Christopher M. Natan
 */

package ProtoNat;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Servo {

    public void setPins(String pins) {
        String execute = "sudo /home/pi/Desktop/Resources/ServoBlaster/user/servod --p1pins=" + pins;
        this.shell(execute);
    }

    public void setNeutral() {
        String execute = "echo 0=150 > /dev/servoblaster";
        this.shell(execute);
    }

    public void setTo(String value) {
        String execute = "echo 0=" + Integer.parseInt(value) + " > /dev/servoblaster";
        this.shell(execute);
    }

    public void setSleep(String value) {
        try {
            print("echo sleep " + value);
            Thread.sleep(Integer.parseInt(value));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String shell(String execute) {
        print(execute);
        return execute;
    }

    private String shellx(String command) {
        StringBuffer output = new StringBuffer();
        Process p;

        try {
            String[] exec = {"sh", "-c", command};
            p = Runtime.getRuntime().exec(exec);

            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            reader.close();
            p.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();
    }

    private static void print(Object obj) {
        System.out.println(obj);
    }
    private static void print(String str) {
        System.out.println(str);
    }
}