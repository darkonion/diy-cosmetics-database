package pl.biologicznieczynny.diycosmeticsdatabase.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Ingredient;
import pl.biologicznieczynny.diycosmeticsdatabase.services.IngredientService;

import java.net.URI;
import java.util.Set;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    //get's

    @GetMapping("/ingredients")
    public Page<Ingredient> getIngredientListPageable(@RequestParam(value = "page") int page,
                                                        @RequestParam(value = "size") int size,
                                                        @RequestParam(value = "sort", defaultValue = "asc") String sort) {

        log.info(String.format("Getting ingredients page of size:%s page number:%s sort:%s", size, page, sort));
        return ingredientService.findAll(page, size, sort);
    }

    @GetMapping("/ingredients/{id}")
    public Ingredient getIngredientById(@PathVariable Long id) {
        log.info("Getting ingredient with id: " + id);
        return ingredientService.findById(id);
    }

    @GetMapping("/ingredients/{id}/replacements")
    public Set<Ingredient> getIngredientReplacements(@PathVariable Long id) {
        log.info("Getting list of replacements for ingredient with id: " + id);
        return ingredientService.getIngredientReplacements(id);
    }

    //post's

    @PostMapping("/ingredients")
    public ResponseEntity<Ingredient> addNewIngredient(@RequestBody Ingredient ingredient) {
        log.info("Persisting new ingredient: " + ingredient.getName());
        Ingredient savedIngredient = ingredientService.addNewIngredient(ingredient);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedIngredient.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(savedIngredient);
    }

    @PostMapping("ingredients/{id}/replacements")
    public ResponseEntity<Ingredient> addIngredientReplacements(@PathVariable Long id, @RequestBody
            Set<Ingredient> replacements) {
        Ingredient ingredient = ingredientService.addReplacements(id, replacements);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity
                .created(uri).body(ingredient);
    }

    //put's

    @PutMapping("/ingredients")
    public ResponseEntity<Ingredient> updateIngredient(@RequestBody Ingredient ingredient) {
        log.info("Updating ingredient: " + ingredient.getName());

        Ingredient updatedIngredient = ingredientService.updateIngredient(ingredient);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updatedIngredient.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(updatedIngredient);
    }

    //delete's

    @DeleteMapping("/ingredients/{id}")
    public void deleteIngredient(@PathVariable Long id) {
        log.info("Deleting ingredient with id " + id);
        ingredientService.deleteIngredientById(id);
    }

}
