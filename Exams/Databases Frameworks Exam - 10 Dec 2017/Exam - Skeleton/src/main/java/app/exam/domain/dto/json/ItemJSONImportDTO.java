package app.exam.domain.dto.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.persistence.Column;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ItemJSONImportDTO {

    @Size(min = 3, max = 30)
    @NotNull
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("price")
    @Column(nullable = false, name = "price")
    @DecimalMin("0.01")
    @NotNull
    @Expose
    @Min(0)
    private BigDecimal price;

    @SerializedName("category")
    @Size(min = 3, max = 30)
    @NotNull
    @Expose
    private String categoryName;

    public ItemJSONImportDTO() {
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
