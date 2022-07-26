package be.assessment.recipe.controller;

import be.assessment.recipe.model.Recipe;
import be.assessment.recipe.model.SearchRequest;
import be.assessment.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class RecipeController {
    @Autowired
    private RecipeService serv;

    /**
     * search REST endpoint, supports text search of instructions, vegetarian only,
     * include/exclude ingredient and number of servings.
     *
     * vegetarian only - if set to true forces results to be vegetarian only
     * number of servings - can be set to zero or null if filter is to be ignored
     * include/exclude ingredients - allows passing a list of ingredients to included or excluded
     * text search of instructions - allows text search of instructions
     *
     * @param req
     * @return return list of recipes matching filter parameters
     */
    @PostMapping("/recipe/search")
    public ResponseEntity search(@RequestBody SearchRequest req){
        return ResponseEntity.ok(serv.search(req));
    }


    /**
     * @param req
     * @return
     */
    @PostMapping("/recipe")
    public ResponseEntity addRecipe(@Valid @RequestBody Recipe req){
        try{
            return ResponseEntity.ok(serv.addRecipe(req));
        }catch (Exception ex){
            return  ResponseEntity.status(400).body(ex.getMessage());
        }
    }

    @PutMapping("/recipe")
    public ResponseEntity updateRecipe(@Valid @RequestBody Recipe req){
        try{
            return ResponseEntity.ok(serv.updateRecipe(req));
        }catch (Exception ex){
            return  ResponseEntity.status(400).body(ex.getMessage());
        }
    }

    @GetMapping("/recipe/get/{id}")
    public Recipe getRecipe(@PathVariable int id){
        return serv.getRecipe(id);
    }

    @DeleteMapping("/recipe/{id}")
    public String deleteRecipe(@PathVariable int id){
        return serv.deleteRecipe(id);
    }

    @PostMapping("/recipe/addMany")
    public ResponseEntity addMany(@Valid @RequestBody List<Recipe> req){
        try{
            return ResponseEntity.ok(serv.addRecipes(req));
        }catch (Exception ex){
            return  ResponseEntity.status(400).body(ex.getMessage());
        }
    }

    @GetMapping("/recipe/listAll")
    public List<Recipe> listAll(){
        return serv.getAllRecipe();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
