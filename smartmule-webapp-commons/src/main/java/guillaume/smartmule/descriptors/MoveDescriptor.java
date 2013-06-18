package guillaume.smartmule.descriptors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Yom
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class MoveDescriptor {

    @XmlElement
    private String from;
    @XmlElement
    private String to;
    @XmlAttribute
    private int polling;

    public MoveDescriptor() {
        super();
    }

    public MoveDescriptor(String from, String to, int polling) {
        this();
        this.from = from;
        this.to = to;
        this.polling = polling;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getPolling() {
        return polling;
    }

    public void setPolling(int polling) {
        this.polling = polling;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
