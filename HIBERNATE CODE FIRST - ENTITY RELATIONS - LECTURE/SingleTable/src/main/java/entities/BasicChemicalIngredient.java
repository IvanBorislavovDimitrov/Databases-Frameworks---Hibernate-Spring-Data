package entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public abstract class BasicChemicalIngredient extends BasicIngredient {

    @Column(name = "chemical_formula")
    private String chemicalFormula;

    protected BasicChemicalIngredient() {
    }

    public BasicChemicalIngredient(String name, BigDecimal price, String chemicalFormula) {
        this.setName(name);
        this.setPrice(price);
        this.setChemicalFormula(chemicalFormula);
    }

    public String getChemicalFormula() {
        return chemicalFormula;
    }

    public void setChemicalFormula(String chemicalFormula) {
        this.chemicalFormula = chemicalFormula;
    }
}
