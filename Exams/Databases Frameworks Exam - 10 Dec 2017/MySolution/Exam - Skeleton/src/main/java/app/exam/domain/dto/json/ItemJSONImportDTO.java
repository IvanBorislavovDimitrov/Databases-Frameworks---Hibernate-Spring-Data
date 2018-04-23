package app.exam.domain.dto.json;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ItemJSONImportDTO {

    @Size(min = 3, max = 30)
    @NotNull
    @Expose
    @SerializedName("name")
    private String name;

    @NotNull
    @Expose
    @SerializedName("category")
    @Size(min = 3, max = 30)
    private String category;

    @DecimalMin("0.01")
    @NotNull
    @Min(0)
    @Expose
    @SerializedName("price")
    private BigDecimal price;

    public ItemJSONImportDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
