package guillaume.smartmule;

import guillaume.smartmule.descriptors.SmartMuleDescriptor;
import guillaume.smartmule.descriptors.SmartMuleDescriptorReader;
import guillaume.tomcat.companion.mail.MailSender;
import guillaume.tomcat.companion.mail.MailSenderFactory;
import org.slf4j.Logger;

import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Guillaume
 */
public class SmartMuleServlet extends HttpServlet {

    private static final String SMARTMULE_DESCRIPTOR_VAR_KEY = "SmartMule-Config";

    protected final Logger logger = getLogger(getClass());
    
    private SmartMule service;

    public SmartMuleServlet() {

        logger.debug("SmartMuleServlet loaded");

        try {

            String path = System.getProperty(SMARTMULE_DESCRIPTOR_VAR_KEY);

            if (path == null || path.trim().isEmpty()) {
                logger.error("Missing configuration var '{}' not found.", SMARTMULE_DESCRIPTOR_VAR_KEY.toString());
                return;
            }  else {
                logger.info("SmartMule started with configuration : " + path);
            }

            SmartMuleDescriptor descriptor = SmartMuleDescriptorReader.getDescriptor(path);

            MailSender mailSender = new MailSenderFactory().getMailSender();

            service = new SmartMule(descriptor, mailSender);
            service.run();

            logger.info("SmartMule service is now running");

        } catch (ConfigurationException ex) {
            logger.error(ex.getMessage(), ex);
        } catch (NamingException e) {
            logger.error(e.getMessage(), e);
        }


    }
}
