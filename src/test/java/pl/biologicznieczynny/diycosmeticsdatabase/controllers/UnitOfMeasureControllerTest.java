package pl.biologicznieczynny.diycosmeticsdatabase.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.biologicznieczynny.diycosmeticsdatabase.models.UnitOfMeasure;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.UnitOfMeasureRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UnitOfMeasureControllerTest {

    @Mock
    UnitOfMeasureRepository repository;

    UnitOfMeasureController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new UnitOfMeasureController(repository);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getUomsList() throws Exception {

        //given
        List<UnitOfMeasure> uoms = new ArrayList<>();
        uoms.add(new UnitOfMeasure());
        uoms.add(new UnitOfMeasure());

        //when
        when(repository.findAll()).thenReturn(uoms);

        //then
        mockMvc.perform(get("/api/units"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        verify(repository, times(1)).findAll();
    }
}
