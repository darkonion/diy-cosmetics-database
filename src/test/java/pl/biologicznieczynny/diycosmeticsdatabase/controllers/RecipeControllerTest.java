package pl.biologicznieczynny.diycosmeticsdatabase.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.biologicznieczynny.diycosmeticsdatabase.exceptionHandling.ControllerExceptionHandler;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Recipe;
import pl.biologicznieczynny.diycosmeticsdatabase.services.RecipeService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RecipeControllerTest {

    @Mock
    RecipeService service;

    RecipeController controller;

    MockMvc mockMvc;

    Recipe testRecipe;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new RecipeController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();

        testRecipe = new Recipe();
        testRecipe.setId(1L);
        testRecipe.setName("test1");
    }

    private List<Recipe> generateList() {
        Recipe testRecipe2 = new Recipe();
        testRecipe2.setId(2L);
        testRecipe2.setName("test2");

        Recipe testRecipe3 = new Recipe();
        testRecipe3.setId(3L);
        testRecipe3.setName("test3");

        Recipe testRecipe4 = new Recipe();
        testRecipe4.setId(4L);
        testRecipe4.setName("test4");

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(testRecipe);
        recipes.add(testRecipe2);
        recipes.add(testRecipe3);
        recipes.add(testRecipe4);

        return recipes;
    }

    @Test
    void getRecipesListPageable() throws Exception {

        Page<Recipe> page = new PageImpl<>(generateList());

        when(service.findAll(anyInt(), anyInt(), anyLong())).thenReturn(page);

        //when
        final Page<Recipe> ingredientsList = controller.getRecipesListPageable(2, 0, 1L);

        //then
        Assertions.assertEquals(page.getSize(), ingredientsList.getSize());
        for (int i = 0; i < ingredientsList.getSize(); i++) {
            Assertions.assertEquals(page.getContent().get(i), ingredientsList.getContent().get(i));
        }
    }

    @Test
    void getRecipeById() throws Exception {
        //when
        when(service.findById(1L)).thenReturn(testRecipe);

        mockMvc.perform(get("/api/recipes/1"))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("test1"));

        verify(service, times(1)).findById(1L);
    }

    @Test
    void getRecipeByIdWrongFormat() throws Exception {

        //when
        when(service.findById(anyLong())).thenReturn(testRecipe);


        mockMvc.perform(get("/api/recipes/asdf"))
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value(containsString("Incorrect number format")));

        verify(service, times(0)).findById(anyLong());
    }

    @Test
    void addNewRecipe() throws Exception {
        //given
        testRecipe.setId(null);
        String json = new ObjectMapper().writeValueAsString(testRecipe);

        //when
        when(service.addNewRecipe(any(Recipe.class))).thenReturn(testRecipe);


        mockMvc.perform(post("/api/recipes")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                //then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("test1"));

        verify(service, times(1)).addNewRecipe(any());
    }
}
