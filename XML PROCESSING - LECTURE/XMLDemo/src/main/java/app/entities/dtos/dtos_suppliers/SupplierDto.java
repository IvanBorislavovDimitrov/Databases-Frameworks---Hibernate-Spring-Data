package app.entities.dtos.dtos_suppliers;

import app.entities.dtos.visible_dtos.Parts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class SupplierDto {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "is_importer")
    private boolean isImporter;

    @XmlElement(name = "parts")
    private Parts parts;

    public SupplierDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }

    public Parts getParts() {
        return parts;
    }

    public void setParts(Parts parts) {
        this.parts = parts;
    }
}
