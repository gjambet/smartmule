package guillaume.smartmule;

import guillaume.smartmule.descriptors.SmartMuleDescriptorReader;
import guillaume.smartmule.descriptors.SmartMuleDescriptor;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;

import guillaume.tomcat.companion.jndi.FilePlaceholder;
import guillaume.tomcat.companion.jndi.JndiFactory;
import guillaume.tomcat.companion.mail.MailSender;
import guillaume.tomcat.companion.mail.MailSenderFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 *
 * @author Guillaume
 */
public class SmartMuleServlet extends HttpServlet {

    private static final String SMARTMULE_DESCRIPTOR_JNDI_KEY = "config/Smartmule";
    
    private final Logger logger;
    
    private SmartMule service;

    public SmartMuleServlet() {
        logger = LoggerFactory.getLogger(getClass());
        logger.debug("SmartMuleServlet loaded");

        try {

            FilePlaceholder placeholder = new JndiFactory<FilePlaceholder>().getRessource(SMARTMULE_DESCRIPTOR_JNDI_KEY);
            SmartMuleDescriptor descriptor = SmartMuleDescriptorReader.getDescriptor(placeholder.getPath());

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
