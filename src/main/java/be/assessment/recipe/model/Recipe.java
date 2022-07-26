package be.assessment.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;


/**
 * recipe model using javax.validation for automatic validation
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_RECIPE")
public class Recipe {
    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Range(min=1)
    private int serving;

    //is default is false unless it is overriden
    private boolean vegetarian;

    @ManyToMany
    @NotEmpty(message = "Ingredient list cannot be empty.")
    private List<Ingredient> ingredientList;

    @NotBlank(message = "Instructions are mandatory")
    private String instructions;
}
