package app.entitites.dtos.import_dtos.cars;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "cars")
public class CarWrapperImport {

    @XmlElement(name = "car")
    private List<CarDtoImport> carDtoImports;

    public CarWrapperImport() {
        this.carDtoImports = new ArrayList<>();
    }

    public List<CarDtoImport> getCarDtoImports() {
        return carDtoImports;
    }

    public void setCarDtoImports(List<CarDtoImport> carDtoImports) {
        this.carDtoImports = carDtoImports;
    }
}
