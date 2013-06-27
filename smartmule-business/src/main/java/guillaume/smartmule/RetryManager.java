package guillaume.smartmule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Guillaume
 */
public class RetryManager {

    protected final Logger logger = getLogger(getClass());
    private int max;

    public RetryManager withMaxRetry(int retry) {
        this.max = retry;
        return this;
    }

    public boolean isStalled(File f) {
        // todo RetryManager - isStalled
        return false ;
    }

    public void onError(File f) {
        // todo RetryManager - onError
    }

}
