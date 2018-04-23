package app.exam.domain.dto.json;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Email;

import java.math.BigDecimal;

public class ItemJSONExportDTO {

    private Integer id;

    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    @Expose
    private Integer quantity;

    public ItemJSONExportDTO() {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
