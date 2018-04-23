package app.domain.dtos.json.importDtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class TownImportJSONDto {

    @NotNull
    @Expose
    private String name;

    @NotNull
    @Expose
    private Integer population;

    public TownImportJSONDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
