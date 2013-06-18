package guillaume.smartmule;

import guillaume.smartmule.descriptors.MoveDescriptor;
import java.io.File;
import java.util.TimerTask;
import javax.mail.MessagingException;

import guillaume.tomcat.companion.mail.MailSender;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Yom
 */
class MoveTask extends TimerTask {

    private final Logger logger;

    private MoveDescriptor move;
    private MailSender sender;
    private String email;
    private RetryManager retryManager;


    MoveTask(MoveDescriptor move, MailSender sender, String email) {
        this.sender = sender;
        this.move = move;
        this.email = email;
        retryManager = new RetryManager().withMaxRetry(10);
        logger = LoggerFactory.getLogger(getClass());
    }

    @Override
    public void run() {
        try {

            logger.trace("running");

            File from = new File(move.getFrom());
            File to = new File(move.getTo());

            for (File f : from.listFiles()) {
                if (f.isFile() && f.canWrite()) { //can write = pas en train d'�tre copi� par emule
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
