package pl.biologicznieczynny.diycosmeticsdatabase.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.biologicznieczynny.diycosmeticsdatabase.models.RecipeCategory;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.RecipeCategoryRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryControllerTest {

    @Mock
    RecipeCategoryRepository repository;

    CategoryController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new CategoryController(repository);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getRecipeCategoriesList() throws Exception {

        //given
        List<RecipeCategory> recipeCategories = new ArrayList<>();
        recipeCategories.add(new RecipeCategory());
        recipeCategories.add(new RecipeCategory());

        //when
        when(repository.findAll()).thenReturn(recipeCategories);

        //then
        mockMvc.perform(get("/api/recipeCategories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        verify(repository, times(1)).findAll();
    }
}
