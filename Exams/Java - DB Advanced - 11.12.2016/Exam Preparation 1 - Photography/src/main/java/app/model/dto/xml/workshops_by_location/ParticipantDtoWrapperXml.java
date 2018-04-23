package app.model.dto.xml.workshops_by_location;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "participants")
public class ParticipantDtoWrapperXml {

    @XmlElement(name = "participant")
    private List<String> participantDtosa;

    public ParticipantDtoWrapperXml() {
        this.participantDtosa = new ArrayList<>();
    }

    public List<String> getParticipantDtosa() {
        return participantDtosa;
    }

    public void setParticipantDtosa(List<String> participantDtosa) {
        this.participantDtosa = participantDtosa;
    }
}
