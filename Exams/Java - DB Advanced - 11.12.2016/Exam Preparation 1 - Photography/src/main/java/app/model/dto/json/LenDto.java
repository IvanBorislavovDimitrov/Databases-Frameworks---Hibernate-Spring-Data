package app.model.dto.json;

import java.math.BigDecimal;

public class LenDto {

    private String make;

    private Integer focalLength;

    private BigDecimal maxAperture;

    private String compatibleWith;

    public LenDto() {
    }

    public LenDto(String make, Integer focalLength, BigDecimal maxAperture, String compatibleWith) {
        this.make = make;
        this.focalLength = focalLength;
        this.maxAperture = maxAperture;
        this.compatibleWith = compatibleWith;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Integer getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(Integer focalLength) {
        this.focalLength = focalLength;
    }

    public BigDecimal getMaxAperture() {
        return maxAperture;
    }

    public void setMaxAperture(BigDecimal maxAperture) {
        this.maxAperture = maxAperture;
    }

    public String getCompatibleWith() {
        return compatibleWith;
    }

    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }
}
