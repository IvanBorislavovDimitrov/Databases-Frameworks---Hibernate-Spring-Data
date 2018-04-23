package app.exam.domain.dto.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OrderJSONExportDTO {

    @Expose
    private String customer;

    @Expose
    @SerializedName("items")
    private List<ItemJSONExportDTO> itemJSONExportDTOS;

    public OrderJSONExportDTO() {
        this.itemJSONExportDTOS = new ArrayList<>();
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<ItemJSONExportDTO> getItemJSONExportDTOS() {
        return itemJSONExportDTOS;
    }

    public void setItemJSONExportDTOS(List<ItemJSONExportDTO> itemJSONExportDTOS) {
        this.itemJSONExportDTOS = itemJSONExportDTOS;
    }
}
