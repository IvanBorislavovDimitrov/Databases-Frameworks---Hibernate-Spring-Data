package app.domain.dtos.json.importDtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotNull;

public class EmployeeCardJSONDto {

    @Expose
    @NotNull
    @SerializedName("number")
    private String number;

    public EmployeeCardJSONDto() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
