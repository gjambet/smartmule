package guillaume.smartmule;

import java.lang.Exception;
import java.lang.String;
import java.lang.Throwable;

/**
 *
 * @author Guillaume
 */
public class ConfigurationException extends Exception {

    public ConfigurationException(String message) {
        super(message);
    }

    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
