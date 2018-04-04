package app.models.entities;

import app.anotations.Thumbnail;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    @Pattern(regexp = "^[A-Z][a-zA-Z0-9 ]{2,100}$")
    private String title;

    @Basic
    @Size(min = 11, max = 11)
    private String youtubeVideo;

    @Column(name = "image_thumbnail")
    @Thumbnail
    private String imageThumbnail;

    @Basic
    @Column(columnDefinition = "DOUBLE(19,2)", precision = 1)
    @Min(0)
    private Double size;

    @Basic
    @Column(name = "price", precision = 20, scale = 2)
    @Min(0)
    private BigDecimal price;

    @Basic
    @Column(columnDefinition = "TEXT")
    @Size(min = 20)
    private String description;

    @Column(name = "release_date")
    private Date releaseDate;

    @ManyToMany(mappedBy = "games")
    private Set<User> users;

    @ManyToMany(mappedBy = "orders")
    private Set<User> buyersInProgress;

    public Game() {
        this.users = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getYoutubeVideo() {
        return youtubeVideo;
    }

    public void setYoutubeVideo(String youtubeVideo) {
        this.youtubeVideo = youtubeVideo;
    }

    public Set<User> getBuyersInProgress() {
        return buyersInProgress;
    }

    public void setBuyersInProgress(Set<User> buyersInProgress) {
        this.buyersInProgress = buyersInProgress;
    }
}
