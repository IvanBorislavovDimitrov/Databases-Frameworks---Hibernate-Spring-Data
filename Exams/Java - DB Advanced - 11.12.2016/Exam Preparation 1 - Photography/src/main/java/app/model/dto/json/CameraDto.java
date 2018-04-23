package app.model.dto.json;

import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CameraDto {

    @NotNull
    private String type;

    @NotNull
    private String make;

    @NotNull
    private String model;

    private boolean isFullName;

    @Min(100)
    @NotNull
    @SerializedName("minISO")
    private Integer minIso;

    @SerializedName("maxISO")
    private Integer maxIso;

    @SerializedName("MaxShutterSpeed")
    private String maxShutterSpeed;

    @SerializedName("maxVideoResolution")
    private String maxVideoResolution;

    @SerializedName("maxFrameRate")
    private Integer maxFrameRate;

    public CameraDto() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isFullName() {
        return isFullName;
    }

    public void setFullName(boolean fullName) {
        isFullName = fullName;
    }

    public Integer getMinIso() {
        return minIso;
    }

    public void setMinIso(Integer minIso) {
        this.minIso = minIso;
    }

    public Integer getMaxIso() {
        return maxIso;
    }

    public void setMaxIso(Integer maxIso) {
        this.maxIso = maxIso;
    }

    public String getMaxShutterSpeed() {
        return maxShutterSpeed;
    }

    public void setMaxShutterSpeed(String maxShutterSpeed) {
        this.maxShutterSpeed = maxShutterSpeed;
    }

    public String getMaxVideoResolution() {
        return maxVideoResolution;
    }

    public void setMaxVideoResolution(String maxVideoResolution) {
        this.maxVideoResolution = maxVideoResolution;
    }

    public Integer getMaxFrameRate() {
        return maxFrameRate;
    }

    public void setMaxFrameRate(Integer maxFrameRate) {
        this.maxFrameRate = maxFrameRate;
    }
}
