package app.entitites.dtos;

public class SupplierShortInfoDto {

    private Long id;
    private String name;
    private int partsCount;

    public SupplierShortInfoDto() {
    }

    public SupplierShortInfoDto(Long id, String name, int partsCount) {
        this.id = id;
        this.name = name;
        this.partsCount = partsCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(int partsCount) {
        this.partsCount = partsCount;
    }
}
