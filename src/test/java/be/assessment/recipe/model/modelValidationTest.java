package be.assessment.recipe.model;


import be.assessment.recipe.repository.IngredientRepository;
import be.assessment.recipe.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit testing models
 * will test models validation rules independent of each other
 * @author Jonathan Israel E. Serrano
 */
@SpringBootTest
public class modelValidationTest {

    /**
     * test recipe model validation
     * @author Jonathan Israel E. Serrano (jyonkheel@yahoo.com)
     */
    @Test
    void testRecipe() {
        Recipe rc = new Recipe();

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Recipe>> violations = validator.validate(rc);
        assertTrue(!violations.isEmpty());

        rc.setName("valid name");
        rc.setServing(1);
        rc.setInstructions("valid instructions");
        Ingredient validIngr = new Ingredient("valid ingr");
        ArrayList ingr = new ArrayList<>();
        ingr.add(validIngr);
        rc.setIngredientList(ingr);

        violations = validator.validate(rc);
        assertTrue(violations.isEmpty());
    }

    /**
     * test ingredient model validation
     */
    @Test
    void testIngredient(){
        Ingredient ing = new Ingredient();

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Ingredient>> violations = validator.validate(ing);
        assertTrue(!violations.isEmpty());

        ing.setIngredient("valid ingredient");
        violations = validator.validate(ing);
        assertTrue(violations.isEmpty());
    }
}
