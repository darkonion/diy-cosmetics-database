package pl.biologicznieczynny.diycosmeticsdatabase.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Ingredient;
import pl.biologicznieczynny.diycosmeticsdatabase.services.IngredientService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class IngredientControllerTest {

    @Mock
    IngredientService service;

    IngredientController controller;

    MockMvc mockMvc;

    List<Ingredient> ingredients;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new IngredientController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        generateList();
    }

    void generateList() {
        ingredients = new ArrayList<>();
        Ingredient testIngredient1 = new Ingredient();
        testIngredient1.setId(1L);
        testIngredient1.setName("test1");
        ingredients.add(testIngredient1);

        Ingredient testIngredient2 = new Ingredient();
        testIngredient2.setId(2L);
        testIngredient2.setName("test2");
        ingredients.add(testIngredient2);

        Ingredient testIngredient3 = new Ingredient();
        testIngredient3.setId(3L);
        testIngredient3.setName("test3");
        ingredients.add(testIngredient3);

        Ingredient testIngredient4 = new Ingredient();
        testIngredient4.setId(4L);
        testIngredient4.setName("test4");
        ingredients.add(testIngredient4);
    }

    @Test
    void getIngredientListPageable() throws Exception {

        Page<Ingredient> pages = new PageImpl<>(ingredients);

        when(service.findAll(anyInt(), anyInt(), anyString())).thenReturn(pages);

        mockMvc.perform(get("/api/ingredients")
                .param("size", "2")
                .param("page", "0")
                .param("sort", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(4)));

        verify(service, times(1)).findAll(anyInt(), anyInt(), anyString());
    }

    @Test
    void getIngredientById() {
    }

    @Test
    void addNewIngredient() {
    }

    @Test
    void updateIngredient() {
    }

    @Test
    void deleteIngredient() {
    }
}
