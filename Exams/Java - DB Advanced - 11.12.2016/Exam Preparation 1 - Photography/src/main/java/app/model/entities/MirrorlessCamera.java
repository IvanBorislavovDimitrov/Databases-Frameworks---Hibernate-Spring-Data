package app.model.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Mirrorless")
public class MirrorlessCamera extends BasicCamera {

    @Column(name = "max_video_resolution")
    private String maxVideoResolution;

    @Column(name = "max_frame_rate")
    private Integer maxFrameRate;

    public MirrorlessCamera() {
    }

    public String getMaxVideoResolution() {
        return maxVideoResolution;
    }

    public void setMaxVideoResolution(String maxVideoResolution) {
        this.maxVideoResolution = maxVideoResolution;
    }

    public Integer getMaxFrameRate() {
        return maxFrameRate;
    }

    public void setMaxFrameRate(Integer maxFrameRate) {
        this.maxFrameRate = maxFrameRate;
    }
}
