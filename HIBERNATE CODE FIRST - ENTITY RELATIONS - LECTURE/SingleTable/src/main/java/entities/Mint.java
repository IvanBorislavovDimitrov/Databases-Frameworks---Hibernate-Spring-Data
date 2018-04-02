package entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "Mint")
public class Mint extends BasicIngredient {


}