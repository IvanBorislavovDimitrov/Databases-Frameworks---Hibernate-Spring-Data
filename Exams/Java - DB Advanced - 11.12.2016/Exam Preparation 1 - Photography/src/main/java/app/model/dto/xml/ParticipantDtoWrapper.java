package app.model.dto.xml;

import app.io.FileIO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "participants")
public class ParticipantDtoWrapper {

    @XmlElement(name = "participant")
    private List<ParticipantDto> participantDtos;

    public ParticipantDtoWrapper() {
        this.participantDtos = new ArrayList<>();
    }

    public List<ParticipantDto> getParticipantDtos() {
        return participantDtos;
    }

    public void setParticipantDtos(List<ParticipantDto> participantDtos) {
        this.participantDtos = participantDtos;
    }
}
