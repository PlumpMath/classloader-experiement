import java.io.File;
import java.net.URL;

/**
 * Created by dbtsai on 5/15/14.
 */
public class Calling1 {

    public void test(String file) throws Exception {
        ClassLoader loader = this.getClass().getClassLoader();

        CustomURLClassLoader customLoader = new CustomURLClassLoader(new URL[0], loader);
        URL jar = new File(file).toURI().toURL();
        System.out.println("Adding jar: " + jar);
        customLoader.addURL(jar);

        // This doesn't work since the customLoader will not be used by default.
        Called called = new Called();

        String result = called.calledReturn();

        System.out.println("\nGoing to call `calledReturn` in added jar\n" + result);
    }
    public static void main(String[] args) throws Exception {
        Calling1 calling = new Calling1();
        calling.test(args[0]);
    }
}
