package app.domain.dtos.json.importDtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotNull;

public class BranchImportJSONDto {

    @NotNull
    @Expose
    private String name;

    @Expose
    @NotNull
    @SerializedName("town")
    private String townName;

    public BranchImportJSONDto() {
    }

    public BranchImportJSONDto(String name, String townName) {
        this.name = name;
        this.townName = townName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
