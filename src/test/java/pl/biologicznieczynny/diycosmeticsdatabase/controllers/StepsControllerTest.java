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
import pl.biologicznieczynny.diycosmeticsdatabase.exceptionHandling.NotFoundException;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Recipe;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Step;
import pl.biologicznieczynny.diycosmeticsdatabase.services.StepService;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StepsControllerTest {

    @Mock
    StepService stepService;

    StepsController controller;

    MockMvc mockMvc;

    Recipe testRecipe;
    Step testStep;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new StepsController(stepService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();

        prepareStepAndRecipe();
    }

    private void prepareStepAndRecipe() {
        testStep = new Step();
        testStep.setId(1L);
        testStep.setName("test1");

        testRecipe = new Recipe();
        testRecipe.setId(1L);
        testRecipe.getSteps().add(testStep);
    }

    @Test
    void addNewStepToRecipe() throws Exception {
        String json = new ObjectMapper().writeValueAsString(testStep);

        //when
        when(stepService
                .addNewStepToRecipe(anyLong(), any(Step.class)))
                .thenReturn(testRecipe);

        mockMvc.perform(post("/api/recipes/1/steps")
                .contentType(MediaType.APPLICATION_JSON).content(json))

                //then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.steps", hasSize(1)))
                .andExpect(jsonPath("$.steps[0].name").value("test1"));

        verify(stepService, times(1)).addNewStepToRecipe(any(), any());
    }

    @Test
    void addNewStepToRecipeNotFound() throws Exception {
        String json = new ObjectMapper().writeValueAsString(testStep);

        //when
        when(stepService
                .addNewStepToRecipe(anyLong(), any(Step.class)))
                .thenThrow(new NotFoundException("Recipe with id 10 not found!"));


        mockMvc.perform(post("/api/recipes/10/steps")
                .contentType(MediaType.APPLICATION_JSON).content(json))

                //then
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.debugMessage")
                        .value(containsString("Recipe with id 10 not found!")));

        verify(stepService, times(1)).addNewStepToRecipe(any(), any());
    }
}
