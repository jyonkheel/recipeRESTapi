package be.assessment.recipe.controller;

import be.assessment.recipe.model.Ingredient;
import be.assessment.recipe.model.Recipe;
import be.assessment.recipe.model.SearchRequest;
import be.assessment.recipe.repository.IngredientRepository;
import be.assessment.recipe.service.RecipeService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.AfterClass;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration testing models
 * will test complete add/edit/get/getall and delete
 * REST controllers
 * @author Jonathan Israel E. Serrano (jyonkheel@yahoo.com)
 */
@SpringBootTest
public class controllerIT {

    @Autowired
    RecipeService serv;
    @Autowired
    IngredientRepository ingRepo;

    @Test
    public void testService(){
        Recipe rc = new Recipe();

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Recipe>> violations = validator.validate(rc);
        assertTrue(!violations.isEmpty());
        assertThrows(Exception.class, ()-> {
            serv.addRecipe(rc);
        });

        rc.setName("valid name");
        rc.setServing(1);
        rc.setInstructions("valid instructions");
        Ingredient validIngr = new Ingredient("valid ingr");
        ArrayList ingr = new ArrayList<>();
        ingr.add(validIngr);
        rc.setIngredientList(ingr);

        violations = validator.validate(rc);
        assertTrue(violations.isEmpty());

        //CREATE
        Recipe rec = serv.addRecipe(rc);

        rec.setName(null);
        assertThrows(Exception.class, ()-> {
            serv.updateRecipe(rec);
        });
        rec.setName("valid name");

        //UPDATE
        serv.updateRecipe(rec);

        //GET and GET ALL
        assertNotNull(serv.getRecipe(rec.getId()));
        assertFalse(serv.getAllRecipe().isEmpty());

        //DELETE
        assertEquals("deleted "+rec.getId() ,serv.deleteRecipe(rec.getId()));

        ingRepo.deleteById("valid ingr");
    }


}
