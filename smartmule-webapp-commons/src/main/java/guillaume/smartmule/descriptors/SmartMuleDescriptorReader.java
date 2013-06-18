package guillaume.smartmule.descriptors;

import guillaume.smartmule.ConfigurationException;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Guillaume
 */
public class SmartMuleDescriptorReader {

    public static SmartMuleDescriptor getDescriptor(String path) throws ConfigurationException {
        
        try {
            
            File f = new File(path);
            if (!f.exists()) {
                throw new ConfigurationException("configuration file does not exist");
            }
            
            JAXBContext context = JAXBContext.newInstance(SmartMuleDescriptor.class);
            Unmarshaller m = context.createUnmarshaller();
            return (SmartMuleDescriptor) m.unmarshal(f);
            
        } catch (JAXBException ex) {
            throw new ConfigurationException ("configuration file is not valid", ex);
        }
    }
}
