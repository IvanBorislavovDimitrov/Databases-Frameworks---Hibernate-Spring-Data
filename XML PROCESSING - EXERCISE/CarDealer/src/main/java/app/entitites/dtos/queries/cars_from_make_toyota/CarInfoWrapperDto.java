package app.entitites.dtos.queries.cars_from_make_toyota;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlRootElement(name = "cars")
public class CarInfoWrapperDto {

    @XmlElement(name = "car")
    private List<CarInfoDto> carInfoDtos;

    public CarInfoWrapperDto() {
        this.carInfoDtos = new ArrayList<>();
    }

    public List<CarInfoDto> getCarInfoDtos() {
        return carInfoDtos;
    }

    public void setCarInfoDtos(List<CarInfoDto> carInfoDtos) {
        this.carInfoDtos = carInfoDtos;
    }
}
