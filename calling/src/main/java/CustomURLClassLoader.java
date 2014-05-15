/**
 * Created by dbtsai on 5/15/14.
 */

import java.net.URLClassLoader;
import java.net.URL;

public class CustomURLClassLoader extends URLClassLoader {

    public CustomURLClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public void addURL(URL url) {
     super.addURL(url);
   }
}
