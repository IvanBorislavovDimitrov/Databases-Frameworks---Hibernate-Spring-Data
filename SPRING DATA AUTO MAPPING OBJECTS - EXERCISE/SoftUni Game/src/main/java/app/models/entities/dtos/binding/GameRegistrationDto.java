package app.models.entities.dtos.binding;

import java.math.BigDecimal;
import java.util.Date;

public class GameRegistrationDto {

    private String title;
    private BigDecimal price;
    private double size;
    private String youtubeVideo;
    private String imageThumbnail;
    private String description;
    private Date releaseDate;

    public GameRegistrationDto() {
    }

    public GameRegistrationDto(String title, BigDecimal price, double size,
                               String youtubeVideo, String imageThumbnail, String description, Date releaseDate) {
        this.setTitle(title);
        this.setPrice(price);
        this.setSize(size);
        this.setYoutubeVideo(youtubeVideo);
        this.setImageThumbnail(imageThumbnail);
        this.setDescription(description);
        this.setReleaseDate(releaseDate);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || (title.length() < 3 || title.length() > 100)) {
            throw new IllegalArgumentException("Invalid title");
        }
        if (! Character.isUpperCase(title.charAt(0))) {
            throw new IllegalArgumentException("Name must start with capital letter!");
        }
        this.title =title;

    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getYoutubeVideo() {
        return youtubeVideo;
    }

    public void setYoutubeVideo(String youtubeVideo) {
        String youtubeString = youtubeVideo.substring(youtubeVideo.lastIndexOf("=") + 1);
        if (youtubeString.length() != 11) {
            throw new IllegalArgumentException("Invalid youtube video!");
        }
        this.youtubeVideo = youtubeString;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        if (imageThumbnail == null) {
            this.imageThumbnail = null;
        } else {
            if (imageThumbnail.startsWith("http://") || imageThumbnail.startsWith("https://")) {
                this.imageThumbnail = imageThumbnail;
            } else {
                throw new IllegalArgumentException("Invalid image thumbnail url");
            }
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.length() < 20) {
            throw new IllegalArgumentException("Invalid description");
        }
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
