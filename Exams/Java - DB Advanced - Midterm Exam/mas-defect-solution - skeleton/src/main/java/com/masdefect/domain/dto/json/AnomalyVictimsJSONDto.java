package com.masdefect.domain.dto.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AnomalyVictimsJSONDto implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer anomalyId;

    @Expose
    private String person;

    public AnomalyVictimsJSONDto() {
    }

    public Integer getAnomalyId() {
        return anomalyId;
    }

    public void setAnomalyId(Integer anomalyId) {
        this.anomalyId = anomalyId;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
