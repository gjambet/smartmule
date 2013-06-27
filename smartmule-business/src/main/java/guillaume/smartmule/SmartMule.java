package guillaume.smartmule;

import guillaume.smartmule.descriptors.MoveDescriptor;
import guillaume.smartmule.descriptors.SmartMuleDescriptor;
import guillaume.tomcat.companion.mail.MailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Timer;

import static java.text.MessageFormat.format;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Guillaume
 */
public class SmartMule implements Runnable {

    private static Timer timer = new Timer("SmartMule");
    protected final Logger logger = getLogger(getClass());
    
    private SmartMuleDescriptor descriptor;
    private MailSender sender;

    public SmartMule(SmartMuleDescriptor descriptor, MailSender sender) {
        this.descriptor = descriptor;
        this.sender = sender;
        logger.info(format("SmartMule ready with {0} jobs", descriptor.getMoves().size()));
    }
    
    @Override
    public void run() {
        for (MoveDescriptor move : descriptor.getMoves()) {
            timer.scheduleAtFixedRate(new MoveTask(move, sender, descriptor.getEmail()), Calendar.getInstance().getTime(), move.getPolling());
        }
    }
}
