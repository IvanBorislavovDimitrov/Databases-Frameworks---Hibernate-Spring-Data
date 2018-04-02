package entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "Ammonium Chloride")
public class AmmoniumChloride extends BasicChemicalIngredient {

    private static final String NAME = "Ammonium Chloride";

    private static final BigDecimal PRICE = new BigDecimal("0.59");

    private static final String CHEMICAL_FORMULA = "NH4Cl";

    public AmmoniumChloride() {
        super(NAME, PRICE, CHEMICAL_FORMULA);
    }

    public static String getNAME() {
        return NAME;
    }

    public static BigDecimal getPRICE() {
        return PRICE;
    }
}
