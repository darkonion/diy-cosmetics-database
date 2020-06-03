package pl.biologicznieczynny.diycosmeticsdatabase.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Tool;
import pl.biologicznieczynny.diycosmeticsdatabase.services.ToolService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ToolControllerTest {

    @Mock
    ToolService toolService;

    ToolController toolController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        toolController = new ToolController(toolService);
        mockMvc = MockMvcBuilders.standaloneSetup(toolController).build();
    }

    @Test
    void getToolList() throws Exception {
        //given
        List<Tool> tools = new ArrayList<>();
        Tool tool = new Tool();
        tool.setId(1L);
        tools.add(tool);

        //when
        when(toolService.getToolsList()).thenReturn(tools);

        //then
        mockMvc.perform(get("/api/tools")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void getToolListEmpty() throws Exception {
        //given
        List<Tool> tools = new ArrayList<>();

        //when
        when(toolService.getToolsList()).thenReturn(tools);

        //then
        mockMvc.perform(get("/api/tools")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void addNewTool() {
    }

    @Test
    void deleteTool() {
    }

    @Test
    void updateTool() {
    }
}
