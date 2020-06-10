package pl.biologicznieczynny.diycosmeticsdatabase.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.biologicznieczynny.diycosmeticsdatabase.exceptionHandling.ControllerExceptionHandler;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Recipe;
import pl.biologicznieczynny.diycosmeticsdatabase.services.RecipeService;

class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    RecipeController recipeController;

    MockMvc mockMvc;

    Recipe testRecipe;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();

        testRecipe = new Recipe();
        testRecipe.setId(1L);
    }

    @Test
    void getRecipesListPageable() {
    }

    @Test
    void getRecipeById() {
    }

    @Test
    void getSearchQueryResults() {
    }

    @Test
    void addNewRecipe() {
    }

    @Test
    void deleteRecipe() {
    }

    @Test
    void updateIngredient() {
    }
}
