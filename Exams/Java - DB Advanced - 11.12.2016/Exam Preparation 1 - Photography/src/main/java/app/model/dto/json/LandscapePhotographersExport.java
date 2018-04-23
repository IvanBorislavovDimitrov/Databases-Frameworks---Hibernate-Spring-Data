package app.model.dto.json;

import com.google.gson.annotations.SerializedName;

public class LandscapePhotographersExport {

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName("LastName")
    private String lastName;

    @SerializedName("CameraMake")
    private String cameraMake;

    @SerializedName("LensesCount")
    private Integer lensesCount;

    public LandscapePhotographersExport() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCameraMake() {
        return cameraMake;
    }

    public void setCameraMake(String cameraMake) {
        this.cameraMake = cameraMake;
    }

    public Integer getLensesCount() {
        return lensesCount;
    }

    public void setLensesCount(Integer lensesCount) {
        this.lensesCount = lensesCount;
    }
}
