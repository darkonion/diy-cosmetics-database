package pl.biologicznieczynny.diycosmeticsdatabase.services;

import org.springframework.stereotype.Service;
import pl.biologicznieczynny.diycosmeticsdatabase.exceptionHandling.NotFoundException;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Recipe;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Step;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.RecipeRepository;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.StepRepository;

import java.util.Comparator;
import java.util.Set;


@Service
public class StepService {

    private final StepRepository stepRepository;
    private final RecipeRepository recipeRepository;

    public StepService(StepRepository stepRepository,
            RecipeRepository recipeRepository) {this.stepRepository = stepRepository;
        this.recipeRepository = recipeRepository;
    }

    public void deleteStepById(Long id) {
        if (!stepRepository.existsById(id)) {
            throw new NotFoundException("Step with id: " + id + " not found!");
        }

        stepRepository.deleteById(id);
    }

    public Recipe addNewStepToRecipe(Long id, Step step) {
        Recipe recipe = recipeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Recipe with id: " + id + " not found!"));

        step.setRecipe(recipe);
        step.setSeq(countOrder(recipe.getSteps()));

        recipe.getSteps().add(step);

        return recipeRepository.save(recipe);

    }

    private int countOrder(Set<Step> steps) {
        if (steps.isEmpty()) {
            return 1;
        }

        int seq = steps.stream()
                .map(Step::getSeq)
                .max(Comparator.comparingInt(s -> s))
                .orElseThrow(() -> new RuntimeException("Steps ordering has been corrupted."));

        if (seq < 1) {
            return  1;
        }
        return seq + 1;
    }
}
