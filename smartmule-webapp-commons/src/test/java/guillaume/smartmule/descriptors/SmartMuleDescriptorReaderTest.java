package guillaume.smartmule.descriptors;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import guillaume.smartmule.ConfigurationException;
import org.junit.Test;

/**
 *
 * @author Guillaume
 */
public class SmartMuleDescriptorReaderTest {
    
    public void serialize() throws ConfigurationException {

        URL url = SmartMuleDescriptorReaderTest.class.getResource("/SmartMuleDescriptor.xml");
        SmartMuleDescriptor descriptor = (SmartMuleDescriptorReader.getDescriptor(url.getFile()));
        assertThat(descriptor).isNotNull();
        assertThat(descriptor.getEmail()).isNotNull();        
        assertThat(descriptor.getMoves()).isNotNull().isNotEmpty();                
        
    }
    
    // Dev method only : used to generate basic file
    @Test
    public void generate() {
        
        SmartMuleDescriptor descriptor = new SmartMuleDescriptor();
        descriptor.setEmail("guillaume@oups.net");
        descriptor.getMoves().add(new MoveDescriptor("c:\\SmartMuleFrom", "c:\\SmartMuleTo", 1000));

        try  {
            JAXBContext context = JAXBContext.newInstance(SmartMuleDescriptor.class);
            Marshaller m = context.createMarshaller();
            m.marshal(descriptor, new File("SmartMuleDescriptor.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }    
    
}

