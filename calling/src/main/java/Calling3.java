import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.lang.reflect.Method;
import java.net.URLClassLoader;

/**
 * Created by dbtsai on 5/15/14.
 */
public class Calling3 {

    /**
     * Parameters of the method to add an URL to the System classes.
     */
    private static final Class<?>[] parameters = new Class[]{URL.class};

    /**
     * Adds a file to the classpath.
     *
     * @param s a String pointing to the file
     * @throws IOException
     */
    public static void addFile(String s) throws IOException {
        File f = new File(s);
        addFile(f);
    }

    /**
     * Adds a file to the classpath
     *
     * @param f the file to be added
     * @throws IOException
     */
    public static void addFile(File f) throws IOException {
        addURL(f.toURI().toURL());
    }

    /**
     * Adds the content pointed by the URL to the classpath.
     *
     * @param u the URL pointing to the content to be added
     * @throws IOException
     */
    public static void addURL(URL u) throws IOException {
        URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
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
        System.out.println("Adding jar: " + jar);
        addURL(jar);

        Called called = new Called();

        String result = called.calledReturn();

        System.out.println("\nGoing to call `calledReturn` in added jar\n" + result);
    }

    public static void main(String[] args) throws Exception {
        Calling3 calling = new Calling3();
        calling.test(args[0]);
    }
}