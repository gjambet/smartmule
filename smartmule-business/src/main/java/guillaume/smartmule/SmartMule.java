package guillaume.smartmule;

import guillaume.smartmule.descriptors.MoveDescriptor;
import guillaume.smartmule.descriptors.SmartMuleDescriptor;
import guillaume.tomcat.companion.mail.MailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Timer;

/**
 *
 * @author Yom
 */
public class SmartMule implements Runnable {

    private static Timer timer = new Timer("SmartMule");
    private final Logger logger;
    
    private SmartMuleDescriptor descriptor;
    private MailSender sender;

    public SmartMule(SmartMuleDescriptor descriptor, MailSender sender) {
        this.descriptor = descriptor;
        this.sender = sender;
        logger = LoggerFactory.getLogger(getClass());
        logger.info("SmartMule ready with " + descriptor.getMoves().size() + " jobs");
    }
    
    @Override
    public void run() {
        for (MoveDescriptor move : descriptor.getMoves()) {
            timer.scheduleAtFixedRate(new MoveTask(move, sender, descriptor.getEmail()), Calendar.getInstance().getTime(), move.getPolling());
        }
    }
}
