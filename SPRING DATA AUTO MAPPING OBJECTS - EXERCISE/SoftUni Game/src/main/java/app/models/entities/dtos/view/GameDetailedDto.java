package app.models.entities.dtos.view;

import java.math.BigDecimal;
import java.util.Date;

public class GameDetailedDto {

    private String title;
    private BigDecimal price;
    private String description;
    private Date releaseDate;

    public GameDetailedDto() {
    }

    public GameDetailedDto(String title, BigDecimal price, String description, Date releaseDate) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return String.format("Title: %s\n" +
                "Price: %.2f \n" +
                "Description: %s\n" +
                "Release date: %s\n", this.getTitle(), this.getPrice(), this.getDescription(), this.getReleaseDate().toString());
    }
}
