package app.exam.domain.dto.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class EmployeeOrdersJSONExportDTO {

    @Expose
    private String employeeName;

    @Expose
    @SerializedName("orders")
    private List<OrderJSONExportDTO> orderJSONExportDTOS;

    public EmployeeOrdersJSONExportDTO() {
        this.orderJSONExportDTOS = new ArrayList<>();
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public List<OrderJSONExportDTO> getOrderJSONExportDTOS() {
        return orderJSONExportDTOS;
    }

    public void setOrderJSONExportDTOS(List<OrderJSONExportDTO> orderJSONExportDTOS) {
        this.orderJSONExportDTOS = orderJSONExportDTOS;
    }
}
