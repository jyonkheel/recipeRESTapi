package be.assessment.recipe.repository;

import be.assessment.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RecipeRepository extends JpaRepository<Recipe,Integer> {

}
