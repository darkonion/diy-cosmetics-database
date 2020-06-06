package pl.biologicznieczynny.diycosmeticsdatabase.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Recipe;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Step;
import pl.biologicznieczynny.diycosmeticsdatabase.services.StepService;

import java.net.URI;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api")
public class StepsController {

    private final StepService stepService;

    public StepsController(StepService stepService) {this.stepService = stepService;}


    @PostMapping("/recipes/{id}/steps")
    public ResponseEntity<Recipe> addNewStepToRecipe(@PathVariable Long id, @RequestBody Step step) {
        log.info("Adding new step: " + step.getName() + " to recipe: " + id);
        Recipe savedRecipeWithNewStep = stepService.addNewStepToRecipe(id, step);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedRecipeWithNewStep.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(savedRecipeWithNewStep);
    }

    @DeleteMapping("/steps/{id}")
    public void deleteStep(@PathVariable Long id) {
        log.info("Deleting step with id: " + id);
        stepService.deleteStepById(id);
    }
}
