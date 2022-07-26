package be.assessment.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * ingredients model using javax.validation for automatic validation
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_INGREDIENT")
public class Ingredient {
    @Id
    @NotBlank(message = "Ingredient is mandatory")
    private String ingredient;
}
