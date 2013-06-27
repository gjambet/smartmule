
package guillaume.tomcat.companion;

import guillaume.tomcat.companion.FilePlaceholder;

import java.io.File;
import java.text.MessageFormat;

import static java.text.MessageFormat.format;

/**
 * @author Guillaume
 */
public class VarFactory {

    public FilePlaceholder getRessource(String var) {

        String path = System.getenv(var);

        if (path == null) {
            throw new IllegalArgumentException("missing property : " + var);
        }

        File f = new File(path);
        if (f.exists()) {
            return new FilePlaceholder().forPath(f.getAbsolutePath());
        }

        throw new IllegalArgumentException(format("File {0} does not exist", f.getAbsolutePath()));

    }
}
