package guillaume.smartmule;

import guillaume.smartmule.descriptors.MoveDescriptor;
import java.io.File;
import java.util.TimerTask;
import javax.mail.MessagingException;

import guillaume.tomcat.companion.mail.MailSender;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Guillaume
 */
class MoveTask extends TimerTask {

    protected final Logger logger = getLogger(getClass());

    private MoveDescriptor move;
    private MailSender sender;
    private String email;
    private RetryManager retryManager;


    MoveTask(MoveDescriptor move, MailSender sender, String email) {
        this.sender = sender;
        this.move = move;
        this.email = email;
        retryManager = new RetryManager().withMaxRetry(10);
    }

    @Override
    public void run() {
        try {

            logger.trace("running");

            File from = new File(move.getFrom());
            File to = new File(move.getTo());

            for (File f : from.listFiles()) {
                if (f.isFile() && f.canWrite()) { // can write = pas en train d'être copié par emule
                    move(f, to);
                }
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }


    }

    private void move(File f, File to) {

        if (retryManager.isStalled(f)){
            return;
        }

        try  {

            FileUtils.moveFileToDirectory(f, to, true);
            sender.notify(email, f.getName());

        } catch (MessagingException ex) {
            logger.debug(ex.getMessage(), ex);
        } catch (Exception ex) {
            logger.debug(ex.getMessage(), ex);
            retryManager.onError(f);
        }

    }


}
