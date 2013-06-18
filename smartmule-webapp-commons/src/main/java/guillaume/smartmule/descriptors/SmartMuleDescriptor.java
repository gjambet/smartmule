/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guillaume.smartmule.descriptors;

import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yom
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SmartMuleDescriptor {

    @XmlElement
    @XmlElementWrapper
    private List<MoveDescriptor> moves;
    
    @XmlAttribute
    private String email;

    public SmartMuleDescriptor() {
        moves = new LinkedList<MoveDescriptor>();
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<MoveDescriptor> getMoves() {
        return moves;
    }

    public void setMoves(List<MoveDescriptor> moves) {
        this.moves = moves;
    }
}
