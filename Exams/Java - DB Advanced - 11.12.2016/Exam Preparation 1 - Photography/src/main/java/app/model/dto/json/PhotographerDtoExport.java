package app.model.dto.json;

import com.google.gson.annotations.SerializedName;

public class PhotographerDtoExport {

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName("LastName")
    private String lastName;

    @SerializedName("Phone")
    private String phone;

    public PhotographerDtoExport() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
