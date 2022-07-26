package be.assessment.recipe.repository;

import be.assessment.recipe.model.Ingredient;
import be.assessment.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository  extends JpaRepository<Ingredient,String> {

}
