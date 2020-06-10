package pl.biologicznieczynny.diycosmeticsdatabase.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import pl.biologicznieczynny.diycosmeticsdatabase.exceptionHandling.NotFoundException;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Ingredient;
import pl.biologicznieczynny.diycosmeticsdatabase.services.IngredientService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class IngredientControllerTest {

    @Mock
    IngredientService service;

    IngredientController controller;

    MockMvc mockMvc;

    List<Ingredient> ingredients;
    Ingredient testIngredient1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new IngredientController(service);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(ControllerExceptionHandler.class)
                .build();
        generateList();
    }

    void generateList() {
        ingredients = new ArrayList<>();
        testIngredient1 = new Ingredient();
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
    void getIngredientById() throws Exception {
        //when
        when(service.findById(anyLong())).thenReturn(testIngredient1);

        //then
        mockMvc.perform(get("/api/ingredients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("test1"));

        verify(service, times(1)).findById(anyLong());
    }

    @Test
    void getIngredientByIdNotFound() throws Exception {

        //when
        when(service.findById(anyLong())).thenThrow(new NotFoundException("Ingredient with id 20 not found"));

        //then
        mockMvc.perform(get("/api/ingredients/20"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.debugMessage")
                        .value(containsString("Ingredient with id 20 not found")));

        verify(service, times(1)).findById(anyLong());
    }

    @Test
    void getIngredientByIdWrongFormat() throws Exception {

        //when
        when(service.findById(anyLong())).thenReturn(testIngredient1);

        //then
        mockMvc.perform(get("/api/ingredients/asdf"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value(containsString("Incorrect number format")));

        verify(service, times(0)).findById(anyLong());
    }

    @Test
    void addNewIngredient() throws Exception {

        //given
        testIngredient1.setId(null);
        String json = new ObjectMapper().writeValueAsString(testIngredient1);

        //when
        when(service.addNewIngredient(any(Ingredient.class))).thenReturn(testIngredient1);

        //then
        mockMvc.perform(post("/api/ingredients")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("test1"));

        verify(service, times(1)).addNewIngredient(any());
    }

    @Test
    void updateIngredient() throws Exception {

        String json = new ObjectMapper().writeValueAsString(testIngredient1);

        //when
        when(service.updateIngredient(any(Ingredient.class))).thenReturn(testIngredient1);

        //then
        mockMvc.perform(put("/api/ingredients")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("test1"))
                .andExpect(jsonPath("$.id").value("1"));

        verify(service, times(1)).updateIngredient(any());
    }

    @Test
    void deleteIngredient() throws Exception {

        mockMvc.perform(delete("/api/ingredients/1"))
                .andExpect(status().isOk());

        verify(service, times(0)).findById(any());
    }
}
