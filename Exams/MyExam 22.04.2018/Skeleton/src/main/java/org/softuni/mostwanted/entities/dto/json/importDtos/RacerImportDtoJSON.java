package org.softuni.mostwanted.entities.dto.json.importDtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotNull;

public class RacerImportDtoJSON {

    @Expose
    @NotNull
    private String name;

    @Expose
    private Integer age;

    @Expose
    private Double bounty;

    @Expose
    @NotNull
    @SerializedName("homeTown")
    private String townName;

    public RacerImportDtoJSON() {
    }

    public RacerImportDtoJSON(String name, Integer age, Double bounty, String townName) {
        this.name = name;
        this.age = age;
        this.bounty = bounty;
        this.townName = townName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getBounty() {
        return bounty;
    }

    public void setBounty(Double bounty) {
        this.bounty = bounty;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
