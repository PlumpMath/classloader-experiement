import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;

/**
 * Created by dbtsai on 5/15/14.
 */
public class Calling4 {

    public void classLoader(String file) throws Exception {
        ClassLoader loader = this.getClass().getClassLoader();

        CustomURLClassLoader customLoader = new CustomURLClassLoader(new URL[0], loader);
        URL jar = new File(file).toURI().toURL();
        System.out.println("Adding jar: " + jar);
        customLoader.addURL(jar);
        //Thread.currentThread().setContextClassLoader(customLoader);

        Class CallingClass =  customLoader.loadClass("Calling4");

        Object calling = CallingClass.newInstance();

        Method testMethod = CallingClass.getDeclaredMethod("test");

        testMethod.invoke(calling);



        Class Called = customLoader.loadClass("Called");

        Object called = Called.newInstance();

        Method calledReturn = Called.getDeclaredMethod("calledReturn");

        String result = calledReturn.invoke(called).toString();

        System.out.println("\nGoing to call `calledReturn` in added jar\n" + result);

        System.out.println("ClassLoader in `classloader`" + customLoader);
        System.out.println("1: " + customLoader.getParent());
        System.out.println("2: " + customLoader.getParent().getParent().getParent());
    }

    public void test() throws Exception {
        //Called called = new Called();

        //String result = called.calledReturn();
        System.out.println("In test function");
        System.out.println("ClassLoader in `test`" + this.getClass().getClassLoader());
        System.out.println("1: " +  this.getClass().getClassLoader().getParent());
      //  System.out.println("1: " +  this.getClass().getClassLoader().getParent());

//
//        Class Called = this.getClass().getClassLoader().loadClass("Called");
//
//        Object called = Called.newInstance();
//
//        Method calledReturn = Called.getDeclaredMethod("calledReturn");
//
//        String result = calledReturn.invoke(called).toString();
//
//        System.out.println("\nGoing to call `calledReturn` in added jar\n" + result);


        //System.out.println("\nGoing to call `calledReturn` in added jar\n" + result);
//
//
//        Class Called = this.getClass().getClassLoader().loadClass("Called");
//
//        Object called = Called.newInstance();
//
//        Method calledReturn = Called.getDeclaredMethod("calledReturn");
//
//        String result = calledReturn.invoke(called).toString();
//
//        System.out.println("\nGoing to call `calledReturn` in added jar\n" + result);


    }
    public static void main(String[] args) throws Exception {
        Calling4 calling = new Calling4();

        calling.classLoader(args[0]);
    }
}
