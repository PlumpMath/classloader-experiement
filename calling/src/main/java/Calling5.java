import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.lang.reflect.Method;
import java.net.URLClassLoader;

/**
 * Created by dbtsai on 5/15/14.
 */
public class Calling5 {

    private final Class<?>[] parameters = new Class[]{URL.class};

    public  void addURL(URL u) throws IOException {
        URLClassLoader sysloader = (URLClassLoader) Thread.currentThread().getContextClassLoader();// this.getClass().getClassLoader();

        System.out.println("Thread Loader : " + sysloader.toString() );
        System.out.println("Class Loader : " +this.getClass().getClassLoader() );

        Class<?> sysclass = URLClassLoader.class;
        try {
            Method method = sysclass.getDeclaredMethod("addURL", parameters);
            method.setAccessible(true);
            method.invoke(sysloader, new Object[]{u});
        } catch (Throwable t) {
            t.printStackTrace();
            throw new IOException("Error, could not add URL to system classloader");
        }
    }

    public void test(String file) throws Exception {
        URL jar = new File(file).toURI().toURL();
        System.out.println("Thread Loader : sdfdddddsfdsf");

        System.out.println("Adding jar: " + jar);
        System.out.println("Thread Loader : sdfdsssssfdsf");

        addURL(jar);
        System.out.println("Thread Loader : sdfdsfdsf");

        Called called = new Called();

        String result = called.calledReturn();

        System.out.println("\nGoing to call `calledReturn` in added jar\n" + result);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Class: Calling5\n");

        System.out.println("Thread Loader : " + Thread.currentThread().getContextClassLoader().toString() );
        System.out.println("Class Loader : " + Calling5.class.getClassLoader() );


        Calling3 calling = new Calling3();
        calling.test(args[0]);
    }
}