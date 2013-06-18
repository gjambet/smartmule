package guillaume.smartmule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * User: Guillaume
 * Date: 31/08/12
 * Time: 00:38
 */
public class RetryManager {

    private final Logger logger;

    private int max;


    public RetryManager(){
        logger = LoggerFactory.getLogger(getClass());
    }

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
