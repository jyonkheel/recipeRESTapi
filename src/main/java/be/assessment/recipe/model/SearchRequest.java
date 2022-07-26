package be.assessment.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * vegetarian only - if set to true forces results to be vegetarian only
 * number of servings - can be set to zero or null if filter is to be ignored
 * include/exclude ingredients - allows passing a list of ingredients to included or excluded
 * text search of instructions - allows text search of instructions
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {
    private String instructionSearch;
    private int serving;
    private boolean vegetarianOnly;
    private List<Ingredient> includeIngredient;
    private List<Ingredient> excludeIngredient;
    private int currentPage;
    private int itemPaginationCount;
}
