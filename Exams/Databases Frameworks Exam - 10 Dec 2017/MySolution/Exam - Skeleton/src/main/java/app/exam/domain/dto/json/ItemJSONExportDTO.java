package app.exam.domain.dto.json;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class ItemJSONExportDTO {

    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    @Expose
    private Integer quantity;

    public ItemJSONExportDTO() {
    }

    public ItemJSONExportDTO(String name, BigDecimal price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
