package app.model.dto.xml.workshops_by_location;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class WorkshopDtoXml {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "total-profit")
    private BigDecimal totalProfit;

    @XmlElement(name = "participants")
    private ParticipantDtoWrapperXml participantDtoWrapper;

    public WorkshopDtoXml() {
        this.participantDtoWrapper = new ParticipantDtoWrapperXml();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }

    public ParticipantDtoWrapperXml getParticipantDtoWrapper() {
        return participantDtoWrapper;
    }

    public void setParticipantDtoWrapper(ParticipantDtoWrapperXml participantDtoWrapper) {
        this.participantDtoWrapper = participantDtoWrapper;
    }
}
