package app.entities.dtos.dtos_cars;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsShortInfoDto {

    @XmlElement(name = "car")
    private List<CarShortInfoDto> carShortInfoDtos;

    public List<CarShortInfoDto> getCarShortInfoDtos() {
        return carShortInfoDtos;
    }

    public void setCarShortInfoDtos(List<CarShortInfoDto> carShortInfoDtos) {
        this.carShortInfoDtos = carShortInfoDtos;
    }
}
