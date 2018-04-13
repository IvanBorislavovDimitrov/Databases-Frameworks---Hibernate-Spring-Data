package app.entitites.dtos.queries.cars_with_their_list_of_parts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "cars")
public class CarInfoAndItsPartsWrapperDto {

    @XmlElement(name = "car")
    private List<CarInfoAndItsPartsDto> cars;

    public CarInfoAndItsPartsWrapperDto() {
        this.cars = new ArrayList<>();
    }

    public List<CarInfoAndItsPartsDto> getCars() {
        return cars;
    }

    public void setCars(List<CarInfoAndItsPartsDto> cars) {
        this.cars = cars;
    }
}
