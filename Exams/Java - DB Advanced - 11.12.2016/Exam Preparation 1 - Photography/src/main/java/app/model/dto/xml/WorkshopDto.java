package app.model.dto.xml;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class WorkshopDto {

    @XmlAttribute(name = "name")
    @NotNull
    private String name;

    @XmlAttribute(name = "start-date")
    @NotNull
    private String startDate;

    @NotNull
    @XmlAttribute(name = "end-date")
    private String endDate;

    @XmlAttribute(name = "location")
    @NotNull
    private String location;

    @XmlAttribute(name = "price")
    @NotNull
    private BigDecimal price;

    @XmlElement(name = "trainer")
    @NotNull
    private String trainer;

    @XmlElement(name = "participants")
    private ParticipantDtoWrapper participantDtoWrapper;

    public WorkshopDto() {
        this.participantDtoWrapper = new ParticipantDtoWrapper();
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public ParticipantDtoWrapper getParticipantDtoWrapper() {
        return participantDtoWrapper;
    }

    public void setParticipantDtoWrapper(ParticipantDtoWrapper participantDtoWrapper) {
        this.participantDtoWrapper = participantDtoWrapper;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
