package pl.biologicznieczynny.diycosmeticsdatabase.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.biologicznieczynny.diycosmeticsdatabase.exceptionHandling.ControllerExceptionHandler;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Ingredient;
import pl.biologicznieczynny.diycosmeticsdatabase.models.IngredientQuantity;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Recipe;
import pl.biologicznieczynny.diycosmeticsdatabase.services.IngredientQuantityService;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class IngredientQuantityControllerTest {

    @Mock
    IngredientQuantityService quantityService;

    IngredientQuantityController controller;

    MockMvc mockMvc;

    IngredientQuantity testQuantity;
    Recipe testRecipe;
    Ingredient testIngredient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new IngredientQuantityController(quantityService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();

        prepareQuantityAndRecipe();
    }

    private void prepareQuantityAndRecipe() {
        testIngredient = new Ingredient();
        testIngredient.setId(1L);
        testIngredient.setName("test1");

        testQuantity = new IngredientQuantity();
        testQuantity.setId(1L);
        testQuantity.setIngredient(testIngredient);


        testRecipe = new Recipe();
        testRecipe.setId(1L);
        testRecipe.getIngredientQuantities().add(testQuantity);
    }

    @Test
    void deleteIngredientQuantityByIdTest() throws Exception {

        mockMvc.perform(delete("/api/quantities/1"))
                .andExpect(status().isOk());

        verify(quantityService, times(1)).deleteQuantity(any());
    }

    @Test
    void deleteIngredientQuantityByIdNotFound() throws Exception {

        mockMvc.perform(delete("/api/quantities/"))
                .andExpect(status().isNotFound());

        verify(quantityService, times(0)).deleteQuantity(any());
    }

    @Test
    void addNewQuantityToRecipe() throws Exception {
        String json = new ObjectMapper().writeValueAsString(testQuantity);

        //when
        when(quantityService
                .addNewQuantityToRecipe(anyLong(), any(IngredientQuantity.class)))
                .thenReturn(testRecipe);

        //then
        mockMvc.perform(post("/api/recipes/1/quantities")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.ingredientQuantities", hasSize(1)))
                .andExpect(jsonPath("$.ingredientQuantities[0].ingredient.name").value("test1"));

        verify(quantityService, times(1)).addNewQuantityToRecipe(any(), any());
    }

    @Test
    void addNewQuantityToRecipeEmptyBody() throws Exception {
        String json = new ObjectMapper().writeValueAsString(testQuantity);

        //when
        when(quantityService
                .addNewQuantityToRecipe(anyLong(), any(IngredientQuantity.class)))
                .thenReturn(testRecipe);

        //then
        mockMvc.perform(post("/api/recipes/1/quantities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.debugMessage")
                .value(containsString("Required request body is missing")));

        verify(quantityService, times(0)).addNewQuantityToRecipe(any(), any());
    }
}
