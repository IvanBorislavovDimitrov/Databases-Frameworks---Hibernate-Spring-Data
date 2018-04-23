package app.domain.dtos.json.exportDtos;

import com.google.gson.annotations.Expose;

public class EmployeeCardExportJSONDto {

    @Expose
    private String number;

    public EmployeeCardExportJSONDto() {
    }

    public EmployeeCardExportJSONDto(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
