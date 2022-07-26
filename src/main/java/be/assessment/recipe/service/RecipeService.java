package be.assessment.recipe.service;

import be.assessment.recipe.model.Recipe;
import be.assessment.recipe.model.SearchRequest;
import be.assessment.recipe.repository.IngredientRepository;
import be.assessment.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    @Autowired
    RecipeRepository repo;

    @Autowired
    IngredientRepository ingrRepo;

    @Autowired
    EntityManager entMgr;

    public List<Recipe> search(SearchRequest req){
        CriteriaBuilder cb = entMgr.getCriteriaBuilder();
        CriteriaQuery<Recipe> criteria  = cb.createQuery(Recipe.class);
        Root<Recipe> root = criteria.from(Recipe.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        if(req.isVegetarianOnly()){
            predicates.add(cb.equal(root.get("vegetarian"), true));
        }
        if(req.getServing()>0){
            predicates.add(cb.equal(root.get("serving"), req.getServing()));
        }
        if(req.getInstructionSearch() != null){
            predicates.add(cb.like(root.get("instructions"), "%"+req.getInstructionSearch()+"%"));
        }

        criteria.select(root).where(predicates.toArray(new Predicate[]{}));

        List<Recipe> currentResult = entMgr.createQuery(criteria).getResultList();

        //Ingredients filter (include) - MUST include all!
        if(req.getIncludeIngredient() != null && !req.getIncludeIngredient().isEmpty()){
            currentResult = currentResult.stream().filter( in -> {
                return in.getIngredientList().containsAll(req.getIncludeIngredient());
            }).collect(Collectors.toList());
        }

        //Ingredients filter (exclude) - if at least one ingredient is matched entire recipe is excluded
        if(req.getExcludeIngredient() != null && !req.getExcludeIngredient().isEmpty()){
            currentResult = currentResult.stream().filter( in -> {
                return !in.getIngredientList().stream().anyMatch(req.getExcludeIngredient()::contains);
            }).collect(Collectors.toList());
        }

        return currentResult;
    }

    public Recipe addRecipe(Recipe rec){
        ingrRepo.saveAll(rec.getIngredientList());
        return repo.save(rec);
    }

    public List<Recipe> addRecipes(List<Recipe> recs){
        recs.stream().forEach(rec -> {
            ingrRepo.saveAll(rec.getIngredientList());
        });
        return repo.saveAll(recs);
    }

    public Recipe getRecipe(int id){
        return repo.findById(id).orElse(null);
    }

    public List<Recipe> getAllRecipe(){
        return repo.findAll();
    }

    public Recipe updateRecipe(Recipe rec){
        Recipe existing = repo.findById(rec.getId()).orElse(null);
        if(existing == null){
            return null;
        }

        ingrRepo.saveAll(rec.getIngredientList());
        existing.setName(rec.getName());
        existing.setInstructions(rec.getInstructions());
        existing.setServing(rec.getServing());
        existing.setIngredientList(rec.getIngredientList());
        existing.setVegetarian(rec.isVegetarian());
        return repo.save(existing);
    }

    public String deleteRecipe(int id){
        repo.deleteById(id);
        return "deleted "+id;
    }

}
