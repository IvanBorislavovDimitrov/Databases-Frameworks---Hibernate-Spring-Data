package app.models.entities.dtos.view;

import java.math.BigDecimal;

public class GameTitleWithPrice {

    private String title;
    private BigDecimal price;

    public GameTitleWithPrice(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
    }

    public GameTitleWithPrice() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s %.2f", this.getTitle(), this.getPrice());
    }
}
