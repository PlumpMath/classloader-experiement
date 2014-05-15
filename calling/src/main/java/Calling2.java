import java.io.File;
import java.net.URL;
import java.lang.reflect.Method;
/**
 * Created by dbtsai on 5/15/14.
 */
public class Calling2 {

    public void test(String file) throws Exception {
        ClassLoader loader = this.getClass().getClassLoader();

        CustomURLClassLoader customLoader = new CustomURLClassLoader(new URL[0], loader);
        URL jar = new File(file).toURI().toURL();
        System.out.println("Adding jar: " + jar);
        customLoader.addURL(jar);

        Class Called = customLoader.loadClass("Called");

        Object called = Called.newInstance();

        Method calledReturn = Called.getDeclaredMethod("calledReturn");

        String result = calledReturn.invoke(called).toString();

        System.out.println("\nGoing to call `calledReturn` in added jar\n" + result);
    }
    public static void main(String[] args) throws Exception {
        Calling2 calling = new Calling2();
        calling.test(args[0]);
    }
}