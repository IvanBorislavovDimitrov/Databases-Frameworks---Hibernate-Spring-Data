package app.model.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Native;

@Entity
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("DSLR")
public class DSLRCamera extends BasicCamera {

    @Column(name = "max_shutter_speed")
    private Integer maxShutterSpeed;

    public DSLRCamera() {
    }

    public Integer getMaxShutterSpeed() {
        return maxShutterSpeed;
    }

    public void setMaxShutterSpeed(Integer maxShutterSpeed) {
        this.maxShutterSpeed = maxShutterSpeed;
    }
}
