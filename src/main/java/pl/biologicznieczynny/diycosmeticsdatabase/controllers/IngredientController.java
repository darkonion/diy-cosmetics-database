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
