package pl.biologicznieczynny.diycosmeticsdatabase.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Difficulty;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Ingredient;
import pl.biologicznieczynny.diycosmeticsdatabase.models.IngredientQuantity;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Tool;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Recipe;
import pl.biologicznieczynny.diycosmeticsdatabase.models.RecipeCategory;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Step;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.IngredientRepository;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.RecipeCategoryRepository;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.RecipeRepository;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.UnitOfMeasureRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class DataBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    private final RecipeCategoryRepository recipeCategoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataBootstrap(IngredientRepository ingredientRepository,
            RecipeRepository recipeRepository,
            RecipeCategoryRepository recipeCategoryRepository,
            UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.recipeCategoryRepository = recipeCategoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("Bootstrapping data in progress...");

        bootstrapData();
    }

    @Transactional
    void bootstrapData() {

        //Creating Ingredients
        Ingredient water = new Ingredient();
        water.setName("Water");
        water.setDescription("Just Water and thats all");
        water.setBlogUrl("Http://biologicznieczynny.com/water");

        Ingredient linenOil = new Ingredient();
        linenOil.setName("Linen Oil");
        linenOil.setDescription("Just Linen Oil and thats all");
        linenOil.setBlogUrl("Http://biologicznieczynny.com/linenOil");

        ingredientRepository.save(water);
        ingredientRepository.save(linenOil);

        IngredientQuantity waterQuantity = new IngredientQuantity();
        waterQuantity.setAmount(100);
        waterQuantity.setIngredient(water);
        waterQuantity.setUnitOfMeasure(unitOfMeasureRepository.findByUom("ml").get());

        IngredientQuantity oilQuantity = new IngredientQuantity();
        oilQuantity.setAmount(80);
        oilQuantity.setIngredient(linenOil);
        oilQuantity.setUnitOfMeasure(unitOfMeasureRepository.findByUom("ml").get());

        Set<IngredientQuantity> ingredientQuantities = new HashSet<>();
        ingredientQuantities.add(oilQuantity);
        ingredientQuantities.add(waterQuantity);

        Tool mixer = new Tool();
        mixer.setName("Mixer");
        mixer.setDescription("Just a simple kitchen mixer");

        Tool spoon = new Tool();
        spoon.setName("Spoon");
        spoon.setDescription("Metal tablespoon");

        Step first = new Step();
        first.setName("First Step Here");
        first.setDetail("Just a first step, mix it and thats it");

        Step second = new Step();
        second.setName("Second Step Here");
        second.setDetail("Just a second step, mix it and thats it");

        RecipeCategory recipeCategory = recipeCategoryRepository.findById(1L).get();


        Recipe oilBalm = new Recipe();
        oilBalm.setPrepTime(100);
        oilBalm.setName("Oil body Balm");
        oilBalm.setDescription("Oil body Balm Oil body Balm Oil body Balm Oil body Balm Oil body Balm");
        oilBalm.setDifficulty(Difficulty.EASY);

        recipeCategory.setRecipes(Set.of(oilBalm));
        oilBalm.setCategories(Set.of(recipeCategory));

        spoon.setRecipe(oilBalm);
        mixer.setRecipe(oilBalm);
        oilBalm.setEquipment(Set.of(spoon, mixer));

        waterQuantity.setRecipe(oilBalm);
        oilQuantity.setRecipe(oilBalm);
        oilBalm.setIngredients(ingredientQuantities);

        first.setRecipe(oilBalm);
        second.setRecipe(oilBalm);
        oilBalm.setSteps(Set.of(first, second));

        recipeRepository.save(oilBalm);
    }
}
