package entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "Nettle")
public class Nettle extends BasicIngredient {

}
