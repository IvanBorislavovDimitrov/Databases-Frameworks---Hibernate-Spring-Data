package com.masdefect.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "anomalies")
public class AnomaliesXMLDto {

    @XmlElement(name = "anomaly")
    private List<AnomalyXMLDto> anomalies;

    public AnomaliesXMLDto() {
        this.anomalies = new ArrayList<>();
    }

    public List<AnomalyXMLDto> getAnomalies() {
        return anomalies;
    }

    public void setAnomalies(List<AnomalyXMLDto> anomalies) {
        this.anomalies = anomalies;
    }
}
