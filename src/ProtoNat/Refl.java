/**
 * RasberryPi ProtoNat
 * A very basic control to servo component, eyes animation and very basic AI approach. 
 * Written in Java using Pi4J. A Pi4J is a friendly object-oriented I/O API and implementation 
 * libraries for Java Programmers to access the full I/O capabilities of the Raspberry Pi platform.
 *
 * @author Christopher M. Natan
 */

package ProtoNat;

import java.lang.reflect.Method;

public class Refl{

    public static void main(String[] args) {

        Class cls = null;
        cls = Test.class;
        check(cls);
    }

    private static void check(Class cls) {
        //no paramater
        Class noparams[] = {};

        //String parameter
        Class[] paramString = new Class[1];
        paramString[0] = String.class;

        //int parameter
        Class[] paramInt = new Class[1];
        paramInt[0] = Integer.TYPE;

        try{
            //load the AppTest at runtime

            Object obj = cls.newInstance();

            //call the printIt method
            Method method = cls.getDeclaredMethod("printIt", noparams);
            method.invoke(obj, null);

            //call the printItString method, pass a String param
            method = cls.getDeclaredMethod("printItString", paramString);
            method.invoke(obj, new String("mkyong"));

            //call the printItInt method, pass a int param
            method = cls.getDeclaredMethod("printItInt", paramInt);
            method.invoke(obj, 123);

            //call the setCounter method, pass a int param
            method = cls.getDeclaredMethod("setCounter", paramInt);
            method.invoke(obj, 9997);

            //call the printCounter method
            method = cls.getDeclaredMethod("printCounter", noparams);
            method.invoke(obj, null);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}


